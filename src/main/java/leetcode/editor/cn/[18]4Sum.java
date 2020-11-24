//Given an array nums of n integers and an integer target, are there elements a,
// b, c, and d in nums such that a + b + c + d = target? Find all unique quadruple
//ts in the array which gives the sum of target. 
//
// Notice that the solution set must not contain duplicate quadruplets. 
//
// 
// Example 1: 
// Input: nums = [1,0,-1,0,-2,2], target = 0
//Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
// Example 2: 
// Input: nums = [], target = 0
//Output: []
// 
// 
// Constraints: 
//
// 
// 0 <= nums.length <= 200 
// -109 <= nums[i] <= 109 
// -109 <= target <= 109 
// 
// Related Topics 数组 哈希表 双指针 
// 👍 677 👎 0

//Java：4Sum
package leetcode.editor.cn;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class P18FourSum {
    public static void main(String[] args) {
        Solution solution = new P18FourSum().new Solution();
        // TO TEST
        solution.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {

            Arrays.sort(nums);
            return nSum(nums, 4, 0, target);
        }

        /**
         * @description: nSum 问题，n >2 递归 等于用基本逻辑 使用双指针必须要排序
         * @author: liran
         * @date: 2020/11/24 上午11:12
         * @Param: [nums, n, target]
         * @return: java.util.List<java.util.List < java.lang.Integer>>
         */
        public List<List<Integer>> nSum(int[] nums, int n, int start, int target) {
            List<List<Integer>> res = new ArrayList<>();
            int sz = nums.length;
            if (n < 2 || n > sz) {
                return res;
            } else if (n == 2) {
                int lo = start, hi = nums.length - 1;
                while (lo < hi) {
                    int left = nums[lo], right = nums[hi];
                    int sum = nums[lo] + nums[hi];
                    if (sum < target) {
                        //去重
                        while (lo < hi && nums[lo] == left) {
                            lo++;
                        }
                    } else if (sum > target) {
                        //去重
                        while (lo < hi && nums[hi] == right) {
                            hi--;
                        }
                    } else {
                        List<Integer> list = new ArrayList<>();
                        list.add(left);
                        list.add(right);
                        res.add(list);
                        while (lo < hi && nums[lo] == left) {
                            lo++;
                        }
                        while (lo < hi && nums[hi] == right) {
                            hi--;
                        }
                    }
                }
            } else {
                for (int i = start; i < sz; i++) {
                    List<List<Integer>> lists = nSum(nums, n - 1, i + 1, target - nums[i]);
                    if (lists != null && !lists.isEmpty()) {
                        for (List<Integer> list : lists) {
                            list.add(nums[i]);
                            res.add(list);
                        }
                        while (i < sz - 1 && i + 1 < sz && nums[i] == nums[i + 1]) {
                            i++;
                        }
                    }
                }
            }
//            System.out.println(JSONObject.toJSON(res).toString());
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}