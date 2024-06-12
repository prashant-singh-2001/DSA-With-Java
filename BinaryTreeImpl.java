import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    BinaryTree() {
        root = null; // Initialize the root to null
    }

    BinaryTree(TreeNode root) {
        this.root = root; // Set the root to the given node
    }

    public void insert(int data) {
        if (root == null) {
            root = new TreeNode(data); // Create a new root node if tree is empty
        } else {
            insertRecur(root, data); // Recursively insert the data
        }
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

        return true;
    }

    // Helper function to find the minimum value node in a subtree
    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left; // Go to the leftmost node
        }
        return node; // Return the leftmost node
    }

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
}

public class BinaryTreeImpl {
    public static void main(String[] args) {
        int[] arr = { 5, 1, 2, 5, 6, 4, 45, 2, 9, 7, 3, 4, 5, 6, 3, 1, 2, 5, 4, 7, 8, 9, 6, 4, 5, 9, 7, 6, 5, 4, 2, 3,
                1, 7, 8, 6, 5, 4, 9, 3, 2, 5, 7, 4, 1, 2, 58, 9 };
        BinaryTree binaryTree = new BinaryTree(); // Create a new binary tree
        for (int i : arr) {
            binaryTree.insert(i); // Insert elements into the binary tree
        }
        System.out.println(binaryTree.preOrderTraversal().toString()); // Print pre-order traversal
        System.out.println(binaryTree.inOrderTraversal().toString()); // Print in-order traversal
        System.out.println(binaryTree.postOrderTraversal().toString()); // Print post-order traversal
    }
}
