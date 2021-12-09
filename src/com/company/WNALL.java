package com.company;

import java.util.ArrayList;//библиотеки
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class WNALL {

   static String c="C";
    static Object lock = new Object();//объект, на котором будет синхронизация
    public static void main(String[] args)  {//а тут интераптед эксепшион особо не нужно

        WNALL wnall = new WNALL();
        Thread t1 = new Thread(()->wnall.A());
        Thread t2 = new Thread(()-> {wnall.B();
        });
        Thread t3 = new Thread(()-> {
           wnall.C();
        });
        t1.start();
        t2.start();
        t3.start();
            }



public static void A() {
    synchronized (lock) {//синхронизация на объекте лок
        for (int i = 0; i < 5; i++) {
        while(c!="C") {
            try {
                lock.wait();//после синхронизации на лок ожидает своей участи
            } catch (InterruptedException e) {//исключение на возобновление метода
                e.printStackTrace();//говорит запускающему что с кодом в этом  месте
            }



            }

        }
        c="A";
        System.out.print("A");

        lock.notifyAll();

    }
}







      public static void B() {
          synchronized (lock) {//синхронизация на объекте лок
              for (int f = 0; f < 5; f++) {
              while(c!="A") {
                  try {
                      lock.wait();
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }


              }

              }
              System.out.print("B");
              c="B";
              lock.notifyAll();

          }
      }



    public static void C()  {
        synchronized (lock) {//синхронизация на объекте лок
            for (int g = 0; g < 5; g++) {
            while(c !="B") {


                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            }
            c="С";
            lock.notifyAll();
            System.out.print("C");
        }
    }




    //запуск потоков



}




