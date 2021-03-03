1698. Number of Distinct Substrings in a String
Medium
Given a string s, return the number of distinct substrings of s.

A substring of a string is obtained by deleting any number of characters (possibly zero) from the front of the string and any number (possibly zero) from the back of the string.

 

Example 1:

Input: s = "aabbaba"
Output: 21
Explanation: The set of distinct strings is ["a","b","aa","bb","ab","ba","aab","abb","bba","aba","aabb","abba","bbab","baba","aabba","abbab","bbaba","aabbab","abbaba","aabbaba"]
Example 2:

Input: s = "abcdefg"
Output: 28

Use a trie to store every substring, count when it is a new substring. Time complexity O(n^2).


# Time:  O(N2)
# Space: O(|E|)
# Trie
Thanks to analoglife, the end mark is not necessary for this trie. 
Because if the current substring was appeared as a prefix previously, it will be already counted as a substring.

def countDistinct(self, s: str) -> int:
	trie, res = dict(), 0
	for i in range(len(s)):
		cur = trie
		for j in range(i,len(s)):# for substring from i to j
			if s[j] not in cur: #if current substring has not appeared previously.
				cur[s[j]] = dict()
				res+=1
			cur = cur[s[j]]
	return res