package BitDp;

public class TravelingSalesMan {
  public static void main(String[] args) {
    int bit = 0;
    int [][]  arr = {{0,1000,5000},{5000, 0, 1000},{1000, 5000, 0} };
    int n = arr.length;
    for(int i =0; i<n; i++) {
      bit = bit^(1<<i);
    }
    int [][] dp = new int[bit][n];
    
   System.out.println(solve(bit, bit, arr));
  }

   static int solve (int i, int bit, int[][] arr) {

   int n = arr.length;
   if( bit == 0) return arr[i][0];

   int min = Integer.MAX_VALUE;

   for(int j =0; j<n; j++) {

    if((bit & (1 << j)) != 0) {
      int newBit = bit ^ (i <<j);
      int take = arr[i][j] + solve(j, newBit, arr)
      min = Math.min(min, take);
    
    }
   }

   return min;

  }
}
