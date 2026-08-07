class Solution {
    static int[] c2 = new int[10], c3 = new int[10], c5 = new int[10], c7 = new int[10];
    static {
        int[][] fac = {{},{},{2},{3},{2,2},{5},{2,3},{7},{2,2,2},{3,3}};
        for (int d = 1; d <= 9; d++)
            for (int p : fac[d]) {
                if (p == 2) c2[d]++;
                else if (p == 3) c3[d]++;
                else if (p == 5) c5[d]++;
                else c7[d]++;
            }
    }

    int A2, A3, A5, A7;
    int[][][][] dp;
    static final int INF = Integer.MAX_VALUE / 2;

    public String smallestNumber(String num, long t) {
        long tt = t;
        int a2 = 0, a3 = 0, a5 = 0, a7 = 0;
        while (tt % 2 == 0) { tt /= 2; a2++; }
        while (tt % 3 == 0) { tt /= 3; a3++; }
        while (tt % 5 == 0) { tt /= 5; a5++; }
        while (tt % 7 == 0) { tt /= 7; a7++; }
        if (tt != 1) return "-1";

        A2 = a2; A3 = a3; A5 = a5; A7 = a7;
        dp = new int[A2 + 1][A3 + 1][A5 + 1][A7 + 1];
        for (int[][][] x : dp) for (int[][] y : x) for (int[] z : y) java.util.Arrays.fill(z, INF);
        dp[0][0][0][0] = 0;

        for (int r2 = 0; r2 <= A2; r2++)
            for (int r3 = 0; r3 <= A3; r3++)
                for (int r5 = 0; r5 <= A5; r5++)
                    for (int r7 = 0; r7 <= A7; r7++) {
                        if (r2 == 0 && r3 == 0 && r5 == 0 && r7 == 0) continue;
                        int best = INF;
                        for (int d = 2; d <= 9; d++) {
                            int nr2 = Math.max(0, r2 - c2[d]);
                            int nr3 = Math.max(0, r3 - c3[d]);
                            int nr5 = Math.max(0, r5 - c5[d]);
                            int nr7 = Math.max(0, r7 - c7[d]);
                            if (nr2 == r2 && nr3 == r3 && nr5 == r5 && nr7 == r7) continue;
                            int val = dp[nr2][nr3][nr5][nr7];
                            if (val < INF) best = Math.min(best, val + 1);
                        }
                        dp[r2][r3][r5][r7] = best;
                    }

        int n = num.length();
        int[] digits = new int[n];
        for (int i = 0; i < n; i++) digits[i] = num.charAt(i) - '0';

        int firstZero = n;
        for (int i = 0; i < n; i++) {
            if (digits[i] == 0) { firstZero = i; break; }
        }

        int[][] reqAfter = new int[n + 1][4];
        reqAfter[0] = new int[]{A2, A3, A5, A7};
        for (int i = 0; i < n; i++) reqAfter[i + 1] = applyDigit(reqAfter[i], digits[i]);

        // num itself is valid only if it's zero-free AND product condition holds
        if (firstZero == n && isZero(reqAfter[n])) return num;

        // Same-length fix: deviation position i must be <= firstZero
        int upperBound = Math.min(n - 1, firstZero);
        for (int i = upperBound; i >= 0; i--) {
            int slotsAfter = n - 1 - i;
            int dStart = (digits[i] == 0) ? 1 : digits[i] + 1;
            for (int d = dStart; d <= 9; d++) {
                int[] nr = applyDigit(reqAfter[i], d);
                if (minDigits(nr) <= slotsAfter) {
                    char[] res = new char[n];
                    for (int j = 0; j < i; j++) res[j] = num.charAt(j);
                    res[i] = (char) ('0' + d);
                    fillGreedy(res, i + 1, n, nr);
                    return new String(res);
                }
            }
        }

        // No same-length fix: minimal valid longer length
        int[] full = new int[]{A2, A3, A5, A7};
        int minLen = minDigits(full);
        int L = Math.max(n + 1, minLen);
        char[] res = new char[L];
        fillGreedy(res, 0, L, full);
        return new String(res);
    }

    private void fillGreedy(char[] res, int start, int end, int[] req) {
        int[] cur = req;
        for (int pos = start; pos < end; pos++) {
            int slotsAfter = end - 1 - pos;
            for (int d = 1; d <= 9; d++) {
                int[] nr = applyDigit(cur, d);
                if (minDigits(nr) <= slotsAfter) {
                    res[pos] = (char) ('0' + d);
                    cur = nr;
                    break;
                }
            }
        }
    }

    private int[] applyDigit(int[] req, int d) {
        return new int[]{
            Math.max(0, req[0] - c2[d]),
            Math.max(0, req[1] - c3[d]),
            Math.max(0, req[2] - c5[d]),
            Math.max(0, req[3] - c7[d])
        };
    }

    private boolean isZero(int[] req) {
        return req[0] == 0 && req[1] == 0 && req[2] == 0 && req[3] == 0;
    }

    private int minDigits(int[] req) {
        return dp[req[0]][req[1]][req[2]][req[3]];
    }
}