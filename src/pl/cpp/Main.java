package pl.cpp;



public class Main {

    public static void main(String[] args) {
        Philosopher[] philosophers = new Philosopher[5];
        Object[] forks = new Object[philosophers.length];
        Object[] knives = new Object[philosophers.length];

        for (int i = 0; i <forks.length; i++) {
            forks[i] = new Object();
        }

        for (int i = 0; i <knives.length; i++) {
            knives[i] = new Object();
        }

        for (int i = 0; i < philosophers.length; i++) {
            Object fork = forks[i];
            Object knife = knives[(i+1) % knives.length];


            if ( i == philosophers.length - 1) {

                philosophers[i] = new Philosopher(knife, fork);
            } else {
                philosophers[i] = new Philosopher(fork, knife);
            }


            Thread t = new Thread(philosophers[i], "Philosopher " + (i +1));
            t.start();
        }
    }
}
