package com.company;

public class WNALL {
    boolean a = true;
    boolean b = false;
    boolean c = false;
    int x=0;

    Object lock = new Object();


    public void C() throws InterruptedException {
        while(x<5) {
            Thread t1 = new Thread(() -> {

                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                synchronized (lock) {
                    try {
                        lock.wait();
                        System.out.println("A");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (a = true) {
                        lock.notify();

                        boolean a = false;
                        boolean b = true;
                        boolean c = false;
                    }

                }


            });
            Thread t2 = new Thread(() -> {

                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                synchronized (lock) {
                    try {
                        lock.wait();
                        System.out.println("B");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (b = true) {

                        lock.notify();

                        boolean a = false;
                        boolean b = false;
                        boolean c = true;
                    }

                }


            });
            Thread t3 = new Thread(() -> {



                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                synchronized (lock) {
                    try {
                        lock.wait();
                        System.out.println("C");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (c = true) {
                        lock.notify();
                        boolean a = true;
                        boolean b = false;
                        boolean c = false;
                    }

                }


            });
            x++;
        }
    }
}
