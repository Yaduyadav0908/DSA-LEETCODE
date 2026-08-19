class Solution {
    public int[] rearrangeArray(int[] nums) {
        ArrayList<Integer> pos=new ArrayList<>();
        ArrayList<Integer> neg=new ArrayList<>();

        for(int i=0;i<nums.length;i++){
            if(nums[i]>=0){
                pos.add(nums[i]);
            }
            else{
                neg.add(nums[i]);
            }
        }
        int a=0;
        for(int i=0;i<nums.length;i+=2){
            nums[i]=pos.get(a);
            nums[i+1]=neg.get(a);
            a++;
        }
        return nums;
    }
}