import java.util.Arrays;
import java.util.Scanner;

public class NextRound {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int k = sc.nextInt();

    int arr[] = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }

    // Arrays.sort(arr);
    int count = 0;

    for (int i = 0; i < arr.length; i++) {
      if (arr[i] >= arr[k-1] && arr[i] > 0) {
        count++;
      }
    }

    System.out.println(count);
  }
}
