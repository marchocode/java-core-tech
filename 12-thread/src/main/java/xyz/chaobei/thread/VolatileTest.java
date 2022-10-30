package xyz.chaobei.thread;

public class VolatileTest {

    public static void main(String[] args) {

        Volatile v = new Volatile();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(v.getDone());
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                v.setDone();
            }
        }).start();

    }

}

class Volatile {

    private volatile boolean done = false;

    public void setDone() {
        this.done = true;
    }

    public boolean getDone() {
        return this.done;
    }


}
