/* 版权所有(C)，欧拉信息服务有限公司，2019，所有权利保留。
 *
 * 项目名：	com.john.leetcode.array
 * 文件名：	Example_1_TwoSum
 * 模块说明：
 * 修改历史：
 * 2019/8/15 - GW00174243 - 创建。
 */

package com.john.leetcode.array;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 *      给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 *      给定 nums = [2, 7, 11, 15], target = 9
 *
 *      因为 nums[0] + nums[1] = 2 + 7 = 9
 *      所以返回 [0, 1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 *
 * @author GW00174243
 * @date 2019/8/15
 */
public class Example_1_TwoSum {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;

        System.out.println(JSONObject.toJSONString(twoSum(nums, target)));
    }

    public static int[] twoSum(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return nums;
        }

        // 使用一个 Hash 表存储已经遍历过的值和下标
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            //
            Integer idx = map.get(nums[i]);
            if(idx != null) {
                return new int[]{idx, i};
            }
            // 把期望的值和当前下标放入 MAP
            map.put(target - nums[i], i);
        }

        return new int[]{};
    }
}
