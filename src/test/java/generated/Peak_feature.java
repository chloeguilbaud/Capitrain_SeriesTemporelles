package generated;

import java.util.HashMap;
import java.util.ArrayList;

public class Peak_feature {

	public static final String FEATURE_ONE = "one";
	public static final String FEATURE_WIDTH = "width";
	public static final String FEATURE_SURF = "surf";
	public static final String FEATURE_MAX = "max";
	public static final String FEATURE_MIN = "min";
	public static final String FEATURE_RANGE = "range";

	interface I {
		int func();
	}

	private int[] timeSerie;
	private int i;
	private String feature;
	private int default_value;
	private String currentState;
	private HashMap<String, ArrayList<I>> indexedVariablesFunctions;
	private HashMap<String, Integer> registers;
	private HashMap<String, ArrayList<Integer>> results;
	
	public HashMap<String, ArrayList<Integer>> resolve(int[] timeSerie, String feature, int default_value) {
		this.timeSerie = timeSerie;
		this.feature = feature;
		this.default_value = default_value;
		this.i = 0;
		this.currentState = "d";
		this.results = new HashMap<>();
		this.indexedVariablesFunctions = new HashMap<>();
		this.registers = new HashMap<>();
		ArrayList<I> listIe = new ArrayList<I>();
		for(int i = 0; i < timeSerie.length; i++) {
			listIe.add(() -> 0);
		}
		this.indexedVariablesFunctions.put("e", listIe);
		ArrayList<Integer> listInte = new ArrayList<Integer>();
		for(int i = 0; i < timeSerie.length; i++) {
			listInte.add(new Integer(0));
		}
		this.results.put("e", listInte);
		ArrayList<I> listIf = new ArrayList<I>();
		for(int i = 0; i < timeSerie.length; i++) {
			listIf.add(() -> 0);
		}
		this.indexedVariablesFunctions.put("f", listIf);
		ArrayList<Integer> listIntf = new ArrayList<Integer>();
		for(int i = 0; i < timeSerie.length; i++) {
			listIntf.add(new Integer(0));
		}
		this.results.put("f", listIntf);
		ArrayList<Integer> listIntC = new ArrayList<Integer>();
		for(int i = 0; i < timeSerie.length; i++) {
			listIntC.add(new Integer(0));
		}
		this.registers.put("C", id(this.feature));
		this.results.put("C", listIntC);
		ArrayList<Integer> listIntD = new ArrayList<Integer>();
		for(int i = 0; i < timeSerie.length; i++) {
			listIntD.add(new Integer(0));
		}
		this.registers.put("D", id(this.feature));
		this.results.put("D", listIntD);
		while(this.i < timeSerie.length - 1) {
			if (currentState.equals("r")) {
				if (timeSerie[i] <= timeSerie[i+1]) {
					maybe_before();
					i++;
					currentState = "r";
				}
				else
				if (timeSerie[i] > timeSerie[i+1]) {
					found();
					i++;
					currentState = "t";
				}
			} else if (currentState.equals("d")) {
				if (timeSerie[i] >= timeSerie[i+1]) {
					out();
					i++;
					currentState = "d";
				}
				else
				if (timeSerie[i] < timeSerie[i+1]) {
					out();
					i++;
					currentState = "r";
				}
			} else if (currentState.equals("t")) {
				if (timeSerie[i] < timeSerie[i+1]) {
					out_after();
					i++;
					currentState = "r";
				}
				else
				if (timeSerie[i] > timeSerie[i+1]) {
					in();
					i++;
					currentState = "t";
				}
				else
				if (timeSerie[i] == timeSerie[i+1]) {
					maybe_after();
					i++;
					currentState = "t";
				}
			} else {
				break;
			}
		}
		this.indexedVariablesFunctions.forEach((key, value) -> {
			for (int i = value.size() - 1; i >= 0; i--) {
				this.results.get(key).set(i, value.get(i).func());
			}
		});
		return this.results;
	}
	
	
	private void maybe_after() {
		int i = this.i;
		int C = this.registers.get("C");
		int D = this.registers.get("D");
		I lambda125993742 = () -> this.results.get("e").get(i+1);
		this.indexedVariablesFunctions.get("e").set(i, lambda125993742);
		I lambda1192108080 = () -> default_fun(this.feature);
		this.indexedVariablesFunctions.get("f").set(i, lambda1192108080);
		this.registers.put("D", phi(this.feature, D, delta(this.feature, (i + 1))));
		this.results.get("C").set(i, this.registers.get("C"));
		this.results.get("D").set(i, this.registers.get("D"));
	}
	
