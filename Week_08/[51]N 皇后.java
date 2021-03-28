//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。 
//
// 
// 
// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 4
//输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[["Q"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 9 
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。 
// 
// 
// 
// Related Topics 回溯算法 
// 👍 811 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<String>> solveNQueens(int n) {
        int[] queens = new int[n];

        List<List<String>> results = new ArrayList<>();
        // 使用位运算解决
        solveNQueens(results, queens, n, 0, 0, 0, 0);

        return results;
    }

    /**
     * int n：总行数
     * int row：当前行数
     * int columns：不可选的列
     * int pie：不可选的左斜边
     * int na：不可选的右斜边
     */
    public void solveNQueens(List<List<String>> results, int[] queens, int n, int row, int columns, int pie, int na) {
        if (row == n) {
            results.add(generateString(queens));
            return;
        }

        int availableLocations = ((1 << n) - 1) & (~(columns | pie | na));

        pie <<= 1;
        na >>= 1;
        // 开始检查每个可选的位置
        while (availableLocations != 0) {
            int position = availableLocations & (-1 * availableLocations);

            int columnNum = Integer.bitCount(position - 1);
            // 将这个位置添加到记录数组中
            queens[row] = columnNum;
            availableLocations = availableLocations & (availableLocations - 1);
            // 沿着这个位置向下搜索
            solveNQueens(results, queens, n,
                    row + 1,
                    columns | position, pie | position << 1,
                    na | position >> 1);
        }
    }

    // 生成字符串
    public List<String> generateString(int[] queens) {
        List<String> result = new ArrayList<>();
        for (int i : queens) {
            char[] chars = new char[queens.length];
            Arrays.fill(chars, '.');
            chars[i] = 'Q';
            result.add(String.valueOf(chars));
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
