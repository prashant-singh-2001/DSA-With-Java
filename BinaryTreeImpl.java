final class TreeNode{
    public int data;
    public TreeNode left;
    public TreeNode right;
    TreeNode(){
        left=null;
        right=null;
    }
    TreeNode(int data){
        this();
        this.data=data;
    }
    TreeNode(int data, TreeNode left, TreeNode right){
        this.data=data;
        this.left=left;
        this.right=right;
    }
}
class BinaryTree{
    private TreeNode root;
    BinaryTree(){
        root=new TreeNode();
    }
    BinaryTree(TreeNode root){
        this.root=root;
    }
}
public class BinaryTreeImpl {

    
}