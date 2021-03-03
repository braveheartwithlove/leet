227. Basic Calculator II
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, 
/ operators and empty spaces . The integer division should 
truncate toward zero.

Input: "3+2*2"
Output: 7

class Solution {
    public int calculate(String s) {
        int res = 0, tmp = 0, num = 0;
		char[] chs = s.toCharArray();
        char opr = '+';
		int n = chs.length;
        for(int i =0; i<chs.length; i++) {
			char c = chs[i];
            if(c == ' ')continue;
            if(Character.isDigit(c)) {
				num = (int)(c-'0');
				while(i+1<n && Character.isDigit(chs[i+1])){
					num = num*10 + (int)(chs[i+1]-'0');
					i++;
				}
   
                switch(opr) {
                    case '+':
                        res += tmp;
                        tmp = num;
                        break;
                    case '-':
                        res += tmp;
                        tmp = -num;
                        break;
                    case '*':
                        tmp *= num;
                        break;
                    case '/':
                        tmp /= num;
                        break;
                    default:
                        return -1;
                }

            }else {
				opr = c;
			}
			
        }
        res += tmp;
        
        return res;
    }
}