//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 8 
// -10 <= nums[i] <= 10 
// 
// Related Topics 回溯算法 
// 👍 593 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 是否已经使用
    private boolean[] vis;

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        // 当前排列结果
        List<Integer> temp = new ArrayList<Integer>();
        vis = new boolean[nums.length];
        // 排序以便重复数字相邻
        Arrays.sort(nums);

        // 回溯调用 idx 表示需要填入数字的位置
        backtrack(nums, res, 0, temp);
        return res;
    }

    public void backtrack(int[] nums, List<List<Integer>> res, int idx, List<Integer> temp) {
        // 终止条件，全部位置都填满，本次全排列完成
        if (idx == nums.length) {
            // 一次排列完成，添加结果
            res.add(new ArrayList<Integer>(temp));
            return;
        }

        // 考虑一次排列中某个位置,需要填入的数字
        for (int i = 0; i < nums.length; i++) {
            // 如果某个数已经选定，重复数字选择第一个填入（保证每次都是拿从左往右第一个未被填过的数字）
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                continue;
            }

            temp.add(nums[i]);
            vis[i] = true;
            backtrack(nums, res, idx + 1, temp);
            // 回溯取消上一步操作
            vis[i] = false;
            temp.remove(idx);
        }
        // 恢复状态（本例不需要）
    }
}
//leetcode submit region end(Prohibit modification and deletion)
