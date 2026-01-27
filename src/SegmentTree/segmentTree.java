
// Online Java Compiler
// Use this editor to write, compile and run your Java code online
class SegmentTree {
    int n;
    int[] tree;

    public SegmentTree(int[] arr) {
        this.n = arr.length;
        tree = new int[4 * n];
        buildTree(0, 0, n - 1, arr);
    }

    private void buildTree(int idx, int l, int r, int[] arr) {
        if (l == r) {
            tree[idx] = arr[l];
            return;
        }

        int mid = l + (r - l) / 2;
        buildTree(idx * 2 + 1, l, mid, arr);
        buildTree(idx * 2 + 2, mid + 1, r, arr);
        tree[idx] = tree[idx * 2 + 1] + tree[idx * 2 + 2];
    }

    public void update(int i, int val) {
        update(0, 0, n - 1, i, val);
    }

    private void update(int idx, int lo, int hi, int i, int val) {
        if (lo == hi) {
            tree[idx] = val;
            return;
        }

        int mid = lo + (hi - lo) / 2;
        if (i <= mid)
            update(idx * 2 + 1, lo, mid, i, val);
        else
            update(idx * 2 + 2, mid + 1, hi, i, val);
        tree[idx] = tree[idx * 2 + 1] + tree[idx * 2 + 2];
    }

    public int query(int l, int r) {
        return query(0, 0, n - 1, l, r);
    }

    private int query(int idx, int lo, int hi, int l, int r) {
        if (lo >= l && hi <= r)
            return tree[idx];
        if (hi < l || lo > r)
            return 0;

        int mid = lo + (hi - lo) / 2;
        int left = query(idx * 2 + 1, lo, mid, l, r);
        int right = query(idx * 2 + 2, mid + 1, hi, l, r);

        return left + right;
    }
};

class Main {
    public static void main(String[] args) {
        int[] arr = { 2, -5, 6, 10, 4, 3, 2, 6 };
        SegmentTree sgTree = new SegmentTree(arr);
        System.out.println(sgTree.query(0, 4));
        sgTree.update(1, 10);
        System.out.println(sgTree.query(1, 5));
    }
}