import java.util.*;
class sumOfQuery2 {
    int seg[];
    int[] arr;//access kr sko in every fn;
    void build(int i,int l,int r){
        if(l==r){
            seg[i] = arr[l];
            return;
        }
        int mid = l + (r-l)/2;
        build(2*i+1,l,mid);
        build(2*i+2,mid+1,r);
        seg[i] = seg[2*i+1] + seg[2*i+2];
    }
    public int query(int i,int l,int r,int st,int end){
        //base case
        if(r<st || end<l){
            return 0;
        }
        if(l>=st && r<=end){
            return seg[i];
        }
        int mid = l+(r-l)/2;
        int left = query(2*i+1,l,mid,st,end);
        int right = query(2*i+2,mid+1,r,st,end);
        return left+right;
    }
    List<Integer> querySum(int n, int arr[], int q, int queries[]) {
        // code here
        List<Integer> ans = new ArrayList<>();
        this.arr = arr;
        seg = new int[4*n];
        build(0,0,n-1);
        for(int i=0; i<queries.length; i +=2){
            int s = queries[i]-1;
            int e = queries[i+1]-1;
            ans.add(query(0,0,n-1,s,e));
        }
        return ans;
    }
}