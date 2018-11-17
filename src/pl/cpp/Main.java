package pl.cpp;

public class Main {

    public static void main(String[] args) {
        Side[] sides = new Side[5];
        Philosopher[] philosophers = new Philosopher[5];
        State state = new State();

        for(int i = 0; i < sides.length; i++) {
            sides[i] = new Side();
        }
        for(int i = 0; i < philosophers.length; i++) {
            philosophers[i] = new Philosopher(i, sides[i], sides[(i+4) % 5], state);
            new Thread(philosophers[i]).start();
        }
    }
}
