//Given a binary tree, check whether it is a mirror of itself (ie, symmetric aro
//und its center). 
//
// For example, this binary tree [1,2,2,3,4,4,3] is symmetric: 
//
// 
//    1
//   / \
//  2   2
// / \ / \
//3  4 4  3
// 
//
// 
//
// But the following [1,2,2,null,3,null,3] is not: 
//
// 
//    1
//   / \
//  2   2
//   \   \
//   3    3
// 
//
// 
//
// Follow up: Solve it both recursively and iteratively. 
// Related Topics 树 深度优先搜索 广度优先搜索 
// 👍 1140 👎 0

//Java：Symmetric Tree
package leetcode.editor.cn;

import javaoptimization.ObserverDemo.QuickList;

import javax.swing.tree.TreeNode;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

class P101SymmetricTree {
    public static void main(String[] args) {
        Solution solution = new P101SymmetricTree().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public boolean isSymmetric(TreeNode root) {
//            if (root == null || (null == root.left && null == root.right)) {
//                return false;
//            }
//            Queue<TreeNode> queue = new LinkedList();
//            queue.add(root.left);
//            queue.add(root.right);
//            while (!queue.isEmpty()) {
//                TreeNode l = queue.poll();
//                TreeNode r = queue.poll();
//
//                /**
//                 * 如果左右都为空，则继续遍历
//                 */
//                if (null == l && null == r) {
//                    continue;
//                }
//
//                /**
//                 * 如果左右其中有一个为空，则返回false
//                 */
//                if (null == l || null == r) {
//                    return false;
//                }
//
//                /**
//                 * 如果左右子树的值不想等，  则返回false
//                 */
//                if (l.val != r.val) {
//                    return false;
//                }
//
//
//                queue.add(l.left);
//                queue.add(r.right);
//
//
//                queue.add(l.right);
//                queue.add(r.left);
//
//            }
//            return  true;

            if(root == null) {
                return true;
            }
            return  isSym(root.left,root.right);
        }


        public boolean isSym(TreeNode l,TreeNode r){
            if(l == null && r==null){
                return true;
            }
            if(l == null || r==null){
                return false;
            }
            return l.val == r.val && isSym(l.left, r.right) && isSym(l.right,r.left);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }




}