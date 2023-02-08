package org.example;

import java.util.ArrayList;
import java.util.List;

class Processor{
    public static final int UPPER_LIMIT = 5;
    public static final int LOWER_LIMIT = 0;
    public List<Integer> list = new ArrayList<>();
    private static final Object lock = new Object();
    private static int value = 0;

    public void producer() throws InterruptedException {
        while(true) {
            synchronized (lock) {
                if(list.size() == UPPER_LIMIT){
                    System.out.println("The producer thread is waiting to add items...");
                    lock.wait();
                }else{
                    System.out.println("Adding: " + value);
                    list.add(value);
                    value++;
                    lock.notify();

                }
            }
        }
    }

    public void consumer() throws InterruptedException {
        while(true) {
            synchronized (lock) {
                if(list.size() == LOWER_LIMIT){
                    System.out.println("The consumer thread is waiting to remove items...");
                    lock.wait();
                }else{
                    System.out.println("Removing: " + list.remove(list.size() - 1));
                    lock.notify();
                }
            }
        }
    }
}
public class ProducerConsumer {
    public static void main(String[] args){
        Processor process = new Processor();

        Thread t1 = new Thread(() -> {
            try {
                process.producer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                process.consumer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();
    }
}
