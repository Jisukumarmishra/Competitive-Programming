package UK;
public class RodCutting {
  public static void main(String[] args) {
    int [] length = {1, 2, 3, 4, 5, 6, 7,8};
    int[] price = {1, 5, 8, 9, 10, 17, 17, 20};
    int totalRod = 8;
    System.out.println(RodCutting(length, price, totalRod));
  }

  static int RodCutting (int [] len, int[] price, int totalRod) {
    //weight=>length, value=>price, W =>totalRod
    int n= price.length;
    int [][] dp = new int [n+1][totalRod+1];

    for (int i=0; i<n+1; i++) {
      for (int j=0;j<totalRod+1;j++) {
        if( i==0 || j ==0 ) {
          dp[i][j] =0;
        }
      }
    }

    for (int i =1; i<n+1; i++) {
      for (int j=1; j<totalRod+1; j++){
        if(len[i-1] <= j ) {
          // unbouded knapsack dp[i][j]=Math.max(val[i-1]+dp[i][j-wt[i-1]],dp[i-1][j]);
        dp[i][j] = Math.max(price[i-1]+dp[i][j-len[i-1]], dp[i-1][j]);
        } else {
          dp[i][j] = dp[i-1][j];
        }
      }
    }
    return dp[n][totalRod];

  }
}
