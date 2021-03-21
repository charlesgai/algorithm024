# 学习笔记

## 剪枝

是对递归、搜索的优化，可以优化掉重复的计算和次优解

+ **重复计算优化**：采用记录中间计算结果避免重复的计算
+ **次优解优化**：通过一定条件进行判断、评估选择代价较小分支进行迭代

## 双向 BFS

双向 BFS 就是对 BFS 的剪枝，是对 BFS 中那些次优解的优化，缩小搜索范围的一种优化

双向 BFS 模板：

```java
public int doubleBfs(String beginWord, String endWord, List<String> wordList) {
    
    // 记录已访问节点
    Set<String> visited = new HashSet<>();
    // 1）创建两端集合（或使用优先队列），并初始化两端集合数据
    Set<String> startVisited = new HashSet<>();
    startVisited.add(beginWord);
    Set<String> endVisited = new HashSet<>();
    endVisited.add(endWord);

    // 3) 双向 BFS，左右交替扩散
    while (!startVisited.isEmpty() && !endVisited.isEmpty()) {
        // 优先选择代价较小者进行搜索（一直保持 start 最小）
        if (startVisited.size() > endVisited.size()) {
            Set<String> temp = startVisited;
            startVisited = endVisited;
            endVisited = temp;
        }

        
        // 做搜索的逻辑，此时更新 startVisited 集合(更新代价最小集合)
        doSomething();
                
    }

    // 返回最终结果
    return result;
}
```

## 启发式搜索

启发式搜索是 **优化掉次优解的一种剪枝算法**，并且需要 **使用启发函数来评估** 哪些节点不是问题的解。

**启发函数**：h(n) ，返回一个非负实数，代表当前分支（节点）到目标（终点）的代价（消耗、费用等）。