package pl.cpp;


public class Philosopher implements Runnable  {

    State state;
    Fork left, right;
    int id;

    public Philosopher(int id, Fork left, Fork right, State state) {
        this.id = id;
        this.left = left;
        this.right = right;
        this.state = state;
    }

    private void eat() {
        try {
            Thread.sleep(2000);
            System.out.println("Philosopher " + (id + 1) + " is eating");
        } catch (Exception e) {

        }
    }

    private void think() {
        try {
            Thread.sleep(2000);
            System.out.println("Philosopher " + (id + 1) + " is thinking");
        } catch (Exception e) {

        }
    }
    @Override
    public void run() {

        while(true) {
            think();
            state.grabForks(id, left, right);
            eat();
            state.releaseForks(id, left, right);

        }
    }
}
