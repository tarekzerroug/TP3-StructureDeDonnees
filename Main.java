
public class Main {
    public static void main(String[] args) {
    
    
    // on réalise les tests pour le Q1
        System.out.println("-----------Q1----------");

    Q1 repo = new Q1();
    System.out.println();
    repo.insert(1000, "a1b2c3", "Alice");
    repo.insert(500, "d4e5f6", "Bob");
    repo.insert(1500, "g7h8i9", "Alice");
    repo.insert(750, "j1k213", "Charlie");
    repo.insert(1200, "m4n5o6", "Bob");

    System.out.println(repo.findCommit(750));
    System.out.println(repo.getCommitsBetween(600,1300));
    System.out.println(repo.findNearestCommit(900));
    System.out.println(repo.countCommitsByAuthor("Alice"));
    System.out.println(repo.getMostActiveAuthor());
    repo.revertToCommit(1100);
    System.out.println(repo.getTimeline());

   
   
    System.out.println("-----------Q2----------");
    // on réalise les tests pour Q2 

        Q2.TreeNode root = new Q2.TreeNode(1);

        Q2 q2 = new Q2(root);

        // Tu peux continuer à construire l’arbre :
        root.left = new Q2.TreeNode(4);
        root.right = new Q2.TreeNode(3);

        root.left.left = new Q2.TreeNode(2);
        root.left.right = new Q2.TreeNode(4);

        root.right.left = new Q2.TreeNode(2);
        root.right.right = new Q2.TreeNode(5);


        root.right.right.right = new Q2.TreeNode(6);
        root.right.right.left = new Q2.TreeNode(4);
        System.out.println(q2.isValidBST(root));

}}