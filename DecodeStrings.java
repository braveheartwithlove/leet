394. Decode String
Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

 
Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"
Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"
Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"

two Stacks O(maxK*n) O(M+N)
class Solution {
    String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();
        int k = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            } else if (ch == '[') {
                // push the number k to countStack
                countStack.push(k);
                // push the currentString to stringStack
                stringStack.push(currentString);
                // reset currentString and k
                currentString = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder decodedString = stringStack.pop();
                // decode currentK[currentString] by appending currentString k times
                for (int currentK = countStack.pop(); currentK > 0; currentK--) {
                    decodedString.append(currentString);
                }
                currentString = decodedString;
            } else {
                currentString.append(ch);
            }
        }
        return currentString.toString();
    }
}


two Stacks O(maxK*n) O(M+N)
class Solution {
    public String decodeString(String s) {
        
        String str="";
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> charStack = new Stack<>();
        
        for(int i=0;i<s.length();i++) {
            char ch=s.charAt(i);
            if(Character.isDigit(ch)) {
                int pos = s.indexOf('[',i);
                int num = Integer.parseInt(s.substring(i,pos));
                i = pos-1;
                numStack.push(num);
            }
            else if(ch == ']') {
                String s1="";
                while(charStack.peek() != '[') {
                    s1= charStack.pop()+s1;
                }
                charStack.pop();
                int num = numStack.pop();
                String s2 = "";
                for(int j=0;j<num;j++) {    
                    s2 += s1;
                }
                for(int j=0;j<s2.length();j++) {
                    charStack.push(s2.charAt(j));
                }
            }
            else 
                charStack.push(ch);
        }
        while(!charStack.isEmpty())
            str = charStack.pop()+str; 
        return str;
    }
}



Approach 3: Using Recursion
class Solution {
    int index = 0;
    String decodeString(String s) {
        StringBuilder result = new StringBuilder();
        while (index < s.length() && s.charAt(index) != ']') {
            if (!Character.isDigit(s.charAt(index)))
                result.append(s.charAt(index++));
            else {
                int k = 0;
                // build k until next character is a digit
                while (index < s.length() && Character.isDigit(s.charAt(index)))
                    k = k * 10 + s.charAt(index++) - '0';
                // ignore the opening bracket '['    
                index++;
                String decodedString = decodeString(s);
                // ignore the closing bracket ']'
                index++;
                // build k[decodedString] and append to the result
                while (k-- > 0)
                    result.append(decodedString);
            }
        }
        return new String(result);
    }
}
