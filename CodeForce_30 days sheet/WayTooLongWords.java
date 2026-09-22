import java.util.Scanner;

public class WayTooLongWords {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    String[] arr = new String[n];

    for (int i = 0; i < n; i++) {
      arr[i] = sc.next();
    }

    for (int i = 0; i < n; i++) {
      int count = 0;

      for (int j = 0; j < arr[i].length(); j++) {
        if (arr[i].charAt(j) != ' ') {
          count++;
        }
      }

      if (count <= 10) {
        System.out.println(arr[i]);
      } else {
        System.out.println(arr[i].charAt(0) + String.valueOf(count - 2) + arr[i].charAt(count - 1));
      }
    }
  }
}
