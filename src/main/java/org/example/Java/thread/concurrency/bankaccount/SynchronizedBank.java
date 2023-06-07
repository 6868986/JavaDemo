package org.example.Java.thread.concurrency.bankaccount;

import org.example.Java.thread.create.ExtendsThread;

/**
 * @author liushixing
 * @date 2023/6/7 19:46
 */
public class SynchronizedBank {

    static SynchronizedBank bank = new SynchronizedBank(500);
    public static void main(String[] args) {
        DemoThread1 thread0 = new DemoThread1();

        DemoThread1 thread1 = new DemoThread1();
        DemoThread2 thread2 = new DemoThread2();
        thread1.start();
        new Thread(thread2).start();
    }

    //存款
    private int balance;

    //构造银行
    public SynchronizedBank(int balance) {
        this.balance = balance;
    }

    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println("本次存入：" + amount + "，总额还剩：" + balance);
    }

    public  void withdraw(int amount) {
        if (balance < amount) {
            System.out.println(Thread.currentThread() + "取款失败，余额为：" + balance);
        } else {
            balance -= amount;
            System.out.println(Thread.currentThread() + "取款成功，余额为：" + balance);
        }
    }

    static class DemoThread1 extends Thread{
        @Override
        public void run(){
            System.out.println("线程1开始取款，余额为：" + bank.balance);
            bank.withdraw(300);
            System.out.println("线程1结束取款，余额为：" + bank.balance);
        }
    }

    static class DemoThread2 implements Runnable{

        @Override
        public void run() {
            System.out.println("线程2开始取款，余额为：" + bank.balance);
            bank.withdraw(300);
            System.out.println("线程2结束取款，余额为：" + bank.balance);
        }
    }
}
