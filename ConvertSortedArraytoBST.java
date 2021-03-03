108. Convert Sorted Array to Binary Search Tree
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth
 of the two subtrees of every node never differ by more than 1.

Example:

Given the sorted array: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5


Approach 1: Preorder Traversal: Always Choose Left Middle Node as a Root
Implement helper function helper(left, right), which constructs BST from 
nums elements between indexes left and right:

If left > right, then there is no elements available for that subtree. Return None.

Pick left middle element: p = (left + right) // 2.

Initiate the root: root = TreeNode(nums[p]).

Compute recursively left and right subtrees: root.left = helper(left, p - 1),
 root.right = helper(p + 1, right).


Return helper(0, len(nums) - 1).
class Solution {
  int[] nums;

  public TreeNode helper(int left, int right) {
    if (left > right) return null;

    // always choose left middle node as a root
    int p = (left + right) / 2;

    // preorder traversal: node -> left -> right
    TreeNode root = new TreeNode(nums[p]);
    root.left = helper(left, p - 1);
    root.right = helper(p + 1, right);
    return root;
  }

  public TreeNode sortedArrayToBST(int[] nums) {
    this.nums = nums;
    return helper(0, nums.length - 1);
  }
}