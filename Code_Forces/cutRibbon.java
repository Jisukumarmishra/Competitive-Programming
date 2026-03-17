import java.util.*;

public class cutRibbon {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int a = sc.nextInt();
    int b = sc.nextInt();
    int c = sc.nextInt();

    int [] dp = new int [n+1];
    Arrays.fill(dp, -1);

    // System.out.println(solve(n, a, b, c, dp));
    int ans = solve(n, a, b, c, dp);
    System.out.println(ans < 0 ? 0 : ans);
  }

  static int solve(int n, int a, int b, int c, int [] dp) {

    if (n == 0) {
      return 0;
    }
    if (n < 0) {
      return Integer.MIN_VALUE;
    }

    // memo check
    if (dp[n] != -1) {
      return dp[n];
    }
    // int pick = 0;
    // if ()
    int cutA= 1 + (solve(n - a, a, b, c, dp));
    int cutB = 1 + (solve(n - b, a, b, c, dp));
    int cutC = 1 + (solve(n - c, a, b, c, dp));

    return dp [n] = Math.max(cutA, Math.max(cutB, cutC));


  }

}