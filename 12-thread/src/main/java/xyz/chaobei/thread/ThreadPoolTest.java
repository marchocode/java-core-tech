package xyz.chaobei.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 创建一个固定大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(12);

        ThreadPoolTest threadPoolTest = new ThreadPoolTest();
        threadPoolTest.invokeAll(executorService);

        executorService.shutdown();
    }

    public void simpleExample(ExecutorService executorService) throws ExecutionException, InterruptedException {
        Runnable task1 = () -> {
            // without return values;
            System.out.println("task1 is doing home work.....");
            try {
                Thread.sleep(1500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Future<?> future = executorService.submit(task1);
        System.out.println("main task is running....");

        // it was blocked.
        // it returns a Null value.
        System.out.println(future.get());
        System.out.println("main task get task1's return value");
    }

    public void invokeAll(ExecutorService executorService) throws InterruptedException, ExecutionException {

        List<Callable<Long>> tasks = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            Callable<Long> item = () -> {

                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + "is doing work...");

                long speedTime = (int) (Math.random() * 2000);
                Thread.sleep(speedTime);

                System.out.println(threadName + "is done... time=" + speedTime);

                return speedTime;
            };

            tasks.add(item);
        }

        List<Future<Long>> futures = executorService.invokeAll(tasks);

        for (Future<Long> future : futures) {
            Long result = future.get();
            System.out.println("a task's result=" + result);
        }

    }


}
