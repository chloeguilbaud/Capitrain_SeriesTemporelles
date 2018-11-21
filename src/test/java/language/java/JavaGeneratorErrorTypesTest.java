package language.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import generator.error.GeneratorError;
import generator.error.GeneratorErrorType;
import generator.error.GeneratorResult;
import model.decoration.table.element.Element;

public class JavaGeneratorErrorTypesTest {
    
    @Test
    public void JAVA_ELEMENT_UNKNOW_ELEMENT_SUBCLASS_TEST() {
        GeneratorResult result = new GeneratorResult();
        new JavaElement(new Element() {}, result);
        assertTrue(result.hasErrors());
        List<GeneratorError<GeneratorErrorType>> errors = result.getErrors();
        assertEquals(errors.size(), 1);
        errors.forEach((error) -> {
            assertEquals(error.getErrorType(), JavaGeneratorErrorType.JAVA_ELEMENT_UNKNOW_ELEMENT_SUBCLASS);
        });
    }

    @Test
    public void JAVA_GUARD_PARAMETER_NOT_AN_AFFECTATION_TEST() {
        GeneratorResult result = new GeneratorResult();
        new JavaGuard(new Element() {}, result);
        assertTrue(result.hasErrors());
        List<GeneratorError<GeneratorErrorType>> errors = result.getErrors();
        assertEquals(errors.size(), 1);
        errors.forEach((error) -> {
            assertEquals(error.getErrorType(), JavaGeneratorErrorType.JAVA_GUARD_PARAMETER_NOT_AN_AFFECTATION);
        });
    }

    @Test
    public void JAVA_UPDATE_PARAMETER_NOT_AN_AFFECTATION_TEST() {
        GeneratorResult result = new GeneratorResult();
        new JavaUpdate(new Element() {}, result);
        assertTrue(result.hasErrors());
        List<GeneratorError<GeneratorErrorType>> errors = result.getErrors();
        assertEquals(errors.size(), 1);
        errors.forEach((error) -> {
            assertEquals(error.getErrorType(), JavaGeneratorErrorType.JAVA_UPDATE_PARAMETER_NOT_AN_AFFECTATION);
        });
    }

}