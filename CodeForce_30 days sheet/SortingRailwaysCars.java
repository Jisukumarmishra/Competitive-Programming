import java.util.Scanner;

public class SortingRailwaysCars {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] pos = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int car = sc.nextInt();
            pos[car] = i;
        }

        int longest = 1;
        int current = 1;

        for (int i = 2; i <= n; i++) {
            if (pos[i] > pos[i - 1]) {
                current++;
            } else {
                current = 1;
            }

            longest = Math.max(longest, current);
        }

        System.out.println(n - longest);
    }
}








// import java.util.Scanner;

// public class SortingRailwaysCars {
//   public static void main(String[] args) {
//     Scanner sc = new Scanner(System.in);
//     int n = sc.nextInt();
//     int arr[] = new int[n];

//     for (int i = 0; i < n; i++) {
//       arr[i] = sc.nextInt();
//     }

//     int move = 0;
//     // no of mismatch count karo karo
//     // also dry run for your own writing test case

//     for (int i = 0; i<arr.length-1; i++) {
//       if(arr[i] > arr[i+1] ) {
//         move++;
//       }
//     }

//    System.out.println(move);

//   }
// }
