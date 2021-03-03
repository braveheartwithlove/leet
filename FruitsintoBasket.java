904. Fruit Into Baskets (Maximum substring with 2 types)
In a row of trees, the i-th tree produces fruit with type tree[i].

You start at any tree of your choice, then repeatedly perform the following steps:

Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
Note that you do not have any choice after the initial choice of starting tree: you must perform step 1,
 then step 2, then back to step 1, then step 2, and so on until you stop.

You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only
 carry one type of fruit each.

What is the total amount of fruit you can collect with this procedure?

 

Example 1:

Input: [1,2,1]
Output: 3
Explanation: We can collect [1,2,1].
Example 2:

Input: [0,1,2,2]
Output: 3
Explanation: We can collect [1,2,2].
If we started at the first tree, we would only collect [0, 1].
Example 3:

Input: [1,2,3,2,2]
Output: 4
Explanation: We can collect [2,3,2,2].
If we started at the first tree, we would only collect [1, 2].
Example 4:

Input: [3,3,3,1,2,1,1,2,3,3,4]
Output: 5
Explanation: We can collect [1,2,1,1,2].
If we started at the first tree or the eighth tree, we would only collect 4 fruits.

//longest substring with 2 different types
class Solution{
	public int totalFruit(int[] tree){
		if(tree==null || tree.length ==0){
			return 0;
		}
		
		int max =1;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int i=0;
		int j=0;
		while (j<tree.length){
			if(map.size()<=2){
				map.put(tree[j],j++);
			}
			if(map.size()>2){
				int min = tree.length -1;
				for (int value:map.values()){
					min = Math.min(min, value);
				}
				i=min+1;
				map.remove(tree[min]);
			}
			max=Math.max(max,j-i);
		}
		return max;
	}
}

Approach 1: Scan Through Blocks  O(N) O(N)

public int totalFruit(int[] tree) {

     if (tree == 0 || tree.length == 0) return 0;
     int max =1;
     HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
     int i = 0;
	 int j = 0;
	 
	 while (j < tree.length){
		 if(map.size() <=2){
			 map.push(tree[j],j++);
		 }
		 
		 if(map.size() >2){
			 int min = tree.length -1;
			 for (int value:map.values()){
				 min = Math.min(min, value);
			 }
			 
			 i = min +1;
			 map.remove(tree[min]);
		 }
		 max =Math.max(max, j-i);
	 }
 }
 Approach 2: Sliding Window  O(N) O(N)

 class Solution {
    public int totalFruit(int[] tree) {
        int ans = 0, i = 0;
        Counter count = new Counter();
        for (int j = 0; j < tree.length; ++j) {
            count.add(tree[j], 1);
            while (count.size() >= 3) {
                count.add(tree[i], -1);
                if (count.get(tree[i]) == 0)
                    count.remove(tree[i]);
                i++;
            }

            ans = Math.max(ans, j - i + 1);
        }

        return ans;
    }
}

class Counter extends HashMap<Integer, Integer> {
    public int get(int k) {
        return containsKey(k) ? super.get(k) : 0;
    }

    public void add(int k, int v) {
        put(k, get(k) + v);
    }
}