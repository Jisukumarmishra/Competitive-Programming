import java.util.Scanner;

public class moveBrackets {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int  t = sc.nextInt();
    
    while (t-- > 0) { //t-- → use value of t, then decrease it
      int  n = sc.nextInt();
      String s = sc.next();

      int fineBracket = 0;
      int count = 0;

      for (char c : s.toCharArray()) {
        if ( c== '(' ) {
          fineBracket++;
        } else {
          fineBracket--;
        }
     
        // below if code is gredy
        if (fineBracket <0) {// mean no iof wrong bracket comes shopw the answwer = to total number of wring bracket
        count++;
        fineBracket =0;

        }
      }
   
      System.out.println(count);
    }

    
    
  }
}

// s = "())()()("
// (  → balance = 1 // fine = balance
// )  → balance = 0
// )  → balance = -1 ❌ → moves/count = 1, reset balance/fine = 0
// (  → balance = 1
// )  → balance = 0
// (  → balance = 1
// )  → balance = 0
// (  → balance = 1