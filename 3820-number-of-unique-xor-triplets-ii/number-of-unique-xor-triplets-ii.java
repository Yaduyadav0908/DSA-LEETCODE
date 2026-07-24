import java.util.*;

class Solution {
    public int uniqueXorTriplets(int[] nums) {

        int n = nums.length;

        if (n == 1)
            return 1;

        HashSet<Integer> pairXor = new HashSet<>();

        // Store all unique XORs of pairs
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                pairXor.add(nums[i] ^ nums[j]);
            }
        }

        HashSet<Integer> tripletXor = new HashSet<>();

        // XOR every pair XOR with every element
        for (int val : pairXor) {
            for (int num : nums) {
                tripletXor.add(val ^ num);
            }
        }

        return tripletXor.size();
    }
}