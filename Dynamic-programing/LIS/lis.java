package LIS;
import java.util.*;

public class lis {
  public static void main(String[] args) {
    int[] arr1 = {5, 0, 1, 2 ,1, 2};
    System.out.println(lis(arr1));
    
  }
  static int lcs (int [] arr1, int [] arr2) {
    int n = arr1.length;
    int m = arr2.length;
    int [][] dp  = new int [n+1][m+1];

    for(int i=1; i<n+1; i++) {
      for (int j=1; j<m+1; j++) {
        if (arr1[i-1]== arr2[j-1]) {
          dp[i][j]=dp[i-1][j-1]+1;
        } else {
          dp[i][j] =Math.max(dp[i][j-1],dp[i-1][j]);
        }
      }
    }
    return dp[n][m];
  }
  static int lis (int[] arr1 ) {
    HashSet<Integer> set = new HashSet<>();
    for (int i=0; i<arr1.length; i++) {
    set.add(arr1[i]);
    }
    // now we have unique eelemnt = set.size()
    
    int[] arr2 = new int[set.size()];
    int i =0; // i->idx
    for (int num: set ) {
      arr2[i] = num;
      i++;
    }

    Arrays.sort(arr2);// ascending
    return lcs(arr1, arr2);
 
  }
}
