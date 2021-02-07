//给你二叉树的根节点 root ，返回它节点值的 前序 遍历。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[1,2,3]
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
//输出：[1,2]
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
// 进阶：递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 
// 👍 511 👎 0


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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) {
            return res;
        }

        // 递归遍历
        //recursionTraversal(root, res);

        // 非递归遍历
        noRecursionTraversal(root, res);

        return res;
    }

    private void  recursionTraversal(TreeNode root, List<Integer> list) {
        if(root == null) {
            return;
        }

        list.add(root.val);
        recursionTraversal(root.left, list);
        recursionTraversal(root.right, list);
    }

    private void noRecursionTraversal(TreeNode root, List<Integer> list) {
        Stack<TreeNode> stack = new Stack<TreeNode>();

        while(root != null || !stack.isEmpty()) {
            // 根据前序遍历 根->左->右 的特点，root 遍历后需要入栈以保持树的节点关系
            while(root != null) {
                list.add(root.val);
                stack.push(root);
                root = root.left;
            }

            // 出栈节点，获取右孩子，因为父节点和左孩子已经遍历.
            root = stack.pop();
            root = root.right;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
