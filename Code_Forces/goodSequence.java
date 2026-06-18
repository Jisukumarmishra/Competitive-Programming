import java.util.Scanner;

public class goodSequence {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int [] arr = new int [n];
    for (int i =0; i<n; i++) {
      arr[i] = sc.nextInt();
    }

    int [] dp = new int [n+1];

    for(int i =0; i<n; i++) {
      dp[i] = 1;
    }

    for (int i =0; i<n; i++) {
      for (int j =0; j<i; j++) {
        if(arr[i] > arr[j] && gcd (i,j) > 1) {
         dp[i] = Math.max(dp[i], 1+dp[j]);
        }
      }
    }
  }

  void gcd (int p, int q) {
   
  }
}
