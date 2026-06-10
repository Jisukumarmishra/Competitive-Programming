import java.util.Scanner;

class main {
  static int rec (int idx, boolean tight, int prev,String s) {
    if(idx == s.length()) {
      return 1;
    }

   int count =0;
   int ul;

   if(tight == true) {
    ul = s.charAt(idx) -'0';
   } else {
    ul = 9;
   }

  for(int num = 0; num <= ul; num++) {

    if(num == prev) {
      continue;
    }

    boolean newTight;
    if( tight == true && num == ul) {
      newTight = true;
    } else {
      newTight = false;
    }

    count += rec(idx+1, newTight, num, s);
   }
   return count;
 }
public static void main(String[] args) {
  Scanner sc = new Scanner(System.in);
  int l = sc.nextInt(); 
  int r = sc.nextInt();

  System.out.println(rec(0,true, -1, r+""));
  
}
}