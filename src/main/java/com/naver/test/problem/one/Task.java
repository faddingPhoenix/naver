package com.naver.test.problem.one;

import java.util.concurrent.CountDownLatch;

public class Task implements Runnable{
    /** to block this thread */
    private CountDownLatch[] blockers;

    /** tell others i have finished */
    private CountDownLatch endSinal;

    /** name for print */
    private String taskName;

    public  Task(CountDownLatch endSinal, String taskName, CountDownLatch... blockers){
        if(taskName == null){
            throw new IllegalArgumentException("taskName cannot be null");
        }
        this.endSinal = endSinal;
        this.taskName = taskName;
        this.blockers = blockers;
    }

    public void run() {

        try {
            if(blockers != null && blockers.length > 0){
                for (CountDownLatch blocker : blockers) {
                    blocker.await();
                }
            }
            System.out.println(taskName);
            endSinal.countDown();
        } catch (InterruptedException e) {
            System.out.println("an error occur while executing task");
            e.printStackTrace();
        }

    }
}
