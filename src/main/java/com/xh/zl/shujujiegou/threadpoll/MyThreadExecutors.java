package com.xh.zl.shujujiegou.threadpoll;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:zl
 * @Description 简单线程池的实现
 * @Date: 2019/12/9 9:11
 */
public class MyThreadExecutors implements Executor {
    private int  coreSize;//核心线程数
    private BlockingQueue<Runnable> queue;//阻塞队列
    private AtomicInteger poolSize = new AtomicInteger(0);//线程池数
    private Set<Wroker> workers = new HashSet<>();
    private ReentrantLock mainLock = new ReentrantLock();

    private volatile int runStatus = -1;// -1为运行状态，0为stop状态

    private static MyThreadExecutors executors = null;
    /**
     * @date:2019/12/9 9:12
     * @author:zl
     * @description:
     * @param coreSize 核心线程数的大小
     * @param queue 处理任务的队列
     */
    private MyThreadExecutors(int coreSize, BlockingQueue queue){
        this.coreSize = coreSize;
        this.queue = queue;
    }
    public static MyThreadExecutors getInstance(int coreSize){
        return new MyThreadExecutors(coreSize,new LinkedBlockingQueue());
    }
    private boolean compareAndSetPoolSize(int expect){
       return poolSize.compareAndSet(expect,expect + 1);
    }
    private boolean addWorker(Runnable command){
        int c = poolSize.get();
        for(;;){
            if(c < coreSize){
                if(compareAndSetPoolSize(c)){
                    break;
                }
                c = poolSize.get();
            }else{
                return false;
            }
        }
        boolean workerAdd = false;
        boolean workerStarted = false;
        Wroker w = new Wroker(command);
        Thread t = w.thread;
        ReentrantLock lock = this.mainLock;//hashSet是线程不安全的
        lock.lock();
        try {
            workers.add(w);
            workerAdd = true;
        }finally {
            lock.unlock();
        }
        if(workerAdd){
            t.start();
            workerStarted = true;
        }
        return workerStarted;
    }
    @Override
    public void execute(Runnable command) {
        try {
            if(poolSize.get() < coreSize){//线程池中大小小于核心数，需要创建线程
                if(addWorker(command)){
                    return;
                }
            }
            queue.offer(command);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void shutDown(){
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        this.runStatus = 0;
        try {
            for(Wroker wroker : workers){
                Thread t = wroker.thread;
                if(!t.isInterrupted()){
                    t.interrupt();
                }
            }
        }finally {
            mainLock.unlock();
        }

    }
    private Runnable getTask(){
        Runnable take = null;
        for(;;){
            if(runStatus == 0){
                return null;
            }
            try {
                take = this.queue.take();
                return take;
            } catch (InterruptedException e) {
            }
        }
    }
     class Wroker implements Runnable{
        private Runnable firstTask;
        public Thread thread;
        public Wroker(Runnable firstTask){
            this.firstTask = firstTask;
            this.thread = new Thread(this);
        }
        private void runTask(Wroker wroker){
            //执行第一个线程任务
            firstTask.run();
            Runnable r;
            while ((r = getTask())!= null){
                r.run();
            }
        }
        @Override
        public void run() {
            runTask(this);
        }
    }

    public static void main(String[] args) {
        MyThreadExecutors executors = MyThreadExecutors.getInstance(4);
        for(int i = 0 ; i < 20 ; i++){
            executors.execute(new Task(i));
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executors.shutDown();
    }
    static class Task implements Runnable{
        private int i ;
        public Task(int i){
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println("打印数字："+i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
        }
    }
}
