22. Generate Parentheses

Instead of adding '(' or ')' every time as in Approach 1, 
let's only add them when we know it will remain a valid sequence. 
We can do this by keeping track of the number of opening and closing brackets we have placed so far.

We can start an opening bracket if we still have one (of n) left to place. 
And we can start a closing bracket if it would not exceed the number of opening brackets.
Approach 2: Backtracking

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, String cur, int open, int close, int max){
        if (cur.length() == max * 2) {
            ans.add(cur);
            return;
        }

        if (open < max)
            backtrack(ans, cur+"(", open+1, close, max);
        if (close < open)
            backtrack(ans, cur+")", open, close+1, max);
    }
}
To enumerate something, generally we would like to express it as a sum of disjoint subsets that are easier to count.

Consider the closure number of a valid parentheses sequence S: the least index >= 0 
so that S[0], S[1], ..., S[2*index+1] is valid. Clearly, every parentheses sequence 
has a unique closure number. We can try to enumerate them individually.

For each closure number c, we know the starting and ending brackets must be at 
index 0 and 2*c + 1. 
Then, the 2*c elements between must be a valid sequence, 
plus the rest of the elements must be a valid sequence.

Approach 3: Closure Number
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c)
                for (String left: generateParenthesis(c))
                    for (String right: generateParenthesis(n-1-c))
                        ans.add("(" + left + ")" + right);
        }
        return ans;
    }
}