package generator;

import language.java.JavaGenerator;

import java.util.Arrays;
import java.util.List;

public enum AvailableLanguages {

    JAVA("Java", new JavaGenerator());

    private String label;
    private LanguageGenerator languageGenerator;

    AvailableLanguages(String lab, LanguageGenerator languageGenerator) {
        this.languageGenerator = languageGenerator;
        this.label = lab;
    }

    public static AvailableLanguages fromLabel(String lab) {
        return valuesAsList().stream().filter(m -> m.getLabel().equalsIgnoreCase(lab)).findAny().orElse(null);
    }

    public static List<AvailableLanguages> valuesAsList() {
        return Arrays.asList(values());
    }

    public String getLabel() {
        return label;
    }

    public LanguageGenerator getLanguageGenerator() {
        return languageGenerator;
    }
}
