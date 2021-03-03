Basic Calculator I
Implement a basic calculator to evaluate a simple expression string.
The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces.
Examples:
Input: "1 + 1"
Return: 2
Input: " 2-1 + 2 "
Output: 3
Input: "(1+(4+5+2)-3)+(6+8)"
Output: 23
At first glance, we see that things in the parentheses should be on higher priority.
 So the first thing came up in my mind was to use a stack.
calculate the numbers on the same level
when we see a `(`, put the intermediate result into the stack, and start new calculation right after this `(`
when we see a `)`, pop the add/minus the result with the last item in the stack


class Solution(object):
    def calculate(self, s):
        """
        :type s: str
        :rtype: int
	its actually the simplied version of lc772, just without * and /
    	Time	O(n*h)
	Space   O(n + h) the recursion tree
    	4496 ms, faster than 5.01%
        """
        arr = []
        for c in s:
            arr.append(c)
        return self.helper(arr)

    def helper(self, arr):
        stack = []
        sign = '+'
        num = 0
        while len(arr) > 0:
            c = arr.pop(0)
            if c.isdigit():
                num = num*10 + int(c)
            if c == '(':
                num = self.helper(arr)
            if c == '+' or c == '-' or c == ')' or len(arr) == 0:
                if sign == '+':
                    stack.append(num)
                elif sign == '-':
                    stack.append(-num)
                sign = c
                num = 0
                if c == ')':
                    break
        return sum(stack)

class Solution(object):
    def calculate(self, s):
        """
        :type s: str
        :rtype: int
        
        Time	O(n)
	Space O(n)
        136 ms, faster than 67.45%
        """
        res = 0
        stack = []
        # 1 means positive, -1 means negative
        # we declare it as an integer because we want to put the +- in the stack too
        sign = 1
        num = 0
        i = 0
        while i < len(s):
            if s[i].isdigit():
                # construct a multi-digits number if any, e.g. "23" = 2*10+3 = 23
                j = i
                num = 0
                while j < len(s) and s[j].isdigit():
                    num = num*10 + int(s[j])
                    j += 1
                # sum up the intermediate result
                res += sign * num
                i = j
            elif s[i] == '+':
                # the next number will be using +
                sign = 1
                i += 1
            elif s[i] == '-':
                # the next number will be using -
                sign = -1
                i += 1
            elif s[i] == '(':
                # put the intermediate result(from the front) and sign into the stack
                stack.append(res)
                stack.append(sign)
                # since we have put the intermediate result in stack,
                # we can reset the things for calculation starting from this (
                res = 0
                sign = 1
                i += 1
            elif s[i] == ')':
                # last item is the sign we saved for calculation e.g. 1+(2+3) the 1st +
                sign = stack.pop()
                # previousLevelResult the intermediate result before this level, (xxx)
                previousLevelResult = stack.pop()
                # sign*res is the result within the current (xxx)
                res = previousLevelResult + sign * res
                i += 1
            else:
                i += 1
        return res



The time complexity of it is O(n) because we just need to run it once, and the space complexity of it is O(n) because the load in the stack is proportional in length of the input.
Let’s dive into a follow-up question
what if we need to calculate +-*/ as well?
It is actually a pretty hard question. Before we do that, let’s try to implement the calculator having the 4 basic operators, +-*/ , which is…


Basic Calculator II
The expression string contains only non-negative integers, +, -, *, / operators and empty spaces. The integer division should truncate toward zero.
Examples:
Input: "3+2*2" 
Return 7
Input: " 3/2 " 
Return 1
Input: " 3+5 / 2 " 
return: 5
Similar to I, we need to prioritize the calculations if there are */ , therefore we can use a stack again for the question. But this time, we don’t save any operators in the stack. Instead, we can use a variable to store the last operator, and once we meet a number after an operator, we can just operate the current number with the last operator.
The approach would be like:
init 1 stack
init 1 buffer for number(cos it might have more than one digit)
init 1 buffer for the last operator
if the current character is an operator, i) operate the current number with the previous operator, ii) put the result into the stack, iii) set the current character as the next operator
sum up all the numbers in the stack to get the result


