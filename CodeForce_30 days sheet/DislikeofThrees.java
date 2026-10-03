import java.util.Scanner;

public class DislikeofThrees {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();
    while (t-- > 0) {
      int k = sc.nextInt();

      int count = 0;
      int num = 1;

      while (count < k) {
        if (num % 3 != 0 && num % 10 != 3) {
          count++;
        }

        num++;
      }

      System.out.println(num - 1);
    }
  }
}

// import java.util.Scanner;

// public class DislikeofThrees {

// public static void main(String[] args) {

// Scanner sc = new Scanner(System.in);

// int t = sc.nextInt();

// int[] arr = new int[t];

// // Input store karna
// for (int i = 0; i < t; i++) {
// arr[i] = sc.nextInt();
// }

// // Har test case solve karna
// for (int i = 0; i < t; i++) {

// int n = arr[i];

// int count = 0;
// int num = 1;

// while (count < n) {

// if (num % 3 != 0 && num % 10 != 3) {
// count++;
// }

// num++;
// }

// System.out.println(num - 1);
// }
// }
// }
