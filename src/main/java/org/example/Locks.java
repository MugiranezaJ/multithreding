package org.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Locks {
    private static int counter = 0;
    private static Lock lock = new ReentrantLock();

    public static void increment(){
        lock.lock();
        try {
            for (int i = 0; i < 1000; i++) {
                counter++;
            }
        }finally {
            lock.unlock();
        }
    }
    public static void main(String [] args) throws InterruptedException{
        // call function using lambda expression
        var t1 = new Thread(() -> increment());

        // call function by reference
        var t2 = new Thread(Locks::increment);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Counter: " + counter);
    }
}
