// import java.util.Scanner;

// public class LuckySum {
//   public static void main(String[] args) {
//     Scanner sc = new Scanner(System.in);
//     int l = sc.nextInt();
//     int r = sc.nextInt();

//     long sum = 0;

//     for (int i = l; i <= r; i++) {

//       int x = i;

//       while (true) {

//         int temp = x;
//         boolean lucky = true;

//         while (temp > 0) {
//           int digit = temp % 10;

//           if (digit != 4 && digit != 7) {
//             lucky = false;
//             break;
//           }

//           temp = temp / 10;

//         }

//         if (lucky) {
//           sum += x;
//           break;
//         }

//         x++;

//       }
//     }

//     System.out.println(sum);
//   }
// }

import java.util.*;

public class LuckySum {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    int l = sc.nextInt();
    int r = sc.nextInt();

    long sum = 0;

    int lucky = 4;

    while (l <= r) {

      // Find the next lucky number >= l
      while (lucky < l) {

        String s = String.valueOf(lucky);
        char[] arr = s.toCharArray();

        int i = arr.length - 1;

        while (i >= 0 && arr[i] == '7') {
          arr[i] = '4';
          i--;
        }

        if (i < 0) {
          char[] newArr = new char[arr.length + 1];

          for (int j = 0; j < newArr.length; j++) {
            newArr[j] = '4';
          }

          lucky = Integer.parseInt(new String(newArr));

        } else {
          arr[i] = '7';
          lucky = Integer.parseInt(new String(arr));
        }
      }

      // How many numbers will use this lucky number?
      int count = Math.min(r, lucky) - l + 1;

      sum += (long) count * lucky;

      // These numbers are already processed
      l += count;
    }

    System.out.println(sum);
  }
}

// import java.util.Scanner;

// public class LuckySum {
// public static void main(String[] args) {

// Scanner sc = new Scanner(System.in);

// int l = sc.nextInt();
// int r = sc.nextInt();

// int sum = 0;

// int[] lucky = { 4, 7, 44, 47, 74, 77,
// 444, 447, 474, 477, 744, 747, 774, 777 };

// int current = l;

// for (int i = 0; i < lucky.length && current <= r; i++) {

// if (current <= lucky[i]) {

// int end = Math.min(r, lucky[i]);

// sum += (end - current + 1) * lucky[i];

// current = end + 1;
// }
// }

// System.out.println(sum);
// }
// }
