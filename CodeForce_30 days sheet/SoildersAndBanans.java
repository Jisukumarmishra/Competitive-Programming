import java.util.Scanner;

public class SoildersAndBanans {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int k = sc.nextInt();
    int n = sc.nextInt();
    int w = sc.nextInt();

    int arr[] = new int[w];

    for (int i = 0; i < w; i++) {
      arr[i] = (i + 1) * k;
    }

    int sum = 0;
    for (int i = 0; i < arr.length; i++) {
      sum += arr[i];
    }

    if (sum <= n) {
      System.out.println(0);
    } else {
      System.out.println(sum - n);
    }
  }
}
