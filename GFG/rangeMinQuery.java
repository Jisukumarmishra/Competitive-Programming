public class rangeMinQuery {

  /* The functions which
builds the segment tree */
class GfG {
    static int st[];

    public static int[] constructST(int arr[], int n) {
        st = new int [4*n];
        // Add your code here
        build(0,0,n-1,arr);
        return st ;
    }
    
    static void build(int i, int l, int r, int [] arr ) {
        if( l== r) {
            st[i] = arr[l];
            return ;
        }
        int mid = (l+r) /2 ;
        
        build(2*i+1,l, mid, arr) ;
        build(2*i+2,mid+1, r, arr);
        st[i] = Math.min(st[2*i+1],st[2*i+2]);
    }

    /* The functions returns the
      min element in the range
      from l and r */
    public static int RMQ(int st[], int n, int l, int r) {
        // Add your code here
        // no overlap
        return query(0, 0, n-1, l, r, st);//// yaha l,r = query range
    }
    
    static int query(int i, int l ,int r, int ql, int qr, int [] st) { //l, r = current segment range
        //  no overlap condation
        
        if(r < ql || l > qr) {
            return Integer.MAX_VALUE; // not 0 because:= min(0, x) = always 0 rong answer
        }
        
        // completer overlapp
        
        if( l>= ql && r <= qr) {
            return st[i];
        }
        
        // partial overlapp
        
        int mid = (l+r)/2;
        int left = query(2*i+1,l, mid, ql, qr, st);//It returns the minimum value in the left child segment
        int right = query(2*i+2, mid+1, r, ql, qr, st);
        
        return Math.min(left, right);
        
    }
}

class Solution {
    
}
  
}
