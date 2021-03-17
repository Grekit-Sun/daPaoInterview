package com.yifan.dapaointerview.algorithm;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description:
 * @Author: ZhengXiang Sun
 * @Data: 2021-03-15
 */
public class Main {

    /**
     * 斐波那契数列（递归）
     *
     * @param n
     * @return
     */
    public int fibo1(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }

        return fibo1(n - 2) + fibo1(n - 1);
    }

    /**
     * 斐波那契数列（动态规划）
     *
     * @param n
     * @return
     */
    public int fibo2(int n) {
        if (n < 1) {
            return -1;
        }
        int[] f = new int[n + 1];
        f[1] = 1;
        f[2] = 1;

        for (int i = 3; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[n];
    }

    /**
     * 链表反转
     */
    public ListNode listReversal(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode next = null;
        ListNode pre = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }


    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 求出ViewGroupSum的总和
     *
     * @param view
     * @return
     */
    public int getViewGroupSum(View view) {
        int viewCnt = 0;
        if (null == view) {
            return 0;
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View child = ((ViewGroup) view).getChildAt(i);
                if (child instanceof ViewGroup) {
                    viewCnt += getViewGroupSum(child);
                } else {
                    viewCnt++;
                }
            }
        } else {
            viewCnt++;
        }
        return viewCnt;
    }

    /**
     * 创建二叉树
     *
     * @param array
     * @return
     */
    public List<TreeNode> createBinaryTree(int[] array) {
        LinkedList<TreeNode> list = new LinkedList<>();
        for (int a : array) {
            list.add(new TreeNode(a));
        }

        for (int parentIndex = 0; parentIndex < array.length / 2 - 1; parentIndex++) {
            list.get(parentIndex).left = list.get(parentIndex * 2 + 1);
            list.get(parentIndex).right = list.get(parentIndex * 2 + 2);
        }
        int lastParentIndex = array.length / 2 - 1;
        list.get(lastParentIndex).left = list.get(lastParentIndex * 2 + 1);
        //如果数组长度有右孩子才有右子树
        if (array.length % 2 == 1) {
            list.get(lastParentIndex).right = list.get(lastParentIndex * 2 + 2);
        }
        return list;
    }

    class TreeNode {
        int val;
        TreeNode right;
        TreeNode left;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 从上至下打印二叉树节点，同层次从左至右打印 （广度优先）
     *
     * @param root
     * @return
     */
    public ArrayList<Integer> breadFirst(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        TreeNode current = root;
        queue.offer(root);
        while (!queue.isEmpty()) {
            current = queue.poll();
            list.add(current.val);
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
        return list;
    }

    /**
     * 二分查找（要先排序）
     *
     * @param array
     * @param target
     * @return
     */
    public boolean binarySearch(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (target == array[mid]) {
                return true;
            } else if (target > array[mid]) {
                low = mid + 1;
            } else if (target < array[mid]) {
                high = mid - 1;
            }
        }
        return false;
    }
}
