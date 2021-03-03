426. Convert Binary Search Tree to Sorted Doubly Linked List
Medium

Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.

You can think of the left and right pointers as synonymous to the predecessor and 
successor pointers in a doubly-linked list. For a circular doubly linked list, 
the predecessor of the first element is the last element, and the successor of 
the last element is the first element.

We want to do the transformation in place. After the transformation, the left 
pointer of the tree node should point to its predecessor, and the right pointer 
should point to its successor. You should return the pointer to the smallest 
element of the linked list.
Approach 1: Recursion
Algorithm

Standard inorder recursion follows left -> node -> right order, where left and right parts are the recursion calls and node part is where all processing is done.

Processing here is basically to link the previous node with the current one, and because of that one has to track the last node which is the largest node in a 
new doubly linked list so far.
One more detail : one has to keep the first, or the smallest, node as well to
 close the ring of doubly linked list.

Here is the algorithm :

Initiate the first and the last nodes as nulls.
Call the standard inorder recursion helper(root) :
If node is not null :
Call the recursion for the left subtree helper(node.left).
If the last node is not null, link the last and the current node nodes.
Else initiate the first node.
Mark the current node as the last one : last = node.
Call the recursion for the right subtree helper(node.right).
Link the first and the last nodes to close DLL ring and then return the first node.
class Solution {
  // the smallest (first) and the largest (last) nodes
  Node first = null;
  Node last = null;

  public void helper(Node node) {
    if (node != null) {
      // left
      helper(node.left);
      // node 
      if (last != null) {
        // link the previous node (last)
        // with the current one (node)
        last.right = node;
        node.left = last;
      }
      else {
        // keep the smallest node
        // to close DLL later on
        first = node;
      }
      last = node;
      // right
      helper(node.right);
    }
  }

  public Node treeToDoublyList(Node root) {
    if (root == null) return null;

    helper(root);
    // close DLL
    last.right = first;
    first.left = last;
    return first;
  }
}