package language.java;

import java.util.ArrayList;

import model.decoration.table.element.Affectation;
import model.decoration.table.element.Element;
import model.decoration.table.element.IndexedVariable;
import model.decoration.table.element.IntegerVal;
import model.decoration.table.element.Sum;
import model.decoration.table.element.Variable;

public class JavaGuard {

    private Affectation baseAffectation;
    private ArrayList<String> localVariables;
    private String javaExpression;
    private String uniqueLambdaName;

    public JavaGuard(Element baseElement) {
        if (!(baseElement instanceof Affectation)) {
            System.err.println("Exception : l'élément de base doit être une affectation");
            return;
        }
        this.baseAffectation = (Affectation) baseElement;
        this.uniqueLambdaName = "lambda" + this.hashCode();
        this.getVariables(this.baseAffectation.getValue());
        this.javaExpression = this.parseToJava(this.baseAffectation.getValue());
    }

    // Reccursive
    private void getVariables(Element e) {
        // Définir toutes les varaiables nécessaires
    }

    // Reccursive
    private String parseToJava(Element e) {
        // Construit le calcul en java
        switch (e.getClass().getName()) {
            case "model.decoration.table.element.Variable":
                return ((Variable) e).getName();
            case "model.decoration.table.element.IndexedVariable":
                return "this.results.get(\""+((IndexedVariable) e).getName()+"\")[i+"+((IndexedVariable) e).getRelativeElementDistance()+"]";
            case "model.decoration.table.element.Integer":
                return ((IntegerVal) e).getValue() + "";
            case "model.decoration.table.element.Sum":
                return "(" + parseToJava(((Sum) e).getLeft()) + " + " + parseToJava(((Sum) e).getRight()) + ")";
            default:
                // System.err.println("Erreur de parsing");
                return e.getClass().getName();
        }
    }

    public void appendCode(String indent, StringBuffer buffer) {
        buffer.append(indent + "I " + this.uniqueLambdaName + " = () -> " + this.javaExpression + ";\n");
        buffer.append(indent + "this.indexedVariablesFunctions.get(\""+this.baseAffectation.getVariable().getName()+"\")[i]" + " = " + this.uniqueLambdaName + ";\n");
    }

    @Override
    public int hashCode() {
        return Math.abs(this.baseAffectation.getValue().hashCode() + this.baseAffectation.getVariable().hashCode());
    }
}