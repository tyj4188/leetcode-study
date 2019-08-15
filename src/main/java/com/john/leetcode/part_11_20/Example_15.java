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
        System.out.println(JSONObject.toJSONString(threeSum_V3(nums)));
    }

    /**
     * 暴力解法
     * 时间复杂度 : O(n^3);
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

    /**
     * 使用 map 缓存书签
     *
     * 时间复杂度 : O(n^2)
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum_V2(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>(nums.length);

        int i, j;
        for(i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for(i = 0; i < nums.length - 2; i++) {
            for(j = i + 1; j < nums.length; j ++) {
                int temp = 0 - (nums[i] + nums[j]);
                Integer idx = map.get(temp);
                if(idx != null && idx != i && idx != j) {
                    List<Integer> tempList = Arrays.asList(nums[i], nums[j], nums[idx]);
                    tempList.sort(Comparator.comparingInt(o -> o.intValue()));
                    result.add(tempList);
                }
            }
        }

        return new ArrayList<>(result);
    }

    /**
     * 排序 + 双指针
     * 时间复杂度 : O(n^2)
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum_V3(int[] nums) {
        // 先排序, 复杂度 O(n log n)
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        // 外层循环
        for(int i = 0; i < nums.length - 2; i++) {
            // 判断当前的数字是否与前一个相等, 相等就跳过这次循环
            if(i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int l = i + 1, r = nums.length - 1;
                int sum = 0 - nums[i];
                while(l < r) {
                    if(nums[l] + nums[r] == sum) {
                        result.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        // 跳过相同值
                        while(l < r && nums[l] == nums[l+1]) {
                            l ++;
                        }
                        while(l < r && nums[r] == nums[r-1]) {
                            r --;
                        }
                        l ++; r --;
                    } else if(nums[l] + nums[r] < sum) {
                        // l 需要右移增大，跳过重复值
                        while(l < r && nums[l] == nums[l+1]) {
                            l ++;
                        }
                        l ++;
                    } else {
                        // 左移减小 r, 跳过重复值
                        while(l < r && nums[r] == nums[r-1]) {
                            r --;
                        }
                        r --;
                    }
                }
            }
        }

        return result;
    }
}
