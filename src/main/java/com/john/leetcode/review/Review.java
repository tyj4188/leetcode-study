/* 版权所有(C)，欧拉信息服务有限公司，2019，所有权利保留。
 *
 * 项目名：	com.john.leetcode.review
 * 文件名：	Review
 * 模块说明：
 * 修改历史：
 * 2019/8/16 - GW00174243 - 创建。
 */

package com.john.leetcode.review;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 习题复习
 * @author GW00174243
 * @date 2019/8/16
 */
public class Review {
    public static void main(String[] args) {
        int[] e1_nums = new int[]{2, 7, 4, 8};
        int e1_target = 9;
        System.out.println(JSONObject.toJSONString(twoSum(e1_nums, e1_target)));

    }

    // 1. 两数之和, 使用 Map 结构缓存下标
    public static int[] twoSum(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return nums;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            Integer idx = map.get(nums[i]);
            if(idx != null) {
                return new int[]{idx, i};
            }
            map.put(target - nums[i], i);
        }

        return null;
    }
}
