package basic_dp;

public class fibonacci_number {
  public static void main(String[] args) {
      
  }
  public static int fibo ( int n) {
    if(n<=1) {
      return n;
    }
    int ans= fibo(n-1) + fibo ( n-2);
  }

}
