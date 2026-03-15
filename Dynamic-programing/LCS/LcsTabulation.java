public class LcsTabulation {
  public static void main(String[] args) {
    String s1 = "abcdge";
    String s2 = "abedg";
    System.out.println(lcsTab(s1, s2));

  }
  static int lcsTab (String s1, String s2) {
    int n= s1.length();
    int m = s2.length();
    // dp matrix intializeation
     int [][] dp = new int[n+1][m+1];
    // now tabulaton vala code 

    for ( int i=1; i<n+1; i++){ // 1 se n tak ja rthe hai matlab chhote problem se bare problem ki or ja rhe hai(buttom up) also 1 se start isliye liye because 0 vala case pahle hi le liye hai 
      for (int j=1; j<m+1; j++){// yha pe +1na and ma me isliye kioye hai becuase of dpp ki zize vha tak hai 
        if (s1.charAt(i-1) == s2.charAt(j-1)) {
          dp[i][j] = 1+dp[i-1][j-1]; // 1 plus isliye kiye because ham same character ko count kar rhe hai lcs ke length me 
        } else {
          dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
        }
      }
    }

    return dp[n][m];
  }
}
