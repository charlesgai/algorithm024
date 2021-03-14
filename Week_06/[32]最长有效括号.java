//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：s = "(()"
//输出：2
//解释：最长有效括号子串是 "()"
// 
//
// 示例 2： 
//
// 
//输入：s = ")()())"
//输出：4
//解释：最长有效括号子串是 "()()"
// 
//
// 示例 3： 
//
// 
//输入：s = ""
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3 * 104 
// s[i] 为 '(' 或 ')' 
// 
// 
// 
// Related Topics 字符串 动态规划 
// 👍 1213 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestValidParentheses(String s) {
        if(s == null || s.length() < 2) {
            return 0;
        }

        // 使用 dp 求解
        return longestWithDp(s);

        // 利用栈求解
        //return longestWithStack(s);
    }

    private int longestWithDp(String s) {
        int max = 0;

        // 到第 i 个字符结尾时,最长有效括号长度
        int[] dp = new int[s.length()];

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                // s(i-1) 为 '(' 时
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                // 当 s[i-1] 为 ')' 且 s[i-dp[i-1] - 1] 为 '('，那么 dp[i] = dp[i-1] + 2 + dp[i-dp[i-1]-2]
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

    private int longestWithStack(String s) {
        int max = 0;

        Stack<Integer> stack = new Stack<Integer>();

        // 初始值,
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            // 利用栈的特性，记录最近一次需要开始的下标
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                // 一个合法的 ')'
                stack.pop();
                // 栈空，记录当前下标
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    // 不空，更新最大长度
                    max = Math.max(max, i - stack.peek());
                }
            }
        }

        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
