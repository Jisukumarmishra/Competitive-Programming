import java.util.Scanner;

// Memolizaion
public class CoutingNumbers {

    static long dp[][][][] = new long[20][2][2][11];
    static boolean vis[][][][] = new boolean[20][2][2][11];

    static long memo(int idx, boolean tight, boolean started, int prev, String s) {

    if (idx == s.length()) {
        return 1;
    }

    int p;

    if (prev == -1) {
        p = 10;
    } else {
        p = prev;
    }

    if (vis[idx][tight ? 1 : 0][started ? 1 : 0][p]) {
        return dp[idx][tight ? 1 : 0][started ? 1 : 0][p];
    }

    long count = 0;
    int ul;

    if (tight) {
        ul = s.charAt(idx) - '0';
    } else {
        ul = 9;
    }

    for (int num = 0; num <= ul; num++) {

        boolean newTight;

        if (tight && num == ul) {
            newTight = true;
        } else {
            newTight = false;
        }

        if (!started && num == 0) {

            count += memo(
            idx + 1,
            newTight,
            false,
            -1,
            s
            );

        } else {

            if (num == prev) {
                continue;
            }

            count += memo(
              idx + 1,
              newTight,
              true,
              num,
              s
            );
        }
    }

        vis[idx][tight ? 1 : 0][started ? 1 : 0][p] = true;

        return dp[idx][tight ? 1 : 0][started ? 1 : 0][p] = count;
    }

    static long solve(long x) {

        if (x < 0) {
            return 0;
        }

        dp = new long[20][2][2][11];
        vis = new boolean[20][2][2][11];

        return memo(
          0,
          true,
          false,
          -1,
          String.valueOf(x)
        );
    }

    public static void main(String[] args) {

      Scanner sc = new Scanner(System.in);

      long a = sc.nextLong();
      long b = sc.nextLong();

      System.out.println(
        solve(b) - solve(a - 1)
      );
    }
}