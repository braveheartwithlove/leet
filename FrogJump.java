403. Frog Jump
A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone.
 The frog can jump on a stone, but it must not jump into the water.

Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross 
the river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.

If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the
 frog can only jump in the forward direction.
[0,1,3,5,6,8,12,17]

There are a total of 8 stones.
The first stone at the 0th unit, second stone at the 1st unit,
third stone at the 3rd unit, and so on...
The last stone at the 17th unit.

[0,1,2,3,4,8,9,11]

Return false. There is no way to jump to the last stone as 
the gap between the 5th and 6th stone is too large.
two stacks

public class Solution {
    public boolean frogJump(int[] stones) {
        for(int i = 3; i < stones.length; i++){
			if(stones[i] > stones[i-1] * 2){
				return false;
			}
		}
		HashSet<Integer> stonePostions = new HashSet<>();
		for(int stone: stones){
			stonePostions.add(stone);
		}
		
		int lastStone = stones[stones.length -1];
		Stack<Integer> positions = new Stack<>();
		Stack<Integer> jumps = new Stack<>();
		positions.add(0);
		jumps.add(0);
		
		while(!positions.isEmpty()){
			int position = positions.pop();
			int jumpDistance = jumps.pop();
			for(int i = jumpDistance -1;i<= jumpDistance+1; i++){
				if(i<=0){
					continue;
				}
				
				int nextPosition = position +i;
				if(nextPostiion == lastStone){
					return true;
				} else if(stonePositions.contains(nextPosition)){
					positions.add(nextPosition);
					jumps.add(i);
				}
			}
		}
		return false;
		
	}
}
