package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Worker{
        private final Lock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();
        public void producer() throws InterruptedException {
            lock.lock();
            System.out.println("Producer running...");
            condition.await();
            System.out.println("producer again...");
            lock.unlock();
        }

        public void consumer() throws InterruptedException {
            lock.lock();
            Thread.sleep(2000);
            System.out.println("Consumer running...");
            condition.signal();
            lock.unlock();
        }
    }
public class ProducerConsumerWithALock {
    public static void main(String [] args) throws InterruptedException {
        Worker worker = new Worker();

        Thread t1 = new Thread(() -> {
            try {
                worker.producer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                worker.consumer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
