package org.example;

public class InterThreadCom {
    public static int counter1 = 0;
    public static int counter2 = 0;

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void increment1(String threadName){
        synchronized (lock1) {
            counter1++;
//            System.out.println("Counter[" + threadName + "]: " + counter1);
        }
    }

    public static void increment2(String threadName){
        synchronized (lock2) {
            counter2++;
//            System.out.println("Counter[" + threadName + "]: " + counter2);
        }
    }
    public static void process() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    increment1("t1");
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    increment2("t2");
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Counter: " + counter1);
        System.out.println("Counter: " + counter2);
    }
    public static void main(String[] args) throws InterruptedException {
        process();
    }
}
