package org.example;

class Process{
    public void producer() throws InterruptedException {
        synchronized (this){
            System.out.println("The producer thread is running...");
            wait();
            System.out.println("Again in the producer thread...");
        }
    }

    public void consumer() throws InterruptedException {
        Thread.sleep(1000);
        synchronized (this){
            System.out.println("The consumer thread is running...");
            notify();
            Thread.sleep(5000);
        }
    }
}
public class ProducerConsumer {
    public static void main(String[] args){
        Process process = new Process();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.producer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.consumer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        t2.start();
    }
}
