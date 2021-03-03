445. Add Two Numbers II
You are given two non-empty linked lists representing two non-negative integers. 
The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7

Approach 1: Reverse Input + Construct Output by Adding to Front
Algorithm

Implement reverseList function.
Reverse both input lists: l1 = reverseList(l1), l2 = reverseList(l2).
Initialize the result list: head = None.
Initialize the carry: carry = 0.
Loop through lists l1 and l2 until you reach both ends.
Set x1 = l1.val if l1 is not finished yet, and x1 = 0 otherwise.
Set x2 = l2.val if l2 is not finished yet, and x2 = 0 otherwise.
Compute the current value: val = (carry + x1 + x2) % 10, and the current carry: carry = (carry + x1 + x2) / 10.
Update the result by adding the current value to front.
Move to the next elements in the lists.
If the carry is not equal to zero, append it to frond of the result list.
Return the result list: return head.
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode last = null;
        while (head != null) {
            // keep the next node
            ListNode tmp = head.next;
            // reverse the link
            head.next = last;
            // update the last node and the current node
            last = head;
            head = tmp;    
        }    
        return last;
    }
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // reverse lists
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        
        ListNode head = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            // get the current values 
            int x1 = l1 != null ? l1.val : 0;
            int x2 = l2 != null ? l2.val : 0;
            
            // current sum and carry
            int val = (carry + x1 + x2) % 10;
            carry = (carry + x1 + x2) / 10;
            
            // update the result: add to front
            ListNode curr = new ListNode(val);
            curr.next = head;
            head = curr;
            
            // move to the next elements in the lists
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        if (carry != 0) {
            ListNode curr = new ListNode(carry);
            curr.next = head;
            head = curr;
        }

        return head;
    }
}
Approach 2: Follow Up: Do not Reverse Input.
Algorithm

Find the length of both lists: n1 and n2.
Parse both lists and sum the corresponding positions without taking carry into account,
 i.e. convert 3->3->3 + 7->7 into 3->10->10 and then into 10->10->3.

To sum the corresponding positions, do the following:
If n1 >= n2, add to the current value the value of the node from the first list,
 and decrease the number of elements to parse: n1 -= 1.

If n1 < n2, add to the current value the value of the node from the second list and 
decrease the number of elements to parse: n2 -= 1.

Update the result by adding the current value to the front.
Now it's time to take care about the carry, to limit each node value by 9, i.e. to convert 10->10->3 into 0->1->4 and then into 4->1->0:

Initialize the carry carry = 0.
Re-initialize the current list: curr1 = head and the output list: head = None.
Parse the current list curr1:
Normalize the current value to be less than 10: val = (curr1.val + carry) % 10, and keep the carry: carry = (curr1.val + carry) // 10.

Update the result by adding the current value to front.

Move to the next element in the list: curr1 = curr1.next.

If the carry is not equal to zero, append it to frond of the result list.

Return the result list: return head.
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // find the length of both lists
        int n1 = 0, n2 = 0;
        ListNode curr1 = l1, curr2 = l2;
        while (curr1 != null) {
            curr1 = curr1.next; 
            ++n1;    
        }
        while (curr2 != null) {
            curr2 = curr2.next; 
            ++n2;    
        }
            
        // parse both lists
        // and sum the corresponding positions 
        // without taking carry into account
        // 3->3->3 + 7->7 --> 3->10->10--> 10->10->3
        curr1 = l1; 
        curr2 = l2;
        ListNode head = null;
        while (n1 > 0 && n2 > 0) {
            int val = 0;
            if (n1 >= n2) {
                val += curr1.val; 
                curr1 = curr1.next; 
                --n1;    
            }
            if (n1 < n2) {
                val += curr2.val; 
                curr2 = curr2.next;
                --n2;    
            }
                
            // update the result: add to front
            ListNode curr = new ListNode(val);
            curr.next = head;
            head = curr;    
        }

        // take the carry into account
        // to have all elements to be less than 10
        // 10->10->3 --> 0->1->4 --> 4->1->0
        curr1 = head;
        head = null;
        int carry = 0;
        while (curr1 != null) {
            // current sum and carry
            int val = (curr1.val + carry) % 10;
            carry = (curr1.val + carry) / 10;
            
            // update the result: add to front
            ListNode curr = new ListNode(val);
            curr.next = head;
            head = curr;

            // move to the next elements in the list
            curr1 = curr1.next;    
        }
        
        // add the last carry
        if (carry != 0) {
            ListNode curr = new ListNode(carry);
            curr.next = head;
            head = curr;    
        }

        return head;
    }
}
