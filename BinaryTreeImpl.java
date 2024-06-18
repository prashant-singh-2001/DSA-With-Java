import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// Class representing a node in a binary tree
final class TreeNode {
    private static final int NaN = 0; // Define a constant for NaN value
    public int data; // Data stored in the node
    public TreeNode left; // Left child
    public TreeNode right; // Right child

    // Default constructor initializing data to NaN and children to null
    TreeNode() {
        data = NaN;
        left = null;
        right = null;
    }

    // Constructor initializing data and setting children to null
    TreeNode(int data) {
        this(); // Call the default constructor
        this.data = data;
    }

    // Constructor initializing data and children to given values
    TreeNode(int data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}

// Binary tree class
class BinaryTree {
    private TreeNode root; // Root of the binary tree
    public int height; // Height of the binary tree
    public int nodeCount; // Number of nodes in the binary tree

    // Default constructor
    BinaryTree() {
        root = null;
        calculateHeight();
        nodeCount = this.inOrderTraversal().size();
    }

    // Constructor with root node parameter
    BinaryTree(TreeNode root) {
        this.root = root;
        calculateHeight();
        nodeCount = this.inOrderTraversal().size();
    }

    // Static function to check if two binary trees are identical
    public static boolean identicalTrees(BinaryTree a, BinaryTree b) {
        if (a.root == null && b.root == null)
            return true;
        return identicalTrees(a.root, b.root);
    }

    // Recursive function to check if two nodes are identical
    private static boolean identicalTrees(TreeNode a, TreeNode b) {
        if (a == null && b == null)
            return true;
        else if (a == null ^ b == null)
            return false;
        return b.data == a.data && identicalTrees(a.right, b.right) && identicalTrees(a.left, b.left);
    }

    // Static function to get the top view of the tree
    public static List<Integer> topViewOfTree(BinaryTree bt) {
        Map<Integer, Integer> topView = new TreeMap<>();
        topViewOfTree(bt.root, 0, topView);
        return new ArrayList<>(topView.values());
    }

    // Recursive function to calculate top view of the tree
    private static void topViewOfTree(TreeNode root, int side, Map<Integer, Integer> topView) {
        if (root == null)
            return;
        if (!topView.containsKey(side)) {
            topView.put(side, root.data);
        }
        topViewOfTree(root.left, side - 1, topView);
        topViewOfTree(root.right, side + 1, topView);
    }

    // Static function to get the bottom view of the tree
    public static List<Integer> bottomViewOfTree(BinaryTree bt) {
        Map<Integer, Integer> bottomView = new TreeMap<>();
        bottomViewOfTree(bt.root, 0, bottomView);
        return new ArrayList<>(bottomView.values());
    }

    // Recursive function to calculate bottom view of the tree
    private static void bottomViewOfTree(TreeNode root, int side, Map<Integer, Integer> bottomView) {
        if (root == null)
            return;
        bottomView.put(side, root.data);
        bottomViewOfTree(root.left, side - 1, bottomView);
        bottomViewOfTree(root.right, side + 1, bottomView);
    }

    // Instance function to insert a node into the binary tree
    public void insert(int data) {
        if (root == null) {
            root = new TreeNode(data);
        } else {
            insertRecur(root, data);
        }
        calculateHeight();
        nodeCount = this.inOrderTraversal().size();
    }

    // Recursive function to insert a node into the binary tree
    public void insertRecur(TreeNode root, int data) {
        if (root.data >= data) {
            if (root.left == null) {
                root.left = new TreeNode(data);
            } else {
                insertRecur(root.left, data);
            }
        } else {
            if (root.right == null) {
                root.right = new TreeNode(data);
            } else {
                insertRecur(root.right, data);
            }
        }
    }

    // Instance function to delete a node from the binary tree
    public boolean delete(int n) {
        TreeNode temp = root;
        TreeNode prev = null;

        // Find the node to delete
        while (temp != null && temp.data != n) {
            prev = temp;
            if (n < temp.data) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }

        // Node not found
        if (temp == null) {
            return false;
        }

        // Cases for deletion
        if (temp.left == null && temp.right == null) {
            if (temp == root) {
                root = null;
            } else if (prev.left == temp) {
                prev.left = null;
            } else {
                prev.right = null;
            }
        } else if (temp.left == null || temp.right == null) {
            TreeNode child = (temp.left != null) ? temp.left : temp.right;
            if (temp == root) {
                root = child;
            } else if (prev.left == temp) {
                prev.left = child;
            } else {
                prev.right = child;
            }
        } else {
            TreeNode successor = findMin(temp.right);
            int successorValue = successor.data;
            delete(successorValue);
            temp.data = successorValue;
        }
        calculateHeight();
        nodeCount = this.inOrderTraversal().size();
        return true;
    }

