package UK;

public class unboundedKnap {
  public static void main(String[] args) {
   int [] val = {15,14,10,45,30};
   int [] wt = {2,5,1,3,4};
   int W = 7;
    System.out.println(UKS(val, wt, W));
  }

  static int UKS (int[] val, int[] wt, int W) {
    int n = val.length;
    int[][] dp = new int[n+1][W+1];

    for (int i =1; i<n+1; i++) {
      for(int j =1; j<W+1; j++){
        if (wt[i-1]<=j) { // think about it why we write the j here not W  ans is below
        dp[i][j] = Math.max(val[i-1] + dp[i][j-wt[i-1]],
        dp[i-1][j]);
        } else {
          dp[i][j] = dp[i-1][j];
        }
      }
    }
    printTable(dp);
    return dp[n][W];


  }
  static void printTable(int[][] dp) {
    for(int i=0; i<dp.length; i++) {
      for(int j=0; j<dp.length; j++){
       System.out.print(dp[i][j]+" ");
      }
      System.out.println();
    }
    System.out.println();
  }
}

//the anser is because ibn every iteration we chacking for the current capacity not total capacity 