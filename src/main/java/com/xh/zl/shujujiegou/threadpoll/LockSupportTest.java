package com.xh.zl.shujujiegou.threadpoll;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author:zl
 * @Description
 * @Date: 2019/12/10 17:08
 */
public class LockSupportTest {
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString("name1".hashCode()));
    }
}
