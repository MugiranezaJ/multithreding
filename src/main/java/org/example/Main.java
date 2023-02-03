package org.example;

 class DaemonThread implements Runnable {
     @Override
     public void run() {
         while (true) {
             try {
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 throw new RuntimeException(e);
             }
             System.out.println("Daemon thread running....");
         }
     }
 }

class NormalThread implements Runnable{
     @Override
    public void run(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Normal thread finishes execution...");
    }
}
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new DaemonThread());
        Thread t2 = new Thread(new NormalThread());
        t1.setDaemon(true);

        t1.start();
        t2.start();
        System.out.println(t1.isDaemon());
    }
}