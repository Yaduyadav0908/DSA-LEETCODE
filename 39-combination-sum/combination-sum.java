import java.util.*;

class Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> ans = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), ans);
        return ans;
    }

    public void backtrack(int[] candidates, int target, int index,
                          List<Integer> list, List<List<Integer>> ans) {

        // Found a valid combination
        if (target == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }

        // Invalid case
        if (index == candidates.length || target < 0) {
            return;
        }

        // Take current element
        list.add(candidates[index]);
        backtrack(candidates, target - candidates[index], index, list, ans);

        // Backtrack
        list.remove(list.size() - 1);

        // Skip current element
        backtrack(candidates, target, index + 1, list, ans);
    }
}