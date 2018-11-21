package utils;

public enum TestConfiguration {

    TEST_FILE_PATH("src/test/resources/parser/examples/"),
    TEST_FILE_PATH_SEED_TRANSDUCER_PARSER("src/test/resources/parser/seed/transducer/"),
    TEST_FILE_PATH_DECORATION_TABLE_PARSER("src/test/resources/parser/decoration/table/"),

    TEST_FILE_PATH_SEED_TRANSDUCER_PARSER_MANAGER("src/test/resources/manager/"),
    TEST_FILE_PATH_SEED_TRANSDUCER_PARSER_MANAGER_SRC("src/test/java/manager/"),

    TEST_FILE_PATH_APP("src/test/resources/app/");

    private String value;

    TestConfiguration(String val) {
        this.value = val;
    }

    public String getValue() {
        return value;
    }

}
