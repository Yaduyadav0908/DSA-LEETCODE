class Solution {

    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> ans = new ArrayList<>();

        backtrack(0, nums, new ArrayList<>(), ans);

        return ans;
    }

    private void backtrack(int index, int[] nums,
                           List<Integer> current,
                           List<List<Integer>> ans) {

        // Every current subset is a valid subset
        ans.add(new ArrayList<>(current));

        for (int i = index; i < nums.length; i++) {

            // Choose
            current.add(nums[i]);

            // Explore
            backtrack(i + 1, nums, current, ans);

            // Backtrack
            current.remove(current.size() - 1);
        }
    }
}