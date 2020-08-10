package mattrandom.creditapp.core.validation.reflection;

import mattrandom.creditapp.core.annotation.NotNull;
import mattrandom.creditapp.core.exception.ValidationException;
import mattrandom.creditapp.core.validation.ValidationUtils;

import java.lang.reflect.Field;

public class NotNullAnnotationProcessor implements FieldAnnotationProcessor {
    @Override
    public void process(Object object, Field field) throws IllegalAccessException, ValidationException {
        if (field.isAnnotationPresent(NotNull.class)) {
            Object fieldValue = field.get(object);
            ValidationUtils.validateNotNull(field.getName(), fieldValue);
        }
    }
}
