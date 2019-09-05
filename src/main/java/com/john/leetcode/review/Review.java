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
import com.john.leetcode.entity.Example_2_ListNode;
import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        for(int i = 0; i < 1000; i++) {
            String date = sdf.format(new Date());
            String timestamp = String.valueOf(new Date().getTime());
            timestamp = timestamp.substring(timestamp.length() - 3);
            String suffix = RandomStringUtils.random(6, false, true);
            System.out.println(date + timestamp + suffix);
        }
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

    // 2. 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字
    public Example_2_ListNode addTwoNumbers(Example_2_ListNode A, Example_2_ListNode B) {
        if(A == null || (A.next == null && A.val == 0)) {
            return B;
        }
        if(B == null || (B.next == null && B.val == 0)) {
            return A;
        }

        Example_2_ListNode head = new Example_2_ListNode(0);

        Example_2_ListNode pA = A, pB = B, pRs = head;

        boolean isUp = false;

        while(pA != null || pB != null || isUp) {
            int valA = pA == null ? 0 : pA.val;
            int valB = pB == null ? 0 : pB.val;

            int temp = valA + valB;
            temp = isUp ? temp + 1 : temp;
            if(isUp = temp >= 10) {
                temp = temp % 10;
            }

            Example_2_ListNode tempNode = new Example_2_ListNode(temp);
            pRs.next = tempNode;
            pRs = pRs.next;
            pA = pA != null ? pA.next : null;
            pB = pB != null ? pB.next : null;
        }

        return head.next;
    }
}
