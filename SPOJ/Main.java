// https://www.spoj.com/problems/DOSA/

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    long price [] = new long [n];
    for (int  i=0; i<n; i++) {
      price[i] = sc.nextLong();
    }

    long [] brr = new long [n];

    for(int i =0; i<n; i++) {
    if (brr[i] < 0) continue;
     brr[i] = price[i]- i ;
    }
    long lis [] = new long [n];
    int len = 0;

    for (int i =0; i<n; i++) {
      int idx = upperBound(lis, len, brr[i]);
      lis[idx] = brr [i];

      if (idx== len) {
        len++;
      }
    }
    System.out.println(n-len);
    
  }

static int upperBound (long[] arr, int len, long target) {
  int  l =0;
  int r = len;

  while (l <r ){
   int mid = l + (r-l) / 2 ;

   if(arr[mid] > target) {
    r = mid ;
   } else {
    l = mid +1;
   }
   
  }
  return l;
}
}
  

// now brr me lis ki lenght
// brr ki length - usme maximim lis ke lenght that is your answer 

// int lis (int [] brr) {
//   int n = brr.length;
//   int [] dp = new int[n];

//   int maxlength = 1;

// Appraoch : =
// calculate brr
// brr = arr[i]-i;
// noww brr me max lis ki length calculate karo
// now arr.length - lisLength = ans
