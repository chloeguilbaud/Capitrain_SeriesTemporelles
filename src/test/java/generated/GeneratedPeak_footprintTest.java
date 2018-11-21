package generated;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import generated.Peak_feature;
import generated.Peak_footprint;
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

    @Test
    public void testMaxPeakSample() {
        int min_inf = Integer.MIN_VALUE;
        int[] timeSerie = {4, 4, 2, 2, 3, 5, 5, 6, 3, 1, 1, 2, 2, 2, 2, 2, 2, 1};
        int[] results_c = {min_inf, min_inf, min_inf, min_inf, min_inf, min_inf, min_inf, 6, 6, 6, 0, 0, 0, 0, 0, 0, 2, 0};
        int[] results_d = {min_inf, min_inf, min_inf, min_inf, 3, 5, 5, min_inf, min_inf, 1, min_inf, 2, 2, 2, 2, 2, min_inf, 0};
        int[] results_e = {0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 0, 0, 0};
        int[] results_f = {0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        Peak_feature generatedCode = new Peak_feature();
        HashMap<String, ArrayList<Integer>> results = generatedCode.resolve(timeSerie, Peak_footprint.FEATURE_MAX, 0);

        results.forEach((key, table) -> {
            if (key.equals("C")) {
                int i = 0;
                for (int value : table) {
                    assertEquals("i=" + i, results_c[i], value);
                    i++;
                }
            } else if (key.equals("D")) {
                int i = 0;
                for (int value : table) {
                    assertEquals("i=" + i, results_d[i], value);
                    i++;
                }
            } else if (key.equals("e")) {
                int i = 0;
                for (int value : table) {
                    assertEquals("i=" + i, results_e[i], value);
                    i++;
                }
            } else if (key.equals("f")) {
                int i = 0;
                for (int value : table) {
                    assertEquals("i=" + i, results_f[i], value);
                    i++;
                }
            }
        });
    }
}