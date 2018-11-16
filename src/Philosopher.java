package pl.cpp;

public class Philosopher implements Runnable  {

    private Object fork;
    private Object knife;
    private boolean isEating;

    public Philosopher(Object fork, Object knife) {
        this.fork = fork;
        this.knife = knife;
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
        Thread.sleep(((int) (Math.random() *100)));
    }

    @Override
    public void run() {

        try {
            while (true) {

                //thinking
                doAction("  Thinking");
                synchronized (fork) {
                    doAction("  Picked up fork");
                    //eating
                    synchronized (knife) {
                        doAction("  Picked up knife - eating");

                        doAction("  Put down right knife");
                    }
                    doAction("  Put down left fork");
                }
            }

        }   catch (InterruptedException e)  {
            Thread.currentThread().interrupt();
            return;
    }



    }
}
