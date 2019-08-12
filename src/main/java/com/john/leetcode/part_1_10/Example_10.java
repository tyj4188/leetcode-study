/*
 *
 * 项目名：	com.john.leetcode.part_1_10
 * 文件名：	Example_10
 * 模块说明：
 * 修改历史：
 * 2019/8/12 - Administrator - 创建。
 */

package com.john.leetcode.part_1_10;

/**
 * 10. 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *      '.' 匹配任意单个字符
 *      '*' 匹配零个或多个前面的那一个元素
 *  说明:
 *      s 可能为空，且只包含从 a-z 的小写字母。
 *      p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * @author Administrator
 * @date 2019/8/12
 */
public class Example_10 {

    public static final char ONE = '.';
    public static final char ANY = '*';

    public static void main(String[] args) {
        String s = "aaa";
        String p = "a*";
        System.out.println(isMatch_V1(s, p));
        System.out.println(isMatch_V2(s, p));
        System.out.println(isMatch_V3(s, p));
        System.out.println(isMatch_V4(s, p));
        System.out.println(isMatch_V5(s, p));
        System.out.println(isMatch_V6(s, p));
    }

    // 先不考虑通配符
    public static boolean isMatch_V1(String s, String p) {
        if(p == null || s == null) {
            return false;
        }

        if(s.length() != p.length()) {
            return false;
        }

        for(int i = 0; i < p.length(); i++) {
            if(p.charAt(i) != s.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    // 换一种写法
    public static boolean isMatch_V2(String s, String p) {
        if(s == null || p == null) {
            return false;
        }

        int i = 0, j = 0;
        while(j < p.length()) {
            if(i >= s.length()) {
                return false;
            }

            if(s.charAt(i ++) != p.charAt(j ++)) {
                return false;
            }
        }
        return j == p.length();
    }

    // 使用递归
    public static boolean isMatch_V3(String s, String p) {
        if(p == null || p.length() == 0) {
            return s == null || s.length() == 0;
        }
        boolean firstMatch = s.charAt(0) == p.charAt(0);

        return firstMatch && isMatch_V3(s.substring(1), p.substring(1));
    }

    // 处理 [.] 通配符
    public static boolean isMatch_V4(String s, String p) {
        if(p == null || p.length() == 0) {
            return s == null || s.length() == 0;
        }
        boolean firstMatch = s.charAt(0) == p.charAt(0) || ONE == p.charAt(0);

        return firstMatch && isMatch_V4(s.substring(1), p.substring(1));
    }

    // 暴力解法, 处理 [*] 通配符
    public static boolean isMatch_V5(String s, String p) {
        if(p == null || p.length() == 0) {
            return s == null || s.length() == 0;
        }
        boolean firstMatch = (s != null && s.length() > 0) ? (s.charAt(0) == p.charAt(0) || ONE == p.charAt(0)) : false;

        // 因为通配符一定会出现在第二个
        if(p.length() >= 2 && p.charAt(1) == ANY) {
            // 处理 * 通配符
            // isMatch_V5(s, p.substring(2)) 跳过 * 直接匹配 s , 相当于匹配 0 次
            // (firstMatch && isMatch_V5(s.substring(1), p)) 当前匹配后继续匹配下面的一个字符, 相当于匹配 N 次
            return isMatch_V5(s, p.substring(2)) || (firstMatch && isMatch_V5(s.substring(1), p));
        } else {
            return firstMatch && isMatch_V5(s.substring(1), p.substring(1));
        }
    }

    // 优化的动态规划解决正则
    enum Result {
        FALSE, TRUE
    }
    static Result[][] dic;

    public static boolean isMatch_V6(String s, String p) {
        // 初始化一个字典表示 s[i] 和 p[j] 是否匹配
        // 字典是防止重复子问题的计算, 到达同一个子问题的路径有可能有多个, 发现该子问题已经解决的时候直接读取字典
        dic = new Result[s.length() + 1][p.length() + 1];
        return dp(0, 0, s, p);
    }
    // 因为 isMatch_V6 只会调用一次，使用 dp 实现递归
    public static boolean dp(int i, int j, String s, String p) {
        // 判断是否已经解决过
        Result cache = dic[i][j];
        if(cache != null) {
            return cache == Result.TRUE;
        }
        boolean result;
        // 判断是否已经匹配到最后
        if(j == p.length()) {
            result = i == s.length();
        } else {
            // 使用暴力解法
            boolean first = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == ONE);
            // 多匹配
            if(j + 1 < p.length() && p.charAt(j + 1) == ANY) {
                // 跳过 * 匹配 0 次, 或者使用 * 继续匹配 i + 1
                result = dp(i, j + 2, s, p) || (first && dp(i + 1, j, s, p));
            } else {
                // 匹配下一个字符
                result = first && dp(i + 1, j + 1, s, p);
            }
        }

        // 缓存字典
        dic[i][j] = result ? Result.TRUE : Result.FALSE;
        return result;
    }
}