    // Helper function to find the minimum value node in a subtree
    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Instance function to find the depth of a node with a given value
    public int depthOf(int a) {
        return depthOf(root, a, 0);
    }

    // Recursive function to find the depth of a node with a given value
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

    // Private function to calculate the height of the tree
    private void calculateHeight() {
        this.height = heightHelper(root);
    }

    // Helper function to calculate the height of a node
    private int heightHelper(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = heightHelper(node.left);
        int rightHeight = heightHelper(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Instance function to check if a node with a given value exists in the tree
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

    // Instance function to check if the binary tree is balanced
    public boolean isBalance() {
        return isBalance(root.left, root.right);
    }

    // Private recursive function to check if the subtree is balanced
    private boolean isBalance(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        else if (left != null && right != null)
            return isBalance(left.left, left.right) && isBalance(right.left, right.right);
        else
            return false;
    }

    // Instance function to find the lowest common ancestor of two nodes
    public int lowestCommonAncestor(int a, int b) {
        return lowestCommonAncestor(root, a, b).data;
    }

    // Recursive function to find the lowest common ancestor of two nodes
    private TreeNode lowestCommonAncestor(TreeNode root, int a, int b) {
        if (root == null || root.data == a || root.data == b) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, a, b);
        TreeNode right = lowestCommonAncestor(root.right, a, b);
        if (left != null && right != null) {
            return root;
        }
        return (left != null) ? left : right;
    }

    // Various traversal techniques for a binary tree

    // Preorder traversal starting from the root
    public List<Integer> preOrderTraversal() {
        List<Integer> ls = new ArrayList<>(); // List to store traversal result
        preOrderTraversalHelper(root, ls); // Call the helper function
        return ls; // Return the result list
    }

    // Helper function for preorder traversal
    private void preOrderTraversalHelper(TreeNode root, List<Integer> ls) {
        if (root == null)
            return;
        ls.add(root.data); // Visit the node
        preOrderTraversalHelper(root.left, ls); // Traverse left subtree
        preOrderTraversalHelper(root.right, ls); // Traverse right subtree
    }

    // Inorder traversal starting from the root
    public List<Integer> inOrderTraversal() {
        List<Integer> ls = new ArrayList<>(); // List to store traversal result
        inOrderTraversalHelper(root, ls); // Call the helper function
        return ls; // Return the result list
    }

    // Helper function for inorder traversal
    private void inOrderTraversalHelper(TreeNode root, List<Integer> ls) {
        if (root == null)
            return;
        inOrderTraversalHelper(root.left, ls); // Traverse left subtree
        ls.add(root.data); // Visit the node
        inOrderTraversalHelper(root.right, ls); // Traverse right subtree
    }

    // Postorder traversal starting from the root
    public List<Integer> postOrderTraversal() {
        List<Integer> ls = new ArrayList<>(); // List to store traversal result
        postOrderTraversalHelper(root, ls); // Call the helper function
        return ls; // Return the result list
    }

    // Helper function for postorder traversal
    private void postOrderTraversalHelper(TreeNode root, List<Integer> ls) {
        if (root == null)
            return;
        postOrderTraversalHelper(root.left, ls); // Traverse left subtree
        postOrderTraversalHelper(root.right, ls); // Traverse right subtree
        ls.add(root.data); // Visit the node
    }

    // Level order traversal starting from the root, storing nodes by level
    public Map<Integer, List<Integer>> levelOrderTraversal() {
        Map<Integer, List<Integer>> ml = new HashMap<>();
        levelOrderTraversalHelper(root, 0, ml);
        return ml;
    }

    // Helper function for level order traversal
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

    // Method to visualize the binary tree with nodes displayed at appropriate
    // levels
    public void printTree() {
        printTreeHelper(root, 0);
    }

    // Helper function for printing the tree in a readable format
    private void printTreeHelper(TreeNode node, int level) {
        if (node == null) {
            return;
        }
        printTreeHelper(node.right, level + 1); // Print right subtree
        if (level != 0) {
            for (int i = 0; i < level - 1; i++) {
                System.out.print("|\t"); // Print indentation for tree structure
            }
            System.out.println("|-------" + node.data); // Print node value
        } else {
            System.out.println(node.data); // Print root node value
        }
        printTreeHelper(node.left, level + 1); // Print left subtree
    }
}

// Class to demonstrate operations on Binary Trees
public class BinaryTreeImpl {

