import java.util.Scanner;

class main {

  static long rec (int idx, boolean tight, boolean started, int prev,String s) {
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
    boolean newTight;

    if( tight == true && num == ul) {
      newTight = true;
    } else {
      newTight = false;
    }

    if(!started && num == 0) {
     count += rec(idx+1, newTight,false, -1, s);
    } else {
      if(num == prev) {
      continue;
    }

    count += rec(idx+1, newTight, true, num, s);

    }

    
   }
   return count;
 }

 static long solve(long x){
    return rec(0, true, false, -1, String.valueOf(x));
}
public static void main(String[] args) {
  Scanner sc = new Scanner(System.in);
  long l = sc.nextLong(); 
  long r = sc.nextLong();

  // System.out.println(rec(0,true,false, -1, r+""));

  System.out.println(
    solve(r) - solve(l - 1)
);
  
}
}