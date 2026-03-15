import java.util.*;
public class knapsack {
  // 0/1 knapsack (recursive approach)
  public static void main(String[] args) {
   int [] val = {15,14,10,45,30};
   int [] wt = {2,5,1,3,4};
   int W = 7;
   // System.out.println(knapsack(val, wt, W, 0));

   // memo
   int dp [][] = new int [val.length+1][W+1];
   for(int i=0; i<val.length;i++) {
      Arrays.fill(dp[i], -1);
   }
   // System.out.println(knapSackMemo(val, wt, W, 0, dp));
   
   // tabo
   System.out.println(ksTabo(val, wt, W));  
  }

 static int knapsack (int[] val, int []wt, int W, int idx) { // change hone vale varriable hai W and idx

 if ( idx == val.length ||  W == 0 ) {
    return 0; // idx = 0 means knapsak me koi element nhi hai show Profit is zero
 }
 if (wt[idx] <=W ) {
 return  Math.max(val[idx]+knapsack(val, wt, W-wt[idx], idx +1), // val[idx] because jis valu ka profit hai usko ad kr liya and next elem ke liye call kga diya
   knapsack(val, wt, W, idx +1));
 } else {
 return knapsack(val, wt, W, idx +1); // not pick
 }

}

// memolization

static int knapSackMemo (int [] val, int[]wt, int W, int idx, int [][] dp) {
   if (idx == val.length || W == 0 ) {
      return 0;
   }
   
   if (dp[idx][W] != -1 ) {
      return dp[idx][W];
   }

   if (wt[idx] <= W) {
      return dp[idx][W] =Math.max(val[idx] + knapSackMemo(val, wt, W-wt[idx], idx+1, dp),
      knapSackMemo(val, wt, W, idx+1, dp));
   } else {
      return dp[idx][W] = knapSackMemo(val, wt, W, idx+1, dp);
   }
}

//Tabulation

static int ksTabo (int[] val,int [] wt, int W) {
   int [][] dp = new int[val.length+1][W+1];

   for (int i=0; i<dp.length; i++) { // 0th col
     dp[i][0] = 0;
      for(int j=0; j<dp[0].length;j++) { // 0th row
         dp[0][j] =0;
      }
   }

   for (int i =1; i<val.length+1; i++) {
    for(int j=1; j<W+1; j++) {
      if (wt[i-1] <=j ) {  // think why  ?
         dp[i][j] =Math.max(val[i-1]+dp[i-1][j-wt[i-1]],dp[i-1][j]);
      } else {
         dp[i][j] = dp[i-1][j];// not pick
      }
    }    
   }
   printTablo(dp);
   return dp[val.length][W];

}

static void printTablo (int[][] dp ) {
   for (int i=0; i<dp.length; i++) {
      for (int j =0; j<dp[0].length;j++) {
         System.out.print(dp[i][j]+" ");
      }
   System.out.println();
   }
System.out.println();
}

} 

