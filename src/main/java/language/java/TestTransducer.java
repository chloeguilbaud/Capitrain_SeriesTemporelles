package language.java;

import java.util.HashMap;

public class TestTransducer {

    interface I {
        int func();
    }
    // TO REMOVE
    int[][] returns;
    I[] p;
    int[] result;
    int[] c;
    int C;
    // ---------

    int[][] timeSerie;
	int i;
    HashMap<String, I[]> indexedVariablesFunctions;
    HashMap<String, Integer> registers;
    HashMap<String, int[]> results;
	
	public HashMap<String, int[]> entryPoint(int[][] timeSerie) {
		this.timeSerie = timeSerie;
        this.i = 0;
        this.results = new HashMap<>();
        this.indexedVariablesFunctions = new HashMap<>();
        // List all indexedVariables
        this.indexedVariablesFunctions.put("p", new I[timeSerie.length - 1]);
        this.results.put("p", new int[timeSerie.length - 1]);
        this.registers = new HashMap<>();
        // List all registers
        this.registers.put("C", 0);
        this.results.put("C", new int[timeSerie.length - 1]);

        
        // TO REMOVE WHEN REMPLACED
		this.returns = new int[timeSerie.length - 1][1];
        this.p = new I[timeSerie.length - 1];
        this.C = 0;
        this.result = new int[timeSerie.length - 1];
        this.c = new int[timeSerie.length - 1];
        // ------------------------

        d();
        
        this.indexedVariablesFunctions.forEach((key, value) -> {
            for (int i = value.length - 1; i >= 0; i--) {
                this.results.get(key)[i] = value[i].func();
            }
        });

        // TO REMOVE WHEN REMPLACED
        for (int j = p.length - 1; j >= 0; j--) {
            result[j] = p[j].func();
        }
        System.out.println("C:");
        for (int j = 0; j < c.length; j++) {
            System.out.println(c[j]);
        }
        System.out.println("");
        System.out.println("p:");
        for (int j = 0; j < result.length; j++) {
            System.out.println(result[j]);
        }
        // -------------------------
        
		return this.results;
	}
	
	public void r() {
        if (i >= timeSerie.length - 1) return;
		if (timeSerie[i][1] <= timeSerie[i+1][1]) {
			maybe_before();
			i++;
			r();
			return;
		}
		if (timeSerie[i][1] > timeSerie[i+1][1]) {
			found();
			i++;
			t();
			return;
		}
	}

    public void d() {
        if (i >= timeSerie.length - 1) return;
		if (timeSerie[i][1] >= timeSerie[i+1][1]) {
			out();
			i++;
			d();
			return;
		}
		if (timeSerie[i][1] < timeSerie[i+1][1]) {
			out();
			i++;
			r();
			return;
		}
	}

    public void t() {
        if (i >= timeSerie.length - 1) return;
		if (timeSerie[i][1] > timeSerie[i+1][1]) {
			in();
			i++;
			t();
			return;
		}
		if (timeSerie[i][1] == timeSerie[i+1][1]) {
			maybe_after();
			i++;
			t();
			return;
		}
		if (timeSerie[i][1] < timeSerie[i+1][1]) {
			out_after();
			i++;
			r();
			return;
		}
	}
	
	void found() {
        int localC = C;
        I lambda = () -> localC + 1;
        p[i] = lambda;
        C++;
        c[i] = C;
	}
	
	void maybe_after() {
        int locali = i;
        I lambda = () -> result[locali+1];
        p[i] = lambda;
        c[i] = C;
	}
	
	void maybe_before() {
        int locali = i;
        I lambda = () -> result[locali+1];
        p[i] = lambda;
        c[i] = C;
	}
	
	void in() {
        int localC = C;
        I lambda = () -> localC;
        p[i] = lambda;
        c[i] = C;
	}
	
	void out() {
        c[i] = C;
        I lambda = () -> 0;
        p[i] = lambda;
	}
	
	void out_after() {
        I lambda = () -> 0;
        p[i] = lambda;
        c[i] = C;
	}
	
	void found_end() {
        int localC = C;
        I lambda = () -> localC + 1;
        p[i] = lambda;
        C++;
        c[i] = C;
	}
	
	void out_reset() {
        I lambda = () -> 0;
        p[i] = lambda;
        c[i] = C;
	}
}
