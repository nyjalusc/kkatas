
class Solution {
    public static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode(int n) {
            this.value = n;
            this.left = null;
            this.right = null;
        }
    }

    // Use an extra class to wrap the original TreeNode class because we need to preserve the isUnival info for each node.
    private static class NodeInfo {
        TreeNode node;
        boolean isUnival;
        NodeInfo(TreeNode node) {
            this.node = node;
        }
    }

    public static int countUnivalSubtrees(TreeNode root) {
        if (root == null) return 0;
        NodeInfo nodeInfo = new NodeInfo(root);
        return countUnival(root, nodeInfo);
    }

    // Bottom up (postOrder) style approach.
    // Once we reach the leaf node, isUnival information for each parent node is set as the recursion unfolds.
    // Simultaneously we also compute the count and return.
    // Unival count at current node is leftSubtree count + rightSubtree count.
    // + 1 if the current node itself is representing a unival subtree.
    private static int countUnival(TreeNode root, NodeInfo nodeInfo) {
        if (root == null) {
            nodeInfo.isUnival = true;
            return 0;
        }

        int left = 0, right = 0;
        NodeInfo leftNodeInfo = new NodeInfo(root.left);
        left = countUnival(root.left, leftNodeInfo);

        NodeInfo rightNodeInfo = new NodeInfo(root.right);
        right = countUnival(root.right, rightNodeInfo);

        int result = left + right;
        if (leftNodeInfo.isUnival == true && rightNodeInfo.isUnival == true) {
            if ((root.left == null && root.right == null)
                || (root.left == null && (root.right != null && root.value == root.right.value))
                || (root.right == null && (root.left != null && root.value == root.left.value))
                || (root.left != null && root.right != null && (root.value == root.left.value && root.value == root.right.value))) {

                // Count itself if both left and right child are unival subtrees
                result++;
                nodeInfo.isUnival = true;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String nodeValues = "1 3 2 null null 2 2";
        TreeNode root = buildTree(nodeValues.split(" "), 0);
        printTree(root);
        System.out.println("\n" + countUnivalSubtrees(root));
    }

    private static void printTree(TreeNode root) {
        if (root == null) return;

        printTree(root.left);
        System.out.print(root.value + "\t");
        printTree(root.right);
    }

    private static TreeNode buildTree(String[] nodeValues, int index) {
        System.out.println("Size: " + nodeValues.length + " Index: " + index);
        // Base case
        if (index >= nodeValues.length) return null;

        // Root is null
        if (nodeValues[index].equals("null")) return null;

        TreeNode root = new TreeNode(Integer.parseInt(nodeValues[index]));
        root.left = buildTree(nodeValues, (2 * index) + 1);
        root.right = buildTree(nodeValues, 2 * (index + 1));
        return root;
    }
}