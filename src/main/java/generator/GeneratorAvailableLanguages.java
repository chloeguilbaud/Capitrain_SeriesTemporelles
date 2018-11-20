package generator;

import language.java.JavaGenerator;
import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Enumeration of the possible languages that the programme can generate from a decoration table and seed transducer.
 * This enumeration links the language to an instance of it's generator.
 */
public enum GeneratorAvailableLanguages {

    JAVA("Java", new JavaGenerator());

    private String label;
    private LanguageGenerator languageGenerator;

    GeneratorAvailableLanguages(String lab, LanguageGenerator languageGenerator) {
        this.languageGenerator = languageGenerator;
        this.label = lab;
    }

    public static Optional<GeneratorAvailableLanguages> fromLabel(String lab) {
        return Optional.of(valuesAsList().stream().filter(m -> m.getLabel().equalsIgnoreCase(lab)).findAny().orElse(null));
    }

    public static List<GeneratorAvailableLanguages> valuesAsList() {
        return Arrays.asList(values());
    }

    public String getLabel() {
        return label;
    }

    public LanguageGenerator getLanguageGenerator() {
        return languageGenerator;
    }
}
