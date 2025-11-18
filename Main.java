
import java.util.*;

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
        System.out.println(repo.getCommitsBetween(600, 1300));
        System.out.println(repo.findNearestCommit(900));
        System.out.println(repo.countCommitsByAuthor("Alice"));
        System.out.println(repo.getMostActiveAuthor());
        repo.revertToCommit(1100);
        System.out.println(repo.getTimeline());

        System.out.println("-----------Q2----------");
        // on réalise les tests pour Q2 

        Q2.TreeNode root = new Q2.TreeNode(1);

        Q2 q2 = new Q2(root);

        root.left = new Q2.TreeNode(4);
        root.right = new Q2.TreeNode(3);

        root.left.left = new Q2.TreeNode(2);
        root.left.right = new Q2.TreeNode(4);

        root.right.left = new Q2.TreeNode(2);
        root.right.right = new Q2.TreeNode(5);

        root.right.right.right = new Q2.TreeNode(6);
        root.right.right.left = new Q2.TreeNode(4);
        System.out.println(q2.maxSumBST(root));
        System.out.println(q2.isValidBST(root));
        System.out.println(q2.findAllBSTRoots());
        System.out.println(q2.countValidBSTs());
        System.out.println(q2.getMinBSTSum());
        System.out.println(q2.getBSTSizeDistribution());
        System.out.println(q2.findLargestBST());
        System.out.println(q2.getInorderBST(root.right.right));

        System.out.println("-----------Q3----------");
        long[] populations = {2, 1, 3, 5, 6};
        int[] multipliers = {2, 2, 2, 2, 2};
        int[] populationsInt = new int[populations.length];
        for (int i = 0; i < populations.length; i++) {
            populationsInt[i] = (int) populations[i];
        }
        Q3 q3 = new Q3(populations, multipliers);
        q3.buildHeap(populationsInt, multipliers);
        System.out.println(Arrays.toString(q3.simulate(5)));
        populations = new long[]{100000, 2000};
        multipliers = new int[]{1000000, 1000000};
        populationsInt = new int[populations.length];
        for (int i = 0; i < populations.length; i++) {
            populationsInt[i] = (int) populations[i];
        }
        q3.buildHeap(populationsInt, multipliers);
        System.out.println(Arrays.toString(q3.simulateOptimized(2)));
        populations = new long[]{2, 2, 3, 1};
        multipliers = new int[]{2, 3, 2, 5};
        populationsInt = new int[populations.length];
        for (int i = 0; i < populations.length; i++) {
            populationsInt[i] = (int) populations[i];
        }
        q3.buildHeap(populationsInt, multipliers);
        System.out.println(q3.findMinIndex());
        populations = new long[]{5, 2, 8};
        multipliers = new int[]{3, 2, 4};
        populationsInt = new int[populations.length];
        for (int i = 0; i < populations.length; i++) {
            populationsInt[i] = (int) populations[i];
        }

        q3.buildHeap(populationsInt, multipliers);
        System.out.println((long) q3.predictPopulation(0, 3));
        System.out.println((long) q3.predictPopulation(1, 3));
        System.out.println((long) q3.predictPopulation(2, 3));

    }

}
