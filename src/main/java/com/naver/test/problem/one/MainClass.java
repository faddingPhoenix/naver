package com.naver.test.problem.one;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClass {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(8);

        CountDownLatch latchA = new CountDownLatch(1);
        CountDownLatch latchB = new CountDownLatch(1);
        CountDownLatch latchC = new CountDownLatch(1);
        CountDownLatch latchD = new CountDownLatch(1);
        CountDownLatch latchE = new CountDownLatch(1);
        CountDownLatch latchF = new CountDownLatch(1);
        CountDownLatch latchG = new CountDownLatch(1);
        CountDownLatch latchH = new CountDownLatch(1);

        executor.submit(new Task(latchA, "A", latchC, latchG));
        executor.submit(new Task(latchB, "B"));
        executor.submit(new Task(latchC, "C", latchH));
        executor.submit(new Task(latchD, "D", latchA));
        executor.submit(new Task(latchE, "E", latchB, latchG));
        executor.submit(new Task(latchF, "F", latchD));
        executor.submit(new Task(latchG, "G", latchB));
        executor.submit(new Task(latchH, "H"));

    }
}
