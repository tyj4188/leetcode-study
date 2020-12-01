/* 版权所有(C)，欧拉信息服务有限公司，2020，所有权利保留。
 *
 * 项目名：	com.john.leetcode.tree
 * 文件名：	Example_0_Base
 * 模块说明：
 * 修改历史：
 * 2020/6/4 - tongyongjian - 创建。
 */

package com.john.leetcode.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 树的基础练习
 *   创建、初始化、前中后序遍历
 * @author tongyongjian
 * @date 2020/6/4
 */
public class Example_0_Base {

    private static transient int size = 0;

    public static void main(String[] args) {
        char[] dataArr = new char[]{'B', 'H', 'E', 'Z', 'Q', 'U', 'P', 'Y', 'I'};
        Node head = new Node('A');
        for(char temp : dataArr) {
            add(head, temp);
        }

        preTraverse(head);
        System.out.println("---------- size = " + size + " ------------");
        midTraverse(head);
    }

    /**
     * 先序遍历
     * @param head
     */
    private static void preTraverse(Node head) {
        if(head == null) {
            return;
        }
        System.out.println(head.getData());
        preTraverse(head.getLeft());
        preTraverse(head.getRight());
    }

    /**
     * 中序
     * @param head
     */
    private static void midTraverse(Node head) {
        if(head == null) {
            return;
        }
        midTraverse(head.getLeft());
        System.out.println(head.getData());
        midTraverse(head.getRight());
    }

    /**
     * 后续
     * @param head
     */
    private static void postTraverse(Node head) {
        if(head == null) {
            return;
        }
        postTraverse(head.getLeft());
        postTraverse(head.getRight());
        System.out.println(head.getData());
    }

    private static void add(Node node, char data) {
        if(node == null) {
            return;
        }

        if(node.getData() >= data) {
            if(node.getLeft() == null) {
                Node n = new Node(data);
                node.setLeft(n);
                size ++;
            } else {
                add(node.getLeft(), data);
            }
            return;
        }

        if(node.getData() < data) {
            if(node.getRight() == null) {
                Node n = new Node(data);
                node.setRight(n);
                size ++;
            } else {
                add(node.getRight(), data);
            }
        }
    }

}


@Data
@NoArgsConstructor
@AllArgsConstructor
class Node {
    private char data;

    private Node left;

    private Node right;

    public Node(char data) {
        this.data = data;
    }
}
