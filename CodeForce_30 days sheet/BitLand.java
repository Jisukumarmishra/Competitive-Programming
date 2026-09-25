import java.util.Scanner;

public class BitLand {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    
    int x = 0;

    while (n-- > 0) {

      String s = sc.next();

      if (s.charAt(1) == '+') {
          x = x + 1;
        }
        if (s.charAt(1) == '-') {
          x = x - 1;
        }
      

    }

    System.out.println(x);
  }
}