	private void maybe_before() {
		int i = this.i;
		int C = this.registers.get("C");
		int D = this.registers.get("D");
		I lambda537548559 = () -> this.results.get("e").get(i+1);
		this.indexedVariablesFunctions.get("e").set(i, lambda537548559);
		I lambda380894366 = () -> default_fun(this.feature);
		this.indexedVariablesFunctions.get("f").set(i, lambda380894366);
		this.registers.put("D", phi(this.feature, D, delta(this.feature, i)));
		this.results.get("C").set(i, this.registers.get("C"));
		this.results.get("D").set(i, this.registers.get("D"));
	}
	
	private void found() {
		int i = this.i;
		int C = this.registers.get("C");
		int D = this.registers.get("D");
		I lambda237852351 = () -> this.results.get("e").get(i+1);
		this.indexedVariablesFunctions.get("e").set(i, lambda237852351);
		I lambda608188624 = () -> this.results.get("e").get(i+0);
		this.indexedVariablesFunctions.get("f").set(i, lambda608188624);
		this.registers.put("C", phi(this.feature, phi(this.feature, D, delta(this.feature, i)), delta(this.feature, (i + 1))));
		this.registers.put("D", id(this.feature));
		this.results.get("C").set(i, this.registers.get("C"));
		this.results.get("D").set(i, this.registers.get("D"));
	}
	
	private void in() {
		int i = this.i;
		int C = this.registers.get("C");
		int D = this.registers.get("D");
		I lambda1451270520 = () -> this.results.get("e").get(i+1);
		this.indexedVariablesFunctions.get("e").set(i, lambda1451270520);
		I lambda1608446010 = () -> default_fun(this.feature);
		this.indexedVariablesFunctions.get("f").set(i, lambda1608446010);
		this.registers.put("C", phi(this.feature, C, phi(this.feature, D, delta(this.feature, (i + 1)))));
		this.registers.put("D", id(this.feature));
		this.results.get("C").set(i, this.registers.get("C"));
		this.results.get("D").set(i, this.registers.get("D"));
	}
	
	private void out_reset() {
		int i = this.i;
		int C = this.registers.get("C");
		int D = this.registers.get("D");
		I lambda992136656 = () -> default_fun(this.feature);
		this.indexedVariablesFunctions.get("e").set(i, lambda992136656);
		I lambda511833308 = () -> default_fun(this.feature);
		this.indexedVariablesFunctions.get("f").set(i, lambda511833308);
		this.registers.put("D", id(this.feature));
		this.results.get("C").set(i, this.registers.get("C"));
		this.results.get("D").set(i, this.registers.get("D"));
	}
	
	private void found_end() {
		int i = this.i;
		int C = this.registers.get("C");
		int D = this.registers.get("D");
		I lambda1297685781 = () -> default_fun(this.feature);
		this.indexedVariablesFunctions.get("e").set(i, lambda1297685781);
		I lambda1705929636 = () -> phi(this.feature, phi(this.feature, D, delta(this.feature, i)), delta(this.feature, (i + 1)));
		this.indexedVariablesFunctions.get("f").set(i, lambda1705929636);
		this.registers.put("D", id(this.feature));
		this.results.get("C").set(i, this.registers.get("C"));
		this.results.get("D").set(i, this.registers.get("D"));
	}
	
