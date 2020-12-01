/* 版权所有(C)，欧拉信息服务有限公司，2020，所有权利保留。
 *
 * 项目名：	com.john.leetcode.test
 * 文件名：	Test
 * 模块说明：
 * 修改历史：
 * 2020/7/24 - tongyongjian - 创建。
 */

package com.john.leetcode.test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author tongyongjian
 * @date 2020/7/24
 */
public class Test {

    public static void main(String[] args) {
        //Thread thread1 = new Thread(new PrintDemo(1));
        //Thread thread2 = new Thread(new PrintDemo(2));
        //Thread thread3 = new Thread(new PrintDemo(3));
        //
        //thread1.start();
        //thread2.start();
        //thread3.start();

        //boolean result = testTimeCheck();
        //System.out.println(result);

        testFinally(11);
    }

    public static void testFinally(int param) {
        try {
            if(param <= 10) {
                return;
            }
            System.out.println("aaaaaaa");
        } finally {
            System.out.println("bbbbbbb");
        }
    }

    public static boolean testTimeCheck() {
        String startTime = "09:00", endTime = "23:30";

        String[] startTimeArr = startTime.split(":");
        String[] endTimeArr = endTime.split(":");
        if(startTimeArr.length != 2 || endTimeArr.length != 2) {
            return false;
        }

        LocalDateTime startDateTime = LocalDateTime.now()
            .withHour(Integer.parseInt(startTimeArr[0]))
            .withMinute(Integer.parseInt(startTimeArr[1]))
            .withSecond(0).withNano(0);

        LocalDateTime endDateTime = LocalDateTime.now()
            .withHour(Integer.parseInt(endTimeArr[0]))
            .withMinute(Integer.parseInt(endTimeArr[1]))
            .withSecond(0).withNano(0);

        long startUnixTime = startDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        long endUnixTime = endDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        if(startUnixTime > endUnixTime) {
            return false;
        }

        long nowUnixTime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        return startUnixTime <= nowUnixTime && nowUnixTime <= endUnixTime;
    }
}

class PrintDemo implements Runnable {

    private static final String PRINT_TEMPLATE = "%s ------ %s";

    private static volatile int AUTO_SEED = 0;

    private int threadNo;

    private String name;

    public PrintDemo(int threadNo) {
        this.threadNo = threadNo;
        this.name = "Thread-" + threadNo;
    }

    @Override
    public void run() {
        while(true) {
            synchronized (PRINT_TEMPLATE) {
                PRINT_TEMPLATE.notify();
                if(AUTO_SEED < 10) {
                    System.out.println(String.format(PRINT_TEMPLATE, name, AUTO_SEED ++));
                    try {
                        PRINT_TEMPLATE.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            }
        }
    }
}
