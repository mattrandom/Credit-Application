package mattrandom.creditapp.core.validation.reflection;

import mattrandom.creditapp.core.exception.ValidationException;

import java.lang.reflect.Field;

public interface FieldAnnotationProcessor {
    void process(Object object, Field field) throws IllegalAccessException, ValidationException;
}
