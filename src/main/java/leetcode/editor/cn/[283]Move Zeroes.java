//Given an array nums, write a function to move all 0's to the end of it while m
//aintaining the relative order of the non-zero elements. 
//
// Example: 
//
// 
//Input: [0,1,0,3,12]
//Output: [1,3,12,0,0] 
//
// Note: 
//
// 
// You must do this in-place without making a copy of the array. 
// Minimize the total number of operations. 
// Related Topics 数组 双指针 
// 👍 875 👎 0

//Java：Move Zeroes
package leetcode.editor.cn;
 class P283MoveZeroes{
    public static void main(String[] args) {
        Solution solution = new P283MoveZeroes().new Solution();
        // TO TEST
        solution.moveZeroes(new int [] {0,1,0,3,12});
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void moveZeroes(int[] nums) {
        int index=0;
        int  val=0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] !=val){
                nums[index]=nums[i];
                index++;
            }
        }
        for( ;index<nums.length;index++){
            nums[index]=val;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}