import java.util.Scanner;
//https://codeforces.com/edu/course/2/lesson/4/3/practice/contest/274545/problem/B
public class inversion2 {
  static int [] tree;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int [] arr = new int [n];
    for (int i =0; i<n; i++) {
      arr[i] = sc.nextInt();
    }
    tree = new int [4*n];

    build(1, 0, n-1);
    int[] p = new int[n];

    for (int i = n - 1; i >= 0; i--) {
    int k = tree[1]-arr[i];

    int idx = query(1, 0, n - 1, k);

    p[i] = idx + 1; // convert to value (1-based)

    update(1, 0, n - 1, idx);
   }
     for (int x : p) System.out.print(x + " ");
  }

  static void build (int i, int l, int r){
    if(l == r) {
      tree[i] = 1;
      return;
    }
    int mid = (l+r)/ 2 ;
    build(2*i, l, mid);
    build(2*i+1, mid+1, r);
    tree[i] = tree[2*i]+tree[2*i+1];
  }

  // find kth avaliable number 
  // static int query(int i, int l ,int r, iny ql, int qr) {
  //   if(r < ql || l > qr) {
      
  //   }
  // }
  static int query(int node, int start, int end, int k) {
    if (start == end) return start;

    int mid = (start + end) / 2;

    if (tree[2*node] >= k)
        return query(2*node, start, mid, k);
    else
        return query(2*node+1, mid+1, end, k - tree[2*node]);
    }

    static void update(int i, int start, int end, int idx) {
    if (start == end) {
        tree[i] = 0; // used
        return;
    } 
    int mid = (start + end) / 2;
         if (idx <= mid)
            update(2*i, start, mid, idx);
        else
            update(2*i+1, mid+1, end, idx);

        tree[i] = tree[2*i] + tree[2*i+1];
    }
}
