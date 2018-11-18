package language.java;

import model.decoration.table.element.Division;
import model.decoration.table.element.Element;
import model.decoration.table.element.Function;
import model.decoration.table.element.IndexedVariable;
import model.decoration.table.element.IntegerVal;
import model.decoration.table.element.Product;
import model.decoration.table.element.Substraction;
import model.decoration.table.element.Sum;
import model.decoration.table.element.Variable;

/**
 * Generate the java code for an {@link Element}
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class JavaElement {

    /**
     * Java code for the {@link Element}
     */
    private String javaCode;

    /**
     * Constructor
     * @param e  {@link Element} to translate into java code
     */
    public JavaElement(Element e) {
        this.javaCode = parseToJava(e);
    }

    /**
     * Reccursive function to translate an {@link Element} into java code
     * @param e     {@link Element} to translate
     * @return      {@link String}: Java code of the {@link Element}
     */
    private String parseToJava(Element e) {
        // Depending of the type of the object
        switch (e.getClass().getName()) {
            case "model.decoration.table.element.Variable":
                return ((Variable) e).getName();
            case "model.decoration.table.element.IndexedVariable":
                return "this.results.get(\""+((IndexedVariable) e).getName()+"\").get(i+"+((IndexedVariable) e).getRelativeElementDistance()+")";
            case "model.decoration.table.element.IntegerVal":
                return ((IntegerVal) e).getValue() + "";
            case "model.decoration.table.element.Sum":
                return "(" + parseToJava(((Sum) e).getLeft()) + " + " + parseToJava(((Sum) e).getRight()) + ")";
            case "model.decoration.table.element.Soustraction":
                return "(" + parseToJava(((Substraction) e).getLeft()) + " - " + parseToJava(((Substraction) e).getRight()) + ")";
            case "model.decoration.table.element.Product":
                return "(" + parseToJava(((Product) e).getLeft()) + " * " + parseToJava(((Product) e).getRight()) + ")";
            case "model.decoration.table.element.Division":
                return "(" + parseToJava(((Division) e).getLeft()) + " / " + parseToJava(((Division) e).getRight()) + ")";
            case "model.decoration.table.element.Function":
                StringBuffer parametersBuffer = new StringBuffer();
                Function f = (Function) e;
                if (f.getName() == "default") {
                    parametersBuffer.append("default_fun(this.feature");
                } else {
                    parametersBuffer.append(f.getName()).append("(this.feature");
                }
                for (Element parameter : f.getParameters()) {
                    parametersBuffer.append(", ");
                    parametersBuffer.append(parseToJava(parameter));
                }
                parametersBuffer.append(")");
                return parametersBuffer.toString();
            default:
                // TODO: handle error here
                return e.getClass().getName();
        }
    }

    @Override
    public String toString() {
        return this.javaCode;
    }
}