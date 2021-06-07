package meetup;

public class BinarySearchTree {
    /* Class containing left and right child of current node and key value*/
    class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode(int item) {
            val = item;
            left = right = null;
        }
    }

    // Root of BST
    TreeNode root;

    // Constructor
    BinarySearchTree() {
        root = null;
    }

    // This method mainly calls insertRec()
    void insert(int key) {
        root = insertRec(root, key);
    }

    /* A recursive function to insert a new key in BST */
    TreeNode insertRec(TreeNode root, int key) {
        // Solves the problem of having an empty tree...
        if(root == null){
            root = new TreeNode(key);
            return root;
        }

        if(key < root.val){
            root.left = insertRec(root.left, key);
        }else if(key > root.val)
            root.right = insertRec(root.right, key);

        /* return the (unchanged) node pointer */
        return root;
    }

    // This method mainly calls InorderRec()
    void inorder() {
        inorderRec(root);
    }

    // A utility function to do inorder traversal of BST
    void inorderRec(TreeNode root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.val);
            inorderRec(root.right);
        }
    }

    // Driver Program to test above functions
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        tree.insert(5);
        tree.insert(3);
        tree.insert(8);
        tree.insert(1);
        tree.insert(4);
        tree.insert(7);
        tree.insert(10);
        tree.insert(2);

        // print inorder traversal of the BST
        tree.inorder();
    }
}

