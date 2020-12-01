/* 版权所有(C)，欧拉信息服务有限公司，2020，所有权利保留。
 *
 * 项目名：	com.john.leetcode.sort
 * 文件名：	QuickSort
 * 模块说明：
 * 修改历史：
 * 2020/12/1 - tongyongjian - 创建。
 */

package com.john.leetcode.sort;

import com.alibaba.fastjson.JSONObject;

/**
 * 快速排序
 *
 * 大致流程 :
 *   1. 先找到第一个数的位置。
 *   2. 从步骤 1 找到的位置把数据分为左右两部分。
 *   3. 递归找左边一半和右边一半，重复步骤1和2。
 *
 * 步骤 1 - 定位 :
 *   一组数字 [5, 2, 1, 4, 6, 8, 3]
 *
 * @author tongyongjian
 * @date 2020/12/1
 */
public class QuickSort {
    private static QuickSort THIS = new QuickSort();

    public static void main(String[] args) {
        int[] data = new int[]{5, 2, 1, 4, 6, 8, 3};
        THIS.quickSort(data, 0, data.length - 1);
        System.out.println(JSONObject.toJSONString(data));
    }

    private void quickSort(int[] data, int start, int end) {
        if(data == null || data.length == 0) {
            return;
        }

        if(start < 0 || end < 0 || start >= end) {
            return;
        }

        // 1. 找到第一个数字的位置
        int position = find(data, start, end);

        // 2. 递归排序左半边
        quickSort(data, start, position - 1);

        // 3. 递归排序右半边
        quickSort(data, position + 1, end);
    }

    /**
     * 定位并交换数字
     *
     * @param data
     * @param start
     * @param end
     * @return
     */
    private int find(int[] data, int start, int end) {
        // 定义两个游标, 指向开头和结尾
        int left = start, right = end;
        // 定义一个临时变量, 存储要定位的数字
        // 这里如果定位数字是头的数字那么下面循环遍历游标时要从右边开始, 防止左游标先变动右边赋给左边时覆盖原值, 反之亦然。
        int positionVal = data[start];

        // 下面排序和比较全按照升序排序来说, 如果降序则把左右游标的指向换一下即可, 也就是左游标指向尾, 右游标指向头
        // 循环直到游标位置重合
        while(left < right) {
            // 如果右游标指向的数字大于定位数字, 游标左移
            while(left < right && data[right] > positionVal) {
                right --;
            }
            // 右游标指向的数字小于定位数字则赋值给左游标
            data[left] = data[right];

            // 如果左游标指向的数字小于或等于定位数字, 游标右移
            while (left < right && data[left] <= positionVal) {
                left ++;
            }
            // 左游标指向的数字大于定位数字则赋值给右游标
            data[right] = data[left];
        }
        // 最后确定定位数字的位置并赋值到该位置
        data[left] = positionVal;

        // 因为游标重合, 返回 left 或 right 都可以
        return left;
    }
}
