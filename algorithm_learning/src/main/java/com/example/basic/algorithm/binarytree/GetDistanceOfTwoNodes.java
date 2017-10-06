package com.example.basic.algorithm.binarytree;

import com.example.basic.algorithm.Node;
import java.util.HashMap;
import java.util.Map;

/**
 * 求二叉树任意两个节点之间的距离（指的是最少边数
 */
public class GetDistanceOfTwoNodes {

  public static void main(String[] args) {
    Node testHead = new Node(6);
    testHead.left = new Node(1);
    testHead.right = new Node(12);
    testHead.left.left = new Node(1);
    testHead.left.right = new Node(3);
    testHead.right.left = new Node(10);
    testHead.right.right = new Node(13);

//    Map<Node, Integer> pathDistances = new HashMap<>();
//    getPathDistances(testHead, 0, pathDistances);
//
//    pathDistances.forEach((node, integer) -> System.out
//        .println(String.format("node: %d, distance is : %d", node.value, integer)));
    System.out.println(getDistanceOfTwoNodes2(testHead, testHead.left.right, testHead.right.right));
  }

  /**
   * 思路：寻找到它们的最近的公共祖先。然后套用公式Dis(n1,n2) = Dis(root,n1)+Dis(root,n2)-2*Dis(root,lca) lca为俩个节点的公共祖先
   */
  public static int getDistanceOfTwoNodes(Node head, Node n1, Node n2) {

    //在二叉树中找到两个节点的最近公共祖先（后序遍历）
    Node lca = BinaryTree.getNearestParentNode(head, n1, n2);
    //记录从根节点到当前节点的距离
    Map<Node, Integer> pathDistances = new HashMap<>();
    getPathDistances(head, 0, pathDistances);

    return (pathDistances.get(n1) + pathDistances.get(n2) - 2 * pathDistances.get(lca));
  }

  /**
   * 将每一个节点到根节点的距离都保存到map中，采用先序遍历
   */
  public static void getPathDistances(Node head, int level, Map<Node, Integer> pathDistances) {
    if (head == null) {
      return;
    }

    if (!pathDistances.containsKey(head)) {
      pathDistances.put(head, level);
    }
    getPathDistances(head.left, level + 1, pathDistances);
    getPathDistances(head.right, level + 1, pathDistances);
  }


  //实现二
  public static int getDistanceOfTwoNodes2(Node head, Node n1, Node n2) {

    //在二叉树中找到两个节点的最近公共祖先（后序遍历）
    Node lca = BinaryTree.getNearestParentNode(head, n1, n2);
    return countDistance(lca, n1) + countDistance(lca, n2);
  }

  /**
   * 计算一个祖先节点到子节点之间的距离
   */
  public static int countDistance(Node head, Node node) {
    if (head == node) {
      return 0;
    } else if (head == null) {
      return -1;
    }

    int left = countDistance(head.left, node);
    int right = countDistance(head.right, node);

    return left == -1 ? right + 1 : left + 1;
  }

}
