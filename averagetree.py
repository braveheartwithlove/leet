Check if a given Binary Tree is SumTree
Last Updated: 18-11-2020
Write a function that returns true if the given Binary Tree is SumTree else false. A SumTree is a Binary Tree where the value of a node is equal to the sum of the nodes present in its left subtree and right subtree. An empty tree is SumTree and sum of an empty tree can be considered as 0. A leaf node is also considered as SumTree.
Following is an example of SumTree. 
          26
        /   \
      10     3
    /    \     \
  4      6      3
  
Method 1 ( Simple ) 

Get the sum of nodes in the left subtree and right subtree. 
Check if the sum calculated is equal to the root’s data. Also,
 recursively check if the left and right subtrees are SumTrees.
# Python3 program to implement 
# the above approach
# A binary tree node has data, 
# left child and right child
class node:

	def __init__(self, x):
	
		self.data = x
		self.left = None
		self.right = None

# A utility function to get the sum
# of values in tree with root as root 
def sum(root):

	if(root == None):
		return 0
	return (sum(root.left) +
			root.data +
			sum(root.right))

# returns 1 if sum property holds 
# for the given node and both of 
# its children 

# Computes the number of nodes in tree 
def size(node): 
    if node is None: 
        return 0 
    else: 
        return (size(node.left)+ 1 + size(node.right)) 

		
def isAvgTree(node):

	# ls, rs

	# If node is None or it's a leaf 
	# node then return true
	if(node == None or
	(node.left == None and
	node.right == None)):
		return 1

	# Get sum of nodes in left and 
	# right subtrees 
	ls = sum(node.left)
	rs = sum(node.right)
	size_left = size(node.left)
	size_right = size(node.right)
	
	# if the node and both of its children
	# satisfy the property return 1 else 0
	if((node.data == (ls + rs)/(size_left+size_right)) and
		isAvgTree(node.left) and
		isAvgTree(node.right)):
		return 1

	return 0		
	
def isSumTree(node):

	# ls, rs

	# If node is None or it's a leaf 
	# node then return true
	if(node == None or
	(node.left == None and
	node.right == None)):
		return 1

	# Get sum of nodes in left and 
	# right subtrees 
	ls = sum(node.left)
	rs = sum(node.right)

	# if the node and both of its children
	# satisfy the property return 1 else 0
	if((node.data == ls + rs) and
		isSumTree(node.left) and
		isSumTree(node.right)):
		return 1

	return 0

# Driver code
if __name__ == '__main__':

	root = node(26)
	root.left= node(10)
	root.right = node(3)
	root.left.left = node(4)
	root.left.right = node(6)
	root.right.right = node(3)
	
	if(isSumTree(root)):
		print("The given tree is a SumTree ")
	else:
		print("The given tree is not a SumTree ")

# This code is contributed by Mohit Kumar 29


# Python Program to find the size of binary tree 
  
# A binary tree node 
class Node: 
  
    # Constructor to create a new node 
    def __init__(self, data): 
        self.data = data  
        self.left = None
        self.right = None
  
# Computes the number of nodes in tree 
def size(node): 
    if node is None: 
        return 0 
    else: 
        return (size(node.left)+ 1 + size(node.right)) 
  
  
# Driver program to test above function 
root = Node(1) 
root.left = Node(2) 
root.right = Node(3) 
root.left.left  = Node(4) 
root.left.right = Node(5) 
  
print "Size of the tree is %d" %(size(root)) 
  
# This code is contributed by Nikhil Kumar Singh(nickzuck_007) 

C code:
#include <stdio.h>
#include <stdlib.h>

/* A binary tree node has data, left child and right child */
struct node
{
	int data;
	struct node* left;
	struct node* right;
};

/* A utility function to get the sum of values in tree with root
as root */
int sum(struct node *root)
{
if(root == NULL)
	return 0;
return sum(root->left) + root->data + sum(root->right);
}

/* returns 1 if sum property holds for the given
	node and both of its children */
int isSumTree(struct node* node)
{
	int ls, rs;

	/* If node is NULL or it's a leaf node then
	return true */
	if(node == NULL ||
			(node->left == NULL && node->right == NULL))
		return 1;

/* Get sum of nodes in left and right subtrees */
ls = sum(node->left);
rs = sum(node->right);

/* if the node and both of its children satisfy the
	property return 1 else 0*/
	if((node->data == ls + rs)&&
			isSumTree(node->left) &&
			isSumTree(node->right))
		return 1;

return 0;
}

/*
Helper function that allocates a new node
with the given data and NULL left and right
pointers.
*/
struct node* newNode(int data)
{
	struct node* node =
		(struct node*)malloc(sizeof(struct node));
	node->data = data;
	node->left = NULL;
	node->right = NULL;
	return(node);
}

/* Driver program to test above function */
int main()
{
	struct node *root = newNode(26);
	root->left		 = newNode(10);
	root->right	 = newNode(3);
	root->left->left = newNode(4);
	root->left->right = newNode(6);
	root->right->right = newNode(3);
	if(isSumTree(root))
		printf("The given tree is a SumTree ");
	else
		printf("The given tree is not a SumTree ");

	getchar();
	return 0;
}
