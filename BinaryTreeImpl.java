import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

final class TreeNode {
    private static final int NaN = 0; // Define a constant for NaN value
    public int data; // Data stored in the node
    public TreeNode left; // Left child
    public TreeNode right; // Right child

    TreeNode() {
        data = NaN; // Initialize data to NaN
        left = null; // Initialize left child to null
        right = null; // Initialize right child to null
    }

    TreeNode(int data) {
        this(); // Call the default constructor
        this.data = data; // Set the node's data
    }

    TreeNode(int data, TreeNode left, TreeNode right) {
        this.data = data; // Set the node's data
        this.left = left; // Set the left child
        this.right = right; // Set the right child
    }
}

class BinaryTree {
    private TreeNode root; // Root of the binary tree
    public int height;
    public int nodeCount;

    BinaryTree() {
        root = null; // Initialize the root to null
        calculateHeight();
        nodeCount = this.inOrderTraversal().size();
    }

    BinaryTree(TreeNode root) {
        this.root = root; // Set the root to the given node
        calculateHeight();
        nodeCount = this.inOrderTraversal().size();
    }

    // Static Functions
    public static boolean identicalTrees(BinaryTree a, BinaryTree b) {
        if (a.root == null && b.root == null)
            return true;
        return identicalTrees(a.root, b.root);
    }

    private static boolean identicalTrees(TreeNode a, TreeNode b) {
        if (a == null && b == null)
            return true;
        else if (a == null ^ b == null)
            return false;
        boolean identicalNode = b.data == a.data;
        boolean rightIdentical = identicalTrees(a.right, b.right);
        boolean leftIdentical = identicalTrees(a.left, b.left);
        return identicalNode && rightIdentical && leftIdentical;
    }

    public static List<Integer> topViewOfTree(BinaryTree bt) {
        Map<Integer, Integer> topView = new TreeMap<>();
        topViewOfTree(bt.root, 0, topView);
        return new ArrayList<>(topView.values());
    }

    private static void topViewOfTree(TreeNode root, int side, Map<Integer, Integer> topView) {
        if (root == null)
            return;
        if (!topView.containsKey(side)) {
            topView.put(side, root.data);
        }
        topViewOfTree(root.left, side - 1, topView);
        topViewOfTree(root.right, side + 1, topView);
    }

    public static List<Integer> bottomViewOfTree(BinaryTree bt) {
        Map<Integer, Integer> bottomView = new TreeMap<>();
        bottomViewOfTree(bt.root, 0, bottomView);
        return new ArrayList<>(bottomView.values());
    }

    private static void bottomViewOfTree(TreeNode root, int side, Map<Integer, Integer> bottomView) {
        if (root == null)
            return;
        bottomView.put(side, root.data);

        bottomViewOfTree(root.left, side - 1, bottomView);
        bottomViewOfTree(root.right, side + 1, bottomView);
    }

    // Instance function
    public void insert(int data) {
        if (root == null) {
            root = new TreeNode(data); // Create a new root node if tree is empty
        } else {
            insertRecur(root, data); // Recursively insert the data
        }
        calculateHeight();
        nodeCount = this.inOrderTraversal().size();
    }

    public void insertRecur(TreeNode root, int data) {
        if (root.data >= data) {
            if (root.left == null) {
                root.left = new TreeNode(data); // Insert new node on the left if empty
            } else {
                insertRecur(root.left, data); // Recur to the left child
            }
        } else {
            if (root.right == null) {
                root.right = new TreeNode(data); // Insert new node on the right if empty
            } else {
                insertRecur(root.right, data); // Recur to the right child
            }
        }
    }

