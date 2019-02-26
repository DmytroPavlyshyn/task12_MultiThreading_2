package com.pavlyshyn.task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;

public class BlockingQueueTest {
    private BlockingQueue<String> strings;
    private String path;
    public final int BUFFER_SIZE = 256;
    public final int SLEEP_TIME = 100;

    public BlockingQueueTest(BlockingQueue<String> strings, String path) throws IOException {
        this.strings = strings;
        this.path = path;
    }

    private void readFromFileToQueue() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path), BUFFER_SIZE)) {
            String line;
            while ((line = reader.readLine()) != null) {
                strings.offer(line + '\n');
                try {
                    Thread.sleep(SLEEP_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void readFromQueue() {

        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String line;
        while ((line = strings.poll()) != null) {
            System.out.print(line);
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public void start() {
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(this::readFromFileToQueue);
        service.submit(this::readFromQueue);
        service.shutdown();
    }

    public static void main(String[] args) throws IOException {
        BlockingQueueTest blockingQueueTest = new BlockingQueueTest(new LinkedTransferQueue<>(), "src/main/java/com/pavlyshyn/task2/poem.txt");
        blockingQueueTest.start();
    }
}