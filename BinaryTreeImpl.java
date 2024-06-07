final class TreeNode{
    private static final int NaN = 0;
    public int data;
    public TreeNode left;
    public TreeNode right;
    TreeNode(){
        data=NaN;
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
        // root=new TreeNode();
        root=null;
    }
    BinaryTree(TreeNode root){
        this.root=root;
    }
    public void insert(int data){
        if(root==null){
            root=new TreeNode(data);
        }else{
            insertRecur(root,data);
        }
    }
    public void insertRecur(TreeNode root,int data){
        if(root.data>data){
            if(root.left==null){
                root.left=new TreeNode(data);
            }else{
                insertRecur(root.left, data);
            }
        }else{
            if(root.right==null){
                root.right=new TreeNode(data);
            }else{
                insertRecur(root.right, data);
            }
        }
    }
}
public class BinaryTreeImpl {

    
}