class Solution(object):
    def calculate(self, s):
        """
        Time    O(2n)
        Space   O(n) the stack
        152 ms, faster than 51.65%
        """
        stack = []
        sign = '+'
        num = 0
        for i in range(len(s)):
            c = s[i]
            if c.isdigit():
                num = num*10+int(c)
            if i + 1 == len(s) or (c == '+' or c == '-' or c == '*' or c == '/'):
                if sign == '+':
                    stack.append(num)
                elif sign == '-':
                    stack.append(-num)
                elif sign == '*':
                    stack[-1] = stack[-1]*num
                elif sign == '/':
                    stack[-1] = int(stack[-1]/float(num))
                sign = c
                num = 0
        # O(n) as we iterate the stack to sum
        return sum(stack)


The time complexity of it is O(2n) because at the end of the day, we need to iterate through the stack to sum up all the numbers. Again, the space complexity of it is O(n) because the load in the stack is proportional in length of the input.
OK, Let’s see what if we need ( and ) as well.


Basic Calculator III
The expression string contains only non-negative integers, +, -, *, / operators, open ( and closing parentheses ) and empty spaces.
Examples:
Input: "1 + 1" 
Return: 2
Input: " 6-4 / 2 " 
Return: 4
Input: "2*(5+5*2)/3+(6/2+8)" 
Return: 21
Input: "(2+6* 3+5- (3*14/7+2)*5)+3" 
Return: -12
At my first glance, I guess it is possible to do recursion when we meet a ( for the next block (........) ,
we just need to find the corresponding closing parenthesis then we can do our recursion.

class Solution(object):
    def calculate(self, s):
        """
        Time    O(n^2) because we have to find the correspondign closing parenthesis for recursion
        Space   O(n)
        204 ms, faster than 7.41%
        """
        if len(s) == 0:
            return 0
        stack = []
        sign = '+'
        num = 0
        i = 0
        while i < len(s):
            c = s[i]
            if c.isdigit():
                num = num*10+int(c)

            if c == '(':
                # find the corresponding ")"
                pCnt = 0
                end = 0
                clone = s[i:]
                while end < len(clone):
                    if clone[end] == '(':
                        pCnt += 1
                    elif clone[end] == ')':
                        pCnt -= 1
                        if pCnt == 0:
                            break
                    end += 1
                # do recursion to calculate the sum within the next (...)
                num = self.calculate(s[i+1:i+end])
                i += end

            if i + 1 == len(s) or (c == '+' or c == '-' or c == '*' or c == '/'):
                if sign == '+':
                    stack.append(num)
                elif sign == '-':
                    stack.append(-num)
                elif sign == '*':
                    stack[-1] = stack[-1]*num
                elif sign == '/':
                    stack[-1] = int(stack[-1]/float(num))
                sign = c
                num = 0
            i += 1

        return sum(stack)
		

O(N) O(N)
class Solution(object):

    def calculate(self, s):
        """
        Time    O(n)
        Space   O(n)
        80 ms, faster than 22.22%
        """
        arr = []
        for c in s:
            arr.append(c)
        return self.helper(arr)

    def helper(self, s):
        if len(s) == 0:
            return 0
        stack = []
        sign = '+'
        num = 0
        while len(s) > 0:
            c = s.pop(0)
            if c.isdigit():
                num = num*10+int(c)
            if c == '(':
                # do recursion to calculate the sum within the next (...)
                num = self.helper(s)
            if len(s) == 0 or (c == '+' or c == '-' or c == '*' or c == '/' or c == ')'):
                if sign == '+':
                    stack.append(num)
                elif sign == '-':
                    stack.append(-num)
                elif sign == '*':
                    stack[-1] = stack[-1]*num
                elif sign == '/':
                    stack[-1] = int(stack[-1]/float(num))
                sign = c
                num = 0
                if sign == ')':
                    break
        return sum(stack)
		
		
