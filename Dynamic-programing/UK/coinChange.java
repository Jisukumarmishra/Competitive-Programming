package UK;
public class coinChange {
  public static void main(String[] args) {
    int [] coin = {2, 5, 3, 6};
    int sum = 10;
    System.out.println(coinChanger(coin, sum));
  }

  static int coinChanger (int [] coin, int sum) {
    int n= coin.length;
    int [][] dp = new int[n+1][sum+1];
    // intialize - sum is zero
    // i- coins, j-sum/change
    for (int i=0 ; i<n+1; i++) {
      dp[i][0] = 1; // i kitne bhi ho sum ( j=0)  hai to 0 aayega

    }

    for (int j=1;j<sum+1; j++) { //j = 1because first intialze me i= 0 and j =0 both case cover ho ggaue hai 
    dp[0][j] = 0;
    }

    // tabo code
  //0(n*sum)
    for(int i=1; i<n+1; i++) {
      for(int j=1; j<sum+1; j++) {
       if (coin[i-1] <= j) {
        dp[i][j] = dp[i][j-coin[i-1]]+dp[i-1][j];
       } else {
        dp[i][j] = dp[i-1][j];
       }
      }
    } // here we can also a space optimization we can write 
    print(dp);
    return dp[n][sum];
  }

  static void print (int [][] dp ) {
    for(int i=0; i<dp.length; i++) {
      for(int j =0; j<dp[0].length; j++) {
        System.out.print(dp[i][j]+" ");
      }
      System.out.println();
    }
    System.out.println();
  }
 // space optimizey
    static int coinChangerSpaceOptimize (int [] coin, int sum) {
    int n= coin.length;
    int [] dp = new int[sum+1];
     // base case
    dp[0] = 1;
     for(int i=1; i<n+1; i++) {
      for(int j=1; j<sum+1; j++) {
        if (coin[i-1] <= j ) {
        dp[j] += dp[j-coin[i-1]];
        }
      }
    } // here we can also a space optimization we can write 
    return dp[sum];
  }


  
}
