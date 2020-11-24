//Given an array nums of n integers, are there elements a, b, c in nums such tha
//t a + b + c = 0? Find all unique triplets in the array which gives the sum of ze
//ro. 
//
// Notice that the solution set must not contain duplicate triplets. 
//
// 
// Example 1: 
// Input: nums = [-1,0,1,2,-1,-4]
//Output: [[-1,-1,2],[-1,0,1]]
// Example 2: 
// Input: nums = []
//Output: []
// Example 3: 
// Input: nums = [0]
//Output: []
// 
// 
// Constraints: 
//
// 
// 0 <= nums.length <= 3000 
// -105 <= nums[i] <= 105 
// 
// Related Topics 数组 双指针 
// 👍 2764 👎 0

//Java：3Sum
package leetcode.editor.cn;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @description: 3sum 问题。
 * @author: liran
 * @date: 2020/11/23 下午3:39
 * @Param:
 * @return:
 */
class P15ThreeSum {
    public static void main(String[] args) {
        Solution solution = new P15ThreeSum().new Solution();
        // TO TEST
        solution.threeSum(new int[]{0,0,0});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            if(nums.length == 0 || nums.length == 1 ){
                return new ArrayList<>();
            }
            Arrays.sort(nums);
            List<List<Integer>> res=new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                List<List<Integer>> tw = twoSum(nums, i+1, 0-nums[i]);
                if(tw !=null && tw.size()>0){
                    for (List<Integer> integers : tw) {
                        integers.add(nums[i]);
                        res.add(integers);
                    }
                    while (i < nums.length && i + 1 <nums.length && nums[i] == nums[i + 1]) {
                        i++;
                    }
                }
            }
//            System.out.println(JSONObject.toJSON(res).toString());
            return res;
        }
        /**
         * @description:采用双指针
         * @author: liran
         * @date: 2020/11/24 上午10:14
         * @Param: [nums, start, target]
         * @return: java.util.List<java.util.List<java.lang.Integer>>
         */
        public List<List<Integer>> twoSum(int[] nums,int start, int target) {
            Arrays.sort(nums);
            List<List<Integer>> list = new ArrayList<>();
            int lo = start, hi = nums.length - 1;
            while (lo < hi) {
                int sum = nums[lo] + nums[hi];
                int left = nums[lo], right = nums[hi];
                //排序(从小到大)后 如果两数之和小于 target 则
                if (sum < target) {
                    //加while 循环是为了去重。[-1,1] 和 [1,-1] 问题
                    while (lo < hi && nums[lo] == left) {lo++;}
                }else if (sum > target){
                    while (lo < hi && nums[hi] == right) { hi--;}
                }else{
                    List<Integer> listsub =new ArrayList<>();
                    listsub.add(left);
                    listsub.add(right);
                    list.add(listsub);
                    while (lo < hi && nums[lo] == left) {lo++;}
                    while (lo < hi && nums[hi] == right) { hi--;}
                }
            }
//            System.out.println(JSONObject.toJSON(list).toString());
            return list;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}