// Given a binary tree, return all root-to-leaf paths.

// For example, given the following binary tree:

//    1
//  /   \
// 2     3
//  \
//   5
// All root-to-leaf paths are:

// ["1->2->5", "1->3"]

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
		
        List<String> paths = new ArrayList<String>();
        
        if(root == null) {
            return paths;
        }
        
        dfs(root, "", paths);
        
        return paths;
    }
    
    public void dfs(TreeNode root, String path, List<String> paths) {
		
        path += root.val;
		
		if(root.left == null && root.right == null) {
            paths.add(path);
        }

        if(root.left != null) {
            dfs(root.left, path + "->", paths);
        }

        if(root.right != null) {
            dfs(root.right, path + "->", paths);
        }
    }
}
