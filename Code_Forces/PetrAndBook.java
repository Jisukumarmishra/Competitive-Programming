import java.util.Scanner;

public class PetrAndBook {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt(); // n = no of pages
    int [] arr = new int [7];

    for (int  i=0; i<7; i++) {
      arr[i] = sc.nextInt();
    }
    int day = 0;

    while (n > 0) { // here ham true bhi llikh sakte hai 
      n = n - arr[day];
      if (n <= 0) {
        System.out.println(day+1);
        break;
      }

      day = (day +1) % 7 ;// move to next daycyccially 
    }

  }
}
