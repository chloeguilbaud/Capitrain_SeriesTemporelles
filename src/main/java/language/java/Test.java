package language.java;

public class Test {

    public static void main(String[] args) {
        TestTransducer t = new TestTransducer();
        int[][] serie = {
            {0,4},
            {1,4},
            {2,2},
            {3,2},
            {4,3},
            {5,5},
            {6,5},
            {7,6},
            {8,3},
            {9,1},
            {10,1},
            {11,2},
            {12,2},
            {13,2},
            {14,2},
            {15,2},
            {16,2},
            {17,1}
        };
        // System.out.println(serie[1][1]);
        t.entryPoint(serie);
    }
}
