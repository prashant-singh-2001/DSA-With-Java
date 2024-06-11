import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

final class TreeNode {
    private static final int NaN = 0;
    public int data;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {
        data = NaN;
        left = null;
        right = null;
    }

    TreeNode(int data) {
        this();
        this.data = data;
    }

    TreeNode(int data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}

class BinaryTree {
    private TreeNode root;

    BinaryTree() {
        // root=new TreeNode();
        root = null;
    }

    BinaryTree(TreeNode root) {
        this.root = root;
    }

    public void insert(int data) {
        if (root == null) {
            root = new TreeNode(data);
        } else {
            insertRecur(root, data);
        }
    }

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

    public boolean delete(int n) {
        TreeNode temp = root;
        TreeNode prev = root;
        while (temp.data != n && temp != null && (temp.right != null || temp.left != null)) {
            prev = temp;
            if (temp.data > n) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
        if (temp == null)
            return false;
        if (temp.right == null && temp.left == null) {
            prev.left = prev.right = null;
            return true;
        }
        // if(!(temp.right == null || temp.left!=null) prev.left=temp.){

        // }

        return true;
    }

    public List<Integer> preOrderTraversal() {
        List<Integer> ls = new ArrayList<>();
        preOrderTraversalHelper(root, ls);
        return ls;
    }

    private void preOrderTraversalHelper(TreeNode root, List<Integer> ls) {
        if (root == null)
            return;
        ls.add(root.data);
        preOrderTraversalHelper(root.left, ls);
        preOrderTraversalHelper(root.right, ls);

    }

    public List<Integer> inOrderTraversal() {
        List<Integer> ls = new ArrayList<>();
        inOrderTraversalHelper(root, ls);
        return ls;
    }

    private void inOrderTraversalHelper(TreeNode root, List<Integer> ls) {
        if (root == null)
            return;
        inOrderTraversalHelper(root.left, ls);
        ls.add(root.data);
        inOrderTraversalHelper(root.right, ls);
    }

    public List<Integer> postOrderTraversal() {
        List<Integer> ls = new ArrayList<>();
        postOrderTraversalHelper(root, ls);
        return ls;
    }

    private void postOrderTraversalHelper(TreeNode root, List<Integer> ls) {
        if (root == null)
            return;
        postOrderTraversalHelper(root.left, ls);
        postOrderTraversalHelper(root.right, ls);
        ls.add(root.data);
    }
}

public class BinaryTreeImpl {
    public static void main(String[] args) {
        int[] arr = { 5, 1, 2, 5, 6, 4, 45, 2, 9, 7, 3, 4, 5, 6, 3, 1, 2, 5, 4, 7, 8, 9, 6, 4, 5, 9, 7, 6, 5, 4, 2, 3,
                1, 7, 8, 6, 5, 4, 9, 3, 2, 5, 7, 4, 1, 2, 58, 9 };
        BinaryTree binaryTree = new BinaryTree();
        for (int i : arr) {
            binaryTree.insert(i);
        }
        System.out.println(binaryTree.preOrderTraversal().toString());
        System.out.println(binaryTree.inOrderTraversal().toString());
        System.out.println(binaryTree.postOrderTraversal().toString());
    }

}