
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class SalaryQuries {

  static class SegTree {
    int n;
    long[] tree;

    SegTree(int n) {
      this.n = n;
      tree = new long[4*n+1];
    }

    void update (int i, int l, int r, int idx, int val)  {
    // leaf node
    if(l==r) {
      tree[i] += val;
      return;
    }

    int mid = (l+r) / 2 ;

    if(idx <= mid) {
      update(2*i, l, mid, idx, val);
    }else {
      update(2*i+1, mid+1, r, idx, val);
    }
    tree[i] = tree[2*i]+tree[2*i+1];

  }

 long Queries (int i,int l, int r, int st, int end) {
  if(r < st || l > end) {
    return 0;
  }

  if(l >= st && r<= end) {
    return tree[i];
  }

  int mid = (l+r)/2;
  long left= Queries(2*i,l,mid, st, end);
  long right = Queries(2*i+1,mid+1,r,st, end);
  return left+right;
 }
}

static class FastReader {
  BufferedReader br;
  StringTokenizer st;

  FastReader(InputStream is) {
    br = new BufferedReader(new InputStreamReader(is));
  }

  String next() throws IOException {
      while (st == null || !st.hasMoreElements())
          st = new StringTokenizer(br.readLine());
    return st.nextToken();
  }

  int nextInt() throws IOException {
      return Integer.parseInt(next());
    }
}
 
static class Query {
  char type;
  int x, y;

  Query(char t, int x, int y) {
    this.type = t;
    this.x = x ;
    this.y = y;
  }
  
}
public static void main(String[] args) throws IOException {
  // Scanner sc = new Scanner(System.in); // this is high risk to cause the tle
  
  FastReader fs = new FastReader(System.in);
  int n = fs.nextInt();
  int q = fs.nextInt();

  int [] p = new int [n];
  ArrayList<Integer> comp = new ArrayList<>();

  for(int i =0; i<n; i++){
    p[i] = fs.nextInt();
    comp.add(p[i]);
  }

ArrayList<Query> queries = new ArrayList<>();

for(int i=0; i<q; i++) {
  char type = fs.next().charAt(0);
  if(type =='!') {
    int k = fs.nextInt();
    int x = fs.nextInt();
    queries.add(new Query(type, k, x));
    comp.add(x);
  } else {
    int a = fs.nextInt();
    int b = fs.nextInt();
    queries.add(new Query(type, a, b));
    comp.add(a);
    comp.add(b);
  }
}

  // coordinate compression

  Collections.sort(comp);
  ArrayList<Integer> unique = new ArrayList<>();
  for(int x : comp) {
    if(unique.isEmpty()|| unique.get(unique.size()-1)!=x) unique.add(x);
  }

  int [] compArr = new int[unique.size()];
  for(int i=0; i<unique.size(); i++) {
    compArr[i] = unique.get(i);
  }


  int m = compArr.length;
  SegTree st = new SegTree(m);

  // get compressed index // here we use loweerBound function
  // HashMap<Integer, Integer> map = new HashMap<>();
  // for (int i = 0; i < comp.size(); i++) {
  //     map.put(comp.get(i), i + 1); // 1-based
  // }

  // iitial bound
  for (int i = 0; i<n; i++) {
    int posi = lowerBound(compArr, p[i]) +1;
    st.update(1,1,m,posi, 1);
  }

  StringBuilder out = new StringBuilder();
  // process Quries
  for (Query qq : queries) {
    if (qq.type == '!') {
      int k = qq.x-1;
      int x = qq.y; // x = newSlaalry
      
      // REMOVE old salary rank
      int oldPos = lowerBound(compArr, p[k])+1;
      st.update(1, 1, m, oldPos, -1);

      p[k] = x;

      // ADD new salary rank
      int newPos = lowerBound(compArr, p[k])+1;
      st.update(1, 1, m, newPos, 1);
  } else {
      int a = qq.x;
      int b = qq.y;

      int l = lowerBound(compArr, a) + 1;
      int r = upperBound(compArr, b);

      if (l > r) out.append(0).append('\n');
      else out.append(st.Queries(1, 1, m, l, r)).append('\n');
  }
}

System.out.print(out);
}

  // lower bound

  static int lowerBound(int[] arr, int x) {
    int l= 0, r = arr.length;

    while (l<r) {
      int mid = (l+r)/2;
        if (arr[mid] >= x) r = mid;
            else l = mid + 1;
        }
      return l;
    }

  //  upperbound
  
  static int upperBound(int[] arr, int x) {
    int l = 0, r = arr.length;
    while (l < r) {
        int mid = (l + r) / 2;
        if (arr[mid] > x) r = mid;
        else l = mid + 1;
    }
    return l;
}

}
