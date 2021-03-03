#brute-force frog jumpsize
public class Solution {
    public boolean canCross(int[] stones) {
        return can_Cross(stones, 0, 0);
    }
    public boolean can_Cross(int[] stones, int ind, int jumpsize) {
        for (int i = ind + 1; i < stones.length; i++) {
            int gap = stones[i] - stones[ind];
            if (gap >= jumpsize - 1 && gap <= jumpsize + 1) {
                if (can_Cross(stones, i, gap)) {
                    return true;
                }
            }
        }
        return ind == stones.length - 1;
    }
}

Another problem with above approaches is that we can make the same function calls coming through different paths e.g. For a given currentIndexcurrentIndex, we can call the recursive function canCrosscanCross with the jumpsizejumpsize, say nn. This nn could be resulting from previous jumpsizejumpsize being n-1n−1,nn or n+1n+1. Thus, many redundant function calls could be made prolonging the running time. This redundancy can be removed by making use of memorization. We make use of a 2-d memomemo array, initialized by -1−1s, to store the result returned from a function call for a particular currentIndexcurrentIndex and jumpsizejumpsize. If the same currentIndexcurrentIndex and jumpsizejumpsize happens is encountered again, we can return the result directly using the memomemo array. This helps to prune the search tree to a great extent.

public class Solution {
    public boolean canCross(int[] stones) {
        int[][] memo = new int[stones.length][stones.length];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return can_Cross(stones, 0, 0, memo) == 1;
    }
    public int can_Cross(int[] stones, int ind, int jumpsize, int[][] memo) {
        if (memo[ind][jumpsize] >= 0) {
            return memo[ind][jumpsize];
        }
        for (int i = ind + 1; i < stones.length; i++) {
            int gap = stones[i] - stones[ind];
            if (gap >= jumpsize - 1 && gap <= jumpsize + 1) {
                if (can_Cross(stones, i, gap, memo) == 1) {
                    memo[ind][gap] = 1;
                    return 1;
                }
            }
        }
        memo[ind][jumpsize] = (ind == stones.length - 1) ? 1 : 0;
        return memo[ind][jumpsize];
    }
}