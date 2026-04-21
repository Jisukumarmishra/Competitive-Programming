public class BooleanParenthesization {
  // User function Template for Java
class Solution {
    static int countWays(String s) {
        // code here
        int n = s.length();
        // dp[i][j][isTrue]
        Integer[][][] dp = new Integer[n][n][2];
        return solve(0, n-1, 1, s, dp);
        
    }
    static int solve(int i, int j, int isTrue, String s, Integer[][][] dp) {

        // Base case
        if (i > j) return 0;

        if (i == j) {
            if (isTrue == 1) {
                return s.charAt(i) == 'T' ? 1 : 0;
            } else {
                return s.charAt(i) == 'F' ? 1 : 0;
            }
        }
        
         if (dp[i][j][isTrue] != null) {
            return dp[i][j][isTrue];
        }

        int ways = 0;

        // Partition loop (operators at odd index)
        for (int k = i + 1; k <= j - 1; k += 2) {

            char op = s.charAt(k);

            int LT = solve(i, k - 1, 1, s, dp);
            int LF = solve(i, k - 1, 0, s, dp);
            int RT = solve(k + 1, j, 1, s, dp);
            int RF = solve(k + 1, j, 0, s, dp);
            
             if (op == '&') {
                if (isTrue == 1) {
                    ways += LT * RT;
                } else {
                    ways += LT * RF + LF * RT + LF * RF;
                }
            } 
            else if (op == '|') {
                if (isTrue == 1) {
                    ways += LT * RT + LT * RF + LF * RT;
                } else {
                    ways += LF * RF;
                }
            } 
            else if (op == '^') {
                if (isTrue == 1) {
                    ways += LT * RF + LF * RT;
                } else {
                    ways += LT * RT + LF * RF;
                }
            }
        }

        return dp[i][j][isTrue] = ways;
    }
}
}
