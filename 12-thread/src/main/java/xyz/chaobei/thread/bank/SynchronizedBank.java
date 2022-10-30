package xyz.chaobei.thread.bank;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedBank {

    private final double[] accounts;

    public SynchronizedBank() {
        this.accounts = new double[10];
        Arrays.fill(this.accounts, 10000d);
    }

    public synchronized void transfer(int from, int to, double amount) throws InterruptedException {

        String threadName = Thread.currentThread().getName();

        System.out.printf("thread name=%s,transfer amount %10.2f,from %d,to %s\n", threadName, amount, from, to);

        // 检查余额是否充足
        while (accounts[from] < amount) {
            System.out.printf("thread name=%s,余额不足,进入等待\n", threadName);
            wait();
            System.out.printf("thread name=%s,获得锁,继续检查\n", threadName);
        }

        System.out.printf("thread name=%s,before=%s\n", threadName, Arrays.toString(this.accounts));
        accounts[from] -= amount;

        accounts[to] += amount;
        System.out.printf("thread name=%s,after=%s\n", threadName, Arrays.toString(this.accounts));

        System.out.printf("thread name=%s,System Total Amount Is:%10.2f\n", threadName, getTotal());

        notifyAll();
    }

    public double getTotal() {
        double total = 0d;
        for (double item : accounts) {
            total += item;
        }
        return total;
    }

    public int getSize() {
        return accounts.length;
    }


    public static void main(String[] args) {

        SynchronizedBank bankEntity = new SynchronizedBank();

        Runnable runnable1 = () -> {

            while (true) {
                int from = (int) (Math.random() * bankEntity.getSize());
                int to = (int) (Math.random() * bankEntity.getSize());
                int randomAmount = (int) (Math.random() * 10000);

                try {

                    bankEntity.transfer(from, to, randomAmount);
                    Thread.sleep((int) (Math.random() * 10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 100; i++) {
            new Thread(runnable1).start();
        }

    }


}
