import java.util.Scanner;

public class TheatreSquare {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    long n = sc.nextLong();
    long m = sc.nextLong();
    long a = sc.nextLong();

    long x = (n + a - 1) / a;
    long y = (m + a - 1) / a;
    
    long ans = x * y;
    System.out.println(ans);

  }
}
