import java.util.ArrayList;
import java.util.Scanner;

// https://cses.fi/problemset/task/2181
public class CoutingTiling {
  static int n ;
  static int m ;
  static long[][] dp ;
  static final int MOD = 1000000007;
  static ArrayList<Integer>[] nextStates;
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    if(!sc.hasNextInt()) return ;
     n = sc.nextInt();
     m = sc.nextInt();

     if (n > m) {
      int temp = n;
      n = m;
      m = temp;
    }

     dp = new long [m+1][1<<n];
     dp[0][0] = 1;

    //  for (int i=0;i<=m; i++) {
    //   java.util.Arrays.fill(dp[i], -1);
    //  }
    nextStates = new ArrayList[1 << n];
      for (int mask = 0; mask < (1 << n); mask++) {
        nextStates[mask] = new ArrayList<>();
        options(mask, 0, 0);
      }

    for (int col = 0; col < m; col++) {
      for (int mask = 0; mask < (1 << n); mask++) {
        if (dp[col][mask] > 0) {
          // options(mask, 0, 0, col);
          for (int nextMask : nextStates[mask]) {
            dp[col + 1][nextMask] = (dp[col + 1][nextMask] + dp[col][mask]) % MOD;
          }
        }
      }
    }

    System.out.println(dp[m][0]);
  }



  // static long solve (int col, int currMask) {
  //   if(col == m) {
  //     return currMask == 0 ? 1 : 0;
  //   }

  //   if(dp[col][currMask] != -1) {
  //     return dp[col][currMask];
  //   }

  //  return dp[col][currMask] = options(currMask, 0, 0, col);
  // }

 static void options (int currMask, int row, int newMask) {
  if (row == n) {
    // return solve(col+1, newMask);
    //  dp[col+1][newMask] = (dp[col+1][newMask] + dp[col][currMask]) % MOD;
    nextStates[currMask].add(newMask);
     return ;
  }

  // for(int i=row; i<n; i++) {

   int bit = ((currMask & (1 << row)) == 0 ) ? 0 : 1 ;

  //  if(bit ==1 ) continue;
  
  if(bit == 1) {
    options(currMask, row + 1, newMask);
    return;
  }
  
  // long ans = 0;
  // verticacl calling 
  if( row+1 <n ) {
  int nextBit = ((currMask & (1 << (row+1))) == 0 ) ? 0 : 1;

   if(nextBit == 0) {
    // currMask = currMask ^ (1 << i);
    // currMask = currMask ^ (1 << (i+1));
    options(currMask, row+2, newMask);

    // undo condation
    // currMask = currMask ^ (1 << i);
    // currMask = currMask ^ (1 << (i+1));
  }

  }
 // horizontal calling
//  newMask = newMask ^ (1 <<i);
 options(currMask, row+1, newMask^(1 << row));
 // undo
//  newMask = newMask ^ (1 << i);
//  return ans;
// }
// return 0 ;
}
}
 

  
