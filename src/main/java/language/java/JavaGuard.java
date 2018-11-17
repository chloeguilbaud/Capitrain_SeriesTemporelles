package language.java;

import model.decoration.table.element.Affectation;
import model.decoration.table.element.Element;

public class JavaGuard {

    private Affectation baseAffectation;
    private JavaElement javaExpression;
    private String uniqueLambdaName;

    public JavaGuard(Element baseElement) {
        if (!(baseElement instanceof Affectation)) {
            System.err.println("Exception : l'élément de base doit être une affectation");
            return;
        }
        this.baseAffectation = (Affectation) baseElement;
        this.uniqueLambdaName = "lambda" + System.identityHashCode(this);
        this.javaExpression = new JavaElement(this.baseAffectation.getValue());
    }

    public void appendCode(String indent, StringBuffer buffer) {
        buffer.append(indent + "I " + this.uniqueLambdaName + " = () -> " + this.javaExpression + ";\n");
        buffer.append(indent + "this.indexedVariablesFunctions.get(\""+this.baseAffectation.getVariable().getName()+"\").set(i, " + this.uniqueLambdaName + ");\n");
    }

    @Override
    public int hashCode() {
        return Math.abs(this.baseAffectation.getValue().hashCode() + this.baseAffectation.getVariable().hashCode());
    }
}