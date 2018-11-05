package parser.seed.transducer;

import com.fasterxml.jackson.databind.ObjectMapper;
import parser.seed.transducer.model.SeedTransducerPOJO;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class SeedTransducerConverter {

    public static void convert(File jsonFile) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        SeedTransducerPOJO pojo = mapper.readValue(jsonFile, SeedTransducerPOJO.class);
        System.out.println(pojo);

    }

}
