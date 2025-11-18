
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
public class Q3 {

    private static final int MOD = 1_000_000_007;

    private class Sample {

        long population;
        int index;
        int multiplier;

        public Sample(long population, int index, int multiplier) {
            this.population = population;
            this.index = index;
            this.multiplier = multiplier;
        }
    }

    private PriorityQueue<Sample> minHeap;
    private long[] currentPopulations;
    private int[] multipliers;

    /*
     * Constructeur : je stocke simplement les populations et multiplicateurs initiaux.
     * Je ne construis pas encore le tas ici, car la vraie construction doit être faite
     * dans buildHeap() selon le format attendu par le TP.
     */
    public Q3(long[] pops, int[] mults) {
        this.currentPopulations = pops;
        this.multipliers = mults;
    }

    /*
     * buildHeap :
     * Je crée un min-heap où chaque élément contient la population, l’indice original
     * et son multiplicateur. Le critère principal est la population, puis l’indice.
     * Le but est de refléter exactement le comportement demandé dans l’énoncé.
     */
    public void buildHeap(int[] populations, int[] multipliers) {
        minHeap = new PriorityQueue<>((Sample s1, Sample s2) -> {
            if (Long.compare(s1.population, s2.population) == 0) {
                return Integer.compare(s1.index, s2.index);
            }
            return Long.compare(s1.population, s2.population);
        });

        this.currentPopulations = new long[populations.length];
        this.multipliers = multipliers;

        for (int i = 0; i < populations.length; i++) {
            this.currentPopulations[i] = populations[i];
            minHeap.offer(new Sample(populations[i], i, multipliers[i])); // insertion dans le tas
        }
    }

    /*
     * simulate :
     * J’effectue une simulation brute : extraire le min, multiplier et réinsérer.
     * Cette version suit exactement la définition du problème, sans optimisation.
     */
    public int[] simulate(int cycles) {
        for (int i = 0; i < cycles; i++) {
            Sample sample = minHeap.poll(); // extraire le plus petit
            sample.population = (sample.population * sample.multiplier) % MOD;
            currentPopulations[sample.index] = sample.population;
            minHeap.offer(sample); // réinsérer
        }

        int[] resultat = new int[minHeap.size()];

        // Copier les valeurs finales par indice original
        for (Sample sample : minHeap) {
            resultat[sample.index] = (int) (currentPopulations[sample.index] % MOD);
        }

        return resultat;
    }

    /*
     * simulateOptimized :
     * Cette version optimise les très grands nombres de cycles en détectant
     * le moment où les populations deviennent comparables. Ensuite, on applique
     * l’exponentiation au lieu de simuler cycle par cycle.
     */
    public int[] simulateOptimized(int cycles) {

        int cyclesNormaux = 0;

        // Phase de simulation "normale"
        while (cyclesNormaux < cycles) {
            Sample plusPetit = minHeap.peek();
            long ppPop = plusPetit.population;
            int ppMult = plusPetit.multiplier;

            long max = 0;
            for (Sample s : minHeap) {
                if (s.population > max) {
                    max = s.population;
                }
            }

            // Condition pour arrêter la phase normale
            if (max >= ppPop * ppMult) {
                break;
            }

            Sample polled = minHeap.poll();
            polled.population = (polled.population * polled.multiplier) % MOD;
            minHeap.offer(polled);

            cyclesNormaux++;
        }

        // Si on a déjà tout simulé
        if (cyclesNormaux == cycles) {
            int[] resultat = new int[minHeap.size()];
            for (Sample sample : minHeap) {
                resultat[sample.index] = (int) sample.population;
            }
            return resultat;
        }

        // Phase optimisée
        long restant = cycles - cyclesNormaux;

        long base = restant / minHeap.size();
        long extra = restant % minHeap.size();

        List<Sample> heapList = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            heapList.add(minHeap.poll());
        }

        for (int i = 0; i < heapList.size(); i++) {
            Sample s = heapList.get(i);
            long multipliant = base;
            if (i < extra) { // certains éléments multiplient une fois de plus
                multipliant++;
            }

            long exp = fastExpo(s.multiplier, multipliant, MOD);
            s.population = (s.population * exp) % MOD;
            currentPopulations[s.index] = s.population;
            minHeap.offer(s);
        }

        int[] resultat = new int[minHeap.size()];
        for (int i = 0; i < heapList.size(); i++) {
            resultat[i] = (int) (currentPopulations[i] % MOD);
        }
        return resultat;
    }

    /*
     * fastExpo :
     * L’exponentiation rapide est utilisée pour éviter de multiplier la même base
     * un grand nombre de fois. C’est essentiel pour les cycles optimisés.
     */
    long fastExpo(long base, long exp, long mod) {
        long resultat = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) { // si bit impair
                resultat = (resultat * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1; // exp = exp / 2
        }
        return resultat;
    }

    /*
     * getCurrentState :
     * Retourne un clone du tableau de populations. Je clone pour éviter que
     * l’extérieur modifie l’état interne de l’objet.
     */
    public long[] getCurrentState() {
        return currentPopulations.clone();
    }

    /*
     * findMinIndex :
     * Une méthode simple : retourner l’indice du prochain échantillon qui sera
     * multiplié. J’utilise peek() pour lire sans retirer.
     */
    public int findMinIndex() {
        return minHeap.peek().index;
    }

    /*
     * predictPopulation :
     * Pour prédire sans modifier le vrai tas, je crée un clone local du tas.
     * Ensuite, je simule futureCycles uniquement dans cette copie et je
     * retourne la population finale du sample demandé.
     */
    public long predictPopulation(int sampleIndex, int futureCycles) {

        // Copier le tas
        PriorityQueue<Sample> copy = new PriorityQueue<>(
                (a, b) -> a.population == b.population
                        ? Integer.compare(a.index, b.index)
                        : Long.compare(a.population, b.population)
        );

        Sample sample = null;

        // Cloner chaque élément
        for (Sample s : minHeap) {
            Sample clone = new Sample(s.population, s.index, s.multiplier);
            if (clone.index == sampleIndex) { // on garde une référence
                sample = clone;
            }
            copy.offer(clone);
        }

        // Simuler futureCycles sur la copie
        for (int i = 0; i < futureCycles; i++) {
            Sample min = copy.poll();
            min.population = (min.population * min.multiplier) % MOD;
            copy.offer(min);
        }

        return sample.population % MOD;
    }

}
