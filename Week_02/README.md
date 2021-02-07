# 学习笔记

## 知识点总结

### 树，二叉树

树遍历方式：

+ 前序：根 -> 左 -> 右
+ 中序：左 -> 根 -> 右
+ 后序：左 -> 右 -> 根

> 注意：
>
> 1）三种遍历方式都有递归和非递归的遍历算法，**利用栈的特性和树的递归定义特点实现遍历**。
>
> 2）在遍历时，由于树的递归定义特性，**访问左右子树，而不是孩子节点**。

例如：

1）前序遍历（非递归方法）：

```java
private void nonRecursionTraversal(TreeNode root, List<Integer> list) {
    Stack<TreeNode> stack = new Stack<TreeNode>();
    while(root != null || !stack.isEmpty()) {
        // 访问根节点，并且入栈,以便找到右孩子(否则树关系断了)
        while(root != null) {
            // 先访问根（父）节点
            list.add(root.val);
            stack.push(root);
            root = root.left;
        }
        // 历史记录出栈，寻找右孩子
        root = stack.pop();
        // 访问右子树
        root = root.right;
    }
}
```



2）中序遍历（非递归方法）：

```java
private void nonRecursionTraversal(TreeNode root, List<Integer> list) {
    Stack<TreeNode> stack = new Stack();

    // 遍历结束条件
    while(root != null || !stack.isEmpty()) {
        // 根据中序遍历：左 -> 根 -> 右 的顺序 结合树的递归定义特性，先找问最左孩子
        while(root != null) {
            stack.push(root);
            root = root.left;
        }

        // 出栈条件
        root = stack.pop();
        list.add(root.val);
        root = root.right;
    }
}
```

注意：入栈出栈时机和条件，**结合树的递归定义特性使用栈进行遍历**。

+ 按层次遍历：

```java
public List<List<Integer>> levelOrder(Node root) {
    // 利用队列实现 DFS 遍历
    Queue<Node> queue = new LinkedList<Node>();
    List<List<Integer>> res = new ArrayList<List<Integer>>();

    if(root == null) {
        return res;
    }

    queue.add(root);
    while(!queue.isEmpty()) {
        List<Integer> layer = new ArrayList<>();

        // 需要先获得队列长度以便明确终止条件，因为本层次遍历过程中需要添加下一层的节点
        int size = queue.size();          
        for(int i=0; i<size; i++) {
            Node node = queue.remove();
            layer.add(node.val);
            queue.addAll(node.children);
        }
        res.add(layer);
    }

    return res;
}
```

> 注意：树的按层次遍历需要使用队列进行，**用队列的特性保存树的父节点和孩子节点的顺序关系**。

### 哈希表

解决链表访问速度慢的问题，访问速度可提高到 O(1) 。

> 注意：冲突和堆积的概念理解。

一般用于解决某些 key 和 value（数据） 的映射关系问题，**利用 hash 函数的特点，以 O(1) 的速度访问元素**。

### 堆

堆是一种抽象数据结构，与具体的实现无关。

> 注意：二叉堆是堆的一种实现，但是，**不是最优实现**。

通常用堆解决最值问题，**因为它可以用 O(1) 的速度访问到最大（最小）元素**。

### 图

有向图，无向图。图的权值表示。

注意：**图遍历时需要用 visited 保存节点是否访问，因为，图中可以存在环**。

## 算法实战总结

+ 二叉树的遍历（非递归算法），需要结合树的递归定义特性，遍历顺序以及栈的特点，注意访问顺序问题。

+ 树的按层次遍历，结合树的层次特性，使用队列保持层次的顺序关系，在没层次遍历时，确定当前层次的访问边界，因为在当前层次遍历时需要记录下层的节点，例如：

  ```java
  queue.add(root);
  while(!queue.isEmpty()) {
      List<Integer> layer = new ArrayList<>();
  
      // 需要先获得队列长度以便明确终止条件，因为本层次遍历过程中需要添加下一层的节点
      int size = queue.size();          
      for(int i=0; i<size; i++) {
          Node node = queue.remove();
          layer.add(node.val);
          // 如果不记录本层次 size 长度，下层次节点入队后会影响队列长度
          queue.addAll(node.children);
      }
      res.add(layer);
  }
  ```

+ 利用堆解决最值（k 个最值）问题时，**需要结合具体题目要求，考虑使用大顶堆和小顶堆解题**。要充分理解堆的特性，就是访问最值速度为 O(1) 。

