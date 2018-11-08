package language.java;

import generator.Generator;
import model.decoration.table.DecorationTable;
import model.seed.transducer.SeedTransducer;

public class JavaGenerator implements Generator {

    public StringBuffer generateCode(SeedTransducer seedTransducer, DecorationTable decorationTable) {
        StringBuffer javaBuffer = new StringBuffer();

        JavaSeedTransducer javaSeedTransducer = new JavaSeedTransducer(seedTransducer);
        JavaDecorationTable javaDecorationTable = new JavaDecorationTable(decorationTable);

        javaBuffer.append("public class " + seedTransducer.getName() + "_" + decorationTable.getName() + " {\n\n");
        javaBuffer.append("\tinterface I {\n");
        javaBuffer.append("\t\tint func();\n");
        javaBuffer.append("\t}\n\n");
        javaBuffer.append("\tint[][] timeSerie;\n");
        javaBuffer.append("\tint i;\n");
        javaBuffer.append("\tHashMap<String, I[]> indexedVariablesFunctions;\n");
        javaBuffer.append("\tHashMap<String, Integer> registers;\n");
        javaBuffer.append("\tHashMap<String, int[]> results;\n");
        javaBuffer.append("\t\n");
        javaBuffer.append("\tpublic int[][] entryPoint(int[][] timeSerie) {\n");
        javaBuffer.append("\t\tthis.timeSerie = timeSerie;\n");
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
        javaBuffer.append("}");

        return javaBuffer;
    }

    
}
