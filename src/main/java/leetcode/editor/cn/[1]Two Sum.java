//Given an array of integers nums and an integer target, return indices of the t
//wo numbers such that they add up to target. 
//
// You may assume that each input would have exactly one solution, and you may n
//ot use the same element twice. 
//
// You can return the answer in any order. 
//
// 
// Example 1: 
//
// 
//Input: nums = [2,7,11,15], target = 9
//Output: [0,1]
//Output: Because nums[0] + nums[1] == 9, we return [0, 1].
// 
//
// Example 2: 
//
// 
//Input: nums = [3,2,4], target = 6
//Output: [1,2]
// 
//
// Example 3: 
//
// 
//Input: nums = [3,3], target = 6
//Output: [0,1]
// 
//
// 
// Constraints: 
//
// 
// 2 <= nums.length <= 103 
// -109 <= nums[i] <= 109 
// -109 <= target <= 109 
// Only one valid answer exists. 
// 
// Related Topics 数组 哈希表 
// 👍 9694 👎 0

//Java：Two Sum
package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class P1TwoSum {
    public static void main(String[] args) {
        Solution solution = new P1TwoSum().new Solution();
        // TO TEST
        System.out.println(Arrays.toString(solution.twoSum(new int[]{3,3}, 6)));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // 暴力遍历 O(n^2)
    class SolutionA {
        public int[] twoSum(int[] nums, int target) {
            int[] res = new int[2];
            if (nums.length == 0 || nums.length == 1) {
                return nums;
            }
            for (int i = 0; i < nums.length; i++) {
                for (int j = i+1; j < nums.length; j++) {
                    int val = target - nums[i];
                    if (nums[j] == val) {
                        res[0] = i;
                        res[1] = j;
                        break;
                    }
                }
            }
            return res;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    /** O(n)
     * @description: 用hash表。 利用key 存值，value 存下标的做法。 [3,3]   出现key相同的情况，直接覆盖key.
     * @author: liran
     * @date: 2021/7/17 下午6:59
     * @Param:
     * @return:
     */
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i <nums.length ; i++) {
                if(map.containsKey(target-nums[i])){
                        return new int[]{map.get(target-nums[i]),i};
                }
                map.put(nums[i], i);
            }
            return new int[0];
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}