package hw3.hash;

import java.util.ArrayList;
import java.util.List;


public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        List<Oomage>[] buckets = (ArrayList<Oomage>[]) new ArrayList[M];
        //Initialized buckets
        for (int i = 0; i < M; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (Oomage s : oomages) {
            int bucketNum = (s.hashCode() & 0x7FFFFFFF) % M;
            buckets[bucketNum].add(s);
        }

        int items = oomages.size();
        double lowest = (double) items / 50;
        double largest = (double) items / 2.5;
        for (List<Oomage> bucket : buckets) {
            if ((bucket.size() < lowest) || (bucket.size() > largest)) {
                return false;
            }
        }
        return true;
    }

}


