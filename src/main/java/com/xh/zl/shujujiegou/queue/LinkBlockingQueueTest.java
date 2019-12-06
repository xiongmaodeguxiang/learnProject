package com.xh.zl.shujujiegou.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @Author:zl
 * @Description
 *
 *  LinkedBlockingQueue 添加元素的方法有 add(E e),offer(E e),put(E e),offer(E e, Long l, TimeUtil t)
 * 一、add方法定义在BlockingQueue和Queue接口中，具体实现在AbstractQueue,实现过程调用了offer(E e),offer(E e)的由LinkedBockingQueue实现,
 * add方法成功添加元素返回true,如果队列已满，再添加则会抛出 IllegalStateException:Deque Full异常；
 * 二、offer(E e)方法定义在BlockingQueuee 和Queue接口中，与add方法的区别是队列满时offer不会抛出异常而是返回false
 * 三、offer(E e,Long l ,TimeUtil t) 在队列已满时添加元素，线程会等待 l t 的时间长度，超时则会返回false,
 * 四、put（E e）方法定义在BlockingQueue接口中，在队列已满时会进行阻塞直到有元素取出后再进行添加
 *
 * 获取队列的方法有：element()、peek()、poll()、poll(Long l,TimeUtil t),take()
 * element和peek()定义在Queue接口中，功能是获取队列中的头元素，但是不会移出数据,区别在于element在队列为空时会抛出异常NoSuchElementException，而peek()方法则会返回null
 *poll()、poll(Long l,TimeUtil t) take()接口定义在BlockingQueue，poll（）会获取头元素并且将其移出,如果队列为null则返回空，poll(Long l,TimeUtil t)则是在poll()基础之上添加了超时等待时间，
 * take()方法则会在队列为空时一直进行阻塞，直到队列中有了元素
 * @Date: 2019/12/6 9:45
 */
public class LinkBlockingQueueTest {
    public BlockingQueue queue = new LinkedBlockingDeque(4);
    public void addElement(Object o){
        boolean add = queue.add(o);
        System.out.println("元素:"+o+"添加成功:"+add);
    }
    public Object take() throws InterruptedException {
        return queue.take();
    }
    public static void main(String[] args) throws InterruptedException {
        final LinkBlockingQueueTest test = new LinkBlockingQueueTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("等待获取元素中......");
                    System.out.println("获取到元素："+test.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        TimeUnit.SECONDS.sleep(5);
        new Thread(new Runnable() {
            @Override
            public void run() {
                test.addElement("haha");
            }
        }).start();
    }
}
