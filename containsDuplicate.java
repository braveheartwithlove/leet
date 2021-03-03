217. Contains Duplicate
Given an array of integers, find if the array contains any duplicates.

Your function should return true if any value appears at least twice in the array,
 and it should return false if every element is distinct.
Input: [1,2,3,1]
Output: true

Approach #1 (Naive Linear Search) [Time Limit Exceeded]
public boolean containsDuplicate(int[] nums) {
    for (int i = 0; i < nums.length; ++i) {
        for (int j = 0; j < i; ++j) {
            if (nums[j] == nums[i]) return true;  
        }
    }
    return false;
}
// Time Limit Exceeded

Approach #2 (Sorting) [Accepted]

public boolean containsDuplicate(int[] nums) {
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 1; ++i) {
        if (nums[i] == nums[i + 1]) 
			return true;
    }
    return false;
}

Approach #3 (Hash Table) [Accepted]

public boolean containsDuplicate(int[] nums) {
    Set<Integer> set = new HashSet<>(nums.length);
    for (int x: nums) {
        if (set.contains(x)) 
			return true;
        set.add(x);
    }
    return false;
}