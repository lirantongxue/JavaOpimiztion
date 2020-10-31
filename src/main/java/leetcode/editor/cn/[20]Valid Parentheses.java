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

import org.apache.commons.lang3.StringUtils;

import java.util.Stack;

class P20ValidParentheses {
    public static void main(String[] args) {
        Solution solution = new P20ValidParentheses().new Solution();
        // TO TEST
        String s = "()[]{]";
        System.out.println(solution.isValid(s));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isValid(String s) {
            Stack<Character> stack = new Stack<>();
            String[] split = s.split(",");
            if (StringUtils.isBlank(s)) {
                return true;
            }
            for (String s1 : split) {
                if (stack.isEmpty()) {
                    continue;
                }
                Character peek = stack.peek();//获取栈顶元素
                if ((peek.charValue() == '(' && s1.equals(")")) || (peek.charValue() == '{' && s1.equals("}")) || (peek.charValue() == '[' && s1.equals("]"))) {
                    stack.pop();
                } else {
                    stack.push(Character.valueOf(s1.toCharArray()[0]));
                }
            }
            return stack.isEmpty();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}