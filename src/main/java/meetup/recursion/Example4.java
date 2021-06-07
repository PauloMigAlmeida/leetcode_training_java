package meetup.recursion;

public class Example4 {



    static class TreeNode{
        TreeNode left, right;
        int val;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    '}';
        }
    }
    /*
        [1,2,3,4,5]
                                4
                               / \
                              2  5
                             / \
                            1  3
                      */
    public boolean findValue(TreeNode root, int valueWeAreLookingFor){
            //1. check if value**** is > or < - Done
            // check if root.val == value**** - Done
            // check root is null - Done***
            // check if root.left || root.right is there - Don't need
            // propagate the return value. - Done
        if(root != null){
            if(root.val == valueWeAreLookingFor)
                return true;
            else if(root.val > valueWeAreLookingFor)
                return findValue(root.left, valueWeAreLookingFor);
            else
                return findValue(root.right, valueWeAreLookingFor);
        }
        return false;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.right = new TreeNode(5);

        TreeNode left_1_level = new TreeNode(2);
        root.left = left_1_level;

        left_1_level.left = new TreeNode(1);
        left_1_level.right = new TreeNode(3);

        var instance = new Example4();
        assert instance.findValue(root, 1);
        assert instance.findValue(root, 2);
        assert instance.findValue(root, 3);
        assert instance.findValue(root, 4);
        assert instance.findValue(root, 5);
        assert instance.findValue(root, 6) == false;
    }
}
