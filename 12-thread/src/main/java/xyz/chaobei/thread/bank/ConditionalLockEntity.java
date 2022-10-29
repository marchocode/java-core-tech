package xyz.chaobei.thread.bank;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionalLockEntity {

    private final double[] accounts;

    /**
     * now it contains a lock entity
     */
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * Adding a condition
     */
    private Condition condition;


    public ConditionalLockEntity() {
        this.accounts = new double[10];
        this.condition = lock.newCondition();
        Arrays.fill(this.accounts, 10000d);
    }

    public void transfer(int from, int to, double amount) {

        try {
            lock.lock();
            String threadName = Thread.currentThread().getName();

            System.out.printf("thread name=%s,transfer amount %10.2f,from %d,to %s\n", threadName, amount, from, to);

            // 检查余额是否充足
            while (accounts[from] < amount) {
                System.out.printf("thread name=%s,余额不足,进入等待\n",threadName);
                condition.await();
                System.out.printf("thread name=%s,获得锁,继续检查\n",threadName);
            }

            System.out.printf("thread name=%s,before=%s\n", threadName, Arrays.toString(this.accounts));
            accounts[from] -= amount;

            accounts[to] += amount;
            System.out.printf("thread name=%s,after=%s\n", threadName, Arrays.toString(this.accounts));

            System.out.printf("thread name=%s,System Total Amount Is:%10.2f\n", threadName, getTotal());

            condition.signalAll();

        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
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

        ConditionalLockEntity bankEntity = new ConditionalLockEntity();

        Runnable runnable1 = () -> {

            while (true) {
                int from = (int) (Math.random() * bankEntity.getSize());
                int to = (int) (Math.random() * bankEntity.getSize());
                int randomAmount = (int) (Math.random() * 10000);

                bankEntity.transfer(from, to, randomAmount);

                try {
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
