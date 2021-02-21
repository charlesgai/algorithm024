# 学习笔记

## 递归(recursion)

自己调用自己，主要用于解决 **重复性** 问题和 **自相似性** 问题。

### 递归泛模板

```java
public void recursion(int level, int param) {
    // 终止条件（terminator）
    // 注意：终止条件并非与层次相关，根据实际问题分析即可
    if(level > MAX_LEVEL) {
        // 处理终止结果 process result

        return ;
    }

    // 处理当前逻辑（process current logic），也就是重复执行的部分
    process(level, param);

    // 调用下一层（drill down）
    recursion(level, param);

    // 恢复当前状态（restore current status），清除临时变量等收尾操作
}
```

> 注意：
>
> 1）终止条件不一定与层次相关，结合实际问题进行分析，得出递归调用终止条件，防止无限递归。
>
> 2）如果遇到复杂问题，可以采用自顶向下的方法，抽象出重复性问题，进行递归调用，将参数变化和初始条件等放在主控函数中，增加易懂性、易读性。

### 递归解题关键

**不要进行人肉递归，解题关键是正确分析出重复子问题**。

## 分治、回溯

分治和回溯是特殊的递归，**本质上还是递归**，关键仍然是找重复性子问题（最近相关，最优相关）。

### 分治与递归的不同

分治在最后需要 **组合中间结果**。

### 分治泛模板

```java
public int divide_conquer(Problem problem) {
    // 终止条件
    if(problem == null) {
        // 处理最后问题
        int res = process_last_result();
        return res;
    }

    // 分解子问题
    subProblem = split_problem(problem);

    // 求解子问题结果（递归）
    result_0 = divide_conquer(subProblem);
    result_1 = divide_conquer(subProblem);
    // ...

    // 组合子问题结果
    result = process_result(result_0, result_1 ...);
    
    // 恢复中间状态，清理临时变量，收尾工作

    return result;
}
```

### 回溯与递归的不同

回溯（**试错思想**）与递归的不同，在于递归查找最优解（试错），如果发现不是最优解（发现错误），需要回退（**取消上一步或几步**）。

### 解题关键

分治、回溯的解题关键：

1）还是 **如何拆解子问题（具体问题具体分析）**。

2）中间结果的质量把控。