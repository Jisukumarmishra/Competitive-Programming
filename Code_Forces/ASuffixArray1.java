import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ASuffixArray1 {
  static class pair {
    String suffix;
    int idx;

    pair (String s, int i) {
      suffix = s;
      idx = i;
    }
  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String s = sc.next();
    int n = s.length();

    pair[] arr = new pair [n+1];

    // creae suffix
    for(int i =0; i<=n; i++) {
      arr[i] = new pair(s.substring(i),i);
    }
    
    //  Arrays.sort(arr, (a, b) -> a.suffix.compareTo(b.suffix)); or
    
    //  arr ko sort and
    // aur comparison suffix string ke basis pe
    Arrays.sort(arr, new Comparator<pair>() {
      public int compare(pair a, pair b) {
        return a.suffix.compareTo(b.suffix);
      }
    });

    // print indices

    for(int  i=0; i<= n; i++) {
      System.out.print(arr[i].idx + " ");
    }

  }
}
