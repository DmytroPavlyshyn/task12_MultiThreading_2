package com.pavlyshyn.task3;

public class SimpleLock {
    private boolean isLocked;
    private Thread lockedBy;
    private int lockCounter;

    public synchronized void lock() throws InterruptedException {
        while (isLocked && Thread.currentThread() != lockedBy) {
            this.wait();
        }
        isLocked = true;
        lockedBy = Thread.currentThread();
        lockCounter++;
    }

    public synchronized void unlock() {
        if (Thread.currentThread() == lockedBy) {
            lockCounter--;
        }
        if (lockCounter == 0) {
            isLocked = false;
        }
    }
    
}
