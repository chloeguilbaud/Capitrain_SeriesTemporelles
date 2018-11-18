package language.java;

import model.decoration.table.DecorationTable;
import model.decoration.table.element.Affectation;
import model.decoration.table.element.Element;

/**
 * Java code of an update of a {@link DecorationTable}
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class JavaUpdate {

    /**
     * Base {@link Affectation} of the update
     */
    private Affectation baseAffectation;
    /**
     * Java Expression (as a {@link JavaElement}) for the guard
     */
    private JavaElement javaExpression;

    /**
     * Constructor
     * @param baseElement   Update {@link Affectation} to convert into java code
     */
    public JavaUpdate(Element baseElement) {
        if (!(baseElement instanceof Affectation)) {
            System.err.println("Exception : l'élément de base doit être une affectation"); // TODO
            return;
        }
        this.baseAffectation = (Affectation) baseElement;
        this.javaExpression = new JavaElement(this.baseAffectation.getValue());
    }

    /**
     * Generate java code of the object
     * @param indent {@link String}: Base indentation to have cleaner code
     * @param buffer {@link StringBuffer}: Append code to this buffer
     */
    public void appendCode(String indent, StringBuffer buffer) {
        // Add the java expression to the corresponding register
        buffer.append(indent + "this.registers.put(\""+this.baseAffectation.getVariable().getName()+"\", " + this.javaExpression + ");\n");
    }
}