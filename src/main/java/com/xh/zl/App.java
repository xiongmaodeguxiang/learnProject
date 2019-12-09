package com.xh.zl;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Hello world!
 *
 */
public class App
{
    // runState is stored in the high-order bits
    public static final int COUNT_BITS = Integer.SIZE - 3;
    public static final int CAPACITY   = (1 << COUNT_BITS) - 1;
    public static final int RUNNING    = -1 << COUNT_BITS;
    public static final int SHUTDOWN   =  0 << COUNT_BITS;
    public static final int STOP       =  1 << COUNT_BITS;
    public static final int TIDYING    =  2 << COUNT_BITS;
    public static final int TERMINATED =  3 << COUNT_BITS;
    public static int ctlOf(int rs, int wc) { return rs | wc; }
    public static int runStateOf(int c)     { return c & ~CAPACITY; }
    public static int workerCountOf(int c)  { return c & CAPACITY; }
    public static final AtomicInteger ctl = new AtomicInteger(ctlOf(STOP, 0));

    public static void main( String[] args )
    {
        System.out.println("CAPACITY: "+Integer.toBinaryString(CAPACITY));
        System.out.println("~CAPACITY: "+Integer.toBinaryString(~CAPACITY));
        System.out.println("RUNNING: "+Integer.toBinaryString(RUNNING));
        System.out.println("SHUTDOWN: "+Integer.toBinaryString(SHUTDOWN));
        System.out.println("STOP: "+Integer.toBinaryString(STOP));
        System.out.println("TIDYING: "+Integer.toBinaryString(TIDYING));
        System.out.println("TERMINATED: "+Integer.toBinaryString(TERMINATED));
        System.out.println("ctl: "+Integer.toBinaryString(ctl.get()));
    }
}
