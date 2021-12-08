package com.company;

import java.util.ArrayList;//библиотеки
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class WNALL {


    public static void main(String[] args)  {//а тут интераптед эксепшион особо не нужно
        AtomicInteger x = new AtomicInteger();//атомарные операции для осуществления многопоточности
        Object lock = new Object();//объект, на котором будет синхронизация
        List<String> list = new ArrayList<>(15);//объявление листа в эррэй лист
        while (x.get() < 15) {//потоки живут пока количество их исполнений не будет равно 15
            Thread t1 = new Thread(() -> {//объявление потока


                try {
                    Thread.sleep(1000);//поток останавливается на секунду

                } catch (InterruptedException e) {//исключение на возобновление метода
                    e.printStackTrace();//говорит запускающему что с кодом в этом  месте
                }
                synchronized (lock) {//синхронизация на объекте лок
                    try {
                        lock.wait();//после синхронизации на лок ожидает своей участи


                    } catch (InterruptedException e) {//исключение на возобновление метода
                        e.printStackTrace();//говорит запускающему что с кодом в этом  месте
                    }
                    if (list.get(1) == null) {//при выполнении условия равенства поток пробуждается
                        //затем в лист добавляется элемент
                        lock.notify();
                    }
                    list.add("A");
                }


                x.getAndIncrement();
            });
            Thread t2 = new Thread(() -> {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {//исключение на возобновление метода
                    e.printStackTrace();
                }

                synchronized (lock) {//синхронизация на объекте лок
                    try {
                        lock.wait(); //то что после синхронизации на лок ожидает своей участи

                    } catch (InterruptedException e) {//исключение на возобновление метода
                        e.printStackTrace();
                    }
                    if (list.get(1) == "A") {//при выполнении условия равенства поток пробуждается
                        //затем в лист добавляется элемент
                        lock.notify();
                    }
                    list.add("B");

                    x.getAndIncrement();
                }


            });

            Thread t3 = new Thread(() -> {

                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {//исключение на возобновление метода
                    e.printStackTrace();//говорит запускающему что с кодом в этом  месте
                }

                synchronized (lock) {//синхронизация на объекте лок
                    try {
                        lock.wait();//то что после синхронизации на лок ожидает своей участи


                    } catch (InterruptedException e) {//исключение на возобновление метода
                        e.printStackTrace();//говорит запускающему что с кодом в этом  месте
                    }
                    if (list.get(2) == "B") {//при выполнении условия равенства поток пробуждается
                        //затем в лист добавляется элемент
                        lock.notify();//при выполнении условия равенства поток пробуждается
                        //затем в лист добавляется элемент
                    }
                    list.add("C");
                    System.out.println(list);


                }

                x.getAndIncrement();//увеличение счётчика на 1 в рамках цикла вайл
            });


            t1.start();//запуск потоков
            t2.start();
            t3.start();
            if(x.get()==15){//если икс равен 15 то его значения переходят в консоль, а цикл завершается вместе с потоками
                System.out.println(list);
                break;
            }
        }


    }
}

