package conf;

public enum TestConfiguration {

    TEST_FILE_PATH("src/test/resources/"),
    TEST_FILE_PATH_SEED_TRANSDUCER_PARSER("src/test/resources/parser/seed/transducer/"),
    TEST_FILE_PATH_DECORATION_TABLE_PARSER("src/test/resources/parser/decoration/table/");

    private String value;

    TestConfiguration(String val) {
        this.value = val;
    }

    public String getValue() {
        return value;
    }

}
