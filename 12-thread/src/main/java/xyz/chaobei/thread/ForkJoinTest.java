package xyz.chaobei.thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.DoublePredicate;

public class ForkJoinTest {

    private static final DoublePredicate predicate = x -> x < 0.5;

    public static void main(String[] args) {

        double[] randoms = new double[100000];
        int countNum = 0;

        for (int i = 0; i < randoms.length; i++) {
            double result = Math.random();
            randoms[i] = result;

            if (predicate.test(result)) {
                countNum++;
            }
        }

        System.out.println("the correct answer =" + countNum);

        Counter counter = new Counter(randoms, 0, randoms.length, predicate);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(counter);

        System.out.println("the fork-join's answer=" + counter.join());
    }

}

class Counter extends RecursiveTask<Integer> {

    private final int RANGE = 1000;

    private final double[] data;

    private final int start;

    private final int end;

    private final DoublePredicate doublePredicate;

    public Counter(double[] data, int start, int end, DoublePredicate doublePredicate) {
        this.data = data;
        this.start = start;
        this.end = end;
        this.doublePredicate = doublePredicate;
    }

    @Override
    protected Integer compute() {

        if (end - start < RANGE) {

            int countNum = 0;

            for (int i = start; i < end; i++) {

                if (doublePredicate.test(data[i])) {
                    countNum++;
                }
            }

            return countNum;

        } else {
            int mid = (start + end) / 2;

            Counter first = new Counter(data, start, mid, doublePredicate);
            Counter second = new Counter(data, mid, end, doublePredicate);

            // 分两半执行
            invokeAll(first, second);
            // get 会抛出异常，使用join
            return first.join() + second.join();
        }
    }
}
