import java.util.Scanner;

public class Accommodation {
  //467A
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    long count = 0;
    long n = sc.nextInt();
    while (n-- > 0) {
      long p = sc.nextInt();
      long q = sc.nextInt();

      
      if (p < q && q-p >= 2) {
        count++;
      }
      
    }

    System.out.println(count);
  }
}
