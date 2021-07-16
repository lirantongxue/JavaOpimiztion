//Given head, the head of a linked list, determine if the linked list has a cycl
//e in it. 
//
// There is a cycle in a linked list if there is some node in the list that can 
//be reached again by continuously following the next pointer. Internally, pos is 
//used to denote the index of the node that tail's next pointer is connected to. N
//ote that pos is not passed as a parameter. 
//
// Return true if there is a cycle in the linked list. Otherwise, return false. 
//
//
// 
// Example 1: 
//
// 
//Input: head = [3,2,0,-4], pos = 1
//Output: true
//Explanation: There is a cycle in the linked list, where the tail connects to t
//he 1st node (0-indexed).
// 
//
// Example 2: 
//
// 
//Input: head = [1,2], pos = 0
//Output: true
//Explanation: There is a cycle in the linked list, where the tail connects to t
//he 0th node.
// 
//
// Example 3: 
//
// 
//Input: head = [1], pos = -1
//Output: false
//Explanation: There is no cycle in the linked list.
// 
//
// 
// Constraints: 
//
// 
// The number of the nodes in the list is in the range [0, 104]. 
// -105 <= Node.val <= 105 
// pos is -1 or a valid index in the linked-list. 
// 
//
// 
// Follow up: Can you solve it using O(1) (i.e. constant) memory? 
// Related Topics 哈希表 链表 双指针 
// 👍 1133 👎 0

//Java：Linked List Cycle
package leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

class P141LinkedListCycle {
    public static void main(String[] args) {
        Solution solution = new P141LinkedListCycle().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 判断链表是否有环。
     * 1.暴力循环，通过Set 特性 判断 是否出现重复节点。
     * 2.快慢指针，龟兔赛跑，如果是环形跑道。乌龟一定被套圈。
     */
    class Solution {
        public boolean hasCycle(ListNode head) {
            Set<ListNode> set = new HashSet<>();
            while (head.next != null) {
                if (!set.add(head)) {
                    return true;
                }
                head = head.next;
            }
            return false;
        }
    }

    /**
     * 快慢指针
     **/
    class SolutionA {
        public boolean hasCycle(ListNode head) {
            if(head == null || head.next == null){
                return false;
            }

            ListNode slow=head;
            ListNode fast=head.next;
            while (slow != fast){
                if(fast == null || fast.next == null){
                    return  false;
                }
                slow=head.next;
                fast=head.next.next;
            }
            return true;
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