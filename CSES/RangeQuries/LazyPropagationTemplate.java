import java.io.BufferedInputStream;
import java.io.IOException;

public class LazyPropagationTemplate {

  static class SegTree {
    private final long[] tree;
    private final long[] lazyAdd;
    private final long[] lazySet;
    private final boolean[] hasSet;
    private final int n;

    SegTree(int[] arr) {
      this.n = arr.length;
      this.tree = new long[4 * n];
      this.lazyAdd = new long[4 * n];
      this.lazySet = new long[4 * n];
      this.hasSet = new boolean[4 * n];
      build(0, 0, n - 1, arr);
    }

    private void build(int node, int l, int r, int[] arr) {
      if (l == r) {
        tree[node] = arr[l];
        return;
      }
      int mid = (l + r) >>> 1;
      build(node * 2 + 1, l, mid, arr);
      build(node * 2 + 2, mid + 1, r, arr);
      tree[node] = tree[node * 2 + 1] + tree[node * 2 + 2];
    }

    private void push(int node, int l, int r) {
      if (hasSet[node]) {
        tree[node] = (long) (r - l + 1) * lazySet[node];
        if (l != r) {
          int left = node * 2 + 1;
          int right = node * 2 + 2;
          hasSet[left] = true;
          hasSet[right] = true;
          lazySet[left] = lazySet[node];
          lazySet[right] = lazySet[node];
          lazyAdd[left] = 0;
          lazyAdd[right] = 0;
        }
        hasSet[node] = false;
      }

      if (lazyAdd[node] != 0) {
        tree[node] += (long) (r - l + 1) * lazyAdd[node];
        if (l != r) {
          int left = node * 2 + 1;
          int right = node * 2 + 2;
          lazyAdd[left] += lazyAdd[node];
          lazyAdd[right] += lazyAdd[node];
        }
        lazyAdd[node] = 0;
      }
    }

    void rangeAdd(int ql, int qr, long val) {
      updateAdd(0, 0, n - 1, ql, qr, val);
    }

    private void updateAdd(int node, int l, int r, int ql, int qr, long val) {
      push(node, l, r);
      if (r < ql || l > qr) return;

      if (ql <= l && r <= qr) {
        lazyAdd[node] += val;
        push(node, l, r);
        return;
      }

      int mid = (l + r) >>> 1;
      updateAdd(node * 2 + 1, l, mid, ql, qr, val);
      updateAdd(node * 2 + 2, mid + 1, r, ql, qr, val);
      tree[node] = tree[node * 2 + 1] + tree[node * 2 + 2];
    }

    void rangeSet(int ql, int qr, long val) {
      updateSet(0, 0, n - 1, ql, qr, val);
    }

    private void updateSet(int node, int l, int r, int ql, int qr, long val) {
      push(node, l, r);
      if (r < ql || l > qr) return;

      if (ql <= l && r <= qr) {
        hasSet[node] = true;
        lazySet[node] = val;
        lazyAdd[node] = 0;
        push(node, l, r);
        return;
      }

      int mid = (l + r) >>> 1;
      updateSet(node * 2 + 1, l, mid, ql, qr, val);
      updateSet(node * 2 + 2, mid + 1, r, ql, qr, val);
      tree[node] = tree[node * 2 + 1] + tree[node * 2 + 2];
    }

    long rangeSum(int ql, int qr) {
      return query(0, 0, n - 1, ql, qr);
    }

    private long query(int node, int l, int r, int ql, int qr) {
      push(node, l, r);
      if (r < ql || l > qr) return 0;
      if (ql <= l && r <= qr) return tree[node];

      int mid = (l + r) >>> 1;
      long left = query(node * 2 + 1, l, mid, ql, qr);
      long right = query(node * 2 + 2, mid + 1, r, ql, qr);
      return left + right;
    }
  }

  public static void main(String[] args) throws Exception {
    FastScanner fs = new FastScanner();
    StringBuilder out = new StringBuilder();

    int n = fs.nextInt();
    int q = fs.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = fs.nextInt();
    }

    SegTree st = new SegTree(arr);

    while (q-- > 0) {
      int type = fs.nextInt();
      int a = fs.nextInt() - 1;
      int b = fs.nextInt() - 1;

      if (type == 1) {
        int x = fs.nextInt();
        st.rangeAdd(a, b, x);
      } else if (type == 2) {
        int x = fs.nextInt();
        st.rangeSet(a, b, x);
      } else {
        out.append(st.rangeSum(a, b)).append('\n');
      }
    }

    System.out.print(out);
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
        if (len <= 0) return -1;
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
