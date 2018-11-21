package generated;

/**
 * This class contain main function that shows the execution time
 * of the resolving of a timeserie by generated code
 * @author Maël MAINCHAIN & Chloé GUILBAUD
 */
public class PerfTest {

    public static void main(String[] args) {
        // To evaluate, just change charge in getSample(Charge)
        int[] timeSerie = getSample(1000);

        Peak_feature generatedCode = new Peak_feature();
        long start_time = System.nanoTime();
        generatedCode.resolve(timeSerie, Peak_feature.FEATURE_MAX, 0);
        long end_time = System.nanoTime();
        StringBuffer buf = new StringBuffer();

        buf.append("Run in ").append((end_time - start_time) / 1e6).append(" ms");

        System.out.println(buf.toString());
    }

    /**
     * Provide a "length"-long timeserie
     */
    private static int[] getSample(int length) {
        int[] baseTimeSerie = {4, 4, 2, 2, 3, 5, 5, 6, 3, 1, 1, 2, 2, 2, 2, 2, 2, 1};
        int[] timeSerie = new int[length];
        for (int i = 0 ; i < length / baseTimeSerie.length; i++) {
            for (int j = 0; j < baseTimeSerie.length; j++) {
                timeSerie[(i * baseTimeSerie.length) + j] = baseTimeSerie[j];
            }
        }
        for (int i = 0 ; i < length % baseTimeSerie.length; i++) {
            timeSerie[(length / baseTimeSerie.length) * baseTimeSerie.length + i] = baseTimeSerie[i];
        }
        return timeSerie;
    }
}