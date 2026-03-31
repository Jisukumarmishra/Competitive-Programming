package RangeQuery;

import java.util.Scanner;

public class inversions {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    

  }

  static void update (int i, int l , int r, int st, int end) {
    if (l == r) {
     tree[i] = end;
    }
    
    int mid = (l+r) / 2 ;
    
    if (st<= mid) {
      update(2*i+1, l, mid, st, end);
    } else {
      update(2*i+2, mid+1, r, st, end);
    }

    // now update the current nod e
     tree[i] = tree[2*i] + tree[2*i+1];
  }

  static int query (int l, int r, int st, int end) {
  if (r < st || end <l ) {
    return 0;
  }
  
  }
}
