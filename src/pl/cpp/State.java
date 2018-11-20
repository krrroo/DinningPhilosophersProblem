package pl.cpp;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class State {
    Lock lock = new ReentrantLock();
    Condition[] condition = new Condition[5];
    int[] id = new int[5];

    public State() {
        for (int i =0; i < 5; i++) {
            id[i] = i;
            condition[i] = lock.newCondition();
        }
    }

    public void grabForks(int id, Side left, Side right, Cutlery fork, Cutlery knife) {
        lock.lock();
        try {
            while(!left.getAvailability() || !right.getAvailability()) {
                condition[id].await();
            }
            while(!fork.getAvailability() || !knife.getAvailability()) {
                condition[id].await();
            }
            left.setAvailability(false);
            right.setAvailability(false);
            fork.setAvailability(false);
            knife.setAvailability(false);
            System.out.println("Philosopher " + (id + 1) + " is grabbing cutlery");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void releaseForks(int id, Side left, Side right, Cutlery fork, Cutlery knife) {
        lock.lock();
        left.setAvailability(true);
        right.setAvailability(true);
        fork.setAvailability(true);
        knife.setAvailability(true);
        condition[(id + 1) % 5].signalAll();
        condition[(id + 4) % 5].signalAll();
        System.out.println("Philosopher " + (id + 1) + " is putting down cutlery");
        lock.unlock();
    }
}
