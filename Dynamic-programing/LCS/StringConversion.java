public class StringConversion {
  // variation of editDistance but the difference is thew only two operation are allowed:-insert and delete

  // logic is :- pahle two string (s1 and s2) ke lcs nikalo jisse  extra elemnt s1 se delete ho jayenge  
  // and then to find the min. no. of opration to convert the s1 to s2 is addition of s1length-lcs.length and lcslength-s2lngth

 public static void main(String[] args) {
  String word1 = "abcdefg";
  String word2 = "aceg";
  System.out.println(stringConversion(word1, word2));
 }


 static int lcs (String s1, String s2) {
  int n= s1.length();
  int m= s2.length();
  int [][] dp = new int[n+1][m+1];
   
  for (int  i=1; i<n+1; i++){
    for (int j=1; j<m+1; j++) {
      if (s1.charAt(i-1) == s2.charAt(j-1)) {
       dp[i][j] = 1+dp[i-1][j-1];
      } else {
       dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
      }
    }
  }
  return dp[n][m];
 }


 static int stringConversion (String s1, String s2) {
  int lcsLength = lcs(s1, s2);

  int deletions = s1.length() - lcsLength;
  int insertions = s2.length()- lcsLength;

  return deletions+insertions;
 }
}
