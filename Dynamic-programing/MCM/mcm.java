package MCM;
import java.util.*;
import java.lang.reflect.Array;

public class mcm {
  public static void main(String[] args) {
    int arr[] = {1,2, 3, 4, 3 }; 
    int n = arr.length;
    int dp [][] = new int [n][n];
    for (int i=0; i<n;i++) {
      Arrays.fill(dp[i],-1);
    }
    System.out.println(mcmRecusion(arr, 1, n-1)); // i index hai par 1 se srtart isliye "if  0 then 0-1 is  -1 that iws not defined"
    System.out.println(Memolization(arr, 1, n-1, dp));
    System.out.println(mcmTab(arr));
  }

  // recursion code
  static int mcmRecusion (int arr[], int i, int j) {
  if (i == j ) {
    return 0; // single matrix case 
    // and single matrix case ka cost 0 hota hai beacuse usko kisi se multiply karoge
  }
  int ans = Integer.MAX_VALUE;
  for ( int k =i;k<j; k++) { // also k<=j-1;
    int cost1 = mcmRecusion(arr, i, k);
    int cost2 = mcmRecusion(arr, k+1, j);
    int cost3 = arr[i-1]*arr[k]*arr[j]; // cost 3 is the final cost of cost1 and cost 2
    int finalCost = cost1+cost2+cost3;
    ans = Math.min(ans, finalCost);
  }
  return ans;
 }


  // memolization :=top down
   static int Memolization (int[] arr, int i, int j, int dp [][]) {
    if ( i == j ) {
      return 0; // single matrix case
    }
    
    int ans = Integer.MAX_VALUE;
    if (dp[i][j] != -1) {
      return dp[i][j];
    }

    for (int k =i;k<j; k++) { // j = n-1  where n is the length of the arr
     int cost1= Memolization(arr, i, k, dp);
     int cost2= Memolization(arr, k+1, j, dp);
     int cost3= arr[i-1]*arr[k]*arr[j];
     int finalCost = cost1+cost2+cost3;
     ans = Math.min(ans, finalCost);
    }
    return dp[i][j] = ans;
   }

   // tabulation

   static int mcmTab (int [] arr ) {
     int n = arr.length;
     int [][] dp = new int [n][n];
      
     // buttom length

     for (int leng = 2; leng<=n-1; leng++) {
      for (int i = 1; i<=n-leng; i++) {
       int j = i+leng-1;
       dp[i][j] = Integer.MAX_VALUE;
       for (int k=i;k<=j-1; k++){
        int c1 = dp[i][k];
        int c2 = dp[k+1][j];
        int c3 = arr[i-1]*arr[k]*arr[j];
        dp[i][j] = Math.min(dp[i][j],c1+c2+c3);
       }
      }
     }
     print(dp);
     return dp[1][n-1];
   }
   static void print(int [][] dp ) {
    for(int i =0; i<dp.length; i++){
      for ( int j =0; j<dp[0].length; j++){
       System.out.print(dp[i][j]+ " ");
      }
      System.out.println();
    }
    System.out.println();
   }
}
