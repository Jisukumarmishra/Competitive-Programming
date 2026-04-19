package AtCoder;
//https://atcoder.jp/contests/dp/tasks/dp_a
public class AFrog {

public static int frogJump(int n, int[] h) {
    int[] dp = new int[n];
    
    dp[0] = 0;
    
    for (int i = 1; i < n; i++) {
    int oneStep = dp[i-1] + Math.abs(h[i] - h[i-1]);
    
    int twoStep = Integer.MAX_VALUE;
    
    if (i > 1) {
        twoStep = dp[i-2] + Math.abs(h[i] - h[i-2]);
    }
        
    dp[i] = Math.min(oneStep, twoStep);

    }
    return dp[n-1];
  }  
}
