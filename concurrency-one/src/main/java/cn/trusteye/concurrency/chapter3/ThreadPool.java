package cn.trusteye.concurrency.chapter3;

import java.util.concurrent.*;

/**
 * Created by Rayman on 2017/7/27.
 */
public class ThreadPool {
/*
    public static void main(String[] args) {
        //RejectedExecutionHandler implementation
        RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();
        //Get the ThreadFactory implementation to use
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        //creating the ThreadPoolExecutor
        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2), threadFactory, rejectionHandler);
        //start the monitoring thread
        MyMonitorThread monitor = new MyMonitorThread(executorPool, 3);
        Thread monitorThread = new Thread(monitor);
        monitorThread.start();
        //submit work to the thread pool
        for(int i=0; i<10; i++){
            executorPool.execute(new WorkerThread("cmd"+i));
        }

        Thread.sleep(30000);
        //shut down the pool
        executorPool.shutdown();
        //shut down the monitor thread
        Thread.sleep(5000);
        monitor.shutdown();

    }

    static class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println(r.toString() + " is rejected");
        }
    }



    class MyMonitorThread implements Runnable {
        private ThreadPoolExecutor executor;
        private int seconds;
        private boolean run = true;

        public MyMonitorThread(ThreadPoolExecutor executor, int delay) {
            this.executor = executor;
            this.seconds = delay;
        }
        public void shutdown() {
            this.run = false;
        }

        @Override
        public void run() {
            while (run) {
                System.out.println(
                        String.format("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
                                this.executor.getPoolSize(),
                                this.executor.getCorePoolSize(),
                                this.executor.getActiveCount(),
                                this.executor.getCompletedTaskCount(),
                                this.executor.getTaskCount(),
                                this.executor.isShutdown(),
                                this.executor.isTerminated()));
                try {
                    Thread.sleep(seconds * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
*/

}
