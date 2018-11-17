package pl.cpp;


import pl.cpp.Philosopher;

public class Main {

    public static void main(String[] args) {
        Fork[] forks = new Fork[5];
        Philosopher[] philosophers = new Philosopher[5];
        State state = new State();

        for(int i = 0; i < forks.length; i++) {
            forks[i] = new Fork();
        }
        for(int i = 0; i < philosophers.length; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i+4) % 5], state);
            new Thread(philosophers[i]).start();
        }
    }
}
