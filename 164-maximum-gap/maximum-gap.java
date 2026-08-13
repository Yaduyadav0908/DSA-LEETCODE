class Solution {
    public int maximumGap(int[] nums) {

        int n = nums.length;

        if (n < 2) {
            return 0;
        }

        int min = nums[0];
        int max = nums[0];

        // Find minimum and maximum
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        if (min == max) {
            return 0;
        }

        // Minimum possible maximum gap
        int gap = (int) Math.ceil((double) (max - min) / (n - 1));

        // Number of buckets
        int bucketCount = (max - min) / gap + 1;

        int[] bucketMin = new int[bucketCount];
        int[] bucketMax = new int[bucketCount];

        // -1 means bucket is empty
        for (int i = 0; i < bucketCount; i++) {
            bucketMin[i] = Integer.MAX_VALUE;
            bucketMax[i] = Integer.MIN_VALUE;
        }

        // Put numbers into buckets
        for (int num : nums) {

            int index = (num - min) / gap;

            bucketMin[index] = Math.min(bucketMin[index], num);
            bucketMax[index] = Math.max(bucketMax[index], num);
        }

        int answer = 0;
        int previousMax = min;

        // Check gap between buckets
        for (int i = 0; i < bucketCount; i++) {

            if (bucketMin[i] == Integer.MAX_VALUE) {
                continue;
            }

            answer = Math.max(answer, bucketMin[i] - previousMax);

            previousMax = bucketMax[i];
        }

        return answer;
    }
}