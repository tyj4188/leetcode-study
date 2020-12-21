/* 版权所有(C)，欧拉信息服务有限公司，2020，所有权利保留。
 *
 * 项目名：	com.john.leetcode.sort
 * 文件名：	MergeSort
 * 模块说明：
 * 修改历史：
 * 2020/12/18 - tongyongjian - 创建。
 */

package com.john.leetcode.sort;

import com.alibaba.fastjson.JSONObject;

/**
 * 归并排序
 *
 * 类型 :
 *   比较排序, 稳定排序算法。
 *
 * 用途 :
 *   一般用于对总体无序，但是各子项相对有序的数列
 *
 * 时间复杂度 :
 *   O(n log n)
 *
 * 大致流程 :
 *   1. 将 n 个元素分为 n / 2 个元素的子序列。
 *   2. 用归并的方式对两个子序列进行排序。
 *   3. 合并两个已排序的子序列。
 *   4. 重复步骤 2 和 3 , 直到循环遍历完成。
 *
 * 实现方式 :
 *   1. 迭代
 *   2. 递归
 *
 * @author tongyongjian
 * @date 2020/12/18
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] param = new int[]{15, 13, 10, 18, 21, 19, 10};
        int[] result = new int[param.length];
        //version_1(param);

        version_2(param, result, 0, param.length - 1);
        System.out.println(JSONObject.toJSONString(param));
    }

    /**
     * 迭代的方式实现
     *
     * @param param
     * @return
     */
    private static void version_1(int[] param) {
        int len = param.length;
        int[] result = new int[param.length];
        int blockSize, start, areaAStart, areaAEnd, areaBStart, areaBEnd, resultIndex;
        // 双层 for 循环

        // 外层的作用是给所有元素分块, blockSize 为每个块中元素的个数, 因为是归并那么每块元素的增长因子为一倍也就是乘以2
        // 比较阈值也应该为总元素个数的一倍
        for(blockSize = 1; blockSize < len * 2; blockSize *= 2) {
            // 内层的作用是每一个分块互相做比较
            // 每个分块的计算公式为
            // Area_1.start = start : start 从 0 开始, 增长因子为 start + (blockSize * 2), 也就是每次要跳过一块区域
            // Area_1.end = start + blockSize : 也就是 start + 元素个数 (最长为 len)
            // Area_2.start = Area_1.end : 两个区块重合一个元素, 因为开头闭尾 (最长为 len)
            // Area_2.end = start + (2 * blockSize) : 到下一个区块的开始 (最长为 len)
            for(start = 0; start < len; start += blockSize * 2) {
                resultIndex = start;
                areaAStart = start;
                areaAEnd = Math.min((areaAStart + blockSize), len);
                areaBStart = areaAEnd;
                areaBEnd = Math.min(areaAStart + (blockSize * 2), len);

                // 每个组都从头开始比, 也就是哪个组比较小哪个元素放进 result 中
                while(areaAStart < areaAEnd && areaBStart < areaBEnd) {
                    result[resultIndex ++] = param[areaAStart] < param[areaBStart]
                        ? param[areaAStart ++] : param[areaBStart ++];
                }

                // 把两个组中剩下的元素直接合并
                while(areaAStart < areaAEnd) {
                    result[resultIndex ++] = param[areaAStart ++];
                }

                while (areaBStart < areaBEnd) {
                    result[resultIndex ++] = param[areaBStart ++];
                }
            }

            // 内层比较完成之后把 param 更新
            int[] temp = param;
            param = result;
            result = temp;
        }
    }

    /**
     * 递归实现
     *
     * @param param
     */
    private static void version_2(int[] param, int[] result, int start, int end) {
        // 递归结束
        if(start >= end) {
            return;
        }

        // 计算长度和中间坐标, 把当前块分为两部分
        int len = end - start;
        // mid = start + 长度 / 2
        int mid = start + (len >> 1);
        // 计算区块 1 的起止位置
        int startA = start, endA = mid;
        // 计算区块 2 的起止位置
        int startB = mid + 1, endB = end;

        // 递归切分
        version_2(param, result, startA, endA);
        version_2(param, result, startB, endB);

        // 开始比较, 比较流程同循环实现的流程
        int resultIndex = start;
        while (startA <= endA && startB <= endB) {
            result[resultIndex ++] = param[startA] < param[startB]
                ? param[startA ++] : param[startB ++];
        }

        // 填入剩余元素
        while(startA <= endA) {
            result[resultIndex ++] = param[startA ++];
        }
        while(startB <= endB) {
            result[resultIndex ++] = param[startB ++];
        }

        // 把排序结果复制给 param
        for(resultIndex = start; resultIndex <= end; resultIndex ++) {
            param[resultIndex] = result[resultIndex];
        }
    }
}
