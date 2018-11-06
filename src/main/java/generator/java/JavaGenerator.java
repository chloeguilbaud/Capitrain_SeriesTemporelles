package generator.java;

import generator.Generator;
import model.decoration.table.DecorationTable;
import model.seed.transducer.SeedTransducer;

public class JavaGenerator implements Generator {

    public StringBuffer generateCode(SeedTransducer seedTransducer, DecorationTable decorationTable) {
        StringBuffer javaBuffer = new StringBuffer();
        javaBuffer.append("test");

        return javaBuffer;
    }

    
}
