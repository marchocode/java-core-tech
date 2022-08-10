package xyz.chaobei.thread;

public class InterruptedTest {


    public static void main(String[] args) {


        Runnable runnable = () -> {

            int i = 0;
            while (!Thread.currentThread().isInterrupted() && i < 100) {

                if (i == 77) {
                    // try stop
                    Thread.currentThread().interrupt();
                }
                System.out.println("Thread's step is " + (i++));

            }

        };

        new Thread(runnable).start();
    }

}
