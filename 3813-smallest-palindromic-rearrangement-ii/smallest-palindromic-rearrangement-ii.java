class Solution {
    private static final long LIMIT = 1_000_000L;

    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];

        for (char c : s.toCharArray())
            freq[c - 'a']++;

        String mid = "";

        for (int i = 0; i < 26; i++) {
            if ((freq[i] & 1) == 1)
                mid = "" + (char) ('a' + i);
            freq[i] /= 2;
        }

        StringBuilder first = new StringBuilder();

        int len = s.length() / 2;

        for (int pos = 0; pos < len; pos++) {

            boolean found = false;

            for (int ch = 0; ch < 26; ch++) {

                if (freq[ch] == 0)
                    continue;

                freq[ch]--;

                long ways = countWays(freq);

                if (ways >= k) {
                    first.append((char) ('a' + ch));
                    found = true;
                    break;
                }

                k -= ways;
                freq[ch]++;
            }

            if (!found)
                return "";
        }

        String second = new StringBuilder(first).reverse().toString();

        return first.toString() + mid + second;
    }

    private long countWays(int[] cnt) {

        int total = 0;
        for (int x : cnt)
            total += x;

        long res = 1;

        int remaining = total;

        for (int c : cnt) {

            if (c == 0)
                continue;

            res *= nCrLimited(remaining, c);

            if (res > LIMIT)
                return LIMIT;

            remaining -= c;
        }

        return Math.min(res, LIMIT);
    }

    private long nCrLimited(int n, int r) {

        r = Math.min(r, n - r);

        long ans = 1;

        for (int i = 1; i <= r; i++) {

            ans = ans * (n - r + i) / i;

            if (ans > LIMIT)
                return LIMIT;
        }

        return ans;
    }
}