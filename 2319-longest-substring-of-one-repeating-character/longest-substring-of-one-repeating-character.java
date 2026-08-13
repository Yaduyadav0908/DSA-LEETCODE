class Solution {

    class Node {
        int len;
        int left;
        int right;
        int max;
        char leftChar;
        char rightChar;

        Node(char c) {
            len = 1;
            left = right = max = 1;
            leftChar = rightChar = c;
        }

        Node() {}
    }

    Node[] tree;
    String s;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {

        this.s = s;

        int n = s.length();

        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[queryIndices.length];

        for (int i = 0; i < queryIndices.length; i++) {

            int index = queryIndices[i];
            char c = queryCharacters.charAt(i);

            update(1, 0, n - 1, index, c);

            ans[i] = tree[1].max;
        }

        return ans;
    }

    void build(int node, int l, int r) {

        if (l == r) {
            tree[node] = new Node(s.charAt(l));
            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    void update(int node, int l, int r, int index, char c) {

        if (l == r) {
            tree[node] = new Node(c);
            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, c);
        } else {
            update(node * 2 + 1, mid + 1, r, index, c);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    Node merge(Node a, Node b) {

        Node res = new Node();

        res.len = a.len + b.len;

        res.leftChar = a.leftChar;
        res.rightChar = b.rightChar;

        res.left = a.left;
        res.right = b.right;

        res.max = Math.max(a.max, b.max);

        if (a.rightChar == b.leftChar) {

            // Combine suffix of left + prefix of right
            res.max = Math.max(res.max, a.right + b.left);

            // Entire left segment is same character
            if (a.left == a.len) {
                res.left = a.len + b.left;
            }

            // Entire right segment is same character
            if (b.right == b.len) {
                res.right = b.len + a.right;
            }
        }

        return res;
    }
}