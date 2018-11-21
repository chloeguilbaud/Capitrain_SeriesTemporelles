package language.java;

import java.util.Hashtable;

import generator.error.GeneratorErrorHandler;
import generator.error.GeneratorResult;
import model.decoration.table.DecorationTable;
import model.decoration.table.element.Affectation;
import model.decoration.table.element.Element;

/**
 * Java code generator for a {@link DecorationTable}'s guard
 * Generate a lambda function, and add it into a {@link Hashtable} by the 
 * name of the impacted variable
 * @author Chloe GUILBAUD &amp Maël MAINCHAIN
 */
public class JavaGuard {

    /**
     * Base {@link Affectation} of the guard
     */
    private Affectation baseAffectation;
    /**
     * Java Expression (as a {@link JavaElement}) for the guard
     */
    private JavaElement javaExpression;
    /**
     * Name of the generated lambda function in order to be unique
     */
    private String uniqueLambdaName;

    /**
     * Constructor
     * @param baseElement   Guard {@link Affectation} to convert into java code
     */
    public JavaGuard(Element baseElement, GeneratorResult result) {
        if (!(baseElement instanceof Affectation)) {
            GeneratorErrorHandler.handle(result, JavaGeneratorErrorType.JAVA_GUARD_PARAMETER_NOT_AN_AFFECTATION, baseElement.toString());
            return;
        }
        this.baseAffectation = (Affectation) baseElement;
        this.uniqueLambdaName = "lambda" + System.identityHashCode(this);
        this.javaExpression = new JavaElement(this.baseAffectation.getValue(), result);
    }

    /**
     * Generate java code of the object
     * @param indent {@link String}: Base indentation to have cleaner code
     * @param buffer {@link StringBuffer}: Append code to this buffer
     */
    public void appendCode(String indent, StringBuffer buffer) {
        // Instantiating lambda expression
        buffer.append(indent + "I " + this.uniqueLambdaName + " = () -> " + this.javaExpression + ";\n");
        // Adding it to the HashTable
        buffer.append(indent + "this.indexedVariablesFunctions.get(\""+this.baseAffectation.getVariable().getName()+"\").set(i, " + this.uniqueLambdaName + ");\n");
    }

    @Override
    public int hashCode() {
        return Math.abs(this.baseAffectation.getValue().hashCode() + this.baseAffectation.getVariable().hashCode());
    }
}