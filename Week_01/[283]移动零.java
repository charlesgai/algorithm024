//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 示例: 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针 
// 👍 931 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void moveZeroes(int[] nums) {
        int left = 0;
        int right = 0;

        while(right < nums.length) {
            if(nums[right] != 0) {
                // 不习惯使用 nums[left ++] = nums[right] 这样的写法
                nums[left] = nums[right];
                right ++;
                left ++;
            } else {
                right ++;
            }
        }

        while (left < nums.length) {
            nums[left ++] = 0;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
