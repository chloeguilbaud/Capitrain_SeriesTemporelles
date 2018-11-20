package language.java;

import generator.LanguageGenerator;
import generator.error.GeneratorResult;
import model.decoration.table.DecorationTable;
import model.seed.transducer.SeedTransducer;

import java.util.Optional;

/**
 * Entry point for generating a java code for a given
 * {@link SeedTransducer} and {@link DecorationTable}
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class JavaGenerator extends LanguageGenerator {

    /**
     * Generate the java code 
     * @param seedTransducer    {@link SeedTransducer} to generate
     * @param decorationTable   linked {@link DecorationTable} to generate
     * @return  {@link StringBuffer} filled by the whole generated code
     */
    public GeneratorResult generateCode(SeedTransducer seedTransducer, DecorationTable decorationTable) {

        GeneratorResult result = new GeneratorResult();

        // Buffer to fill with java code
        StringBuffer javaBuffer = new StringBuffer();

        // Translate SeedTransducer into JavaSeedTransducer
        JavaSeedTransducer javaSeedTransducer = new JavaSeedTransducer(seedTransducer);
        // Translate DecorationTable into JavaDecorationTable
        JavaDecorationTable javaDecorationTable = new JavaDecorationTable(decorationTable, seedTransducer.getAfter());

        // Add package generation (to change depending on when the class will be used)
        javaBuffer.append("package generated;\n\n");

        // Add needed imports to code
        javaBuffer.append("import java.util.HashMap;\n");
        javaBuffer.append("import java.util.ArrayList;\n\n");

        // Class declaration named with concatenation of seed transducer name and decoration table name
        javaBuffer.append("public class " + seedTransducer.getName().substring(0, 1).toUpperCase()
                                            + seedTransducer.getName().substring(1)
                                            + "_" + decorationTable.getName() + " {\n\n");

        // Declaration of constants used for Features values
        javaBuffer.append("\tpublic static final String FEATURE_ONE = \"one\";\n");
        javaBuffer.append("\tpublic static final String FEATURE_WIDTH = \"width\";\n");
        javaBuffer.append("\tpublic static final String FEATURE_SURF = \"surf\";\n");
        javaBuffer.append("\tpublic static final String FEATURE_MAX = \"max\";\n");
        javaBuffer.append("\tpublic static final String FEATURE_MIN = \"min\";\n");
        javaBuffer.append("\tpublic static final String FEATURE_RANGE = \"range\";\n");

        // Declaration of an interface in order to use it with lambda functions in code
        javaBuffer.append("\n\tinterface I {\n");
        javaBuffer.append("\t\tint func();\n");
        javaBuffer.append("\t}\n\n");

        // Declaration of all class values
        javaBuffer.append("\tprivate int[] timeSerie;\n");
        javaBuffer.append("\tprivate int i;\n");
        javaBuffer.append("\tprivate String feature;\n");
        javaBuffer.append("\tprivate int default_value;\n");
        javaBuffer.append("\tprivate String currentState;\n");
        javaBuffer.append("\tprivate HashMap<String, ArrayList<I>> indexedVariablesFunctions;\n");
        javaBuffer.append("\tprivate HashMap<String, Integer> registers;\n");
        javaBuffer.append("\tprivate HashMap<String, ArrayList<Integer>> results;\n");
        javaBuffer.append("\t\n");

        // Add main function
        javaBuffer.append("\tpublic HashMap<String, ArrayList<Integer>> resolve(int[] timeSerie, String feature, int default_value) {\n");
        javaBuffer.append("\t\tthis.timeSerie = timeSerie;\n");
        javaBuffer.append("\t\tthis.feature = feature;\n");
        javaBuffer.append("\t\tthis.default_value = default_value;\n");
        javaBuffer.append("\t\tthis.i = 0;\n");
        javaBuffer.append("\t\tthis.currentState = \"" + seedTransducer.getInitState().getName() + "\";\n");
        javaBuffer.append("\t\tthis.results = new HashMap<>();\n");
        javaBuffer.append("\t\tthis.indexedVariablesFunctions = new HashMap<>();\n");
        javaBuffer.append("\t\tthis.registers = new HashMap<>();\n");
        
        // Declare variables of decoration table
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

        // Declare registers of decoration table
        decorationTable.getRegisters().forEach((key, value) -> {
            javaBuffer.append("\t\tArrayList<Integer> listInt" + key + " = new ArrayList<Integer>();\n");
            javaBuffer.append("\t\tfor(int i = 0; i < timeSerie.length; i++) {\n");
            javaBuffer.append("\t\t\tlistInt" + key + ".add(new Integer(0));\n");
            javaBuffer.append("\t\t}\n");
            javaBuffer.append("\t\tthis.registers.put(\"" + key + "\", " + new JavaElement(value) + ");\n");
            javaBuffer.append("\t\tthis.results.put(\"" + key + "\", listInt" + key + ");\n");
        });

        // Processing loop
        javaBuffer.append("\t\twhile(this.i < timeSerie.length - 1) {\n");
        // Append seed transducer code (States code)
        javaSeedTransducer.appendCode("\t\t\t", javaBuffer, result);
        javaBuffer.append("\t\t}\n");

        // Resolve all lambda functions by descending indexes
        javaBuffer.append("\t\tthis.indexedVariablesFunctions.forEach((key, value) -> {\n");
        javaBuffer.append("\t\t\tfor (int i = value.size() - 1; i >= 0; i--) {\n");
        javaBuffer.append("\t\t\t\tthis.results.get(key).set(i, value.get(i).func());\n");
        javaBuffer.append("\t\t\t}\n");
        javaBuffer.append("\t\t});\n");

        // Return results of process
        javaBuffer.append("\t\treturn this.results;\n");

        // Close main function
        javaBuffer.append("\t}\n");

        // Append DecorationTable code (SemanticLetter functions)
        javaDecorationTable.appendCode("\t", javaBuffer);

        // Now implements all known functions depending on feature value

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
        javaBuffer.append("\t\t\t\treturn this.timeSerie.length + 1;\n");
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

        // Close class
        javaBuffer.append("}");

        // Return buffer
        // If errors, the result value is empty
        if (!result.hasErrors()) {
            result.setResult(Optional.of(javaBuffer));
        }
        return result;
    }

    
}
