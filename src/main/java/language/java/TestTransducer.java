package language.java;

import java.util.HashMap;

public class TestTransducer {

    // Fixed ----------
    interface I {
        int func();
    }

    int[][] timeSerie;
	int i;
    HashMap<String, I[]> indexedVariablesFunctions;
    HashMap<String, Integer> registers;
    HashMap<String, int[]> results;
    // ----------------
	
	public HashMap<String, int[]> entryPoint(int[][] timeSerie) {
        // Fixed ----------
		this.timeSerie = timeSerie;
        this.i = 0;
        this.results = new HashMap<>();
        this.indexedVariablesFunctions = new HashMap<>();
        this.registers = new HashMap<>();
        // ----------------
        // Variables ------
        this.indexedVariablesFunctions.put("p", new I[timeSerie.length - 1]);
        this.results.put("p", new int[timeSerie.length - 1]);
        this.registers.put("C", 0);
        this.results.put("C", new int[timeSerie.length - 1]);
        // ----------------
        // Fixed ----------
        d();
        this.indexedVariablesFunctions.forEach((key, value) -> {
            for (int i = value.length - 1; i >= 0; i--) {
                this.results.get(key)[i] = value[i].func();
            }
        });
        return this.results;
        // ----------------
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
        int C = this.registers.get("C");
        I lambda = () -> C + 1;
        this.indexedVariablesFunctions.get("p")[i] = lambda;
        this.registers.put("C", C + 1);
        this.results.get("C")[i] = this.registers.get("C");
	}
	
	void maybe_after() {
        int i = this.i;
        I lambda = () -> this.results.get("p")[i+1];
        this.indexedVariablesFunctions.get("p")[i] = lambda;
        this.results.get("C")[i] = this.registers.get("C");
	}
	
	void maybe_before() {
        int i = this.i;
        I lambda = () -> this.results.get("p")[i+1];
        this.indexedVariablesFunctions.get("p")[i] = lambda;
        this.results.get("C")[i] = this.registers.get("C");
	}
	
	void in() {
        int C = this.registers.get("C");
        I lambda = () -> C;
        this.indexedVariablesFunctions.get("p")[i] = lambda;
        this.results.get("C")[i] = this.registers.get("C");
	}
	
	void out() {
        I lambda = () -> 0;
        this.indexedVariablesFunctions.get("p")[i] = lambda;
        this.results.get("C")[i] = this.registers.get("C");
	}
	
	void out_after() {
        I lambda = () -> 0;
        this.indexedVariablesFunctions.get("p")[i] = lambda;
        this.results.get("C")[i] = this.registers.get("C");
	}
	
	void found_end() {
        int C = this.registers.get("C");
        I lambda = () -> C + 1;
        this.indexedVariablesFunctions.get("p")[i] = lambda;
        this.registers.put("C", C + 1);
        this.results.get("C")[i] = this.registers.get("C");
	}
	
	void out_reset() {
        I lambda = () -> 0;
        this.indexedVariablesFunctions.get("p")[i] = lambda;
        this.results.get("C")[i] = this.registers.get("C");
	}
}
