//给定一个非空的整数数组，返回其中出现频率前 k 高的元素。 
//
// 
//
// 示例 1: 
//
// 输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
// 
//
// 示例 2: 
//
// 输入: nums = [1], k = 1
//输出: [1] 
//
// 
//
// 提示： 
//
// 
// 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。 
// 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。 
// 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。 
// 你可以按任意顺序返回答案。 
// 
// Related Topics 堆 哈希表 
// 👍 631 👎 0

import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return new int[0];
        }

        // 建立元素和频次的映射关系
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if(map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        // 使用小顶堆保存 k 个频率的元素
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b)-> {
            return map.get(a) - map.get(b);
        });
        for (Integer mk : map.keySet()) {
            if(heap.size() < k) {
                heap.offer(mk);
                // 替换堆顶元素，通过堆的调整保持前 k 个高频元素
            } else if(map.get(mk) > map.get(heap.peek())) {
                heap.poll();
                heap.offer(mk);
            }
        }

        int[] res = new int[k];
        for (int i = 0; i <k; i++) {
            res[i] = heap.poll();
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
