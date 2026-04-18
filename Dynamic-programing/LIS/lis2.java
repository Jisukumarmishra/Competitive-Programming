package LIS;

import java.util.Arrays;

public class lis2 {
  public static void main(String[] args) {
    int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
     int n = arr.length;
     
    // call in the main
     int ans = 1;
    for (int i = 0; i < n; i++) {
         ans = Math.max(ans, rec(arr, i));
    }
    System.out.println(ans);



    // memo 
    int[] dp = new int[n+1];
    Arrays.fill(dp, -1);
    
    int memoans = 1;
    for (int  i=0; i<n; i++) {
      memoans = Math.max(memoans, memo(arr, i, dp));
    }
    System.out.println("Memoization Answer: " + memoans);

    // Tabo code 
    int tabAns = tabo(arr);
    System.out.println("Tabulation Answer: " + tabAns);


  }

  static int rec(int[] arr, int i) {
    if( i == 0) return 1;

    int ans = 1;

      for (int j=0; j<i; j++){
        if(arr[i]> arr[j]){
          ans = Math.max(ans, 1+rec(arr, j));
        }
      }
    return ans;
  }



  static int memo (int [] arr, int i, int [] dp ) {

  if (i ==0 ) {
    dp[i] = 1;
  }

  if(dp[i] != -1) {
    return dp[i];
  }

  int ans = 1;
  for (int j =0; j<i; j++) {
  if(arr[i] > arr[j]) 
    {
     ans = Math.max(ans, 1+memo(arr, j, dp));
    }
    dp[i] = ans;
  }
  return dp[i] = ans;
  }


  static int tabo (int [] arr) {
  int n = arr.length;
  int [] dp  = new int [n];
  for (int i =0; i<n; i++) {
  dp[i] = 1;
  }
  for (int i =0;  i<n; i++) {
    for (int j =0; j<i; j++) {
        if (arr[i] > arr[j]) {
          dp[i] = Math.max(dp[i],1+dp[j]);
        }
      }
  }

  int maxlis = 1;
  for(int len : dp) {
    maxlis = Math.max(maxlis, len);
  }

 return maxlis;
  }
}
