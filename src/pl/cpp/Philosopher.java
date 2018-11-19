package pl.cpp;


public class Philosopher implements Runnable  {

    State state;
    Side left, right;
    int id;
    Cutlery fork, knife;

    public Philosopher(int id, Side left, Side right, State state, Cutlery fork, Cutlery knife) {
        this.id = id;
        this.left = left;
        this.right = right;
        this.state = state;
        this.fork = fork;
        this. knife = knife;
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
            state.grabForks(id, left, right, fork, knife);
            eat();
            state.releaseForks(id, left, right, fork, knife);

        }
    }
}
