class Solution {
    public int[] validSequence(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        // last[j] = position in word1 where word2[j] can be matched
        // while matching word2[j...m-1] as a subsequence
        int[] last = new int[m];

        int i = n - 1;
        int j = m - 1;

        while (i >= 0 && j >= 0) {
            if (word1.charAt(i) == word2.charAt(j)) {
                last[j] = i;
                j--;
            }
            i--;
        }

        // If word2 cannot be matched even with no modification
        if (j >= 0) {
            // We still might be able to match using one modification,
            // so don't return here.
        }

        int[] ans = new int[m];

        i = 0;
        j = 0;

        boolean used = false;

        while (i < n && j < m) {

            // Characters are already equal
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                i++;
                j++;
            }

            // Try using the one allowed modification
            else if (!used) {

                // Check whether remaining word2 can be matched
                // after using modification at i
                if (j == m - 1 || 
                    (j + 1 < m && j + 1 < m && last[j + 1] > i)) {

                    ans[j] = i;
                    used = true;
                    i++;
                    j++;
                } 
                else {
                    i++;
                }
            }

            else {
                i++;
            }
        }

        if (j == m) {
            return ans;
        }

        return new int[0];
    }
}