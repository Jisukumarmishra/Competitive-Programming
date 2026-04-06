// In the Question We check For Ecery Element We have greater numbet at left sides = How Many Numbers
import java.util.Scanner;

public class inversions {
  static int []  tree;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int [] arr = new int [n];
    for(int i =0; i<n; i++) {
      arr[i] = sc.nextInt();
    }

    tree = new int [4*n];
    for(int i =0; i<n; i++) {

      // left sidde greater element
      int ans = query(0, 1, n, arr[i]+1, n);
      System.out.print(ans+" ");

      // update current elemnt
      update(0, 1, n, arr[i], n); // arr[i] +1 because we want greater elment

    }

  }

  static void update (int i, int l , int r, int st, int end) {
    if (l == r) {
     tree[i] = 1; // end nhi likhenge because bara value ho jaega
     return ;
    }
    
    int mid = (l+r) / 2 ;
    
    if (st<= mid) {
      update(2*i+1, l, mid, st, end);
    } else {
      update(2*i+2, mid+1, r, st, end);
    }

    // now update the current nod e
     tree[i] = tree[2*i+1] + tree[2*i+2];
  }

  static int query ( int i ,int l, int r, int st, int end) {
  if (r < st || end <l ) {
    return 0;
  }

  if (l >= st && r <= end) {
  return tree[i];
  }

  int mid = (l+r)/ 2 ;

  int left = query(2*i+1, l, mid, st, end);
  int right = query(2*i+2, mid+1, r, st, end);

  return left+right;

  
  }
}
