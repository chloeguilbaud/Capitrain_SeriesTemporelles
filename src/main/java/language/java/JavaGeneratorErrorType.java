package language.java;

import generator.error.GeneratorErrorType;

/**
 * Generation possible error types enumeration
 * @author Chloe GUILBAUD &amp; Mael MAINCHAIN
 */
public enum JavaGeneratorErrorType implements GeneratorErrorType {

    JAVA_ELEMENT_UNKNOW_ELEMENT_SUBCLASS("Unknown subclass of model.element used\n%s"),
    JAVA_GUARD_PARAMETER_NOT_AN_AFFECTATION("Element passed in parameter to a Java Guard is not an affectation\n%s"),
    JAVA_UPDATE_PARAMETER_NOT_AN_AFFECTATION("Element passed in parameter to a Java Update is not an affectation\n%s");

    private String label;

    JavaGeneratorErrorType(String lab) {
        this.label = lab;
    }

    public String getLabel() {
        return label;
    }

}
