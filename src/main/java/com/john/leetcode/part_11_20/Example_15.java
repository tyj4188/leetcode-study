/* 版权所有(C)，欧拉信息服务有限公司，2019，所有权利保留。
 *
 * 项目名：	com.john.leetcode.part_11_20
 * 文件名：	Example_15
 * 模块说明：
 * 修改历史：
 * 2019/8/13 - GW00174243 - 创建。
 */

package com.john.leetcode.part_11_20;

import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * 15. 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 *  注意：答案中不可以包含重复的三元组。
 *
 * @author GW00174243
 * @date 2019/8/13
 */
public class Example_15 {
    public static void main(String[] args) {
        int[] nums = new int[]{-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0};
        System.out.println(JSONObject.toJSONString(threeSum_V1(nums)));
        System.out.println(JSONObject.toJSONString(threeSum_V2(nums)));
    }

    /**
     * 暴力解法, 会有重复的组合
     * 时间复杂度 : O(3n);
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum_V1(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();

        int i, j, k;
        for(i = 0; i < nums.length - 2; i++) {
            for(j = i + 1; j < nums.length - 1; j ++) {
                for(k = j + 1; k < nums.length; k++) {
                    if(0 == nums[i] + nums[j] + nums[k]) {
                        List<Integer> newList = Arrays.asList(nums[i], nums[j], nums[k]);
                        newList.sort(Comparator.comparingInt(o -> o.intValue()));
                        result.add(newList);
                    }
                }
            }
        }

        return new ArrayList<>(result);
    }

    public static List<List<Integer>> threeSum_V2(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        Map<Integer, List<Integer>> map = new HashMap<>();

        int i, j;
        for(i = 0; i < nums.length - 2; i++) {
            for(j = i + 1; j < nums.length; j ++) {
                List<Integer> tmpList = map.get(nums[j]);
                if(tmpList != null && tmpList.size() < 3) {
                    tmpList = new ArrayList<>(tmpList);
                    tmpList.add(nums[j]);
                    tmpList.sort(Comparator.comparingInt(o -> o.intValue()));
                    result.add(tmpList);
                    map.remove(nums[j]);
                } else {
                    int tmp = 0 - (nums[i] + nums[j]);
                    map.put(tmp, Arrays.asList(nums[i], nums[j]));
                }
            }
        }

        return new ArrayList<>(result);
    }
}
