The Tower Hopper problem gives us an array of values representing heights 
that express how far we can jump from a certain tower, and asks whether 
there's a way to get from tower[0] (0 indexed) to outside of the array.

For ex, if we have towers = [4, 2, 0, 0, 2, 0], we can jump from towers[0]
 to towers[4] and then outside of the bounds of the array. Or we could jump 
 from towers[0] to towers[1] to towers[4] and then out of the array. 
 But if we had towers = [4, 2, 0, 0, 1, 0], there would be no way to 
 hop out of the array and we should return False.
Approach #3 (Two Pointers) [Accepted]
O(N2)
public class 3SumSmaller {
    public int threeSumSmaller(int[] nums, int target) {
        //initialize total count to zero
        int count = 0;
        
        //sort the array
        Arrays.sort(nums);
        
        //loop through entire array
        for(int i = 0; i < nums.length - 2; i++) {
            //set left to i + 1
            int left = i + 1;
            
            //set right to end of array
            int right = nums.length - 1;
            
            //while left index < right index
            while(left < right) {
                //if the 3 indices add to less than the target increment count
                if(nums[i] + nums[left] + nums[right] < target) {
                    //increment the count by the distance between left and right because the array is sorted
                    count += right - left;
                    
                    //decrement right pointer
                    left++;
                } else {
                    //if they sum to a value greater than target...
                    //increment left pointer
                    right--;
                }
            }
        }
        
        return count;
    }
}


Approach #2 (Binary Search) [Accepted]
O(n2logn) O(1)
public int threeSumSmaller(int[] nums, int target) {
    Arrays.sort(nums);
    int sum = 0;
    for (int i = 0; i < nums.length - 2; i++) {
        sum += twoSumSmaller(nums, i + 1, target - nums[i]);
    }
    return sum;
}

private int twoSumSmaller(int[] nums, int startIndex, int target) {
    int sum = 0;
    for (int i = startIndex; i < nums.length - 1; i++) {
        int j = binarySearch(nums, i, target - nums[i]);
        sum += j - i;
    }
    return sum;
}

private int binarySearch(int[] nums, int startIndex, int target) {
    int left = startIndex;
    int right = nums.length - 1;
    while (left < right) {
        int mid = (left + right + 1) / 2;
        if (nums[mid] < target) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return left;
}