class Solution {
    public int majorityElement(int[] arr) {
        int count=1;
        int maxc=arr[0];
        for(int i=1;i<arr.length;i++){
            if(count==0){
                maxc=arr[i];
            }
            if(arr[i]!=maxc){
                count--;
            }
            else{
                count++;
            }
        }
        return maxc;
    }
}