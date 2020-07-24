/* 版权所有(C)，欧拉信息服务有限公司，2020，所有权利保留。
 *
 * 项目名：	com.john.leetcode.test
 * 文件名：	Test
 * 模块说明：
 * 修改历史：
 * 2020/7/24 - tongyongjian - 创建。
 */

package com.john.leetcode.test;

/**
 * @author tongyongjian
 * @date 2020/7/24
 */
public class Test {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new PrintDemo(1));
        Thread thread2 = new Thread(new PrintDemo(2));
        Thread thread3 = new Thread(new PrintDemo(3));

        thread1.start();
        thread2.start();
        thread3.start();
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
