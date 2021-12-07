package com.company;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class WNALL {


    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        List<String> list = new CopyOnWriteArrayList<>();

            Thread t1 = new Thread(() -> {



                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock) {
                    try {
                        lock.wait();



                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (list.get(1)==null) {
                        lock.notify();
                    }
                    list.add("A");
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

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (list.get(1)=="A") {
                        lock.notify();
                    }
                    list.add("B");


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


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                    if (list.get(2)=="B") {
                        lock.notify();
                    }
                    list.add("C");
                    System.out.println(list);



                }


            });
        Thread t4 = new Thread(() -> {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        synchronized (lock) {
            try {


                lock.wait();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (list.get(3)=="C") {
                lock.notify();
            }
            list.add("A");

        }

        });
        Thread t5 = new Thread(() -> {

                try {

                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            synchronized (lock) {
            try {

                lock.wait();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                if (list.get(4)=="A") {
                    lock.notify();
                }
                list.add("B");



            }


        });

        Thread t6 = new Thread(() -> {

                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            synchronized (lock) {
            try {
                lock.wait();


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                if (list.get(5)=="B") {
                    lock.notify();
                }
                list.add("C");
                System.out.println(list);


            }


        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        }


    }

