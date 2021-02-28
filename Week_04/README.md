# 学习笔记

## 深度优先、广度优先搜索

### 深度优先搜索

深度优先本质上也是递归的一种，以深度优先进行的遍历。

+ 递归写法

```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> allResults = new ArrayList<>();
    if (root == null) {
        return allResults;
    }
    // 初始化递归调用
    travel(root, 0, allResults);
    return allResults;
}

// DFS 模板
private void travel(TreeNode root, int level, List<List<Integer>> results) {
    // 终止条件
    if (results.size() == level) {
        results.add(new ArrayList<>());
    }
    // 处理逻辑
    results.get(level).add(root.val);
    
    // 向下递归
    if (root.left != null) {
        travel(root.left, level + 1, results);
    }
    if (root.right != null) {
        travel(root.right, level + 1, results);
    }
    // 状态恢复，清理临时变量等
}
```

+ 非递归写法

```java
public void dfs(TreeNode root) {
    // 记录节点是否访问
    Map<Integer, Integer> visited = new HashMap<>();

    if(root == null) {
        return ;
    }

    Stack<TreeNode> stack = new Stack<TreeNode>();
    stack.push(root);

    // 显式使用栈实现非递归
    while (!stack.isEmpty()) {
        TreeNode node = stack.pop();
        if(visited.containsKey(node.val)) {
            continue;
        } else {
            visited.put(node.val, 1);
        }

        for(int i=node.children.size()-1; i>=0; i--) {
            stack.push(node.children.get(i));
        }
    }

}
```

### 广度优先算法

按照广度优先进行搜索遍历，类似于树的层次遍历。

```java
// 树节点定义
public class TreeNode { 
    int val;    
    TreeNode left;    
    TreeNode right;    
    TreeNode(int x) {
        val = x;
    }
}

// 树的按层次遍历(BFS)
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> allResults = new ArrayList<>();
    if (root == null) {
        return (allResults);
    }
    Queue<TreeNode> nodes = new LinkedList<>();
    nodes.add(root);
    while (!nodes.isEmpty()) {
        // 记录本层次终止条件
        int size = nodes.size();
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            TreeNode node = nodes.poll();
            results.add(node.val);
            // 添加下一层次节点
            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
        }
        allResults.add(results);
    }
    return (allResults);
}
```

### 注意事项

+ 广度优先注意当前层次的终止条件
+ 图结构中需要记录是否访问（因为 **图存在环**）

## 贪心算法

贪心算法在每一步都选择当前状态下的最优解，从而希望选择出的解也是全局最优解。

注意：**与动态规划不同之处是它只关心当前状态的最优解，并且不回退**。

## 二分查找

二分查找是提高查找效率的一种算法，时间复杂度为 O(logN)。

注意使用二分查找的前提条件：

+ 单调（单调递增或单调递减）
+ 存在上下边界
+ 可以根据索引访问

> 有些问题可以通过排序使用二分法