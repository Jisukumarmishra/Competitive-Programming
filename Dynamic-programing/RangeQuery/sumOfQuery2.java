package RangeQuery;

public class sumOfQuery2 {
  static int [] arr;
  static int [] segTree;
  public static void main(String[] args) {
    arr = new int[]{3, 1, 2};
    int n = arr.length;
    segTree = new int[4 * n];
    buildSegTree(0, 0, n-1);
    System.out.println(buildSegTree(0, 0, n-1));
    
    // // Print the segment tree
    // for(int i = 0; i < segTree.length; i++) {
    //   if(segTree[i] != 0) {
    //     System.out.print(segTree[i]);
    //   }
    // }

  }

  public static int buildSegTree(int i, int l, int r) { // l =0 , r = n-1
    if(l == r) {
      segTree[i] = arr[l];
      return segTree[i];
    }

    int mid = (l+r)/2;
     buildSegTree(2*i+1, l, mid);
     buildSegTree(2*i+2, mid+1, r);
    return segTree[i] = segTree[2*i+1]+segTree[2*i+2];

  }

  public int Query (int i, int l, int r, int ql, int qr) {

  // no overlapping
  if(r < ql || l > qr)  {
   return 0; 
  }

  // overlapp
  if(l>= ql && r<= qr) {
    return segTree[i];
  }

  // partial overlapp
  int  mid = (l+r)/2;
  int left = Query(2*i+1, l, mid, ql, qr);
  int right =Query(2*i+2, mid+1, r, ql, qr);

  }


}
