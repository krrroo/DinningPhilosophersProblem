package pl.cpp;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Side[] sides = new Side[5];
        Philosopher[] philosophers = new Philosopher[5];
        State state = new State();
        Cutlery[] forks = new Cutlery[5];
        Cutlery[] knives = new Cutlery[5];
        Random rand = new Random();

        for(int i = 0; i < sides.length; i++) {
            sides[i] = new Side();
        }

        for(int i = 0; i < forks.length; i++) {
            forks[i] = new Cutlery();
        }

        for(int i = 0; i < knives.length; i++) {
            knives[i] = new Cutlery();
        }

        for(int i = 0; i < philosophers.length; i++) {
            philosophers[i] = new Philosopher(i, sides[i], sides[(i+4) % 5], state, forks[rand.nextInt(3)], knives[rand.nextInt(3)]);
            new Thread(philosophers[i]).start();
        }
    }
}
