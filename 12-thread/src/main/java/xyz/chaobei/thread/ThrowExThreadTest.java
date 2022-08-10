package xyz.chaobei.thread;

public class ThrowExThreadTest {


    public static void main(String[] args) {


        Runnable runnable = () -> {
            int i = 0;
            Thread current = Thread.currentThread();
            while (!current.isInterrupted() && i < 100) {
                if (i == 33) {
                    // try throw exception
                    throw new RuntimeException("xxx");
                }
                System.out.println("Thread's step is " + (i++) + "name=" + current.getName());
            }
        };

        Thread thread = new Thread(runnable);
        thread.setName("noHandler");
        Thread.UncaughtExceptionHandler exceptionHandler = thread.getUncaughtExceptionHandler();
        System.out.println("default exception handler =" + exceptionHandler.getClass());
        System.out.println("thread getThreadGroup =" + thread.getThreadGroup());
        System.out.println("thread getThreadGroup main parent =" + thread.getThreadGroup().getParent());
        thread.start();

        Thread.UncaughtExceptionHandler handler = (t, e) -> {
            System.err.println("custom handler error");
            e.printStackTrace();
        };

        Thread thread1 = new Thread(runnable);
        thread1.setName("haveExceptionHandler");
        thread1.setUncaughtExceptionHandler(handler);
        thread1.start();

    }

}
