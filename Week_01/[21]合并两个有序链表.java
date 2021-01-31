//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [1,2,4], l2 = [1,3,4]
//输出：[1,1,2,3,4,4]
// 
//
// 示例 2： 
//
// 
//输入：l1 = [], l2 = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [], l2 = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 两个链表的节点数目范围是 [0, 50] 
// -100 <= Node.val <= 100 
// l1 和 l2 均按 非递减顺序 排列 
// 
// Related Topics 递归 链表 
// 👍 1506 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null && l2 != null) {
            return l2;
        }

        if(l1 != null && l2 == null) {
            return l1;
        }

        // 新链表头结点指针
        ListNode mergeHead = new ListNode(-1);
        // 新链表游标指针
        ListNode preIndex = mergeHead;

        while(l1 != null && l2 != null) {
            if(l1.val <= l2.val) {
                preIndex.next = l1;
                l1 = l1.next;
            } else {
                preIndex.next = l2;
                l2 = l2.next;
            }

            // 游标指针后移
            preIndex = preIndex.next;
        }

        preIndex.next = l1 == null ? l2 : l1;

        // 两个链表 l1 和 l2 都没有头结点，返回新链表第一个元素开始的链表
        return mergeHead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
