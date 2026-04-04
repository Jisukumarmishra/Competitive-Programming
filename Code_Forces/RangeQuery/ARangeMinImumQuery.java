import java.util.Scanner;

public class ARangeMinImumQuery {
  static int [] tree;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();// no of test case

    while (t-- > 0) {
    int n = sc.nextInt(); // no of element
    int [] arr  = new int[n];
    for(int i=0; i<n; i++) {
    arr[i]= sc.nextInt();
    }
    int q = sc.nextInt(); // no of quries

    tree = new int [4*n];
    
    build(0, 0, n-1, arr);
    while (q-- > 0) {
      int l = sc.nextInt();
      int r = sc.nextInt();
      int ans = Query(0, 0, n-1, l, r);
      System.out.println(ans);
    }
    }
    
    
  }

  static void build (int i, int l, int r, int [] arr) {
    if( l== r) {
      tree[i] = arr[l];
      return;
    }

    int mid = (l+r) /2 ;

    build(2*i+1, l, mid, arr);
    build(2*i+2, mid+1, r, arr);

    tree[i] = Math.min(tree[2*i+1],tree[2*i+2]);

  }

  static int Query(int i,int l, int r, int ql,int qr) {
    // no overlapp condation
    if(r < ql || l > qr) {
      return Integer.MAX_VALUE;
    }
    // comp overlap
    if(l>= ql && r <= qr) {
      return tree[i];
    }

    int mid = (l+r) / 2;
    int left = Query(2*i+1, l, mid, ql, qr);
    int right = Query(2*i+2, mid+1, r, ql, qr);

    return Math.min(left, right);
  }
}
