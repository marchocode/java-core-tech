package xyz.chaobei.thread;

public class ThreadTest {

    private final static Integer STEPS = 100;
    private final static Integer DELAY = 10;
    private final static Double MAX_AMOUNT = 1000D;

    public static void main(String[] args) {

        Runnable task1 = () -> {

            for (int i = 0; i < STEPS; i++) {
                double amount = MAX_AMOUNT * Math.random();
                System.out.println("Thread 1 amount=" + amount);
                try {
                    Thread.sleep((int) (DELAY * Math.random()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };

        Runnable task2 = () -> {
            for (int i = 0; i < STEPS; i++) {
                double amount = MAX_AMOUNT * Math.random();
                System.out.println("Thread 2 amount=" + amount);
                try {
                    Thread.sleep((int) (DELAY * Math.random()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        // 启动线程，从而调用run() 方法，
        thread1.start();
        thread2.start();

    }

}
