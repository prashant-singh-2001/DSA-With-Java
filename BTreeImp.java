import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
class BTree {
    private class InnerBTree {
        private static final int NaN = 0;
        private int data;
        private BTree leftSubTree;
        private BTree rightSubTree;

        InnerBTree() {
            data = NaN;
            leftSubTree = null;
            rightSubTree = null;
        }

        InnerBTree(int data) {
            this();
            this.data = data;
        }

        InnerBTree(int data, BTree leftSubTree, BTree rightSubTree) {
            this.data = data;
            this.leftSubTree = leftSubTree;
            this.rightSubTree = rightSubTree;
        }
    }

    private InnerBTree root;

    BTree() {
        root = new InnerBTree();
    }

    BTree(int data) {
        root = new InnerBTree(data);
    }

    BTree(BTree b) {
        root = new InnerBTree(b.root.data, b.root.leftSubTree, b.root.rightSubTree);
    }

    public boolean insert(int data) {
        if (root.data == InnerBTree.NaN) {
            root.data = data;
            return true;
        }
        return insertRec(root, data);
    }

    private boolean insertRec(InnerBTree node, int data) {
        if (data < node.data) {
            if (node.leftSubTree == null) {
                node.leftSubTree = new BTree(data);
                return true;
            } else {
                return node.leftSubTree.insert(data);
            }
        } else if (data > node.data) {
            if (node.rightSubTree == null) {
                node.rightSubTree = new BTree(data);
                return true;
            } else {
                return node.rightSubTree.insert(data);
            }
        } else {
            return false; // Duplicate data
        }
    }

    public int delete(int data) {
        if (root == null || root.data == InnerBTree.NaN) {
            return InnerBTree.NaN;
        }
        root = deleteRec(root, data);
        return data;
    }

    private InnerBTree deleteRec(InnerBTree node, int data) {
        if (node == null || node.data == InnerBTree.NaN) {
            return null;
        }

        if (data < node.data) {
            if (node.leftSubTree != null) {
                node.leftSubTree.root = deleteRec(node.leftSubTree.root, data);
            }
        } else if (data > node.data) {
            if (node.rightSubTree != null) {
                node.rightSubTree.root = deleteRec(node.rightSubTree.root, data);
            }
        } else {
            if (node.leftSubTree == null) {
                return (node.rightSubTree != null) ? node.rightSubTree.root : null;
            } else if (node.rightSubTree == null) {
                return (node.leftSubTree != null) ? node.leftSubTree.root : null;
            }

            node.data = minValue(node.rightSubTree.root);
            node.rightSubTree.root = deleteRec(node.rightSubTree.root, node.data);
        }

        return node;
    }

    private int minValue(InnerBTree node) {
        int minValue = node.data;
        while (node.leftSubTree != null && node.leftSubTree.root != null && node.leftSubTree.root.data != InnerBTree.NaN) {
            minValue = node.leftSubTree.root.data;
            node = node.leftSubTree.root;
        }
        return minValue;
    }
}



public class BTreeImp {

}
