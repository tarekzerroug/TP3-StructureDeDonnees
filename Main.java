
public class Main {
    public static void main(String[] args) {
    Q1 repo = new Q1();

    repo.insert(1000, "a1b2c3", "Alice");
    repo.insert(500, "d4e5f6", "Bob");
    repo.insert(1500, "g7h8i9", "Alice");
    repo.insert(750, "j1k2l3", "Charlie");
    repo.insert(1200, "m4n5o6", "Bob");
    


    System.out.println(repo.findCommit(752));
   System.out.println(repo.findCommit(1200));

}}