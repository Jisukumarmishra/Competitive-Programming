import java.util.Scanner;

public  class HelpfulMaths {

  public static void main(String[] args) {
    Scanner sc = new  Scanner(System.in);
    String s = sc.next();

    char[] arr = s.toCharArray();

    for(int i =0; i<s.length()-2; i++) {
      for(int j = 0; j<s.length()-2; j++) {
         if(arr[j] > arr[j+2]) {
          char temp  = arr[j];
          arr[j] = arr[j+2];
          arr[j+2] = temp ;
         }
      }
    }

    String ans = new String(arr);

    System.out.println(ans);


    
  }
}