	private void out_after() {
		int i = this.i;
		int C = this.registers.get("C");
		int D = this.registers.get("D");
		I lambda1221555852 = () -> C;
		this.indexedVariablesFunctions.get("e").set(i, lambda1221555852);
		I lambda1509514333 = () -> default_fun(this.feature);
		this.indexedVariablesFunctions.get("f").set(i, lambda1509514333);
		this.registers.put("C", default_fun(this.feature));
		this.registers.put("D", id(this.feature));
		this.results.get("C").set(i, this.registers.get("C"));
		this.results.get("D").set(i, this.registers.get("D"));
	}
	
	private void out() {
		int i = this.i;
		int C = this.registers.get("C");
		int D = this.registers.get("D");
		I lambda1556956098 = () -> default_fun(this.feature);
		this.indexedVariablesFunctions.get("e").set(i, lambda1556956098);
		I lambda1252585652 = () -> default_fun(this.feature);
		this.indexedVariablesFunctions.get("f").set(i, lambda1252585652);
		this.results.get("C").set(i, this.registers.get("C"));
		this.results.get("D").set(i, this.registers.get("D"));
	}

	private int id(String feature) {
		switch(feature) {
			case FEATURE_ONE:
				return 0;
			case FEATURE_WIDTH:
				return 0;
			case FEATURE_SURF:
				return 0;
			case FEATURE_MAX:
				return Integer.MIN_VALUE;
			case FEATURE_MIN:
				return Integer.MAX_VALUE;
			case FEATURE_RANGE:
				return 0;
			default:
				return 0; // TODO: throw error
		}
	}

	private int min(String feature) {
		switch(feature) {
			case FEATURE_ONE:
				return 0;
			case FEATURE_WIDTH:
				return 0;
			case FEATURE_SURF:
				return Integer.MIN_VALUE;
			case FEATURE_MAX:
				return Integer.MIN_VALUE;
			case FEATURE_MIN:
				return Integer.MIN_VALUE;
			case FEATURE_RANGE:
				return 0;
			default:
				return 0; // TODO: throw error
		}
	}

	private int max(String feature) {
		switch(feature) {
			case FEATURE_ONE:
				return 1;
			case FEATURE_WIDTH:
				return this.timeSerie.length + 1;
			case FEATURE_SURF:
				return Integer.MAX_VALUE;
			case FEATURE_MAX:
				return Integer.MAX_VALUE;
			case FEATURE_MIN:
				return Integer.MAX_VALUE;
			case FEATURE_RANGE:
				return Integer.MAX_VALUE;
			default:
				return 0; // TODO: throw error
		}
	}

	private int phi(String feature, int arg1, int arg2) {
		switch(feature) {
			case FEATURE_ONE:
				return Math.max(arg1, arg2);
			case FEATURE_WIDTH:
				return arg1 + arg2;
			case FEATURE_SURF:
				return arg1 + arg2;
			case FEATURE_MAX:
				return Math.max(arg1, arg2);
			case FEATURE_MIN:
				return Math.min(arg1, arg2);
			case FEATURE_RANGE:
				return 0; // TODO: throw error
			default:
				return 0; // TODO: throw error;
		}
	}

	private int delta(String feature, int index) {
		switch(feature) {
			case FEATURE_ONE:
				return 1;
			case FEATURE_WIDTH:
				return 1;
			case FEATURE_SURF:
				return this.timeSerie[index];
			case FEATURE_MAX:
				return this.timeSerie[index];
			case FEATURE_MIN:
				return this.timeSerie[index];
			case FEATURE_RANGE:
				return this.timeSerie[index];
			default:
				return 0; // TODO: throw error
		}
	}

	private int delta2(String feature, int index) {
		switch(feature) {
			case FEATURE_ONE:
				return -1;
			case FEATURE_WIDTH:
				return -1;
			case FEATURE_SURF:
				return (-1) * this.timeSerie[index];
			case FEATURE_MAX:
				return 0; // TODO: throw error
			case FEATURE_MIN:
				return 0; // TODO: throw error
			case FEATURE_RANGE:
				return 0; // TODO: throw error
			default:
				return 0; // TODO: throw error
		}
	}

	public int default_fun(String feature) {
		return this.default_value;
	}
}
