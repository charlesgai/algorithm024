# 学习笔记

## 待解答问题

+ **栈的应用（例如：柱状图面积问题）不看解析根本会想不出来，往老师帮助，有什么办法针对性的提高？**

## 知识点理解总结

### 跳表的理解

从 LinkedList 发展而来，为了解决 lookup 速度 O(n) 的问题，个人理解就是对 LinkList 进行加索引，然后对索再加索引。

### 栈的应用

在使用栈解题时，关键需要考虑入栈、出栈操作与题目的逻辑关系。

## 算法实战问题总结

+ 循环边界问题，对于数组的边界判断不清晰，需要加强这方面的思考和练习，例如：283-移动零元素问题：

  ```java
  // 注意细节：如果使用 nums.length 就使用 < 号，如果使用 nums.length-1 使用 <= 判断
  while (right < nums.length) {
      if(nums[right] != 0) {
          nums[left] = nums[right];
          right ++;
          left ++;
      } else {
          right ++;
      }
  }
  ```

+ 关于 ++ 操作符的使用问题

  ```java
  while (right < nums.length) {
      if(nums[right] != 0) {
          // 个人不习惯 nums[left++] = nums[right]; 的方式
          nums[left] = nums[right];
          right ++;
          left ++;
      } else {
          right ++;
      }
  }
  ```

+ 栈的应用问题，需要专项训练，感觉是自己的弱项，需要加强练习