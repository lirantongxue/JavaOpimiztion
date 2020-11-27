//You are given two non-empty linked lists representing two non-negative integer
//s. The digits are stored in reverse order, and each of their nodes contains a si
//ngle digit. Add the two numbers and return the sum as a linked list. 
//
// You may assume the two numbers do not contain any leading zero, except the nu
//mber 0 itself. 
//
// 
// Example 1: 
//
// 
//Input: l1 = [2,4,3], l2 = [5,6,4]
//Output: [7,0,8]
//Explanation: 342 + 465 = 807.
// 
//
// Example 2: 
//
// 
//Input: l1 = [0], l2 = [0]
//Output: [0]
// 
//
// Example 3: 
//
// 
//Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//Output: [8,9,9,9,0,0,0,1]
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in each linked list is in the range [1, 100]. 
// 0 <= Node.val <= 9 
// It is guaranteed that the list represents a number that does not have leading
// zeros. 
// 
// Related Topics 链表 数学 
// 👍 5292 👎 0

//Java：Add Two Numbers
package leetcode.editor.cn;

class P2AddTwoNumbers {
    public static void main(String[] args) {
        Solution solution = new P2AddTwoNumbers().new Solution();
        // TO TEST

        ListNode listNode1 = new P2AddTwoNumbers().new ListNode();
        ListNode listNode2 = new P2AddTwoNumbers().new ListNode();
        listNode1.val=2;
        listNode1.next = new P2AddTwoNumbers().new ListNode(4);
        listNode1.next.next = new P2AddTwoNumbers().new ListNode(3);

        listNode2.val = 5;
        listNode2.next = new P2AddTwoNumbers().new ListNode(6);
        listNode2.next.next = new P2AddTwoNumbers().new ListNode(2);
        ListNode listNode = solution.addTwoNumbers(listNode1, listNode2);
        while (listNode!=null){
            System.out.println(listNode.val);
            listNode=listNode.next;
        }
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

            ListNode dummyHead = new ListNode(-1);
            ListNode curr=dummyHead;
            ListNode p = l1, q = l2;
            int carry = 0;
            while (p != null || q != null) {
                int x = (p != null) ? p.val : 0;
                int y = (q != null) ? q.val : 0;
                int sum = carry + x + y;
                carry = sum / 10;//进位
                curr.next = new ListNode(sum % 10);
                curr = curr.next;// 前继指针curr 后移指向到 当前node节点(curr.next)。原理使用 curr 作为临时指针,进行新链表的关联。
                if (p != null) {
                    p = p.next;
                }
                if (q != null) {
                    q = q.next;
                }
            }
            if (carry > 0) {//假设相加大还于10，则继续进位 例如 20 30  40 则进位 1 2 3
                curr.next = new ListNode(carry);
            }
            return dummyHead.next;

        }
    }

    class ListNode {

        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}