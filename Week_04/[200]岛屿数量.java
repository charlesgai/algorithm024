//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] 的值为 '0' 或 '1' 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 997 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }

        if(grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];
        int count = 0;
        // 循环遍历每一个元素，采用BFS搜索周围元素，判断是否与之相邻
        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                if(grid[i][j] == '1' && !visited[i][j]) {
                    // BFS 标记当前元素周围的元素为已经访问
                    bfs(grid, i, j, visited);
                    count ++;
                }
            }
        }
        return count;
    }

    // BFS 搜索 i, j 元素周围的元素
    private void bfs(char[][] grid, int i, int j, boolean[][] visited) {
        // 分别定义上下左右坐标信息，原点在左上角
        int x[] = new int[]{0, 1, 0, -1};
        int y[] = new int[]{-1, 0, 1, 0};

        visited[i][j] = true;

        Queue<Integer> xQueue = new LinkedList<Integer>();
        Queue<Integer> yQueue = new LinkedList<Integer>();
        xQueue.offer(i);
        yQueue.offer(j);

        while(!xQueue.isEmpty()) {
            int curX = xQueue.poll();
            int curY = yQueue.poll();
            for(int qi=0; qi<4; qi++) {
                int newX = curX + x[qi];
                int newY = curY + y[qi];
                if(newX >=0 && newY >=0 && newX < grid.length && newY < grid[0].length && grid[newX][newY] == '1' && !visited[newX][newY]) {
                    xQueue.add(newX);
                    yQueue.add(newY);
                    visited[newX][newY] = true;
                }
            }
        }
    }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
