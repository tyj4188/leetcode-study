/* 版权所有(C)，欧拉信息服务有限公司，2020，所有权利保留。
 *
 * 项目名：	com.john.leetcode.sort
 * 文件名：	BubbleSort
 * 模块说明：
 * 修改历史：
 * 2020/12/21 - tongyongjian - 创建。
 */

package com.john.leetcode.sort;

import com.alibaba.fastjson.JSONObject;

/**
 * 冒泡排序
 *
 * 类型 :
 *   比较排序, 稳定排序算法。
 *
 * 时间复杂度 :
 *   O(n * n)
 *
 * 大致流程 :
 *   1. 每个元素都和自己以外的元素相比较。
 *   2. 升序情况下, 和在自己后面并比自己小的数字做替换。
 *
 * @author tongyongjian
 * @date 2020/12/21
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] param = new int[]{15, 3, 19, 6, 5, 10, 9};
        sort(param);
        System.out.println(JSONObject.toJSONString(param));
    }

    private static void sort(int[] param) {
        int i, j, t;

        for(i = 0; i < param.length; i ++) {
            for (j = i + 1; j < param.length; j ++) {
                if(param[i] > param[j]) {
                    t = param[i];
                    param[i] = param[j];
                    param[j] = t;
                }
            }
        }
    }
}
