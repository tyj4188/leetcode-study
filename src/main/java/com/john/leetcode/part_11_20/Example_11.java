/* 版权所有(C)，欧拉信息服务有限公司，2019，所有权利保留。
 *
 * 项目名：	com.john.leetcode.part_11_20
 * 文件名：	Example_11
 * 模块说明：
 * 修改历史：
 * 2019/8/13 - GW00174243 - 创建。
 */

package com.john.leetcode.part_11_20;

/**
 * 11. 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 *      在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 *      找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 *  说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * @author GW00174243
 * @date 2019/8/13
 */
public class Example_11 {
    public static void main(String[] args) {
        int[] arr = {1,8,6,2,5,4,8,3,7};

        System.out.println(maxArea_V1(arr));
        System.out.println(maxArea_V2(arr));
    }

    /**
     * 暴力解法
     *  时间复杂度 : O( n^2 )
     * @param height
     * @return
     */
    public static int maxArea_V1(int[] height) {
        if(height.length == 2) {
            return Math.min(height[0], height[1]) * 1;
        }

        int max = 0;

        for(int i = 0; i < height.length; i++) {
            for(int j = i + 1; j < height.length; j ++) {
                int tmp = Math.min(height[i], height[j]);
                int tmpArea = tmp * (j - i);
                if(tmpArea > max) {
                    max = tmpArea;
                }
            }
        }

        return max;
    }

    /**
     * 双指针解法
     *  使用指针 i, j 分别指向头和尾，每次计算之后使指向短的那边的指针往长的那一边移动一个
     *
     *  时间复杂度 : O(n)
     *
     * @param height
     * @return
     */
    public static int maxArea_V2(int[] height) {
        if(height.length == 2) {
            return Math.min(height[0], height[1]) * 1;
        }

        int max = 0;

        for(int i = 0, j = height.length - 1; i < j;) {
            int area = Math.min(height[i], height[j]) * (j - i);
            if(area > max) {
                max = area;
            }

            if(height[i] < height[j]) {
                i ++;
            } else {
                j --;
            }
        }

        return max;
    }
}
