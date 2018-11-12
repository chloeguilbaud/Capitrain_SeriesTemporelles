package parser.decoration.table;

import com.fasterxml.jackson.databind.ObjectMapper;
import parser.decoration.table.model.DecorationTablePOJO;

import java.io.File;
import java.io.IOException;

class DecorationTableParser {

    static void parse(File jsonFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        DecorationTablePOJO res = mapper.readValue(jsonFile, DecorationTablePOJO.class);
        System.out.println(res);
    }

}
