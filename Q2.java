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
    
    public boolean isValidBST(TreeNode root) {
        // TODO
        return false;
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