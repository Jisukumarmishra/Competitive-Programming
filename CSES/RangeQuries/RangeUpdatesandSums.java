import java.io.BufferedInputStream;
import java.io.IOException;
//https://cses.fi/problemset/task/1735

public class RangeUpdatesandSums {
static long[] tree;
static long[] lazyAdd;
static long[] lazySet;
static boolean[] isSet;
static int n;
  public static void main(String[] args) throws Exception {
    FastScanner sc = new FastScanner();
    StringBuilder out = new StringBuilder();

    n = sc.nextInt();
    int q = sc.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }

    tree = new long[4 * n];
    lazyAdd = new long[4 * n];
    lazySet = new long[4 * n];
    isSet = new boolean[4 * n];

    build(0, 0, n - 1, arr);

    while (q-- > 0) {
      int type = sc.nextInt();

      if (type == 1) {
        int a = sc.nextInt() - 1;
        int b = sc.nextInt() - 1;
        int x = sc.nextInt();
        updateAdd(0, 0, n - 1, a, b, x);
      } else if (type == 2) {
        int a = sc.nextInt() - 1;
        int b = sc.nextInt() - 1;
        int x = sc.nextInt();
        updateSet(0, 0, n - 1, a, b, x);
      } else {
        int a = sc.nextInt() - 1;
        int b = sc.nextInt() - 1;
        out.append(query(0, 0, n - 1, a, b)).append('\n');
      }
    }

    System.out.print(out);
  }

  static void build (int i, int l, int r, int [] arr) {
    if (l == r) {
     tree[i] = arr[l]; 
     return;
    }

    int mid = (l + r) / 2;
    build(2*i+1, l, mid, arr);
    build(2*i+2,mid+1,r,arr);
    tree[i] = tree[2*i+1] + tree[2*i+2];
  }

 static long query (int i, int l, int r, int ql, int qr) {
    push(i, l, r);
    if(r<ql || l> qr) {
      return 0;
    }

    if(l >= ql && r<= qr) {
      return tree[i];
    }

    int mid = (l + r) / 2;
    long left = query(2*i+1, l, mid, ql, qr);
    long right = query(2*i+2, mid+1, r, ql, qr);
    return left+right;
  } 

  static void push(int i, int l, int r) {

  if (isSet[i]) {
      tree[i] = (r - l + 1) * lazySet[i];

      if (l != r) {
        isSet[2*i+1] = true;
        isSet[2*i+2] = true;

        lazySet[2*i+1] = lazySet[i];
        lazySet[2*i+2] = lazySet[i];

        lazyAdd[2*i+1] = 0;
        lazyAdd[2*i+2] = 0;
      }

    isSet[i] = false;
  }

  if (lazyAdd[i] != 0) {
    tree[i] += (r - l + 1) * lazyAdd[i];

    if (l != r) {
        lazyAdd[2*i+1] += lazyAdd[i];
        lazyAdd[2*i+2] += lazyAdd[i];
    }

    lazyAdd[i] = 0;
  }
}

static void updateAdd(int i, int l, int r, int ql, int qr, long val) {

  push(i, l, r);

  if (r < ql || l > qr) return;

  if (l >= ql && r <= qr) {
      lazyAdd[i] += val;
      push(i, l, r);
      return;
  }

  int mid = (l + r) / 2;

  updateAdd(2*i+1, l, mid, ql, qr, val);
  updateAdd(2*i+2, mid+1, r, ql, qr, val);

  tree[i] = tree[2*i+1] + tree[2*i+2];
}

static void updateSet(int i, int l, int r, int ql, int qr, long val) {

  push(i, l, r);

  if (r < ql || l > qr) return;

  if (l >= ql && r <= qr) {
      isSet[i] = true;
      lazySet[i] = val;
      lazyAdd[i] = 0;
      push(i, l, r);
      return;
  }

  int mid = (l + r) / 2;

  updateSet(2*i+1, l, mid, ql, qr, val);
  updateSet(2*i+2, mid+1, r, ql, qr, val);

  tree[i] = tree[2*i+1] + tree[2*i+2];
}

  static class FastScanner {
    private final BufferedInputStream in = new BufferedInputStream(System.in);
    private final byte[] buffer = new byte[1 << 16];
    private int ptr = 0;
    private int len = 0;

    private int read() throws IOException {
      if (ptr >= len) {
        len = in.read(buffer);
        ptr = 0;
        if (len <= 0) {
          return -1;
        }
      }
      return buffer[ptr++];
    }

    int nextInt() throws IOException {
      int c;
      do {
        c = read();
      } while (c <= ' ');

      int sign = 1;
      if (c == '-') {
        sign = -1;
        c = read();
      }

      int val = 0;
      while (c > ' ') {
        val = val * 10 + (c - '0');
        c = read();
      }
      return val * sign;
    }
  }
}
