
import java.util.*;

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

    public Q3(long[] pops, int[] mults) {
        this.currentPopulations = pops;
        this.multipliers = mults;
    }

    public void buildHeap(int[] populations, int[] multipliers) {
        minHeap = new PriorityQueue<>((Sample s1, Sample s2) -> {
            if (Long.compare(s1.population, s2.population) == 0) {
                return Integer.compare(s1.index, s2.index);
            }
            return Long.compare(s1.population, s2.population);
        });

        for (int i = 0; i < populations.length; i++) {
            minHeap.offer(new Sample(populations[i], i, multipliers[i]));
        }
    }

    public int[] simulate(int cycles) {
        for (int i = 0; i < cycles; i++) {
            Sample sample = minHeap.poll();
            sample.population = (sample.population * sample.multiplier) % MOD;
            minHeap.offer(sample);
        }

        int[] resultat = new int[minHeap.size()];

        for (Sample sample : minHeap) {
            resultat[sample.index] = (int) sample.population;
        }

        return resultat;
    }

    public int[] simulateOptimized(int cycles) {

        int cyclesNormaux = 0;
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

            if (max >= ppPop * ppMult) {
                break;
            }

            Sample polled = minHeap.poll();
            polled.population = (polled.population * polled.multiplier) % MOD;
            minHeap.offer(polled);

            cyclesNormaux++;
        }

        if (cyclesNormaux == cycles) {
            int[] resultat = new int[minHeap.size()];

            for (Sample sample : minHeap) {
                resultat[sample.index] = (int) sample.population;
            }

            return resultat;
        }

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
            if (i < extra) {
                multipliant++;
            }

            long exp = fastExpo(s.multiplier, multipliant, MOD);
            s.population = (s.population * exp) % MOD;
        }

        int[] resultat = new int[minHeap.size()];
        for (Sample sample : minHeap) {
            resultat[sample.index] = (int) sample.population;
        }
        return resultat;
    }

    long fastExpo(long base, long exp, long mod) {
        long resultat = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                resultat = (resultat * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return resultat;
    }

    public long[] getCurrentState() {
        long[] resultat = new long[minHeap.size()];
        for (Sample sample : minHeap) {
            resultat[sample.index] = sample.population;
        }
        return resultat;
    }

    public int findMinIndex() {
        return minHeap.peek().index;
    }

    public long predictPopulation(int sampleIndex, int futureCycles) {
        long base = futureCycles / minHeap.size();
        long extra = futureCycles % minHeap.size();

        long k = base;

        int position = -1;
        for (Sample s : minHeap) {
            position++;
            if (s.index == sampleIndex) {
                break;
            }
        }
        if (position < extra) {
            k++;
        }

        return (fastExpo(multipliers[sampleIndex], k, MOD) * currentPopulations[sampleIndex]) % MOD;
    }

}
