package Basic_Dp;
public class ClimbingStairs {
  public static void main(String[] args) {
    int n = 5; // n = 3-> 3 & n = 4 -> 5 ways
    System.out.println(coutWays(n));

    // memo
    int [] dp = new int [n+1];
    for(int i=0; i<=n;i++){// = islue lga because arra yki sizze n+1 hai 
      dp[i] = -1;
    }
    System.out.println(countWaysMamo(n, dp));
    // tabbleu
    System.out.println(CountTablu(n));
  }

  // rec.O(2^n)
  static int coutWays( int n) {
    if (n == 0) {
      return 1; // because if we stand on the ground then there is one way to stay there 
    }
    if (n <0) {
      return 0;
    }
     return coutWays(n-1)+coutWays(n-2);// if varation like 3, 4, 5, 6 tarhe se clamp kar sakte hai then same add the n-3, n-4, m-5,..
  }


  // memoization O(n)
  static int countWaysMamo(int n, int [] dp) {

    if (n==0) {
      return 1;
    }
    if (n <0 ){
      return 0;
    }
    if (dp[n] != -1){
      return dp[n];
    }
    return dp[n] = countWaysMamo(n-1, dp) + countWaysMamo(n-2, dp);
  }

  // tabulation Buttom Up -> small to large where mall is the your base case

  static int CountTablu (int n) {
  int [] dp = new int [n+1];// 0 to n so n+1
  dp[0] = 1;

  // tabulation
  for(int i =1; i<=n; i++) {
    if ( i==1 ) {
      dp[i] = dp[i-1];
    } else {
      dp[i] = dp[i-1]+dp[i-2];
    }
  }
  return dp[n];
  }
}
