import java.util.Scanner;

public class flowers {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int t = sc.nextInt();
    int k = sc.nextInt();

    
    //Because constraints go up to 100000.
    int MOD = 1000000007;
    int MAX = 100000;

    int [] dp = new int [MAX+1]; // dp[i]=>number of way to make length i
    int [] pref = new int [MAX+1];

    // base case 
    dp[0] = 1; // one way for empty sequence

    // dp[i] = dp[i-1]+dp[i-k] // constraint

    for (int i =1; i<=MAX; i++) {
      dp[i] = dp[i-1];

      if (i >=k ) {
        dp[i] = (dp[i]+dp[i-k]) % MOD;
      }
    }
    
    // to answer range quries fast
    for(int  i=1; i<=MAX; i++) {
      pref[i] = (pref[i-1]+dp[i]) % MOD;
    }
  
    // test case handle here 
    while ( t > 0) {
      int a = sc.nextInt();
      int b = sc.nextInt();
       
      int ans = (pref[b]-pref[a-1]+MOD) % MOD;
      t--;
      System.out.println(ans);
    }


  }
}
