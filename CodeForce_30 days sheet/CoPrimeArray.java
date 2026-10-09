import java.util.*;

public class CoPrimeArray {

  static int gcd(int a, int b) {
    while (b != 0) {
      int temp = a % b;
      a = b;
      b = temp;
    }
    return a;
  }

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();

    int[] a = new int[n];

    for (int i = 0; i < n; i++) {
      a[i] = sc.nextInt();
    }

    int count = 0;

    // First find how many insertions are needed
    for (int i = 0; i < n - 1; i++) {
      if (gcd(a[i], a[i + 1]) != 1) {
        count++;
      }
    }

    System.out.println(count);

    // Construct answer
    for (int i = 0; i < n - 1; i++) {

      System.out.print(a[i] + " ");

      if (gcd(a[i], a[i + 1]) != 1) {
        System.out.print("1 ");
      }
    }

    System.out.println(a[n - 1]);
  }
}