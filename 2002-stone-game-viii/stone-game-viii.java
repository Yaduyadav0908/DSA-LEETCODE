class Solution {
    public int stoneGameVIII(int[] stones) {

        int n = stones.length;

        // Calculate prefix sum
        for (int i = 1; i < n; i++) {
            stones[i] = stones[i] + stones[i - 1];
        }

        // Initially, all stones are taken
        int f = stones[n - 1];

        // Work backwards
        for (int i = n - 2; i > 0; i--) {
            f = Math.max(f, stones[i] - f);
        }

        return f;
    }
}