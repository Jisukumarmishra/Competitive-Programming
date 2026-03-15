public class EditDistance {
  public static void main(String[] args) {
    String s1 = "intention";
    String s2 = "execution";
    System.out.println(editDistance(s1, s2));
    
  }

  //O(n*W)
  static int editDistance (String s1, String s2) {
    int n = s1.length();
    int m = s2.length();
    int[][] dp = new int [n+1][m+1];
    
 // intisalize or base case // or small problem  // edge case // first row and and first col fgillinh in dp table
    for (int i=0; i<n+1; i++) {
      for (int j=0; j<m+1; j++) {
        if (i == 0  ) { // i==0 means first row means s1 khali hai
          dp[i][j] = j;
        }
        if( j==0 ) {
        dp[i][j] = i; // j= 0 means first column in dp table also s2 khali hai 
        }
      }
    }
  
    // another way to intialize the dpo table
    // // first column
    // for (int i = 0; i <= n; i++) {
    //     dp[i][0] = i;
    // }

    // // first row
    // for (int j = 0; j <= m; j++) {
    //     dp[0][j] = j;
    // }

    // buttom up step
    // here i and j s1 and s2 ki length denote kar rahe hai
    for (int i=1; i<n+1; i++) {
      for (int j=1; j<m+1; j++) {
      if (s1.charAt(i-1) == s2.charAt(j-1)) { // both string lase element/chara is same vala case
      dp[i][j] = dp[i-1][j-1];// np operation mpove to the next step
      } else {
      dp[i][j] = Math.min( dp[i][j-1], Math.min( dp[i-1][j], dp[i-1][j-1] )) + 1; // character is differnt vala three case,respectively is add, dcelete, replace operation
      }
      }
    }
    return dp[n][m];
  }
}
