400. Nth Digit
Input:11    Output:0

Explanation:
The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.

class Solution {
    public int findNthDigit(int n) {
        if (n < 10) return n;
        int i = 1;
        while (n > 9 * Math.pow(10, i - 1) * i) {
            n -= 9 * Math.pow(10, i - 1) * i;
            i++;
        }
        n--;
        int num = (int)Math.pow(10, i - 1) + n / i, digit = n % i;
        return Integer.parseInt(String.valueOf(num).substring(digit, digit + 1));
    }
}


class Solution {
    public int findNthDigit(int n) {
        
        /**
        Awesome explanation : 
        https://leetcode.com/problems/nth-digit/discuss/717042/Python-solution-with-very-detailed-Explanation
        **/
        
        // step 1, size 9 : 1,...9
        // step 2, size 90 : 10,11...99
        // step 3, size 900 : 100,101...999
        // ...
        // long for considering last region whose size extends beyond int bounds, eg input 1000000000
        long step = 1;
        long size = 9 * (int)Math.pow(10, step-1);
        
        // loop until we reach desired step
        while (n > step * size) {
            n -= step * size;
            step++;
            size = 9 * (int)Math.pow(10, step-1);
        }        
        // extracting number from the current step
        long number = size/9 + (long)Math.ceil((n*1.0d)/step) -1;
        // extracting digit from above calculated number
        // mod adjustment for converting -1 to last index
        char ansDigit = String.valueOf(number).charAt((int)((n%step-1+step)%step));
        
        // retriving int out of char
        return (int)(ansDigit - '0');
    }
}



