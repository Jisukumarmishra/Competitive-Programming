import java.util.*;
public class minarrayJumps {
  public static void main(String[] args) {
    int [] nums = {2,3,1,1,4};
    System.out.println(minJump(nums));
  }

  static int minJump (int [] nums) {
    int n= nums.length;
    int [] dp = new int[n];
    Arrays.fill(dp, -1);
    dp[n-1] =0; // intilazize for last index or base case or sabse choothi problem

    for (int i =n-2; i>=0; i--) {
     int steps = nums[i];
     int ans = Integer.MAX_VALUE;
     for(int j=i+1; j<=i+steps && j<n; j++) { // i pe khare hai to j = i+1 ho jayega na 
      if (dp[j] != -1) { // valid case   
      ans = Math.min(ans, dp[j]+1);
      }
     }
     if (ans != Integer.MAX_VALUE) {
      dp[i] = ans;
     }
    }
    // dp[i] means i to n-1 me kitne steps and we find the 0 to n-1 me kitne steps show dp[0] is the answerr
    print(dp);
  return dp[0];  
  }

  static void print (int [] dp ) {
    for(int i =0; i<dp.length; i++) {
      System.out.print(dp[i]+" ");
    }
    System.out.println();
  }
}
