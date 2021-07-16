//Given a string s containing just the characters '(', ')', '{', '}', '[' and ']
//', determine if the input string is valid. 
//
// An input string is valid if: 
//
// 
// Open brackets must be closed by the same type of brackets. 
// Open brackets must be closed in the correct order. 
// 
//
// 
// Example 1: 
//
// 
//Input: s = "()"
//Output: true
// 
//
// Example 2: 
//
// 
//Input: s = "()[]{}"
//Output: true
// 
//
// Example 3: 
//
// 
//Input: s = "(]"
//Output: false
// 
//
// Example 4: 
//
// 
//Input: s = "([)]"
//Output: false
// 
//
// Example 5: 
//
// 
//Input: s = "{[]}"
//Output: true
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 104 
// s consists of parentheses only '()[]{}'. 
// 
// Related Topics 栈 字符串 
// 👍 1943 👎 0

//Java：Valid Parentheses
package leetcode.editor.cn;


import org.apache.commons.lang.StringUtils;

import java.util.Stack;

class P20ValidParentheses {
    public static void main(String[] args) {
        Solution solution = new P20ValidParentheses().new Solution();
        // TO TEST
        String s = "({[]})";
        System.out.println(solution.isValid(s));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isValid(String s) {
            Stack<Character> stack = new Stack<>();
            if (StringUtils.isBlank(s)) {
                return true;
            }
            for (char s1 : s.toCharArray()) {
                if (stack.isEmpty()) {
                    stack.push(Character.valueOf(s1));
                    continue;
                }
                char peek = stack.peek();//获取栈顶元素
                if ((peek == '(' &&  s1== ')') || (peek == '{' && s1=='}') || (peek == '[' && s1==']')) {
                    stack.pop(); //弹出
                } else {
                    stack.push(s1); //压栈
                }
            }
            return stack.isEmpty();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}