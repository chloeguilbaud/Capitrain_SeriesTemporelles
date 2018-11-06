package generator;

import model.decoration.table.DecorationTable;
import model.seed.transducer.SeedTransducer;

public interface Generator {

    public StringBuffer generateCode(SeedTransducer seedTransducer, DecorationTable decorationTable);
}
