/* 
 * 
 * 项目名：	com.john.leetcode.part_1_10
 * 文件名：	Example_1
 * 模块说明：	
 * 修改历史：
 * 2019/8/1 - Administrator - 创建。
 */

package com.john.leetcode.linked;

/**
 * 题目 2
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * ---- 示例
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * @author Administrator
 * @date 2019/8/1
 */
public class Example_2_AddTwoNumbers {

    public static void main(String[] args) {
        ListNode param1 = createLink(new int[]{2, 4});
        ListNode param2 = createLink(new int[]{5, 9});

        ListNode result = addTwoNumbers(param1, param2);
        System.out.println("结果: ");
        printLink(result);

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if(isZero(l1)) {
            return l2;
        }

        if(isZero(l2)) {
            return l1;
        }

        ListNode resultHead = new ListNode(0);
        ListNode resultItr = resultHead;
        ListNode itrA = l1;
        ListNode itrB = l2;
        boolean isUp = false;
        do{

            int result = nullToZero(itrA) + nullToZero(itrB);
            result = isUp ? result + 1 : result;

            if(isUp = (result >= 10)) {
                result = result % 10;
            }

            resultItr.next = new ListNode(result);
            resultItr = resultItr.next;

            itrA = itrA != null ? itrA.next : null;
            itrB = itrB != null ? itrB.next : null;
        }
        while(hasNext(itrA) || hasNext(itrB) || isUp);

        return resultHead.next;

    }

    public static int nullToZero(ListNode node) {
        return node == null ? 0 : node.val;
    }

    public static boolean hasNext(ListNode node) {
        return node != null;
    }

    public static boolean isZero(ListNode node) {
        return node == null || (!hasNext(node) && node.val == 0);
    }

    public static ListNode createLink(int[] arr) {
        ListNode head = new ListNode(0);
        ListNode itr = head;

        for(int temp : arr) {
            itr.next = new ListNode(temp);
            itr = itr.next;
        }

        return head.next;
    }

    public static void printLink(ListNode head) {
        StringBuilder sb = new StringBuilder("[");
        ListNode itr = head;
        while(itr != null) {
            sb.append(itr.val).append(", ");
            itr = itr.next;
        }
        sb.append("]");
        System.out.println(sb.toString());
    }


}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
