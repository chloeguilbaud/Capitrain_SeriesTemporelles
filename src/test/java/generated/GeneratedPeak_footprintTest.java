package generated;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class GeneratedPeak_footprintTest {

    @Test
    public void testPeakFootprintSample() {
        int[] timeSerie = {4, 4, 2, 2, 3, 5, 5, 6, 3, 1, 1, 2, 2, 2, 2, 2, 2, 1};
        int[] results_c = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0};
        int[] results_p = {0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 2, 2, 2, 2, 2, 2, 0};

        Peak_footprint generatedCode = new Peak_footprint();
        HashMap<String, ArrayList<Integer>> results = generatedCode.resolve(timeSerie, Peak_footprint.FEATURE_ONE, 0);

        results.forEach((key, table) -> {
            if (key.equals("C")) {
                int i = 0;
                for (int value : table) {
                    assertEquals(value, results_c[i]);
                    i++;
                }
            } else if (key.equals("p")) {
                int i = 0;
                for (int value : table) {
                    assertEquals(value, results_p[i]);
                    i++;
                }
            }
        });
    }
}