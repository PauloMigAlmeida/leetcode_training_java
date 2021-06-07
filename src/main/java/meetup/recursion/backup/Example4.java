package meetup.recursion.backup;

public class Example4 {

    static class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode(int item) {
            val = item;
            left = right = null;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    '}';
        }
    }


    public boolean findValue(TreeNode root, int value){
        if(root != null){
            if(root.val == value)
                return true;
            else if (root.val > value && root.left != null)
                return findValue(root.left, value);
            else if (root.val < value && root.right != null)
                return findValue(root.right, value);
        }
        return false;
    }

    public static void main(String[] args) {
        /*
             4
            /  \
           2    5
          / \
         1   3

         */

        TreeNode root = new TreeNode(4);
        root.right = new TreeNode(5);

        TreeNode left_1_level = new TreeNode(2);
        root.left = left_1_level;

        left_1_level.left = new TreeNode(1);
        left_1_level.right = new TreeNode(3);

        Example4 instance = new Example4();


        assert instance.findValue(root, 1);
        assert instance.findValue(root, 2);
        assert instance.findValue(root, 3);
        assert instance.findValue(root, 4);
        assert instance.findValue(root, 5);
    }
}
