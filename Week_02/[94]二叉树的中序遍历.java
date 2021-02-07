//给定一个二叉树的根节点 root ，返回它的 中序 遍历。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[1,3,2]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,2]
//输出：[2,1]
// 
//
// 示例 5： 
//
// 
//输入：root = [1,null,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 哈希表 
// 👍 852 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) {
            return res;
        }

        // 递归方式遍历
        //recursionTraversal(root, res);

        // 非递归方式遍历
        nonRecursionTraversal(root, res);
        return res;
    }

    private void recursionTraversal(TreeNode root, List<Integer> list) {
        if(root == null) {
            return;
        }

        recursionTraversal(root.left, list);
        list.add(root.val);
        recursionTraversal(root.right, list);
    }

    /**
     * 非递归方式遍历，利用栈的特性进行遍历
     * @param root
     */
    private void nonRecursionTraversal(TreeNode root, List<Integer> list) {
        Stack<TreeNode> stack = new Stack<>();

        // 遍历完成条件
        while(root != null || !stack.isEmpty()) {
            // 找最左节点开始中序遍历
            while(root != null) {
                stack.push(root);
                root = root.left;
            }

            // 当内层循环退出时，此时栈顶节点为最左节点(或者父节点)，根据 左->根->右 顺序进行遍历
            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
