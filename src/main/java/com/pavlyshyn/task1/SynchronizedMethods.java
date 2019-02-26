package com.pavlyshyn.task1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedMethods {
    private Lock lock = new ReentrantLock();

    public void method1() {
        lock.lock();
        double result = 0;
        System.out.println("Method 1 starts");
        for (int i = 0; i < 100; i++) {
            result += ((double) i + i + 1) / 2;
        }
        System.out.println("Result = " + result);
        System.out.println("Method 1 ends");
        lock.unlock();
    }

    public void method2() {
        lock.lock();
        double result = 0;
        System.out.println("Method 2 starts");
        for (int i = 0; i < 100; i++) {
            result += ((double) i + i + 1) / 2;
        }
        System.out.println("Result = " + result);
        System.out.println("Method 2 ends");
        lock.unlock();

    }

    public void method3() {
        lock.lock();
        double result = 0;
        System.out.println("Method 3 starts");
        for (int i = 0; i < 100; i++) {
            result += ((double) i + i + 1) / 2;
        }
        System.out.println("Result = " + result);
        System.out.println("Method 3 ends");
        lock.unlock();
    }

    public void run() {
        ExecutorService service = Executors.newFixedThreadPool(3);
        service.execute(this::method1);
        service.execute(this::method2);
        service.execute(this::method3);
        service.shutdown();
    }

    public static void main(String[] args) {
        SynchronizedMethods synchronizedMethods = new SynchronizedMethods();
        synchronizedMethods.run();
    }
}
