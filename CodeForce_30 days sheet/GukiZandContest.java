import java.util.Scanner;

public class GukiZandContest {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int arr[] = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }

    for (int i = 0; i < n; i++) {
      int count = 0; // count har student ike liye change ho rahi hai show ye andar hai
      for (int j = 0; j < n; j++) {
        if (arr[i] < arr[j]) {
          count++;
        }
      }
      int ans = 1 + count; // Answer ko har student ke liye print karna hai
      System.out.print(ans + " "); // show in dono ko loop ke andar rakha hu
    }

  }
}
