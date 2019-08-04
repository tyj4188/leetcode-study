/* 
 * 
 * 项目名：	com.john.leetcode.part_1_10
 * 文件名：	Example_4
 * 模块说明：	
 * 修改历史：
 * 2019/8/4 - Administrator - 创建。
 */

package com.john.leetcode.part_1_10;

/**
 * 4. 寻找两个 有序 数组的中位数
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * @author Administrator
 * @date 2019/8/4
 */
public class Example_4 {

    public static void main(String[] args) {
        int[] nums1 = new int[]{2,3,4,5,6};
        int[] nums2 = new int[]{1};

        //System.out.println(findMedianSortedArrays_V1(nums1, nums2));
        //System.out.println(findMedianSortedArrays_V2(nums1, nums2));
        System.out.println(findMedianSortedArrays_V3(nums1, nums2));
    }

    /**
     * 遍历了所有的元素并且使用了新的数组
     *  时间复杂度为 O(n + m)
     *  空间复杂度为 O(n + m)
     *
     * 解题思路：遍历数组 A 和 B，合并为一个新的有序数组，并求取中位数
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays_V1(int[] nums1, int[] nums2) {

        int n = nums1.length, m = nums2.length;
        int[] newArr = new int[n + m];

        int i = 0, j = 0, total = 0;
        while(total != (n + m)) {
            // 数组 A 遍历完了就把剩余的 B 数组遍历
            if(i == n) {
                while(j != m) {
                    newArr[total ++] = nums2[j ++];
                }
                break;
            }
            // 数组 B 遍历完了就把剩余的 A 数组遍历
            if(j == m) {
                while(i != n) {
                    newArr[total ++] = nums1[i ++];
                }
                break;
            }

            // 哪个当前下标的元素小就放入哪个，并且下标 + 1
            // 最终合并成一个有序的合集
            if(nums1[i] < nums2[j]) {
                newArr[total ++] = nums1[i ++];
            } else {
                newArr[total ++] = nums2[j ++];
            }

        }

        if(total % 2 == 0) {
            return (newArr[total / 2 - 1] + newArr[total / 2]) / 2.0;
        } else {
            return newArr[total / 2];
        }

    }

    /**
     * 遍历到中位数所在的位置，不需要重新创建数组
     *  时间复杂度为 O( ((n + m) / 2) + 1 )
     *  空间复杂度为 O(1)
     *
     * 解题思路：
     *  先找出中位数所在的位置, 奇数为：(n + m) / 2，偶数为 (n + m) / 2 和 (n + m) / 2 + 1
     *  然后遍历 A 和 B，遍历到中位数的位置。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays_V2(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int len = n + m;

        // A 和 B 的下标
        int itrA = 0, itrB = 0;

        // 中位数左右两个数字
        int left = -1, right = -1;
        for(int i = 0; i < len / 2 + 1; i++) {
            // 每次循环都记录 right 的上一个数字
            left = right;
            // itrB >= m 判断防止 nums2 数组越界
            if(itrA < n && (itrB >= m || nums1[itrA] < nums2[itrB])) {
                right = nums1[itrA ++];
            } else {
                right = nums2[itrB ++];
            }

        }
        // 偶数
        if((len & 1) == 0) {
            return (left + right) / 2.0;
        }
        return right;
    }

    /**
     * 遍历 log(k) 次，log(k) = log( (n + m) / 2 ) = log( n + m )
     *  时间复杂度为 O( log(n + m) )
     *  空间复杂度为 O(1)
     *
     * 解题思路：使用查找 "第 k 小的数字" 算法
     *  解题 2 中，每循环一次都排除一个不符合的数字
     *  由于数组是有序的，那么可以直接一半一半的排除，每次可以排除 k/2 个数字。
     *
     *  两个数组 A/B 中， A[k/2] > B[k/2]，那么可以排除 B[1] B[2] B[3] B[k/2]，
     *  因为 A 中比 A[k/2] 小的数字有 k/2-1 个，假设 B[k/2] 前面的数字都比 A[k/2] 小，也只有 k/2-1 个，
     *  那么比 A[k/2] 小的数字就是 k - 2 个，也就是 A[k/2] 最多是 k - 1 小的数字，
     *  而比 A[k/2] 小的数更不可能是第 k 小的数了，所以前面的都可以排除掉。
     *
     *  每次排除之后只需要在剩下的里面查找第 k - (排除掉数量) 小的数字就可以。
     *  最终 k = 1 或者数组 A 或 数组 B 有一个已经全部出局，可以直接返回结果。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays_V3(int[] nums1, int[] nums2) {
        // 算出 k 的值
        int n = nums1.length, m = nums2.length;

        // 偶数计算中间两个值，奇数计算两次同样的值
        // n = 2, m = 3, (n + m + 1) = 6, (n + m + 2) = 7, leftK = 3, rightK = 3.5 = 3
        // n = 2, m = 2, (n + m + 1) = 5, (n + m + 2) = 6, leftK = 2.5 = 2, rightK = 3
        int leftK = (n + m + 1) / 2;
        int rightK = (n + m + 2) / 2;

        double left = v3_do_1(nums1, 0, n - 1, nums2, 0, m - 1, leftK);
        double right = v3_do_1(nums1, 0, n - 1, nums2, 0, m - 1, rightK);
        return (left + right) / 2;
    }

    /**
     *
     * @param nums1 数组 A
     * @param start1 A 开始下标
     * @param end1 A 结束下标
     * @param nums2 数组 B
     * @param start2 B 开始下标
     * @param end2 B 结束下标
     * @param k 第 K 小的数字
     * @return
     */
    public static double v3_do_1(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        // 因为传入的是下标，所以需要 + 1
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;

        // 保证如果有某个数组为空，则一定是 nums1
        if(len1 > len2) return v3_do_1(nums2, start2, end2, nums1, start1, end1, k);
        // 如果有个数组为空，直接从另外一个数组中找出下标为 start + k + 1 的即可，因为 start 不一定是下标 0
        if(len1 == 0) return nums2[start2 + k - 1];

        // k == 1 时，只需要返回两个数组中最小的那个
        if(k == 1) return nums1[start1] > nums2[start2] ? nums2[start2] : nums1[start1];

        // 找出当前数组中第 k/2 个数字的下标，因为 len 和 k / 2 都是数量，所以转换成下标需要 -1
        int t1 = start1 + Math.min(len1, k / 2) - 1;
        int t2 = start2 + Math.min(len2, k / 2) - 1;
        if(nums1[t1] > nums2[t2]) {
            // 如果数组 A 的 k / 2 大于 B 的 k / 2 那么就排除掉 B 中 start 到 t2 的数字
            // t2 + 1 = 排除掉包含 t2 的数字，下一次从 t2 + 1 开始计算
            // k - (t2 - start2 + 1), 这里的 + 1 是从下标值转为个数
            return v3_do_1(nums1, start1, end1, nums2, t2 + 1, end2, k - (t2 - start2 + 1));
        } else {
            return v3_do_1(nums1, t1 + 1, end1, nums2, start2, end2, k - (t2 - start2 + 1));
        }
    }


}
