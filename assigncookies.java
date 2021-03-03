455. Assign Cookies
Input: [1,2,3], [1,1]

Output: 1

Input: [1,2], [1,2,3]

Output: 2

class Solution {
    public int assignCookies(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int contentChildren = 0;
		int i = g.length -1;
		int j = s.length -1;
		
        while (i >=0 && j>=0) {
			if(s[j]>=g[i]){
				contentChildren++;
				i--;
				j--;
			} else {
				i--;
			}
      
        }
        return contentChildren;
    }
}

class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int num = g.length;
        int k = 0, res = 0; 
        for(int i = 0; i < num; i++) {
            while(k < s.length && s[k] < g[i]) k++;
            if(k < s.length && s[k] >= g[i]) {
                res++;
                k++;
            }
        }
        return res;
    }
}