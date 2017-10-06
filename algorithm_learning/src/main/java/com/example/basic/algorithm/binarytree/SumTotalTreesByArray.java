package com.example.basic.algorithm.binarytree;

import com.example.basic.algorithm.Node;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lichao on 2017/9/24.
 */
public class SumTotalTreesByArray {

  public static void main(String[] args) {

  }

  /**
   * 以某个节点（i）作为头节点，i的左子树有i-1个节点，所以有num(i-1)种可能结构,i的右子树有n-i个节点，所以有num(n-i)种可能结构
   * 故以i为头节点的可能结构有num(i-1)*num(n-i)种
   */
  public static int numTrees(int n) {
    if (n < 2) {
      return 1;
    }

    int[] num = new int[n + 1];
    num[0] = 1;

    for (int i = 1; i < n + 1; i++) {
      for (int j = 1; j < i + 1; j++) {
        num[i] += num[j - 1] * num[i - j];
      }
    }

    return num[n];
  }


  /**
   * 进阶：输入n返回假设可能的二叉树结构有M种，请返回M个二叉树的头节点，每一颗二叉树代表一种可能的结构
   */
  public static List<Node> generateTrees(int n) {
    return generate(1, n);
  }

  public static List<Node> generate(int start, int end) {
    List<Node> res = new LinkedList<>();

    if (start > end) {
      res.add(null);
    }

    Node head = null;
    for (int i = start; i < end + 1; i++) {
      head = new Node(i);
      List<Node> lsub = generate(start, i - 1);
      List<Node> rsub = generate(i + 1, end);

      for (Node left : lsub) {
        for (Node right : rsub) {
          head.left = left;
          head.right = right;
          //这里得深度拷贝一下，不然每一次引用变换都会覆盖之前的结果
          res.add(cloneTree(head));
        }
      }
    }

    return res;
  }

  public static Node cloneTree(Node head) {
    if (head == null) {
      return null;
    }

    Node res = new Node(head.value);
    res.left = cloneTree(head.left);
    res.right = cloneTree(head.right);
    return res;
  }

}