    public boolean delete(int n) {
        TreeNode temp = root; // Start from the root
        TreeNode prev = null; // Track the parent of the current node

        // Find the node to delete
        while (temp != null && temp.data != n) {
            prev = temp; // Update the parent
            if (n < temp.data) {
                temp = temp.left; // Go to the left child
            } else {
                temp = temp.right; // Go to the right child
            }
        }

        // Node not found
        if (temp == null) {
            return false;
        }

        // Case 1: Node to delete has no children (leaf node)
        if (temp.left == null && temp.right == null) {
            if (temp == root) {
                root = null; // If the tree has only one node, set root to null
            } else if (prev.left == temp) {
                prev.left = null; // Remove the leaf node
            } else {
                prev.right = null; // Remove the leaf node
            }
        }
        // Case 2: Node to delete has one child
        else if (temp.left == null || temp.right == null) {
            TreeNode child = (temp.left != null) ? temp.left : temp.right; // Find the child
            if (temp == root) {
                root = child; // Replace root with the child
            } else if (prev.left == temp) {
                prev.left = child; // Replace the node with its child
            } else {
                prev.right = child; // Replace the node with its child
            }
        }
        // Case 3: Node to delete has two children
        else {
            TreeNode successor = findMin(temp.right); // Find the successor
            int successorValue = successor.data; // Store the successor's value
            delete(successorValue); // Delete the successor node
            temp.data = successorValue; // Replace temp's data with successor's data
        }
        calculateHeight();
        nodeCount = this.inOrderTraversal().size();
        return true;
    }

