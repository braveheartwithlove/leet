515. Find Largest Value in Each Tree Row

#BFS Do level-order traversal and keep the max of any level

public List<Integer> largestValues(TreeNode root) {
	if (root == null) return Collections.emptyList();

	Queue<TreeNode> queue = new LinkedList<>();
	queue.offer(root);

	List<Integer> result = new ArrayList<>();

	while (!queue.isEmpty()) {
		int max = Integer.MIN_VALUE;
		for (int count = queue.size(); count > 0; count--) {
			TreeNode current = queue.poll();
			if (current.left != null) queue.offer(current.left);
			if (current.right != null) queue.offer(current.right);
			
			max = Math.max(max, current.val);
		}
		result.add(max);
	}
	return result;
}

#DFS Pre-order traversal. In each call, you pass the level on which the max value has to be set.

public List<Integer> largestValues(TreeNode root) {
	if (root == null) return Collections.emptyList();

	List<Integer> result = new ArrayList<>();
	dfs(root, 0, result);
	return result;
}

private void dfs(TreeNode root, int level, List<Integer> result) {
	if (root == null) return;

	if (result.size() == level) result.add(root.val); // New entry
	else 
		result.set(level, Math.max(result.get(level), root.val)); // Update if necessary

	dfs(root.left, level + 1, result);
	dfs(root.right, level + 1, result);
}