    // Main method to run the program
    public static void main(String[] args) {
        // Array of elements to insert into the first binary tree
        int[] arr = { 5, 2, 1, 3, 7, 6, 9, 8, 10 };
        BinaryTree binaryTree = new BinaryTree(); // Create a new binary tree
        for (int i : arr) {
            binaryTree.insert(i); // Insert elements into the binary tree
        }

        // Array of elements to insert into the second binary tree
        int[] arr2 = { 5, 2, 1, 3, 8, 6, 9, 7, 10 };
        BinaryTree binaryTreeBeta = new BinaryTree(); // Create another binary tree
        for (int i : arr2) {
            binaryTreeBeta.insert(i); // Insert elements into the binary tree
        }

        // Output section separator
        System.out.println("==========================================================================");

        // Output the height of the first tree
        System.out.println("\nHeight of Tree :");
        System.out.println(binaryTree.height);

        // Output section separator
        System.out.println("\n\n\n==========================================================================");

        // Output the total number of nodes in the first tree
        System.out.println("\nTotal Node :");
        System.out.println(binaryTree.nodeCount);

        // Output section separator
        System.out.println("\n\n\n==========================================================================");

        // Output whether the first tree is balanced or not
        System.out.println("\nIs Balanced : ");
        System.out.println(binaryTree.isBalance());

        // Output section separator
        System.out.println("\n\n\n==========================================================================");

        // Output the pre-order traversal of the first tree
        System.out.println("Pre-order Traversal:");
        System.out.println(binaryTree.preOrderTraversal().toString());

        // Output section separator
        System.out.println("\n\n\n==========================================================================");

        // Output the in-order traversal of the first tree
        System.out.println("In-order Traversal:");
        System.out.println(binaryTree.inOrderTraversal().toString());

        // Output section separator
        System.out.println("\n\n\n==========================================================================");

        // Output the post-order traversal of the first tree
        System.out.println("Post-order Traversal:");
        System.out.println(binaryTree.postOrderTraversal().toString());

        // Output section separator
        System.out.println("\n\n\n==========================================================================");

        // Output the level-order traversal of the first tree
        System.out.println("Level-order Traversal:");
        System.out.println(binaryTree.levelOrderTraversal().toString());

        // Output section separator
        System.out.println("\n\n\n==========================================================================");

        // Output the visualization of the first tree's structure
        System.out.println("\nTree Visualization:");
        binaryTree.printTree(); // Print the tree structure

        // Output section separator
        System.out.println("\n\n\n==========================================================================");

        // Output the lowest common ancestor of nodes 10 and 6 in the first tree
        System.out.println("\nLCA of 10,6");
        System.out.println(binaryTree.lowestCommonAncestor(10, 6));

        // Output section separator
        System.out.println("\n\n\n==========================================================================");

        // Output the depth of node 10 in the first tree
        System.out.println("\nDepth of node 10 is : ");
        System.out.println(binaryTree.depthOf(10));

        // Output section separator
        System.out.println("\n\n\n==========================================================================");

        // Output whether the two trees are identical or not
        System.out.println("\nAre Trees identical :");
        System.out.println(BinaryTree.identicalTrees(binaryTree, binaryTreeBeta));

        // Output section separator
        System.out.println("\n\n\n==========================================================================");

        // Output the top view of the first tree
        System.out.println("\nTop View of Tree :");
        System.out.println(BinaryTree.topViewOfTree(binaryTree));

        // Output section separator
        System.out.println("\n\n\n==========================================================================");

        // Output the bottom view of the first tree
        System.out.println("\nBottom View of Tree :");
        System.out.println(BinaryTree.bottomViewOfTree(binaryTree));
    }
}
