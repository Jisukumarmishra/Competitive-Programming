import java.util.*;

public class Main {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    int V = sc.nextInt();

    int[] a = new int[n];
    int[] b = new int[n];

    int sum = 0;

    for (int i = 0; i < n; i++) {
      a[i] = sc.nextInt();
      sum += a[i];
    }

    for (int i = 0; i < n; i++) {
      b[i] = sc.nextInt();
    }

    double x = Double.MAX_VALUE;

    for (int i = 0; i < n; i++) {
      x = Math.min(x, (double) b[i] / a[i]);
    }

    double answer = x * sum;

    answer = Math.min(answer, V);

    System.out.println(answer);
  }
}