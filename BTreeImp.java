import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class BTree {
    private class InnerBTree {
        private static final int NaN = 0;
        private int data;
        private int left;
        private BTree rightSubTree;

        InnerBTree() {
            data = NaN;
            left = NaN;
            rightSubTree = null;
        }

        InnerBTree(int data) {
            this();
            this.data = data;
        }

        InnerBTree(int data, int left, BTree rightSubTree) {
            this.data = data;
            this.left = left;
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
        root = new InnerBTree(b.root.data, b.root.left, b.root.rightSubTree);
    }
}

public class BTreeImp {

}
