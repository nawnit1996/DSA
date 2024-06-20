#LeetCode 307
class NumArray {
    int [] BIT;
    int[] nums;
    int len;
    public NumArray(int[] nums) {
        this.nums = nums;
        BIT = new int[nums.length+1];

        len = nums.length;
        int []prefixSum =  new int[nums.length+1];
        for(int i=1;i<=nums.length;i++){
            prefixSum[i] = prefixSum[i-1] + nums[i-1];
            if(i%2==0){
                BIT[i] = prefixSum[i]- prefixSum[ i - ((-i)&(i))];
            }else{
                BIT[i] = nums[i-1];
            }
        } 
    }
    
    public void update(int index, int val) {
        int diff = val - nums[index];
        nums[index] = val;
        index++;
        while(index<=len){
            BIT[index] += diff;
            index += ((-index) & index);
        }
    }
    
    public int sumRange(int left, int right) {
        return sum (++right) - sum(left);
    }

    public int sum(int ind){
        int sum =0;
        while(ind>0){
            sum += BIT[ind];
            ind -= (ind & (-ind));
        }
        return sum;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
