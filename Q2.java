import java.util.*;

public class Q2 {
    
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        public TreeNode(int val) {
            this.val = val;
        }
    }
    
    private TreeNode root;
    
    // on rajoute la fonction in order pour parcourir dans l'ordre croissant l'arbre .
    public void inorder(List<Integer> l,TreeNode root){
         if(root==null){
            return;
         }
         inorder(l,root.left);
         l.add(root.val);
         inorder(l,root.right);
    }
    
    
    
    
    
    
    public Q2(TreeNode root) {
        this.root = root;
    }
    
    public Q2() {
        this.root = null;
    }
    
    public int maxSumBST(TreeNode root) {
        // TODO
        return 0;
    }
    
    public boolean isValidBST(TreeNode root)  {
        List<Integer> l=new ArrayList<>();
        inorder(l,root);
        for (int i = 0; i < l.size() - 1; i++) {
            if (l.get(i) >= l.get(i + 1)) {
                    return false;
            }
        }
        return true;
    }
    
    public List<Integer> findAllBSTRoots() {
        // TODO
        return new ArrayList<>();
    }
    
    public int countValidBSTs() {
        // TODO
        return 0;
    }
    
    public int getMinBSTSum() {
        // TODO
        return Integer.MAX_VALUE;
    }
    
    public Map<Integer, Integer> getBSTSizeDistribution() {
        // TODO
        return new HashMap<>();
    }
    
    public TreeNode findLargestBST() {
        // TODO
        return null;
    }
    
    public List<Integer> getInorderBST(TreeNode root) {
        // TODO
        return new ArrayList<>();
    }
}