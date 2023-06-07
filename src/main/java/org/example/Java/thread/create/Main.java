package org.example.Java.thread.create;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author liushixing
 * @date 2023/6/5 19:50
 */
public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable).start();
        new Thread(myRunnable).start();

        ExtendsThread thread1 = new ExtendsThread();
        thread1.start();

        RunnableThread runnable = new RunnableThread();
        Thread thread2 = new Thread(runnable);
        thread2.start();

        CallableThread callable = new CallableThread();
        FutureTask futureTask = new FutureTask<>(callable);
        Thread thread3 = new Thread(futureTask);
        thread3.start();
        String res = (String) futureTask.get();
        System.out.println("res：" + res);

    }

    static class MyRunnable implements Runnable{

        private int num = 1;

        @Override
        public void run() {
            while(true){
                //锁住当前myRunnable对象
                synchronized (this){
                    //唤醒另一个线程，让另一个线程开始等待锁资源
                    notify();
                    if(num <= 10){
                        System.out.println(Thread.currentThread().getName() + "：" + num);
                        num++;
                        try{
                            //阻塞当前线程并释放锁资源，使被唤醒的另一个线程获取到锁
                            wait();
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }else {
                        break;
                    }
                }
            }
        }
    }

}

