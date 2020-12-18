/* 版权所有(C)，欧拉信息服务有限公司，2020，所有权利保留。
 *
 * 项目名：	com.john.leetcode.sort
 * 文件名：	BucketSort
 * 模块说明：
 * 修改历史：
 * 2020/12/16 - tongyongjian - 创建。
 */

package com.john.leetcode.sort;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 桶排序
 *
 * 类型 :
 *   非比较排序, 稳定排序
 *
 * 时间复杂度:
 *   复杂度分为两个部分, 一个部分是遍历所有元素的 O(n), 另外一部分是桶内排序的复杂度。
 *   假设有 n 个元素和 m 个桶, 并且是 m 个桶均分, 每个桶的元素个数为 n / m, 并且桶内排序采用快排算法(O(n log n))。
 *   那么, 时间复杂度应该为 O(n) + (m * ((n/m) log (n/m)))
 *   O(n) + (m * ((n/m) log (n/m))) = O(n + n * (log n - log m))
 *
 *   最终复杂度为 : O(n + n * (log n - log m))
 *
 *   极端情况 :
 *     1. 元素取值范围极不平均, 99% 的元素分在一个桶中, 那么时间复杂度取决于桶内比较排序算法的复杂度(快排为 O(n log n))。
 *     2. 所有元素每个都是单独的桶, 那就变成了计数排序, 复杂度为 O(n)。
 *
 * 大致流程:
 *   1. 根据元素总个数或最大最小值分成若干个桶 (*分捅算法是关键*)。
 *   2. 把元素通过映射放入相应的桶。
 *   3. 每个桶中采用某种排序算法单独排序(这里采用快排)。
 *   4. 顺序输出每个桶的每个元素放入结果数组即可。
 *
 * 问题:
 *   1. 如果数字分布极不均匀是否不应该使用桶排序, 例如: [3,1,5,6,...此处n个数字均小于100..,99999]。
 *   2. 如何分桶能够尽量减少空桶。
 *
 * @author tongyongjian
 * @date 2020/12/16
 */
public class BucketSort {

    public static void main(String[] args) {
        int[] param = new int[]{103, 101, 106, 108, 110, 101, 15, 2, 51, 45, 6, 88, 3};
        int[] result = bucketSort(param);
        System.out.println(JSONObject.toJSONString(result));
    }

    private static int[] bucketSort(int[] param) {
        // 1. 建捅 (采用最大最小值均分)
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int temp : param) {
           min = Math.min(temp, min);
           max = Math.max(temp, max);
        }

        // 计算桶的个数
        int bucketCount = (max / 10) - (min / 10) + 1;

        // 创建桶
        List<List<Integer>> bucketList = new ArrayList<>(bucketCount);
        for(int i = 0; i < bucketCount; i ++) {
            bucketList.add(new ArrayList<>());
        }

        // 循环遍历把每个元素放入相应的桶中
        for(int temp : param) {
            bucketList.get(temp / 10 - min / 10).add(temp);
        }

        // 每个桶单独排序
        for(List<Integer> temp : bucketList) {
            if(temp.size() > 0) {
                temp.sort(null);
            }
        }

        // 循环遍历把每个桶中的元素顺序输出
        int[] result = new int[param.length];
        int index = 0;
        for(List<Integer> bucket : bucketList) {
            for(Integer item : bucket) {
                result[index ++] = item;
            }
        }

        return result;
    }

}
