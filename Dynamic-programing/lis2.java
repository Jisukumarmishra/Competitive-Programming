public class lis2 {
  public static void main(String[] args) {
    int [] nums = { 5, 3, 2, 1, 4};
    
  }

  static int lis (int [] nums, int i) {
   if (i == nums.length) {
    return Integer.MIN_VALUE;
   }

   int ans = 1;
   for (int i =0; i<n; i++) {
    for (int j= 0; j<i; j++) {
      if (nums[i] > nums[j]) {
      ans = Math.max (ans, lis(nums, i+1));
      }
    }
    return ans;
   }
  }
}
