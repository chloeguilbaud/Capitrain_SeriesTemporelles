package language.java;

import model.decoration.table.element.Affectation;
import model.decoration.table.element.Element;

public class JavaUpdate {

    private Affectation baseAffectation;
    private JavaElement javaExpression;

    public JavaUpdate(Element baseElement) {
        if (!(baseElement instanceof Affectation)) {
            System.err.println("Exception : l'élément de base doit être une affectation");
            return;
        }
        this.baseAffectation = (Affectation) baseElement;
        this.javaExpression = new JavaElement(this.baseAffectation.getValue());
    }

    public void appendCode(String indent, StringBuffer buffer) {
        buffer.append(indent + "this.registers.put(\""+this.baseAffectation.getVariable().getName()+"\", " + this.javaExpression + ");\n");
    }
}