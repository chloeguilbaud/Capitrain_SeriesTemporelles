package generator;

import model.decoration.table.DecorationTable;
import model.seed.transducer.SeedTransducer;

public interface Language {

    StringBuffer generateCode(SeedTransducer seedTransducer,
                        DecorationTable decorationTable);

}
