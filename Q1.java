import java.util.*;


public class Q1 {
    

    private class CommitNode {
        int timestamp;
        String commitHash;
        String author;
        CommitNode left, right;
        
        public CommitNode(int timestamp, String commitHash, String author) {
        this.timestamp = timestamp;
         this.commitHash = commitHash;
         this.author  = author;
 
        }
    }
    
    private CommitNode root;
    
    public Q1() {
    }
    

    public void insert(int timestamp, String commitHash, String author) {
        CommitNode newnode = new CommitNode(timestamp, commitHash, author);
        

        if (root == null) {
            root = newnode ;
            return ;
        }
        CommitNode marqueur = root ;

        while(true) {
          if (newnode.timestamp < marqueur.timestamp ) {
                if (marqueur.left == null) {
                  marqueur.left = newnode ;
                  System.out.println("inserted left of " + marqueur.timestamp);
                  return ;
                } else{
                  marqueur = marqueur.left;
                }
                                
            } else if (newnode.timestamp > marqueur.timestamp) {
                
                if (marqueur.right == null) {
                  marqueur.right = newnode ;
                  System.out.println("inserted right of " + marqueur.timestamp);

                  return ;
                } else{
                  marqueur = marqueur.right;
                }
            }

         
        }
        
    
    }
        
  

    public String findCommit(int timestamp) {
        CommitNode marqueur = root;

    while (marqueur != null) {
        if (timestamp == marqueur.timestamp) {
            return marqueur.commitHash;
        } else if (timestamp < marqueur.timestamp) {
            marqueur = marqueur.left;
        } else {
            marqueur = marqueur.right;
        }
    }

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
    

    // debogage 
    // à supprimer après
    public void printRootInfo() {
    if (root == null) {
        System.out.println("Tree is empty");
    } else {
        System.out.println("Root: " + root.timestamp);
        System.out.println("Left: " + (root.left != null ? root.left.timestamp : "null"));
        System.out.println("Right: " + (root.right != null ? root.right.timestamp : "null"));
    }
}
    
}

