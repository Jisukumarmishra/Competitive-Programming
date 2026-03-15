package CatalanNumber;

public class Catlan {
  public static void main(String[] args) {
    int n = 4 ;
    // System.out.println(catalanRec(n));

    // memo
    // int [] dp = new int [n+1];
    // Arrays.fill(dp, -1);
    // System.out.println(CatLanMemo(n, dp));
    System.out.println(CatlanTabo(n));
  }

  static int catalanRec(int n){
    if (n == 0|| n==1) {
      return 1;
    }

    int ans =0;

    for (int i=0; i<=n-1; i++) {
      ans += catalanRec(i) * catalanRec(n-i-1);
    }

    return ans ;

  }

  static int CatLanMemo (int n, int [] dp ) {
    
    if (n == 0 || n == 1) {
      return 1;
    }
    if (dp[n] != -1) {
      return dp[n];
    }
    int ans =0;
    for (int i=0;i<=n-1; i++) {
      ans += CatLanMemo(i, dp) * CatLanMemo(n-i-1, dp);
    }
    dp[n] = ans;
    return ans;
  }
 
  //O(n^2)
  static int CatlanTabo (int n ) {
    int [] dp = new int [n+1];
    // intillization
    dp[0] =1;
    dp[1] =1;

    for (int i=2;i<=n; i++) {
      for (int j=0; j<=i-1; j++){
      dp[i] += dp[j] * dp[i-j-1];
      }
    }
    return dp[n];
  }
  
}
