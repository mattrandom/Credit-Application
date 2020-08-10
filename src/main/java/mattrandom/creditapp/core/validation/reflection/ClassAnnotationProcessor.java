package mattrandom.creditapp.core.validation.reflection;

import mattrandom.creditapp.core.exception.ValidationException;

public interface ClassAnnotationProcessor {
    void process(Object object, Class aClass) throws IllegalAccessException, ValidationException;
}
