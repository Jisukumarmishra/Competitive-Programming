public class wildCardMatching {
  public static void main(String[] args) {
    String s1 = "abc";
    String  p = "**d";
    System.out.println(isMatching(s1, p));
  }

  static boolean isMatching (String s1, String p) {
  int n= s1.length();
  int m=p.length();
  boolean [][] dp = new boolean [n+1][m+1];
  
  // intializem 3 steps

  dp[0][0] = true;
    
    // pattern khali hai
    for(int i=1; i<n+1; i++) {
      dp[i][0] = false;
    }
    
    // string khali hai
    for (int j =1; j<m+1; j++) {
      if (p.charAt(j-1) == '*') {
       dp[0][j] = dp[0][j-1]; // true or false
      }
    }
    
    //O(n*w)
    // buttom up
    for (int i =1; i<n+1; i++ ) {
      for (int j=1; j<m+1; j++) {
        // case-> ith char == jth char || jth char == ?
        if (s1.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?') {
          dp[i][j] = dp[i-1][j-1];
        } else if (p.charAt(j-1) == '*') {
        dp[i][j] = dp[i-1][j] || dp[i][j-1]; // first vala me last char ke sath match kar diya and dusrta vale4 me ignore kar diya or as a empty le liya 
        } else {
          dp[i][j] = false;
        }
      }
    }
    return dp[n][m];
  }
  
}
