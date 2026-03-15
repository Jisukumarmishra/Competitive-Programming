package CatalanNumber;

public class CountingTree {
  public static void main(String[] args) {
   int n= 3; 
   System.out.println(countBST(n));
   System.out.println(countBSTsecondView(n));
  }
  // O(n^2)
  static int countBST (int n) {
  int [] dp = new int [n+1];
  dp[0] =1 ;
  dp[1] =1;
  
  for (int i=2; i<n+1; i++) {
    // Ci calculte when in your bst have i nodde what is the answer store in dp[i]
    for (int j=0; j<i; j++){
      dp[i] += dp[j] * dp[i-j-1];
    }
  }
  return dp[n];
  }

  static int countBSTsecondView (int n) {
  int [] dp = new int [n+1];
  dp[0] =1 ;
  dp[1] =1;
  
  for (int i=2; i<n+1; i++) {
    // Ci calculte when in your bst have i nodde what is the answer store in dp[i]
    for (int j=0; j<i; j++){
      int leftSubTree = dp [j];// left sub tree ke nodes kitne tarike se arrange ho sakte hai dp[j] tarike se
      int rightSubTree = dp[i-j-1];//right sub tree ke nodes kitne tarike se arrange ho sakte hai dp[j-i-1] tarike se
      dp[i] += leftSubTree * rightSubTree;
    }
  }
  return dp[n];
  }
}
