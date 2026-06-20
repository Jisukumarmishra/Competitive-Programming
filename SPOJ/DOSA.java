// https://www.spoj.com/problems/DOSA/

import java.util.Scanner;

public class DOSA {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int price [] = new int [n];
    for (int  i=0; i<n; i++) {
      price[i] = sc.nextInt();
    }

    int [] brr = new int [n];

    for(int i =0; i<n; i++) {
      brr[i] = price[i]- i ;
    }
    // now brr me lis ki lenght
    // brr ki length - usme maximim lis ke lenght that is your answer 
  }

  int lis (int [] brr) {
    int n = brr.length;
    int [] dp = new int[n];

    int maxlength = 1;
    

  }
  
}
