package RangeQuery;

public class lazyPropregation {

    public static void main(String[] args) {
        int n = 6;
        int[] arr = {1, 3, 5, 7, 9, 11};

        int[] tree = new int[4 * n];
        int[] lazy = new int[4 * n];

        lazyPropregation obj = new lazyPropregation();

        obj.build(0, n - 1, 1, arr, tree);

        // Update range [1,3] with +10
        obj.updateSegTree(0, n - 1, 1, 1, 3, 10, tree, lazy);

        // Print tree
        System.out.println("Segment Tree after update:");
        for (int i = 1; i < 2 * n; i++) {
            System.out.print(tree[i] + " ");
        }
    }

    // Build segment tree
    void build(int st, int end, int idx, int[] arr, int[] tree) {
        if (st == end) {
            tree[idx] = arr[st];
            return;
        }

        int mid = (st + end) / 2;

        build(st, mid, 2 * idx, arr, tree);
        build(mid + 1, end, 2 * idx + 1, arr, tree);

        tree[idx] = tree[2 * idx] + tree[2 * idx + 1];
    }

    // Lazy Propagation Update
    void updateSegTree(int st, int end, int idx, int l, int r, int val, int[] tree, int[] lazy) {

        // Step 1: Resolve previous lazy updates
        if (lazy[idx] != 0) {
            tree[idx] += (end - st + 1) * lazy[idx];

            if (st != end) {
                lazy[2 * idx] += lazy[idx];
                lazy[2 * idx + 1] += lazy[idx];
            }

            lazy[idx] = 0;
        }

        // Step 2: No overlap
        if (st > r || end < l) {
            return;
        }

        // Step 3: Complete overlap
        if (st >= l && end <= r) {
            tree[idx] += (end - st + 1) * val;

            if (st != end) {
                lazy[2 * idx] += val;
                lazy[2 * idx + 1] += val;
            }
            return;
        }

        // Step 4: Partial overlap
        int mid = (st + end) / 2;

        updateSegTree(st, mid, 2 * idx, l, r, val, tree, lazy);
        updateSegTree(mid + 1, end, 2 * idx + 1, l, r, val, tree, lazy);

        tree[idx] = tree[2 * idx] + tree[2 * idx + 1];
    }
}
