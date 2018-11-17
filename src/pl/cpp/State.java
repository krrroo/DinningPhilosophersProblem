package pl.cpp;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class State {
    Lock mutex = new ReentrantLock();
    Condition[] condition = new Condition[5];
    int[] id = new int[5];

    public State() {
        for (int i =0; i < 5; i++) {
            id[i] = i;
            condition[i] = mutex.newCondition();
        }
    }

    public void grabForks(int id, Side left, Side right) {
        mutex.lock();
        try {
            while(!left.getAvailability() || !right.getAvailability()) {
                condition[id].await();
            }
            left.setAvailability(false);
            right.setAvailability(false);
            System.out.println("Philosopher " + (id + 1) + " is grabbing forks");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mutex.unlock();
        }
    }

    public void releaseForks(int id, Side left, Side right) {
        mutex.lock();
        left.setAvailability(true);
        right.setAvailability(true);
        condition[(id + 1) % 5].signalAll();
        condition[(id + 4) % 5].signalAll();
        System.out.println("Philosopher " + (id + 1) + " is putting down forks");
        mutex.unlock();
    }
}
