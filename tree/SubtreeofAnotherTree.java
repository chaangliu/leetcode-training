package tree;

import java.util.HashSet;

/**
 * Created by DrunkPiano on 2017/5/7.
 */

public class SubtreeofAnotherTree {
//    public boolean isSubtree(TreeNode s, TreeNode t) {
//        if (s!=null){
//            if (s.val == t.val) return checkSame(s , t );
//            else return isSubtree(s.left , t) || isSubtree(s.right , t);
//        }
//        return false ;
////        return s != null && (checkSame(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t));
//    }
//
//    private boolean checkSame(TreeNode s, TreeNode t) {
//        if (t == null && s == null) return true;
//        if (s == null || t == null) return false;
//        if (s.val != t.val) return false;
//        return checkSame(s.left, t.left) && checkSame(s.right, t.right);
//    }

    HashSet<String> trees = new HashSet<>();
    String val = "";

    public boolean isSubtree(TreeNode s, TreeNode t) {
        String tree = inorder(t);
        findAllSubTrees(s);
        return trees.contains(tree);
    }

    public String findAllSubTrees(TreeNode s) {
        if (s == null) {
            return "";
        }
        val = findAllSubTrees(s.left) + s.val + findAllSubTrees(s.right);
        trees.add(val);
        return val;
    }

    public String inorder(TreeNode t) {
        if (t == null)
            return "";
        return inorder(t.left) + t.val + inorder(t.right);
    }


//    HashSet < String > trees = new HashSet < > ();
//    public boolean isSubtree(TreeNode s, TreeNode t) {
//        String tree1 = preorder(s, true);
//        String tree2 = preorder(t, true);
//        System.out.println(tree1);
//        System.out.println(tree2);
//        return tree1.indexOf(tree2) >= 0;
//    }
//    public String preorder(TreeNode t, boolean left) {
//        if (t == null) {
//            if (left)
//                return "lnull";
//            else
//                return "rnull";
//        }
//        return    preorder(t.left, true)+" " + "#"+t.val + " " + preorder(t.right, false);
//    }


    public static void main(String args[]) {
        TreeNode root = new TreeNode(2);
        TreeNode singleRoot = new TreeNode(1);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        System.out.println(new SubtreeofAnotherTree().isSubtree(root, singleRoot));
    }
}
