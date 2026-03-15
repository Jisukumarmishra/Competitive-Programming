public class minimumPartationing {
  public static void main(String[] args) {
    int [] nums = {1, 1,3};
    // System.out.println(minPartationing(nums));
    System.out.println(minPartation(nums));
  }

  static int minPartationing (int [] arr) {
    int n= arr.length;
    // calc sum
    int sum =0;
    for(int i=0; i<n; i++) {
      sum += arr[i];
    }

    int w = sum/2;
    int [][] dp = new int [n+1][w+1];

    // buttom-up
    for(int i=1; i<n+1; i++) {
      for(int j=1; j<w+1;j++){
      if (arr[i-1] <= j) { // valid
        // included
        dp[i][j] = Math.max(arr[i-1]+dp[i-1][j-arr[i-1]], dp[i-1] [j]);
      } else {
        dp[i][j] = dp[i-1][j];
       }
      }
    }
    int sum1 = dp[n][w];
    int sum2 = sum-sum1;
    return Math.abs(sum1-sum2);
    
  }

  // code for when the minimum sum difference is equal to 0; above are the sum difference is miniumm ; and sum is parttion of the given array 

  static boolean minPartation (int [] arr) {
    int n = arr.length;
    int sum =0;
    for (int i=0; i<n; i++) {
       sum += arr[i];
    }

   if (sum % 2 != 0) {
    return false;
   }

    int w = sum/2;
    int [][] dp = new int [n+1][w+1];

    // buttom-up

    for(int i=1; i<n+1; i++) {
      for(int j=1; j<w+1; j++) {
        if(arr[i-1] <=j) {
          dp[i][j] = Math.max(arr[i-1] +dp[i-1][j-arr[i-1]], dp[i-1][j]);
        } else {
          dp[i][j] = dp[i-1][j];
        }
      }
    }
    int sum1 = dp[n][w];
    if (sum1 == w) {
      return true;
    } else {
    return false;
    }
  }
}