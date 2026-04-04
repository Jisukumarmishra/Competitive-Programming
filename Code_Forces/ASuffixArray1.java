import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ASuffixArray1 {
  static class pair {
    int idx;

    pair ( int i) {
      idx = i;
    }
  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String s = sc.next();
    int n = s.length();

    pair[] arr = new pair [n+1];

    // creae suffix
    // here we store the index rather than string then memory is optimized

    // for(int i =0; i<=n; i++) {
    //   arr[i] = new pair(s.substring(i),i);
    // }

     for(int i =0; i<=n; i++) {
      arr[i] = new pair(i);
    }
    
    //  Arrays.sort(arr, (a, b) -> a.suffix.compareTo(b.suffix)); or
    
    //  arr ko sort and
    // aur comparison suffix string ke basis pe
    // Arrays.sort(arr, new Comparator<pair>() {
    //   public int compare(pair a, pair b) {
    //     return a.suffix.compareTo(b.suffix);
    //   }
    // });

    Arrays.sort(arr, new Comparator<pair>() {
  public int compare(pair a, pair b) {

    int i = a.idx;
    int j = b.idx;

    while (i < s.length() && j < s.length()) {
      if (s.charAt(i) != s.charAt(j)) {
        return s.charAt(i) - s.charAt(j);
      }
      i++;
      j++;
    }

    // shorter suffix comes first
    return (s.length() - a.idx) - (s.length() - b.idx);
  }
});

    // print indices

    for(int  i=0; i<= n; i++) {
      System.out.print(arr[i].idx + " ");
    }

  }
}
