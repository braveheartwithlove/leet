759. Employee Free Time
Hard
We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).  Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

Example 1:

Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation: There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.
Example 2:

Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]
Key points:

recognize that this is very similar to merging intervals (https://leetcode.com/problems/merge-intervals/description/)
it doesn't matter which employee an interval belongs to, so just flatten
can build result array while merging, don't have to do afterward (and don't need full merged arr)
def employeeFreeTime(self, schedule):
    ints = sorted([i for s in schedule for i in s], key=lambda x: x.start)
    res, pre = [], ints[0]
    for i in ints[1:]:
        if i.start <= pre.end and i.end > pre.end:
            pre.end = i.end
        elif i.start > pre.end:
            res.append(Interval(pre.end, i.start))
            pre = i
    return res
	
	
Approach #2: Priority Queue [Accepted]
Intuition

Say we are at some time where no employee is working. That work-free period will last until the next time some employee has to work.

So let's maintain a heap of the next time an employee has to work, and it's associated job. When we process
 the next time from the heap, we can add the next job for that employee.

Algorithm

Keep track of the latest time anchor that we don't know of a job overlapping that time.

When we process the earliest occurring job not yet processed, it occurs at time t, by employee e_id, 
and it was that employee's e_jx'th job. If anchor < t, then there was a free interval Interval(anchor, t).


class Solution(object):
    def employeeFreeTime(self, avails):
        ans = []
        pq = [(emp[0].start, ei, 0) for ei, emp in enumerate(avails)]
        heapq.heapify(pq)
        anchor = min(iv.start for emp in avails for iv in emp)
        while pq:
            t, e_id, e_jx = heapq.heappop(pq)
            if anchor < t:
                ans.append(Interval(anchor, t))
            anchor = max(anchor, avails[e_id][e_jx].end)
            if e_jx + 1 < len(avails[e_id]):
                heapq.heappush(pq, (avails[e_id][e_jx+1].start, e_id, e_jx+1))

        return ans
Time Complexity: O(C\log N)O(ClogN), where NN is the number of employees, and CC is the number of jobs across all employees. The maximum size of the heap is NN, so each push and pop operation is O(\log N)O(logN), and there are O(C)O(C) such operations.

Space Complexity: O(N)O(N) in additional space complexity.