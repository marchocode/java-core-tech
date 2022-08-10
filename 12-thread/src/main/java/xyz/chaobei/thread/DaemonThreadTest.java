package xyz.chaobei.thread;

public class DaemonThreadTest {

    public static void main(String[] args) {

        Runnable runnable = () -> {

            while (true) {
                System.out.println("product something...");
            }

        };

        System.out.println("current thread name="+Thread.currentThread().getName());

        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();

        System.out.println("current thread end=");

    }

}
