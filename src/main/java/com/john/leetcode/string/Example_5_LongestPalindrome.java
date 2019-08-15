/* 
 * 
 * 项目名：	com.john.leetcode.part_1_10
 * 文件名：	Example_4_FindMedianSortedArrays
 * 模块说明：	
 * 修改历史：
 * 2019/8/4 - Administrator - 创建。
 */

package com.john.leetcode.string;

/**
 * 5. 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 *
 * @author Administrator
 * @date 2019/8/4
 */
public class Example_5_LongestPalindrome {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abc"));
        System.out.println(longestPalindrome("aba"));
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("cbbd"));
        System.out.println(longestPalindrome("ab"));
        System.out.println(longestPalindrome("ac"));
    }

    public static String longestPalindrome(String s) {
        if(s == null || s.length() == 0 || s.length() == 1) {
            return s;
        }

        int start = 0, end = 0;

        for(int i = 0; i < s.length(); i ++) {
            int len1 = getLength(s, i, i);
            int len2 = getLength(s, i, i + 1);
            int len = Math.max(len1, len2);
            // 数量相等会覆盖前面的
            if(len > end - start) {
                start = i - ((len - 1) / 2);
                end = i + (len / 2);
            }

        }

        return s.substring(start, end + 1);
    }

    public static int getLength(String s, int l, int r) {
        int L = l, R = r;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L --;
            R ++;
        }
        return R - L - 1;
    }

}
