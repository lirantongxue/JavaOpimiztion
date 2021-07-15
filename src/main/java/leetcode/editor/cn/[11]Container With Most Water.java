//Given n non-negative integers a1, a2, ..., an , where each represents a point 
//at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of
// the line i is at (i, ai) and (i, 0). Find two lines, which, together with the x
//-axis forms a container, such that the container contains the most water. 
//
// Notice that you may not slant the container. 
//
// 
// Example 1: 
//
// 
//Input: height = [1,8,6,2,5,4,8,3,7]
//Output: 49
//Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,
//3,7]. In this case, the max area of water (blue section) the container can conta
//in is 49.
// 
//
// Example 2: 
//
// 
//Input: height = [1,1]
//Output: 1
// 
//
// Example 3: 
//
// 
//Input: height = [4,3,2,1,4]
//Output: 16
// 
//
// Example 4: 
//
// 
//Input: height = [1,2,1]
//Output: 2
// 
//
// 
// Constraints: 
//
// 
// n == height.length 
// 2 <= n <= 105 
// 0 <= height[i] <= 104 
// 
// Related Topics 贪心 数组 双指针 
// 👍 2609 👎 0

//Java：Container With Most Water
package leetcode.editor.cn;

class P11ContainerWithMostWater {
    public static void main(String[] args) {
        Solution solution = new P11ContainerWithMostWater().new Solution();
        // TO TEST
        System.out.println(solution.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    /** 向内加逼方法，时间复杂度 O(n) **/
    class SolutionA {
        public int maxArea(int[] height) {
            int max = 0;
            for (int i = 0, j = height.length - 1; i < j;) {
                int minxHeight = height[i] < height[j] ? height[i++] : height[j--];
                int area = (j-i + 1) * minxHeight;
                max = Math.max(max, area);
            }
            return max;
        }
    }

    /** 穷举法，时间复杂度 O(n^2) **/
    class Solution {
        public int maxArea(int[] height) {
            int max = 0;
            for (int i = 0; i < height.length; i++) {
                for (int j = 1; j <height.length ; j++) {
                  int hegintMin=  height[i] < height[j] ?   height[i] : height[j];
                  max=Math.max(max,(j-i)*hegintMin);
                }
            }
            return max;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}