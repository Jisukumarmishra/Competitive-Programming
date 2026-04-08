public class LazyPropogation {
  // lazy propogation templete
  class SegmentTree {

    int[] tree;
    int[] lazy;
    int n;

    public SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];
        lazy = new int[4 * n];
        build(0, 0, n - 1, arr);
    }

    void build(int i, int l, int r, int[] arr) {
        if (l == r) {
            tree[i] = arr[l];
            return;
        }

        int mid = (l + r) / 2;
        build(2*i+1, l, mid, arr);
        build(2*i+2, mid+1, r, arr);

        tree[i] = tree[2*i+1] + tree[2*i+2];
    }

    void push(int i, int l, int r) {
        if (lazy[i] != 0) {
            tree[i] += (r - l + 1) * lazy[i];

            if (l != r) {
                lazy[2*i+1] += lazy[i];
                lazy[2*i+2] += lazy[i];
            }

            lazy[i] = 0;
        }
    }

    void update(int i, int l, int r, int ql, int qr, int val) {
        push(i, l, r);

        if (r < ql || l > qr) return;

        if (l >= ql && r <= qr) {
            lazy[i] += val;
            push(i, l, r);
            return;
        }

        int mid = (l + r) / 2;

        update(2*i+1, l, mid, ql, qr, val);
        update(2*i+2, mid+1, r, ql, qr, val);

        tree[i] = tree[2*i+1] + tree[2*i+2];
    }

    int query(int i, int l, int r, int ql, int qr) {
        push(i, l, r);

        if (r < ql || l > qr) return 0;

        if (l >= ql && r <= qr) return tree[i];

        int mid = (l + r) / 2;

        return query(2*i+1, l, mid, ql, qr)
             + query(2*i+2, mid+1, r, ql, qr);
    }
}
}

// uses
// SegmentTree st = new SegmentTree(arr);

// range add
// st.update(0, 0, n-1, l, r, val);

 // range sum
// st.query(0, 0, n-1, l, r);