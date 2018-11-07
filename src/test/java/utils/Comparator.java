package utils;

import model.seed.transducer.Arc;
import model.seed.transducer.SeedTransducer;
import org.junit.Assert;

public class Comparator {

    public static boolean compare(SeedTransducer s1, SeedTransducer s2) {

        for(Arc a1 : s1.getArcs()) {
            boolean tmp1 = false;
            for(Arc a2 : s2.getArcs()) {
                if(a1.equals(a2)) {
                    tmp1 = true;
                }
            }
            if (!tmp1) {
                return false;
            }
        }
        return true;
    }

}
