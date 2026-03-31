package RangeQuery;

public class sumOfQuery2 {
  int [] arr;
  int [] segTree;
  public static void main(String[] args) {
    int []  arr = {3, 1,2};

  }

  void buildSegTree(int i, int l, int r) { // l =0 , r = n-1
    if(l== r) {
      segTree[i] = arr[l];
      return;
    }

    int mid = (l+r)/2;
    int left = buildSegTree(2*i+1, l, mid);
    int right = buildSegTree(2*i+2, mid+1, right);
    segTree[i] = left +right;
  }

  public int Query ()


}
