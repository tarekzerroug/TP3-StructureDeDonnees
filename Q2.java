
import java.util.*;

 /*
 * ================================================================
 *                ATTESTATION D'INTÉGRITÉ ACADÉMIQUE
 * ================================================================
 *
 * [ ]  Je certifie n’avoir utilisé aucun outil d’IA générative
 *      pour résoudre ce problème.
 *
 * [ oui ]  J’ai utilisé un ou plusieurs outils d’IA générative.
 *      Détails ci-dessous :
 *
 *      • Outil(s) utilisé(s) :
 *        - ChatGPT 
 *
 *      • Raison(s) de l’utilisation :
 *        - Compréhension du problème  , écriture des commentaires
 *       
 *
 *      • Parties du code affectées :
 *        - aucune 
 * ================================================================
 */



public class Q2 {

    public static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        // on rajoute la méthode tostring
        @Override
        public String toString() {
            return "" + val;
        }

    }

    private TreeNode root;

    public Q2(TreeNode root) {
        this.root = root;
    }

    public Q2() {
        this.root = null;
    }

    // on définit qlqs fonctions auxiliaires
    // Parcours in-order, mais on stocke les valeurs uniquement
    // (utile pour vérifier si l'arbre respecte l'ordre croissant d'un BST)
    public void inorder(List<Integer> l, TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(l, root.left);
        l.add(root.val);
        inorder(l, root.right);
    }

    // Variante : on stocke les noeuds eux-mêmes
    // (utile pour explorer tous les sous-arbres possibles)
    public void inorderNodes(List<TreeNode> nodes, TreeNode root) {
        if (root == null) {
            return;
        }
        inorderNodes(nodes, root.left);
        nodes.add(root);
        inorderNodes(nodes, root.right);
    }

    // 1) Somme maximale parmi tous les sous-arbres BST
    public int maxSumBST(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<>();
        inorderNodes(nodes, root);

        int best = 0;

        for (TreeNode n : nodes) {
            if (isValidBST(n)) {

                // on récupère tous les noeuds du sous-arbre
                List<TreeNode> sub = new ArrayList<>();
                inorderNodes(sub, n);

                // on calcule la somme
                int somme = 0;
                for (TreeNode t : sub) {
                    somme += t.val;
                }

                // on garde la meilleure
                if (somme > best) {
                    best = somme;
                }
            }
        }

        return best;
    }

    // 2) Vérifie si un arbre est un BST
    // en utilisant le fait que l'ordre in-order doit être STRICTEMENT croissant
    public boolean isValidBST(TreeNode root) {
        List<Integer> l = new ArrayList<>();
        inorder(l, root);

        for (int i = 0; i < l.size() - 1; i++) {
            if (l.get(i) >= l.get(i + 1)) {
                return false;
            }
        }

        return true;
    }

    // 3).  Retourne les racines de tous les sous-arbres BST valides, triées
    public List<Integer> findAllBSTRoots() {
        List<TreeNode> nodes = new ArrayList<>();
        inorderNodes(nodes, root);

        List<Integer> result = new ArrayList<>();

        // pour chaque noeud, on vérifie si c’est un BST quand il est racine
        for (TreeNode n : nodes) {
            if (isValidBST(n)) {
                result.add(n.val);
            }
        }

        Collections.sort(result); // ordre croissant demandé
        return result;
    }

    // 4) Compte le nombre total de sous-arbres BST valides
    public int countValidBSTs() {
        List<TreeNode> nodes = new ArrayList<>();
        inorderNodes(nodes, root);

        int count = 0;
        for (TreeNode n : nodes) {
            if (isValidBST(n)) {
                count++;
            }
        }
        return count;
    }

    // 5) Somme minimale parmi tous les sous-arbres BST
    public int getMinBSTSum() {
        List<TreeNode> nodes = new ArrayList<>();
        inorderNodes(nodes, root);

        int best = Integer.MAX_VALUE; // valeur sentinelle

        for (TreeNode n : nodes) {
            if (isValidBST(n)) {

                // récupérer le sous-arbre
                List<TreeNode> sub = new ArrayList<>();
                inorderNodes(sub, n);

                // calculs de la somme
                int somme = 0;
                for (TreeNode t : sub) {
                    somme += t.val;
                }

                // mise a jour
                if (somme < best) {
                    best = somme;
                }
            }
        }

        if (best == Integer.MAX_VALUE) {
            return -1; // aucun BST

                }return best;
    }

    //  Répartition par taille : {taille : fréquence}
    public Map<Integer, Integer> getBSTSizeDistribution() {
        List<TreeNode> nodes = new ArrayList<>();
        inorderNodes(nodes, root);

        Map<Integer, Integer> freq = new HashMap<>();

        for (TreeNode n : nodes) {
            if (isValidBST(n)) {

                // taille du sous-arbre = nombre de noeuds en inorder
                List<TreeNode> sub = new ArrayList<>();
                inorderNodes(sub, n);

                int size = sub.size();
                freq.put(size, freq.getOrDefault(size, 0) + 1);
            }
        }

        return freq;
    }

    // 7) Trouve le plus grand BST. Priorité :
    //   1) taille maximale
    //   2) si égalité → somme maximale
    public TreeNode findLargestBST() {
        List<TreeNode> nodes = new ArrayList<>();
        inorderNodes(nodes, root);

        TreeNode bestRoot = null;
        int bestSize = 0;
        int bestSum = Integer.MIN_VALUE;

        for (TreeNode n : nodes) {
            if (isValidBST(n)) {

                List<TreeNode> sub = new ArrayList<>();
                inorderNodes(sub, n);

                int size = sub.size();
                int somme = 0;
                for (TreeNode t : sub) {
                    somme += t.val;
                }

                // priorité à la taille, puis somme
                if (size > bestSize || (size == bestSize && somme > bestSum)) {
                    bestSize = size;
                    bestSum = somme;
                    bestRoot = n;
                }
            }
        }

        return bestRoot;
    }

    // ) Retourne l’ordre in-order d’un BST donné
    public List<Integer> getInorderBST(TreeNode root) {
        // si pas un BST, on retourne une liste vide
        if (!isValidBST(root)) {
            return new ArrayList<>();
        }

        List<Integer> l = new ArrayList<>();
        inorder(l, root);
        return l;
    }
}
