package xyz.chaobei.thread.bank;

import java.util.Arrays;

public class BankEntity {

    private final double[] accounts;

    public BankEntity() {
        this.accounts = new double[10];
        Arrays.fill(this.accounts, 10000d);
    }

    public void transfer(int from, int to, double amount) {

        String threadName = Thread.currentThread().getName();
        System.out.printf("name=%s,from %d,to %s\n", threadName, from, to);

        if (accounts[from] < amount) {
            return;
        }

        accounts[from] -= amount;
        System.out.printf("name=%s,amount %10.2f,from %d,to %s\n", threadName, amount, from, to);
        accounts[to] += amount;

        System.out.printf("name=%s,list=%s\n", threadName, Arrays.toString(this.accounts));
        System.out.printf("name=%s,System Total Amount Is:%10.2f\n", threadName, getTotal());
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

        BankEntity bankEntity = new BankEntity();

        Runnable runnable1 = () -> {

            while (true) {
                int from = (int) (Math.random() * bankEntity.getSize());
                int to = (int) (Math.random() * bankEntity.getSize());
                int randomAmount = (int) (Math.random() * 1000);

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
