207. Course Schedule
Medium
There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.

# bfs solution
According to my code test, BFS is much faster than DFS. From my perspective DFS searches more branches. EX: 1->3->4 //1->5->3 the first branch we need search 3's children, in second we still need to do so.

BFS:

public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        Queue queue = new LinkedList();
        int count=0;
        
        for(int i=0;i<numCourses;i++)
            graph[i] = new ArrayList();
            
        for(int i=0; i<prerequisites.length;i++){
            degree[prerequisites[i][1]]++;
            graph[prerequisites[i][0]].add(prerequisites[i][1]);
        }
        for(int i=0; i<degree.length;i++){
            if(degree[i] == 0){
                queue.add(i);
                count++;
            }
        }
        
        while(queue.size() != 0){
            int course = (int)queue.poll();
            for(int i=0; i<graph[course].size();i++){
                int pointer = (int)graph[course].get(i);
                degree[pointer]--;
                if(degree[pointer] == 0){
                    queue.add(pointer);
                    count++;
                }
            }
        }
        if(count == numCourses)
            return true;
        else    
            return false;
    }
}


My version, 从最基础的课程开始遍历。 先把最基础的课程加入到queue，如果最后所有的课都变成了0，就是true

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(numCourses <= 0)
        	return false;
        Queue<Integer> queue = new LinkedList<Integer>();
        int[] inDegree = new int[numCourses];
        for(int i = 0; i < prerequisites.length; i++) inDegree[prerequisites[i][0]]++;
        for(int i = 0; i < numCourses; i++){
        	if(inDegree[i] == 0){
        		queue.offer(i); // find the start courses
        	}
        }
        // begin traverse
        while(!queue.isEmpty()){
        	int startCourse = queue.poll();
        	for(int i = 0; i < prerequisites.length; i++){
        		if(startCourse == prerequisites[i][1]){
        			if(--inDegree[prerequisites[i][0]] == 0){
        				queue.offer(prerequisites[i][0]);
        			}
        		}
        	}
        }
        for(int i =0; i < numCourses; i++){
        	if(inDegree[i] !=0) return false;
        }
        return true;
    }
}
