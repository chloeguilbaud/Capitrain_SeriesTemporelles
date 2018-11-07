package language.java;

import generator.Generator;
import model.decoration.table.DecorationTable;
import model.seed.transducer.SeedTransducer;

public class JavaGenerator implements Generator {

    public StringBuffer generateCode(SeedTransducer seedTransducer, DecorationTable decorationTable) {
        StringBuffer javaBuffer = new StringBuffer();

        JavaSeedTransducer javaSeedTransducer = new JavaSeedTransducer(seedTransducer);
        JavaDecorationTable javaDecorationTable = new JavaDecorationTable(decorationTable);

        javaBuffer.append("public class " + seedTransducer.getName() + "_" + decorationTable.getName() + " {\n");
        javaBuffer.append("\tint[][] timeSerie;\n");
        javaBuffer.append("\tint i;\n");
        javaBuffer.append("\tint[][] returns;\n");
        javaBuffer.append("\t\n");
        javaBuffer.append("\tpublic int[][] entryPoint(int[][] timeSerie) {\n");
        javaBuffer.append("\t\tthis.timeSerie = timeSerie;\n");
        javaBuffer.append("\t\tthis.i = 0;\n");
        javaBuffer.append("\t\tthis.returns = new int[timeSerie.length - 1][" + decorationTable.getRegisters().size() + "];\n");
        javaBuffer.append("\t\t" + seedTransducer.getInitState().getName() + "();\n");
        javaBuffer.append("\t\treturn this.returns;\n");
        javaBuffer.append("\t}\n");
        javaSeedTransducer.appendCode("\t", javaBuffer);
        javaDecorationTable.appendCode("\t", javaBuffer);
        javaBuffer.append("}");

        return javaBuffer;
    }

    
}
