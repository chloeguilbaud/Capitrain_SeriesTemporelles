package language.java;

import generator.Generator;
import model.decoration.table.DecorationTable;
import model.seed.transducer.SeedTransducer;

public class JavaGenerator implements Generator {

    public StringBuffer generateCode(SeedTransducer seedTransducer, DecorationTable decorationTable) {
        StringBuffer javaBuffer = new StringBuffer();

        JavaSeedTransducer javaSeedTransducer = new JavaSeedTransducer(seedTransducer);
        JavaDecorationTable javaDecorationTable = new JavaDecorationTable(decorationTable, seedTransducer.getAfter());

        // Imports
        javaBuffer.append("import java.util.HashMap;\n");
        javaBuffer.append("import java.util.ArrayList;\n\n");

        javaBuffer.append("public class " + seedTransducer.getName() + "_" + decorationTable.getName() + " {\n\n");
        javaBuffer.append("\tpublic static final String FEATURE_ONE = \"one\";\n");
        javaBuffer.append("\tpublic static final String FEATURE_WIDTH = \"width\";\n");
        javaBuffer.append("\tpublic static final String FEATURE_SURF = \"surf\";\n");
        javaBuffer.append("\tpublic static final String FEATURE_MAX = \"max\";\n");
        javaBuffer.append("\tpublic static final String FEATURE_MIN = \"min\";\n");
        javaBuffer.append("\tpublic static final String FEATURE_RANGE = \"range\";\n");
        javaBuffer.append("\n\tinterface I {\n");
        javaBuffer.append("\t\tint func();\n");
        javaBuffer.append("\t}\n\n");
        javaBuffer.append("\tprivate int[] timeSerie;\n");
        javaBuffer.append("\tprivate int i;\n");
        javaBuffer.append("\tprivate String feature;\n");
        javaBuffer.append("\tprivate int default_value;\n");
        javaBuffer.append("\tprivate String currentState;\n");
        javaBuffer.append("\tprivate HashMap<String, ArrayList<I>> indexedVariablesFunctions;\n");
        javaBuffer.append("\tprivate HashMap<String, Integer> registers;\n");
        javaBuffer.append("\tprivate HashMap<String, ArrayList<Integer>> results;\n");
        javaBuffer.append("\t\n");

        // Main function
        javaBuffer.append("\tpublic HashMap<String, ArrayList<Integer>> entryPoint(int[] timeSerie, String feature, int default_value) {\n");
        javaBuffer.append("\t\tthis.timeSerie = timeSerie;\n");
        javaBuffer.append("\t\tthis.feature = feature;\n");
        javaBuffer.append("\t\tthis.default_value = default_value;\n");
        javaBuffer.append("\t\tthis.i = 0;\n");
        javaBuffer.append("\t\tthis.currentState = \"" + seedTransducer.getInitState().getName() + "\";\n");
        javaBuffer.append("\t\tthis.results = new HashMap<>();\n");
        javaBuffer.append("\t\tthis.indexedVariablesFunctions = new HashMap<>();\n");
        javaBuffer.append("\t\tthis.registers = new HashMap<>();\n");
        
        decorationTable.getReturns().forEach((key, value) -> {
            javaBuffer.append("\t\tArrayList<I> listI" + key + " = new ArrayList<I>();\n");
            javaBuffer.append("\t\tfor(int i = 0; i < timeSerie.length; i++) {\n");
            javaBuffer.append("\t\t\tlistI" + key + ".add(() -> 0);\n");
            javaBuffer.append("\t\t}\n");
            javaBuffer.append("\t\tthis.indexedVariablesFunctions.put(\"" + key + "\", listI" + key + ");\n");
            javaBuffer.append("\t\tArrayList<Integer> listInt" + key + " = new ArrayList<Integer>();\n");
            javaBuffer.append("\t\tfor(int i = 0; i < timeSerie.length; i++) {\n");
            javaBuffer.append("\t\t\tlistInt" + key + ".add(new Integer(0));\n");
            javaBuffer.append("\t\t}\n");
            javaBuffer.append("\t\tthis.results.put(\"" + key + "\", listInt" + key + ");\n");
        });
        decorationTable.getRegisters().forEach((key, value) -> {
            javaBuffer.append("\t\tArrayList<Integer> listInt" + key + " = new ArrayList<Integer>();\n");
            javaBuffer.append("\t\tfor(int i = 0; i < timeSerie.length; i++) {\n");
            javaBuffer.append("\t\t\tlistInt" + key + ".add(new Integer(0));\n");
            javaBuffer.append("\t\t}\n");
            javaBuffer.append("\t\tthis.registers.put(\"" + key + "\", " + new JavaElement(value) + ");\n");
            javaBuffer.append("\t\tthis.results.put(\"" + key + "\", listInt" + key + ");\n");
        });

        // Traitement
        javaBuffer.append("\t\twhile(this.i < timeSerie.length - 1) {\n");
        javaSeedTransducer.appendCode("\t\t\t", javaBuffer);
        javaBuffer.append("\t\t}\n");

        javaBuffer.append("\t\tthis.indexedVariablesFunctions.forEach((key, value) -> {\n");
        javaBuffer.append("\t\t\tfor (int i = value.size() - 1; i >= 0; i--) {\n");
        javaBuffer.append("\t\t\t\tthis.results.get(key).set(i, value.get(i).func());\n");
        javaBuffer.append("\t\t\t}\n");
        javaBuffer.append("\t\t});\n");
        javaBuffer.append("\t\treturn this.results;\n");

        javaBuffer.append("\t}\n");
        javaDecorationTable.appendCode("\t", javaBuffer);

        // Function id
        javaBuffer.append("\n\tprivate int id(String feature) {\n");
        javaBuffer.append("\t\tswitch(feature) {\n");
        javaBuffer.append("\t\t\tcase FEATURE_ONE:\n");
        javaBuffer.append("\t\t\t\treturn 0;\n");
        javaBuffer.append("\t\t\tcase FEATURE_WIDTH:\n");
        javaBuffer.append("\t\t\t\treturn 0;\n");
        javaBuffer.append("\t\t\tcase FEATURE_SURF:\n");
        javaBuffer.append("\t\t\t\treturn 0;\n");
        javaBuffer.append("\t\t\tcase FEATURE_MAX:\n");
        javaBuffer.append("\t\t\t\treturn Integer.MAX_VALUE;\n");
        javaBuffer.append("\t\t\tcase FEATURE_MIN:\n");
        javaBuffer.append("\t\t\t\treturn Integer.MIN_VALUE;\n");
        javaBuffer.append("\t\t\tcase FEATURE_RANGE:\n");
        javaBuffer.append("\t\t\t\treturn 0;\n");
        javaBuffer.append("\t\t\tdefault:\n");
        javaBuffer.append("\t\t\t\treturn 0; // TODO: throw error\n");
        javaBuffer.append("\t\t}\n");
        javaBuffer.append("\t}\n");

        // Function min
        javaBuffer.append("\n\tprivate int min(String feature) {\n");
        javaBuffer.append("\t\tswitch(feature) {\n");
        javaBuffer.append("\t\t\tcase FEATURE_ONE:\n");
        javaBuffer.append("\t\t\t\treturn 0;\n");
        javaBuffer.append("\t\t\tcase FEATURE_WIDTH:\n");
        javaBuffer.append("\t\t\t\treturn 0;\n");
        javaBuffer.append("\t\t\tcase FEATURE_SURF:\n");
        javaBuffer.append("\t\t\t\treturn Integer.MIN_VALUE;\n");
        javaBuffer.append("\t\t\tcase FEATURE_MAX:\n");
        javaBuffer.append("\t\t\t\treturn Integer.MIN_VALUE;\n");
        javaBuffer.append("\t\t\tcase FEATURE_MIN:\n");
        javaBuffer.append("\t\t\t\treturn Integer.MIN_VALUE;\n");
        javaBuffer.append("\t\t\tcase FEATURE_RANGE:\n");
        javaBuffer.append("\t\t\t\treturn 0;\n");
        javaBuffer.append("\t\t\tdefault:\n");
        javaBuffer.append("\t\t\t\treturn 0; // TODO: throw error\n");
        javaBuffer.append("\t\t}\n");
        javaBuffer.append("\t}\n");

        // Function max
        javaBuffer.append("\n\tprivate int max(String feature) {\n");
        javaBuffer.append("\t\tswitch(feature) {\n");
        javaBuffer.append("\t\t\tcase FEATURE_ONE:\n");
        javaBuffer.append("\t\t\t\treturn 1;\n");
        javaBuffer.append("\t\t\tcase FEATURE_WIDTH:\n");
        javaBuffer.append("\t\t\t\treturn 0; // TODO: (n+1) <- what is n?\n");
        javaBuffer.append("\t\t\tcase FEATURE_SURF:\n");
        javaBuffer.append("\t\t\t\treturn Integer.MAX_VALUE;\n");
        javaBuffer.append("\t\t\tcase FEATURE_MAX:\n");
        javaBuffer.append("\t\t\t\treturn Integer.MAX_VALUE;\n");
        javaBuffer.append("\t\t\tcase FEATURE_MIN:\n");
        javaBuffer.append("\t\t\t\treturn Integer.MAX_VALUE;\n");
        javaBuffer.append("\t\t\tcase FEATURE_RANGE:\n");
        javaBuffer.append("\t\t\t\treturn Integer.MAX_VALUE;\n");
        javaBuffer.append("\t\t\tdefault:\n");
        javaBuffer.append("\t\t\t\treturn 0; // TODO: throw error\n");
        javaBuffer.append("\t\t}\n");
        javaBuffer.append("\t}\n");

        // Function phi
        javaBuffer.append("\n\tprivate int phi(String feature, int arg1, int arg2) {\n");
        javaBuffer.append("\t\tswitch(feature) {\n");
        javaBuffer.append("\t\t\tcase FEATURE_ONE:\n");
        javaBuffer.append("\t\t\t\treturn Math.max(arg1, arg2);\n");
        javaBuffer.append("\t\t\tcase FEATURE_WIDTH:\n");
        javaBuffer.append("\t\t\t\treturn arg1 + arg2;\n");
        javaBuffer.append("\t\t\tcase FEATURE_SURF:\n");
        javaBuffer.append("\t\t\t\treturn arg1 + arg2;\n");
        javaBuffer.append("\t\t\tcase FEATURE_MAX:\n");
        javaBuffer.append("\t\t\t\treturn Math.max(arg1, arg2);\n");
        javaBuffer.append("\t\t\tcase FEATURE_MIN:\n");
        javaBuffer.append("\t\t\t\treturn Math.min(arg1, arg2);\n");
        javaBuffer.append("\t\t\tcase FEATURE_RANGE:\n");
        javaBuffer.append("\t\t\t\treturn 0; // TODO: throw error\n");
        javaBuffer.append("\t\t\tdefault:\n");
        javaBuffer.append("\t\t\t\treturn 0; // TODO: throw error;\n");
        javaBuffer.append("\t\t}\n");
        javaBuffer.append("\t}\n");

        // Function delta
        javaBuffer.append("\n\tprivate int delta(String feature, int index) {\n");
        javaBuffer.append("\t\tswitch(feature) {\n");
        javaBuffer.append("\t\t\tcase FEATURE_ONE:\n");
        javaBuffer.append("\t\t\t\treturn 1;\n");
        javaBuffer.append("\t\t\tcase FEATURE_WIDTH:\n");
        javaBuffer.append("\t\t\t\treturn 1;\n");
        javaBuffer.append("\t\t\tcase FEATURE_SURF:\n");
        javaBuffer.append("\t\t\t\treturn this.timeSerie[index];\n");
        javaBuffer.append("\t\t\tcase FEATURE_MAX:\n");
        javaBuffer.append("\t\t\t\treturn this.timeSerie[index];\n");
        javaBuffer.append("\t\t\tcase FEATURE_MIN:\n");
        javaBuffer.append("\t\t\t\treturn this.timeSerie[index];\n");
        javaBuffer.append("\t\t\tcase FEATURE_RANGE:\n");
        javaBuffer.append("\t\t\t\treturn this.timeSerie[index];\n");
        javaBuffer.append("\t\t\tdefault:\n");
        javaBuffer.append("\t\t\t\treturn 0; // TODO: throw error\n");
        javaBuffer.append("\t\t}\n");
        javaBuffer.append("\t}\n");

        // Function delta2
        javaBuffer.append("\n\tprivate int delta2(String feature, int index) {\n");
        javaBuffer.append("\t\tswitch(feature) {\n");
        javaBuffer.append("\t\t\tcase FEATURE_ONE:\n");
        javaBuffer.append("\t\t\t\treturn -1;\n");
        javaBuffer.append("\t\t\tcase FEATURE_WIDTH:\n");
        javaBuffer.append("\t\t\t\treturn -1;\n");
        javaBuffer.append("\t\t\tcase FEATURE_SURF:\n");
        javaBuffer.append("\t\t\t\treturn (-1) * this.timeSerie[index];\n");
        javaBuffer.append("\t\t\tcase FEATURE_MAX:\n");
        javaBuffer.append("\t\t\t\treturn 0; // TODO: throw error\n");
        javaBuffer.append("\t\t\tcase FEATURE_MIN:\n");
        javaBuffer.append("\t\t\t\treturn 0; // TODO: throw error\n");
        javaBuffer.append("\t\t\tcase FEATURE_RANGE:\n");
        javaBuffer.append("\t\t\t\treturn 0; // TODO: throw error\n");
        javaBuffer.append("\t\t\tdefault:\n");
        javaBuffer.append("\t\t\t\treturn 0; // TODO: throw error\n");
        javaBuffer.append("\t\t}\n");
        javaBuffer.append("\t}\n");

        // Function default
        javaBuffer.append("\n\tpublic int default_fun(String feature) {\n");
        javaBuffer.append("\t\treturn this.default_value;\n");
        javaBuffer.append("\t}\n");

        javaBuffer.append("}");

        return javaBuffer;
    }

    
}
