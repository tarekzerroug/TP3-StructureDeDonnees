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
    

    // Insère un nouveau commit dans l’arbre binaire de recherche
public void insert(int timestamp, String commitHash, String author) {
    CommitNode newnode = new CommitNode(timestamp, commitHash, author);

    // Si l’arbre est vide, ce commit devient la racine
    if (root == null) {
        root = newnode;
        return;
    }

    CommitNode marqueur = root;

    while (true) {

        // Aller à gauche
        if (newnode.timestamp < marqueur.timestamp) {
            if (marqueur.left == null) {
                marqueur.left = newnode;
                return;  // insertion terminée
            } else {
                marqueur = marqueur.left;
            }
        }

        // Aller à droite
        else if (newnode.timestamp > marqueur.timestamp) {
            if (marqueur.right == null) {
                marqueur.right = newnode;
                return;  // insertion terminée
            } else {
                marqueur = marqueur.right;
            }
        }

        // Même timestamp : on ne gère pas ce cas  on arrête
        else {
            return;
        }
    }
}  
  

    // Recherche un commit exact par son timestamp
public String findCommit(int timestamp) {
    CommitNode marqueur = root;

    while (marqueur != null) {
            if (timestamp == marqueur.timestamp) {
            return marqueur.commitHash;
        } 
        else if (timestamp < marqueur.timestamp) {
            marqueur = marqueur.left;   // aller à gauche
        } 
        else {
            marqueur = marqueur.right;  // aller à droite
        }
    }
    return null; // pas trouvé
}
public List<String> getCommitsBetween(int startTime, int endTime) {
    List<String> commits = new ArrayList<>();
    Stack<CommitNode> stack = new Stack<>();
    CommitNode current = root;

    while (current != null || !stack.isEmpty()) {
        // Descend a gauche
        while (current != null) {
            stack.push(current);
            current = current.left;
        }

        // noeud visité
        current = stack.pop();

        // Ajoute si dans intervalle
        if (current.timestamp >= startTime && current.timestamp <= endTime) {
            commits.add(current.commitHash);
        }

       
      //   aller a droite
        current = current.right;
    }

    return commits;
}
 
 public String findNearestCommit(int timestamp) {
    if (root == null) return null;

    CommitNode marqueur = root;
    CommitNode best = root;
    int bestDist = Math.abs(root.timestamp - timestamp);

    while (marqueur != null) {

        int dist = Math.abs(marqueur.timestamp - timestamp);

        // On met à jour le plus proche si nécessaire
        if (dist < bestDist) {
            bestDist = dist;
            best = marqueur;
        }

    
        if (marqueur.timestamp >= timestamp) {
            marqueur = marqueur.left;
        } else {
            marqueur = marqueur.right;
        }
    }

    return best.commitHash;
}

    // Compte le nombre de commits faits par un auteur donné
public int countCommitsByAuthor(String author) {
    if (root == null) return 0;

    int count = 0;

    Stack<CommitNode> stack = new Stack<>();
    stack.push(root);

    // Parcours DFS simple
    while (!stack.isEmpty()) {
        CommitNode marqueur = stack.pop();

        if (marqueur.author.equals(author)) {
            count++;
        }

        if (marqueur.left != null) stack.push(marqueur.left);
        if (marqueur.right != null) stack.push(marqueur.right);
    }

    return count;
}
 // Retourne l'auteur qui a fait le plus de commits
public String getMostActiveAuthor() {
    if (root == null) return null;

    Stack<CommitNode> stack = new Stack<>();
    Map<String, Integer> map = new HashMap<>();

    stack.push(root);

    // On parcourt l'arbre et on compte
    while (!stack.isEmpty()) {
        CommitNode marqueur = stack.pop();

        map.put(marqueur.author,
                map.getOrDefault(marqueur.author, 0) + 1);

        if (marqueur.left != null) stack.push(marqueur.left);
        if (marqueur.right != null) stack.push(marqueur.right);
    }

    // On cherche la plus grande valeur
    String bestAuthor = null;
    int max = -1;

    for (Map.Entry<String, Integer> entry : map.entrySet()) {
        if (entry.getValue() > max) {
            max = entry.getValue();
            bestAuthor = entry.getKey();
        }
    }

    return bestAuthor;
}
    
  
    // Supprime tous les commits dont le timestamp est > donné
public void revertToCommit(int timestamp) {
    if (root == null) return;

    Stack<CommitNode> stack = new Stack<>();
    Stack<CommitNode> parents = new Stack<>();
    Stack<Boolean> isLeft = new Stack<>();

    stack.push(root);
    parents.push(null);
    isLeft.push(false); // racine

    while (!stack.isEmpty()) {
        CommitNode node = stack.pop();
        CommitNode parent = parents.pop();
        boolean leftChild = isLeft.pop();

        if (node == null) continue;

        // Si le node doit être supprimé
        if (node.timestamp > timestamp) {

            // Cas : racine supprimée remplacer par son sous-arbre gauche
            if (parent == null) {
                root = node.left;
            } else if (leftChild) {
                parent.left = node.left;
            } else {
                parent.right = node.left;
            }

            // Continuer à nettoyer le sous-arbre gauche
            stack.push(node.left);
            parents.push(parent);
            isLeft.push(leftChild);
        }

        // Sinon on garde ce node  on explore ses enfants normalement
        else {
            stack.push(node.right);
            parents.push(node);
            isLeft.push(false);

            stack.push(node.left);
            parents.push(node);
            isLeft.push(true);
        }
    }
}
  // Retourne la timeline complète triée par timestamp croissant)
public List<String> getTimeline() {
    List<String> timeline = new ArrayList<>();
    Stack<CommitNode> stack = new Stack<>();
    CommitNode marqueur = root;

    // Parcours in-order (gauche  racine  - droite)
    while (marqueur != null || !stack.isEmpty()) {

        // descendre à gauche
        while (marqueur != null) {
            stack.push(marqueur);
            marqueur = marqueur.left;
        }

        // visiter
        marqueur = stack.pop();

        String entry = "[" + marqueur.timestamp + "]- "
                      + marqueur.commitHash + " - "
                      + marqueur.author;

        timeline.add(entry);

        // aller à droite
        marqueur = marqueur.right;
    }

    return timeline;
}

   
    
}

