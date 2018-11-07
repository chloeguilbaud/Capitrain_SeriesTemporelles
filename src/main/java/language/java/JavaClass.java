package language.java;

import generator.Language;
import model.decoration.table.DecorationTable;
import model.seed.transducer.SeedTransducer;

public class JavaClass implements Language {

    @Override
    public StringBuffer generateCode(SeedTransducer seedTransducer, DecorationTable decorationTable) {
        throw new RuntimeException("Not Implemented yet");
    }

}
