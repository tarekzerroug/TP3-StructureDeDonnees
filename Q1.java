import java.util.*;


public class Q1 {
    

    private class CommitNode {
        int timestamp;
        String commitHash;
        String author;
        CommitNode left, right;
        
        public CommitNode(int timestamp, String commitHash, String author) {
        }
    }
    
    private CommitNode root;
    
    public Q1() {
    }
    

    public void insert(int timestamp, String commitHash, String author) {
        // TODO:
    }
    

    public String findCommit(int timestamp) {
        // TODO: 
        return null;
    }

    public List<String> getCommitsBetween(int startTime, int endTime) {
        // TODO: 
        return new ArrayList<>();
    }
    
 
    public String findNearestCommit(int timestamp) {
        // TODO: 
        return null;
    }
    

    public int countCommitsByAuthor(String author) {
        // TODO: 
        return 0;
    }
    
 
    public String getMostActiveAuthor() {
        // TODO: 
        return null;
    }
    
  
    public void revertToCommit(int timestamp) {
        // TODO: 
    }
    
   
    public List<String> getTimeline() {
        // TODO: 
        return new ArrayList<>();
    }
    

    
    
    
}
