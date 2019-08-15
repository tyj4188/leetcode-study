/* 
 * 
 * 项目名：	com.john.leetcode.part_1_10
 * 文件名：	Example_3_LengthOfLongestSubstring
 * 模块说明：	
 * 修改历史：
 * 2019/8/1 - Administrator - 创建。
 */

package com.john.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目 3 （滑动窗口解题法）
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * ---- 示例
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 *
 * @author Administrator
 * @date 2019/8/1
 */
public class Example_3_LengthOfLongestSubstring {

    public static void main(String[] args) {
        String param1 = "abcabcbb";
        String param2 = "bbbbb";
        String param3 = "pwwkew";
        String param4 = "aab";
        String param5 = "au";
        String param6 = "dvdf";
        String param7 = "cddd";

        System.out.println(lengthOfLongestSubstring(param1));
        System.out.println(lengthOfLongestSubstring(param2));
        System.out.println(lengthOfLongestSubstring(param3));
        System.out.println(lengthOfLongestSubstring(param4));
        System.out.println(lengthOfLongestSubstring(param5));
        System.out.println(lengthOfLongestSubstring(param6));
        System.out.println(lengthOfLongestSubstring(param7));
    }

    public static int lengthOfLongestSubstring(String s) {
        if(s == null || s.equals("")) {
            return 0;
        }

        if(s.length() == 1) {
            return 1;
        }

        char[] charArr = s.toCharArray();
        if(s.length() == 2) {
            return charArr[0] == charArr[1] ? 1 : 2;
        }

        int current, length = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(int start = 0, end = 0; end < charArr.length; end++) {
            char temp = charArr[end];
            Integer idx = map.get(temp);

            if(idx != null) {
                start = idx > start ? idx : start;
            }
            current = end - start + 1;
            length = current > length ? current : length;
            map.put(temp, end + 1);

        }

        return length;
    }
}
