//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
// 
//
// 示例 2： 
//
// 
//输入：height = [4,2,0,3,2,5]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 0 <= n <= 3 * 104 
// 0 <= height[i] <= 105 
// 
// Related Topics 栈 数组 双指针 动态规划 
// 👍 1975 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /** 双指针算法 */
    public int trap(int[] height) {
        if(height == null && height.length == 0) {
            return  0;
        }

        int res = 0;

        int maxLeft = 0;
        int maxRight = 0;

        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            // 水的容量由高度小的值决定
            if(height[left] <= height[right]) {
                // 下一个高度小于最高值才能留住水，否则不能留住雨水,更新高度
                if(height[left] >= maxLeft) {
                    maxLeft = height[left];
                } else {
                    res += (maxLeft - height[left]);
                }
                left ++;
                // 右侧同理
            } else {
                if(height[right] >= maxRight) {
                    maxRight = height[right];
                } else {
                    res += (maxRight - height[right]);
                }
                right --;
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
