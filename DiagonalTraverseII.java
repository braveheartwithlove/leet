1424. Diagonal Traverse II

Given a list of lists of integers, nums, return all elements of nums in diagonal order as shown in the below images.
 
Example 1:
Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,4,2,7,5,3,8,6,9]

In a 2D matrix, elements in the same diagonal have same sum of their indices.

So if we have all elements with same sum of their indices together, then it’s just a matter of printing those elements in order.

Algorithm

Insert all elements into an appropriate bucket i.e. nums[i][j] in (i+j)th bucket.
For each bucket starting from 0 to max, print all elements in the bucket.
Note: Here, diagonals are from bottom to top, but we traversed the input matrix from first row to 
last row. Hence we need to print the elements in reverse order.
C++:
vector<int> findDiagonalOrder(vector<vector<int>>& nums) {
        vector<int> answer;
        unordered_map<int, vector<int>> m;
        int maxKey = 0; // maximum key inserted into the map i.e. max value of i+j indices.
        
        for (int i=0; i<nums.size(); i++) {
            for (int j=0; j<nums[i].size(); j++) {
                m[i+j].push_back(nums[i][j]); // insert nums[i][j] in bucket (i+j).
                maxKey = max(maxKey, i+j); // 
            }
        }
        for (int i=0; i<= maxKey; i++) { // Each diagonal starting with sum 0 to sum maxKey.
            for (auto x = m[i].rbegin(); x != m[i].rend(); x++) { // print in reverse order.
                answer.push_back(*x); 
            }
        }
        
        return answer;
    }
	
public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int size = 0;
        Map<Integer, LinkedList<Integer>> map = new HashMap<>(); 
        for(int i=0; i<nums.size(); i++) {
            List<Integer> numList = nums.get(i);
            for(int j=0; j<numList.size(); j++) {
                int index = i+j;
                LinkedList<Integer> list = map.get(index);
                if(list == null) {
                   list = new LinkedList<>();
                }
                list.addFirst(numList.get(j));
                map.put(index, list);
                size++;
            }
        }

        int maxLen = Collections.max(map.keySet());
        List<Integer> resultList = new ArrayList<Integer>();
        for(int i = 0; i <= maxLen; i++) {
            List<Integer> diagValue = map.get(i);
            resultList.addAll(diagValue);
        }
        
        int[] res = new int[resultList.size()];
        for(int i = 0; i < res.length; i++) {
             res[i] = resultList.get(i); 
        }
         
        return res;   
    }