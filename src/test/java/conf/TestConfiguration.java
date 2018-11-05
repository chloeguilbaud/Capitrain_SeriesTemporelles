package conf;

public enum TestConfiguration {

    TEST_FILE_PATH("src/test/resources/");

    private String value;

    TestConfiguration(String val) {
        this.value = val;
    }

    public String getValue() {
        return value;
    }

}
