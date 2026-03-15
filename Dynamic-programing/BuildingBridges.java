import java.util.*;
public class BuildingBridges {
  public static void main(String[] args) {
    int n =4;
    // System.out.println(catalanRecur(n));

    // memo
    
    // int [] dp = new int [n+1];
    //   Arrays.fill(dp, -1);
    
    // System.out.println(catalanMemo(n, dp));
    
    System.out.println(catlanTabo(n));
  }

  static int catalanRecur (int n) {
    if (n ==0 || n ==1 ) {
      return 1;
    }
    int ans =0; //Cn
    for (int  i=0; i<=n-1; i++ ) {
      ans += catalanRecur(i)*catalanRecur(n-i-1);
    }
    return ans;
  }

  static int catalanMemo (int n, int [] dp) {
  // base case
  if (n == 0 || n ==1 ) {
    return 1;
  }

  if (dp[n] != -1 ) {
  return dp[n];
  }

  int ans = 0;
  for (int i =0; i<=n-1; i++) {
    ans += catalanMemo(i, dp) * catalanMemo(n-i-1, dp);
  }

  dp[n] = ans;
  return dp[n];
  }


  // tabo code 
  //O(n*n)

  static int catlanTabo (int n) {
   int [] dp = new int [n+1];

   // intilization
   dp[0] = 1;
   dp[1] = 1;

   // tabo code 
   for (int i=2; i<=n; i++) {
    for (int j=0; j<=i-1; j++) {
      dp[i] += dp[j] * dp[i-j-1];
    }
   }
   return dp[n];

  }
  
}
