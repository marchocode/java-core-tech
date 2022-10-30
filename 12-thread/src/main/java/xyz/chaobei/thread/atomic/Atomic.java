package xyz.chaobei.thread.atomic;

public class Atomic {

    public static void main(String[] args) {

        NoAtomicTest noAtomicTest = new NoAtomicTest();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                String name = Thread.currentThread().getName();
                System.out.printf("current name=%s, get id=%s\n", name, noAtomicTest.nextId());
            }).start();
        }

        /**
         * the following errors
         *
         * current name=Thread-47, get id=48
         * current name=Thread-48, get id=47
         */

    }

}

class NoAtomicTest {

    private int id = 0;

    public int nextId() {
        return id++;
    }

}
