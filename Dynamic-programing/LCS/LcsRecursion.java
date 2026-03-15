public class LcsRecursion {
  public static void main(String[] args) {
    String s1= "abcd";
    String s2 = "cdesf";
    System.out.println(lcs(s1, s2, s1.length(), s2.length()));
  }
  static int lcs (String s1, String s2, int i,int j) {
    if ( i==0|| j ==0 ) {
      return 0;
    }
    if(s1.charAt(i-1) == s2.charAt(j-1)) {
      return 1+lcs(s1, s2, i-1, j-1); // har bar 1 plus karenge gor length calculate karne ke liye 
    } else {
      return Math.max(lcs(s1, s2, i, j-1),lcs(s1, s2, i-1, j));
    }
  }
}
