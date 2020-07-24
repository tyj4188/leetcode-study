/* 版权所有(C)，欧拉信息服务有限公司，2020，所有权利保留。
 *
 * 项目名：	com.john.leetcode.tree
 * 文件名：	Example_0_Base
 * 模块说明：
 * 修改历史：
 * 2020/6/4 - tongyongjian - 创建。
 */

package com.john.leetcode.tree;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 树的基础练习
 *   创建、初始化、前中后序遍历
 * @author tongyongjian
 * @date 2020/6/4
 */
public class Example_0_Base {

    private static Example_0_Base SELF = new Example_0_Base();

    private static void test() {
        String pre = "value_";
        String val = pre + "1";
        System.out.println(val.substring(pre.length()));
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(JSONObject.toJSONString(scrollAssign(list, 20)));
        //test();
    }

    private int sum(int param) {
        if(param == 1) {
            return 1;
        }
        return param + sum(param - 1);
    }

    /**
     * 创建一棵树
     * @param degree 度
     * @param height 高度
     * @return
     */
    private TreeNode createTree(int degree, int height) {
        char value = 65;
        TreeNode root = new TreeNode(String.valueOf(value));

        TreeNode tempNode = null, tempNext = root;
        for(int h = 0; h < height; h ++) {
            for(int d = 0; d < degree; d ++) {
                tempNode = new TreeNode(String.valueOf(++ value));
                tempNext.child.add(tempNode);
            }
            //tempNext
        }

        return root;
    }


    class TreeNode {
        private TreeNode parent;

        private List<TreeNode> child;

        private String value;

        public TreeNode() {
        }

        public TreeNode(String value) {
            this.value = value;
        }
    }

    private static <T> List<List<T>> scrollAssign(List<T> source, int size) {
        List<List<T>> result = new ArrayList<>();
        if(source.size() <= size) {
            result.add(source);
            return result;
        }

        int total = source.size();
        int idx = 0;
        List<T> value = new ArrayList<>(size);
        while(true) {
            value.add(source.get(idx));
            if(idx + 1 == total) {
                result.add(value);
                break;
            }
            if((idx + 1) % size == 0) {
                result.add(value);
                value = new ArrayList<>(size);
            }

            idx ++;
        }
        return result;
    }

    private static <T> List<List<T>> averageAssign(List<T> source, int size) {
        int parts = ((source.size() - 1) / size) + 1;
        List<List<T>> result = new ArrayList<>();
        int remainder = source.size() % parts;  //(先计算出余数)
        int number = source.size() / parts;  //然后是商
        int offset = 0;//偏移量
        for (int i = 0; i < parts; i++) {
            List<T> value = null;
            if (remainder > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remainder --;
                offset ++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }
}
