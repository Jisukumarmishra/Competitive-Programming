import java.util.*;

public class cutRibbon {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int a = sc.nextInt();
    int b = sc.nextInt();
    int c = sc.nextInt();
    System.out.println(solve(n, a, b, c));
  }

  static int solve(int n, int a, int b, int c) {
    if (n == 0) {
      return 0;
    }
    if (n < 0) {
      return Integer.MIN_VALUE;
    }
    // int pick = 0;
    // if ()
    int cutA = 1 + (solve(n - a, a, b, c));
    int cutB = 1 + (solve(n - b, a, b, c));
    int cutC = 1 + (solve(n - c, a, b, c));

    return Math.max(cutA, Math.max(cutB, cutC));

  }

}