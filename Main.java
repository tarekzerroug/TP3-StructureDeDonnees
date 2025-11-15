
public class Main {
    public static void main(String[] args) {
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

    

}}