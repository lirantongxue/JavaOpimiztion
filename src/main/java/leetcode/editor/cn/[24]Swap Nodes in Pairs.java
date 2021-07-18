//Given a linked list, swap every two adjacent nodes and return its head. You mu
//st solve the problem without modifying the values in the list's nodes (i.e., onl
//y nodes themselves may be changed.) 
//
// 
// Example 1: 
//
// 
//Input: head = [1,2,3,4]
//Output: [2,1,4,3]
// 
//
// Example 2: 
//
// 
//Input: head = []
//Output: []
// 
//
// Example 3: 
//
// 
//Input: head = [1]
//Output: [1]
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the list is in the range [0, 100]. 
// 0 <= Node.val <= 100 
// 
// Related Topics 递归 链表 
// 👍 972 👎 0

//Java：Swap Nodes in Pairs
package leetcode.editor.cn;
 class P24SwapNodesInPairs{
    public static void main(String[] args) {
        Solution solution = new P24SwapNodesInPairs().new Solution();
        // TO TEST
    }
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
    public ListNode swapPairs(ListNode head) {
// 已有的链表加一个头部 head node
        ListNode resultHead = new ListNode();
        resultHead.next = head;

        // curNode 遍历链表时用
        ListNode curNode = resultHead;

        // 开始遍历链表
        while(curNode != null && curNode.next != null && curNode.next.next != null) {
            ListNode  f = curNode;
            ListNode  s = curNode.next;
            ListNode  t = s.next;

            // 两两交换链表结点
            f.next = t;
            s.next = t.next;
            t.next = s;

            // 标杆位后移2位
            curNode = curNode.next.next;
        }
        return resultHead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
  public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}