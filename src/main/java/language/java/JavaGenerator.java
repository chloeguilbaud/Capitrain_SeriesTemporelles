package language.java;

import generator.Generator;
import model.decoration.table.DecorationTable;
import model.seed.transducer.SeedTransducer;

public class JavaGenerator implements Generator {

    public StringBuffer generateCode(SeedTransducer seedTransducer, DecorationTable decorationTable) {
        StringBuffer javaBuffer = new StringBuffer();

        JavaSeedTransducer javaSeedTransducer = new JavaSeedTransducer(seedTransducer);
        JavaDecorationTable javaDecorationTable = new JavaDecorationTable(decorationTable, seedTransducer.getAfter());

        javaBuffer.append("public class " + seedTransducer.getName() + "_" + decorationTable.getName() + " {\n\n");
        javaBuffer.append("\tpublic final String FEATURE_ONE = 'one';\n");
        javaBuffer.append("\tpublic final String FEATURE_WIDTH = 'width';\n");
        javaBuffer.append("\tpublic final String FEATURE_SURF = 'surf';\n");
        javaBuffer.append("\tpublic final String FEATURE_MAX = 'max';\n");
        javaBuffer.append("\tpublic final String FEATURE_MIN = 'min';\n");
        javaBuffer.append("\tpublic final String FEATURE_RANGE = 'range';\n");
        javaBuffer.append("\n\tinterface I {\n");
        javaBuffer.append("\t\tint func();\n");
        javaBuffer.append("\t}\n\n");
        javaBuffer.append("\tint[][] timeSerie;\n");
        javaBuffer.append("\tint i;\n");
        javaBuffer.append("\tString feature;\n");
        javaBuffer.append("\tint default;\n");
        javaBuffer.append("\tHashMap<String, I[]> indexedVariablesFunctions;\n");
        javaBuffer.append("\tHashMap<String, Integer> registers;\n");
        javaBuffer.append("\tHashMap<String, int[]> results;\n");
        javaBuffer.append("\t\n");

        // Main function
        javaBuffer.append("\tpublic int[][] entryPoint(int[][] timeSerie, String feature, int default) {\n");
        javaBuffer.append("\t\tthis.timeSerie = timeSerie;\n");
        javaBuffer.append("\t\tthis.feature = feature;\n");
        javaBuffer.append("\t\tthis.default = default;\n");
        javaBuffer.append("\t\tthis.i = 0;\n");
        javaBuffer.append("\t\tthis.results = new HashMap<>();\n");
        javaBuffer.append("\t\tthis.indexedVariablesFunctions = new HashMap<>();\n");
        javaBuffer.append("\t\tthis.registers = new HashMap<>();\n");
        
        decorationTable.getReturns().forEach((key, value) -> {
            javaBuffer.append("\t\tthis.indexedVariablesFunctions.put(\"" + key + "\", new I[timeSerie.length - 1]);\n");
            javaBuffer.append("\t\tthis.results.put(\"" + key + "\", new int[timeSerie.length - 1]);\n");
        });
        decorationTable.getRegisters().forEach((key, value) -> {
            javaBuffer.append("\t\tthis.registers.put(\"" + key + "\", " + value + ");\n");
            javaBuffer.append("\t\tthis.results.put(\"" + key + "\", new int[timeSerie.length - 1]);\n");
        });

        javaBuffer.append("\t\td();\n");
        javaBuffer.append("\t\tthis.indexedVariablesFunctions.forEach((key, value) -> {\n");
        javaBuffer.append("\t\t\tfor (int i = value.length - 1; i >= 0; i--) {\n");
        javaBuffer.append("\t\t\t\tthis.results.get(key)[i] = value[i].func();\n");
        javaBuffer.append("\t\t\t}\n");
        javaBuffer.append("\t\t});\n");
        javaBuffer.append("\t\treturn this.results;\n");

        javaBuffer.append("\t}\n");
        javaSeedTransducer.appendCode("\t", javaBuffer);
        javaDecorationTable.appendCode("\t", javaBuffer);

        // Function id
        javaBuffer.append("\n\tpublic int id(feature) {\n");
        javaBuffer.append("\t\tswitch(feature) {\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_ONE:\n");
        javaBuffer.append("\t\t\t\treturn 0;\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_WIDTH:\n");
        javaBuffer.append("\t\t\t\treturn 0;\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_SURF:\n");
        javaBuffer.append("\t\t\t\treturn 0;\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_MAX:\n");
        javaBuffer.append("\t\t\t\treturn Integer.MAX_VALUE;\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_MIN:\n");
        javaBuffer.append("\t\t\t\treturn Integer.MIN_VALUE;\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_RANGE:\n");
        javaBuffer.append("\t\t\t\treturn 0;\n");
        javaBuffer.append("\t\t\tdefault:\n");
        javaBuffer.append("\t\t\t\treturn 0; // TODO: throw error\n");
        javaBuffer.append("\t\t}\n");
        javaBuffer.append("\t}\n");

        // Function min
        javaBuffer.append("\n\tpublic int min(feature) {\n");
        javaBuffer.append("\t\tswitch(feature) {\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_ONE:\n");
        javaBuffer.append("\t\t\t\treturn 0;\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_WIDTH:\n");
        javaBuffer.append("\t\t\t\treturn 0;\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_SURF:\n");
        javaBuffer.append("\t\t\t\treturn Integer.MIN_VALUE;\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_MAX:\n");
        javaBuffer.append("\t\t\t\treturn Integer.MIN_VALUE;\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_MIN:\n");
        javaBuffer.append("\t\t\t\treturn Integer.MIN_VALUE;\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_RANGE:\n");
        javaBuffer.append("\t\t\t\treturn 0;\n");
        javaBuffer.append("\t\t\tdefault:\n");
        javaBuffer.append("\t\t\t\treturn 0; // TODO: throw error\n");
        javaBuffer.append("\t\t}\n");
        javaBuffer.append("\t}\n");

        // Function max
        javaBuffer.append("\n\tpublic int max(feature) {\n");
        javaBuffer.append("\t\tswitch(feature) {\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_ONE:\n");
        javaBuffer.append("\t\t\t\treturn 1;\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_WIDTH:\n");
        javaBuffer.append("\t\t\t\treturn 0; // TODO: (n+1) <- what is n?\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_SURF:\n");
        javaBuffer.append("\t\t\t\treturn Integer.MAX_VALUE;\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_MAX:\n");
        javaBuffer.append("\t\t\t\treturn Integer.MAX_VALUE;\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_MIN:\n");
        javaBuffer.append("\t\t\t\treturn Integer.MAX_VALUE;\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_RANGE:\n");
        javaBuffer.append("\t\t\t\treturn Integer.MAX_VALUE;\n");
        javaBuffer.append("\t\t\tdefault:\n");
        javaBuffer.append("\t\t\t\treturn 0; // TODO: throw error\n");
        javaBuffer.append("\t\t}\n");
        javaBuffer.append("\t}\n");

        // Function phi
        javaBuffer.append("\n\tpublic int phi(feature, int arg1, int arg2) {\n");
        javaBuffer.append("\t\tswitch(feature) {\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_ONE:\n");
        javaBuffer.append("\t\t\t\treturn Math.max(arg1, arg2);\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_WIDTH:\n");
        javaBuffer.append("\t\t\t\treturn arg1 + arg2;\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_SURF:\n");
        javaBuffer.append("\t\t\t\treturn arg1 + arg2;\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_MAX:\n");
        javaBuffer.append("\t\t\t\treturn Math.max(arg1, arg2);\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_MIN:\n");
        javaBuffer.append("\t\t\t\treturn Math.min(arg1, arg2);\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_RANGE:\n");
        javaBuffer.append("\t\t\t\treturn 0; // TODO: throw error\n");
        javaBuffer.append("\t\t\tdefault:\n");
        javaBuffer.append("\t\t\t\treturn 0 // TODO: throw error;\n");
        javaBuffer.append("\t\t}\n");
        javaBuffer.append("\t}\n");

        // Function delta
        javaBuffer.append("\n\tpublic int delta(feature, int index) {\n");
        javaBuffer.append("\t\tswitch(feature) {\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_ONE:\n");
        javaBuffer.append("\t\t\t\treturn 1;\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_WIDTH:\n");
        javaBuffer.append("\t\t\t\treturn 1;\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_SURF:\n");
        javaBuffer.append("\t\t\t\treturn this.timeSerie[index][1];\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_MAX:\n");
        javaBuffer.append("\t\t\t\treturn this.timeSerie[index][1];\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_MIN:\n");
        javaBuffer.append("\t\t\t\treturn this.timeSerie[index][1];\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_RANGE:\n");
        javaBuffer.append("\t\t\t\treturn this.timeSerie[index][1];\n");
        javaBuffer.append("\t\t\tdefault:\n");
        javaBuffer.append("\t\t\t\treturn 0; // TODO: throw error\n");
        javaBuffer.append("\t\t}\n");
        javaBuffer.append("\t}\n");

        // Function delta2
        javaBuffer.append("\n\tpublic int delta2(feature, int index) {\n");
        javaBuffer.append("\t\tswitch(feature) {\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_ONE:\n");
        javaBuffer.append("\t\t\t\treturn -1;\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_WIDTH:\n");
        javaBuffer.append("\t\t\t\treturn -1;\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_SURF:\n");
        javaBuffer.append("\t\t\t\treturn (-1) * this.timeSerie[index][1];\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_MAX:\n");
        javaBuffer.append("\t\t\t\treturn 0; // TODO: throw error\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_MIN:\n");
        javaBuffer.append("\t\t\t\treturn 0; // TODO: throw error\n");
        javaBuffer.append("\t\t\tcase this.FEATURE_RANGE:\n");
        javaBuffer.append("\t\t\t\treturn 0; // TODO: throw error\n");
        javaBuffer.append("\t\t\tdefault:\n");
        javaBuffer.append("\t\t\t\treturn 0; // TODO: throw error\n");
        javaBuffer.append("\t\t}\n");
        javaBuffer.append("\t}\n");

        javaBuffer.append("}");

        return javaBuffer;
    }

    
}
