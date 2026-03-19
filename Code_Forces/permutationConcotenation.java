import java.util.*;
// D. New Year and the Permutation Concatenation
public class permutationConcotenation {
  static final long MOD = 998244353;

  static long power(long base, long exp, long mod) {
    long result = 1;
    base %= mod;
    while (exp > 0) {
      if ((exp & 1) == 1)
        result = result * base % mod;
      base = base * base % mod;
      exp >>= 1;
    }
    return result;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();

    long[] fact = new long[n + 1];
    fact[0] = 1;
    for (int i = 1; i <= n; i++)
      fact[i] = fact[i - 1] * i % MOD;

    // ans = n * n! - sum_{j=1}^{n-1} n!/j!
    // n!/j! = (j+1)*(j+2)*...*n
    long sum = 0;
    for (int j = 1; j < n; j++) {
      // fact[n] / fact[j] mod p = fact[n] * modInverse(fact[j])
      long term = fact[n] * power(fact[j], MOD - 2, MOD) % MOD;
      sum = (sum + term) % MOD;
    }

    long ans = ((long) n % MOD * fact[n] % MOD - sum + MOD) % MOD;
    System.out.println(ans);
  }
}