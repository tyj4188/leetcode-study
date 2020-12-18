/* 版权所有(C)，欧拉信息服务有限公司，2020，所有权利保留。
 *
 * 项目名：	com.john.leetcode.sort
 * 文件名：	CountingSort
 * 模块说明：
 * 修改历史：
 * 2020/12/10 - tongyongjian - 创建。
 */

package com.john.leetcode.sort;

import com.alibaba.fastjson.JSONObject;

/**
 * 计数排序
 *
 * 时间复杂度:
 *   O(n)
 *
 * 类型 :
 *   非比较算法, 稳定排序算法
 *
 * 大致流程 :
 *   1. 循环遍历找到所有数字中最大的, 记为 "max"。
 *   2. 创建一个长度为 max + 1 的 int 类型数组 "array", 初始所有元素为 0。
 *   3. 遍历所有数字, 把数字对应
 *
 * @author tongyongjian
 * @date 2020/12/10
 */
public class CountingSort {


    public static void main(String[] args) {
        int[] param = new int[]{103, 101, 106, 108, 110, 101};
        int[] result = null;
        //result = version_1(param);
        //System.out.println(JSONObject.toJSONString(result));

        //result = version_2(param);
        //System.out.println(JSONObject.toJSONString(result));

        result = version_3(param);
        System.out.println(JSONObject.toJSONString(result));
    }

    /**
     * 初级版本
     * 注: 会有无效浪费, 比如需要排序的数字为 [103, 101, 106, 108, 110],
     *   那么需要创建一个长度为 111 的数组, 但是 0 - 100 的空间全部被浪费了。
     * @param param
     * @return
     */
    private static int[] version_1(int[] param) {
        int max = 0;
        for(int temp : param) {
            if(temp > max) {
                max = temp;
            }
        }

        int[] countArr = new int[max + 1];
        for(int temp : param) {
            countArr[temp] ++;
        }

        int[] resultArr = new int[param.length];
        for(int i = 0, j = 0; i < countArr.length; i++) {
            while(countArr[i] > 0) {
                resultArr[j ++] = i;
                countArr[i] --;
            }
        }

        return resultArr;
    }

    /**
     * 优化版
     * 注: 优化内存浪费的问题, 例: [103, 101, 106, 108, 110],
     *   找出最小、最大, 设置数组长度为 max - min + 1
     * @param param
     * @return
     */
    private static int[] version_2(int[] param) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int temp : param) {
            min = Math.min(temp, min);
            max = Math.max(temp, max);
        }

        int[] countArr = new int[max - min + 1];
        for(int temp : param) {
            countArr[temp - min] ++;
        }

        int[] result = new int[param.length];
        for(int i = 0, j = 0; i < countArr.length; i ++) {
            while(countArr[i] > 0) {
                result[j ++] = i + min;
                countArr[i] --;
            }
        }
        return result;
    }

    /**
     * 针对 v2 版本做优化
     * 注 : 在遍历 count 数组计算完对应下标值之后, 做一次 count[i + 1] = count[i + 1] + count[i] 的步骤
     *
     * @param param
     * @return
     */
    private static int[] version_3(int[] param) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int temp : param) {
            min = Math.min(temp, min);
            max = Math.max(temp, max);
        }

        int[] countArr = new int[max - min + 1];
        for(int temp : param) {
            countArr[temp - min] ++;
        }

        // 做 count[i + 1] = count[i + 1] + count[i] 的步骤
        // 这一步得出的是最后的 "排名"
        // 执行前 countArr 对应的结果是
        // 下标 = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        // 数值 = [2, 0, 1, 0, 0, 1, 0, 1, 0, 1]
        for(int i = 1; i < countArr.length; i ++) {
            countArr[i] += countArr[i - 1];
        }
        // 执行后 countArr 对应的结果是
        // 下标 = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        // 数值 = [2, 2, 3, 3, 3, 4, 4, 5, 5, 6]
        // 刚好是排名, count[0] 的值是 2 是因为 101 有两个, 这样在后面 *引用a* 步骤中递减1就是为了重复数字
        // 一个 "101" 排在第二位, 递减之后第二个 "101" 就排在第一位了
        // 也就是说这里的两个相同的元素变为了倒叙, 先出现的排在后面了
        // 如果要解决这个问题: 方案1 - 最后倒序遍历; 方案2 - count 全部往后挪一位采用+1算法计算。

        int[] result = new int[param.length];
        // 前一步就是为了能够直接通过 countArr[param[i] - min] - 1
        // 找到原始数组 param 中对应数字在 result 数组中的下标
        for(int temp : param) {
            // 通过 countArr 中存储的 "排名", 这里 "-1" 是 "排名" - 1 = 下标
            result[countArr[temp - min] - 1] = temp;
            // 这里递减是为了防止有重复的元素, 重复元素比如原数组中的两个 "101"
            // *引用a* - 递减解决重复数字的排序问题
            countArr[temp - min] --;
        }

        return result;
    }
}