    // Helper function to find the minimum value node in a subtree
    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left; // Go to the leftmost node
        }
        return node; // Return the leftmost node
    }

    public int depthOf(int a) {
        return depthOf(root, a, 0);
    }

    private int depthOf(TreeNode root, int a, int depth) {
        if (root == null)
            return -1;
        if (root.data == a)
            return depth;
        if (root.data > a)
            return depthOf(root.left, a, depth + 1);
        else
            return depthOf(root.right, a, depth + 1);
    }

    private void calculateHeight() {
        this.height = heightHelper(root);
    }

    private int heightHelper(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = heightHelper(node.left);
        int rightHeight = heightHelper(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public boolean exists(int data) {
        TreeNode temp = root;
        while (temp != null) {
            if (temp.data == data) {
                return true;
            } else if (temp.data > data) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
        return false;
    }

    public boolean isBalance() {
        return isBalance(root.left, root.right);
    }

    private boolean isBalance(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        else if (left != null && right != null)
            return isBalance(left.left, left.right) && isBalance(right.left, right.right);
        else
            return false;
    }

    public int lowestCommonAncestor(int a, int b) {
        return lowestCommonAncestor(root, a, b).data;
    }

    private TreeNode lowestCommonAncestor(TreeNode root, int a, int b) {
        if (root == null || root.data == a || root.data == b) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, a, b);
        TreeNode right = lowestCommonAncestor(root.right, a, b);

        if (left != null && right != null) {
            return root;
        }

        return left != null ? left : right;
    }

    // Various Traversal Techniques
    public List<Integer> preOrderTraversal() {
        List<Integer> ls = new ArrayList<>(); // List to store traversal result
        preOrderTraversalHelper(root, ls); // Call the helper function
        return ls; // Return the result list
    }

    private void preOrderTraversalHelper(TreeNode root, List<Integer> ls) {
        if (root == null)
            return;
        ls.add(root.data); // Visit the node
        preOrderTraversalHelper(root.left, ls); // Traverse left subtree
        preOrderTraversalHelper(root.right, ls); // Traverse right subtree
    }

    public List<Integer> inOrderTraversal() {
        List<Integer> ls = new ArrayList<>(); // List to store traversal result
        inOrderTraversalHelper(root, ls); // Call the helper function
        return ls; // Return the result list
    }

    private void inOrderTraversalHelper(TreeNode root, List<Integer> ls) {
        if (root == null)
            return;
        inOrderTraversalHelper(root.left, ls); // Traverse left subtree
        ls.add(root.data); // Visit the node
        inOrderTraversalHelper(root.right, ls); // Traverse right subtree
    }

    public List<Integer> postOrderTraversal() {
        List<Integer> ls = new ArrayList<>(); // List to store traversal result
        postOrderTraversalHelper(root, ls); // Call the helper function
        return ls; // Return the result list
    }

    private void postOrderTraversalHelper(TreeNode root, List<Integer> ls) {
        if (root == null)
            return;
        postOrderTraversalHelper(root.left, ls); // Traverse left subtree
        postOrderTraversalHelper(root.right, ls); // Traverse right subtree
        ls.add(root.data); // Visit the node
    }

    public Map<Integer, List<Integer>> levelOrderTraversal() {
        Map<Integer, List<Integer>> ml = new HashMap<>();
        levelOrderTraversalHelper(root, 0, ml);
        return ml;
    }

    private void levelOrderTraversalHelper(TreeNode root, int level, Map<Integer, List<Integer>> ml) {
        if (root == null)
            return;
        if (ml.get(level) == null) {
            List<Integer> li = new ArrayList<>();
            li.add(root.data);
            ml.put(level, li);
        } else {
            List<Integer> li = ml.get(level);
            li.add(root.data);
            ml.put(level, li);
        }
        levelOrderTraversalHelper(root.left, level + 1, ml);
        levelOrderTraversalHelper(root.right, level + 1, ml);
    }

    // Method to visualize the binary tree
    public void printTree() {
        printTreeHelper(root, 0);
    }

    private void printTreeHelper(TreeNode node, int level) {
        if (node == null) {
            return;
        }
        printTreeHelper(node.right, level + 1); // Print right subtree
        if (level != 0) {
            for (int i = 0; i < level - 1; i++) {
                System.out.print("|\t");
            }
            System.out.println("|-------" + node.data);
        } else {
            System.out.println(node.data);
        }
        printTreeHelper(node.left, level + 1); // Print left subtree
    }
}

public class BinaryTreeImpl {
    public static void main(String[] args) {
        int[] arr = { 5, 2, 1, 3, 7, 6, 9, 8, 10 };
        BinaryTree binaryTree = new BinaryTree(); // Create a new binary tree
        for (int i : arr) {
            binaryTree.insert(i); // Insert elements into the binary tree
        }
        int[] arr2 = { 5, 2, 1, 3, 8, 6, 9, 7, 10 };
        BinaryTree binaryTreeBeta = new BinaryTree(); // Create a new binary tree
        for (int i : arr2) {
            binaryTreeBeta.insert(i); // Insert elements into the binary tree
        }

        System.out.println("==========================================================================");
        System.out.println("\nHeight of Tree :");
        System.out.println(binaryTree.height);

        System.out.println("\n\n\n==========================================================================");
        System.out.println("\nTotal Node :");
        System.out.println(binaryTree.nodeCount);

        System.out.println("\n\n\n==========================================================================");
        System.out.println("\nIs Balanced : ");
        System.out.println(binaryTree.isBalance());

        System.out.println("\n\n\n==========================================================================");
        System.out.println("Pre-order Traversal:");
        System.out.println(binaryTree.preOrderTraversal().toString());

        System.out.println("\n\n\n==========================================================================");
        System.out.println("In-order Traversal:");
        System.out.println(binaryTree.inOrderTraversal().toString());

        System.out.println("\n\n\n==========================================================================");
        System.out.println("Post-order Traversal:");
        System.out.println(binaryTree.postOrderTraversal().toString());

        System.out.println("\n\n\n==========================================================================");
        System.out.println("Level-order Traversal:");
        System.out.println(binaryTree.levelOrderTraversal().toString());

        System.out.println("\n\n\n==========================================================================");
        System.out.println("\nTree Visualization:");
        binaryTree.printTree(); // Print the tree structure

        System.out.println("\n\n\n==========================================================================");
        System.out.println("\nLCA of 10,6");
        System.out.println(binaryTree.lowestCommonAncestor(10, 6));

        System.out.println("\n\n\n==========================================================================");
        System.out.println("\nDepth of node 10 is : ");
        System.out.println(binaryTree.depthOf(10));

        System.out.println("\n\n\n==========================================================================");
        System.out.println("\nAre Trees identical :");
        System.out.println(BinaryTree.identicalTrees(binaryTree, binaryTreeBeta));

        System.out.println("\n\n\n==========================================================================");
        System.out.println("\nTop View of Tree :");
        System.out.println(BinaryTree.topViewOfTree(binaryTree));

        System.out.println("\n\n\n==========================================================================");
        System.out.println("\nBottom View of Tree :");
        System.out.println(BinaryTree.bottomViewOfTree(binaryTree));
    }
}
