First duplicate



Input: [2, 1, 3, 5, 3, 2]
Output: 3


Approach 1: Hashset O(N) O(N)

class Solution {
    public int[] firstDuplicate(int[] a) {
        if (a == null) return null;
        
        HashSet<Integer> seen = new HashSet();
		
		for (int i =0; i<a.length; i++){
			if (seen.contains(a[i])){
				return a[i];
			} else {
				seen.add(a[i]);
			}
		}
		
		return -1;
     
    }
}


Approach 1: Turn into negatives O(N) O(1)

[2,   1,  3, 5,  3, 2]
[-2, -1, -3, 5, -3, 2]

class Solution {
    public int[] firstDuplicate(int[] a) {
		
		for (int i =0; i<a.length; i++){
			if (a[Math.abs(a[i])-1]<0){
				return a[i];
			} else {
				a[Math.abs(a[i])-1] =-a[Math.abs(a[i])-1];
			}
		}
		
		return -1;
     
    }
}
pos =1, [2, -1 3, 5,3, 2]
pos =0   -2 -1
pos =2   -2 -1 -3 
pos=4    -2 -1 -3 5 -3
pos=2    3
package arrays;

public class FirstDuplicate {
	int firstDuplicate(int[] a) {
	       if(a.length<=1) return -1;
	       for(int i=0; i< a.length; i++){
	              int pos= Math.abs(a[i])-1;    
	              if(a[pos]<0) return pos+1;
	              else a[pos]=-a[pos];
	       }
	       return -1;
	}
}
