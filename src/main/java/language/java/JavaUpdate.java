package language.java;

import model.decoration.table.element.Affectation;
import model.decoration.table.element.Division;
import model.decoration.table.element.Element;
import model.decoration.table.element.Function;
import model.decoration.table.element.IndexedVariable;
import model.decoration.table.element.IntegerVal;
import model.decoration.table.element.Product;
import model.decoration.table.element.Soustraction;
import model.decoration.table.element.Sum;
import model.decoration.table.element.Variable;

public class JavaUpdate {

    private Affectation baseAffectation;
    private String javaExpression;

    public JavaUpdate(Element baseElement) {
        if (!(baseElement instanceof Affectation)) {
            System.err.println("Exception : l'élément de base doit être une affectation");
            return;
        }
        this.baseAffectation = (Affectation) baseElement;
        this.javaExpression = this.parseToJava(this.baseAffectation.getValue());
    }

    // Reccursive
    private String parseToJava(Element e) {
        // Construit le calcul en java
        switch (e.getClass().getName()) {
            case "model.decoration.table.element.Variable":
                return ((Variable) e).getName();
            case "model.decoration.table.element.IndexedVariable":
                return "this.results.get(\""+((IndexedVariable) e).getName()+"\")[i+"+((IndexedVariable) e).getRelativeElementDistance()+"]";
            case "model.decoration.table.element.IntegerVal":
                return ((IntegerVal) e).getValue() + "";
            case "model.decoration.table.element.Sum":
                return "(" + parseToJava(((Sum) e).getLeft()) + " + " + parseToJava(((Sum) e).getRight()) + ")";
            case "model.decoration.table.element.Soustraction":
                return "(" + parseToJava(((Soustraction) e).getLeft()) + " - " + parseToJava(((Soustraction) e).getRight()) + ")";
            case "model.decoration.table.element.Product":
                return "(" + parseToJava(((Product) e).getLeft()) + " * " + parseToJava(((Product) e).getRight()) + ")";
            case "model.decoration.table.element.Division":
                return "(" + parseToJava(((Division) e).getLeft()) + " / " + parseToJava(((Division) e).getRight()) + ")";
            case "model.decoration.table.element.Function":
                StringBuffer parametersBuffer = new StringBuffer();
                parametersBuffer.append(((Function) e).getName()).append("(");
                boolean comaNeeded = false;
                for (Element parameter : ((Function) e).getParameters()) {
                    if (comaNeeded) {
                        parametersBuffer.append(", ");
                    }
                    parametersBuffer.append(parseToJava(parameter));
                    comaNeeded = true;
                }
                parametersBuffer.append(")");
                return parametersBuffer.toString();
            default:
                // System.err.println("Erreur de parsing");
                return e.getClass().getName();
        }
    }

    public void appendCode(String indent, StringBuffer buffer) {
        buffer.append(indent + "this.registers.put(\""+this.baseAffectation.getVariable().getName()+"\", " + this.javaExpression + ");\n");
    }
}