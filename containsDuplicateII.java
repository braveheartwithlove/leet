219. Contains Duplicate II

Given an array of integers and an integer k, find out 
whether there are two distinct indices i and j in the
 array such that nums[i] = nums[j] and the absolute 
 difference between i and j is at most k.

Example 1:

Input: nums = [1,2,3,1], k = 3
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1
Output: true
Example 3:

Input: nums = [1,2,3,1,2,3], k = 2
Output: false

HashMap
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        
        int index = 0;
        for(int each : nums){
            if(map.containsKey(each)){
                if((index - map.get(each)) <= k) return true;
            }
            map.put(each, index);
            index++;
        }
        return false;
    }
}


let containsNearbyDuplicate = function(nums, k) {
  let numsMap = new Map();

  for (let i = 0; i < nums.length; i++) {
    let num = nums[i];

    if (numsMap.has(num) && i - numsMap.get(num) <= k) {
      return true;
    } else {
      numsMap.set(num, i);
    }
  }

  return false;  
};

Approach #1: Naive Linear Search
public boolean containsNearbyDuplicate(int[] nums, int k) {
    for (int i = 0; i < nums.length; ++i) {
        for (int j = Math.max(i - k, 0); j < i; ++j) {
            if (nums[i] == nums[j]) return true;
        }
    }
    return false;
}
// Time Limit Exceeded.