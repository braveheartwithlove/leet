
The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Given two integers x and y, calculate the Hamming distance.

Note:
0 ≤ x, y < 231.

Example:

Input: x = 1, y = 4

Output: 2

Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑
	   
	   O(1) O(1)
class Solution {
  public int hammingDistance(int x, int y) {
    int distance = 0;
  while (x>0 || y>0) {
	  distance += (x%2)^(y%2);
	  x>>1;
	  y>>1;
    }
    return distance;
  }
}


class Solution {
  public int hammingDistance(int x, int y) {
    int xor = x ^ y;
    int distance = 0;
    while (xor != 0) {
      if (xor % 2 == 1)
        distance += 1;
      xor = xor >> 1;
    }
    return distance;
  }
}