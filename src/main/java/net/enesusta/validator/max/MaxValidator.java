package net.enesusta.validator.max;

import net.enesusta.validator.core.FieldValidator;
import net.enesusta.validator.core.Validator;

import java.lang.reflect.Field;

public class MaxValidator implements Validator {

    @Override
    public final boolean isValid(final Object object) throws IllegalAccessException {

        final Class<?> clazz = object.getClass();
        final Field[] fields = clazz.getDeclaredFields();
        final FieldValidator fieldValidator = new MaxFieldValidator(object);
        boolean hasAnyFalse = false;

        for (Field field : fields)
            if (isAnnotationPresentWithMaxAnnotation(field))
                if (!fieldValidator.isFieldValid(field))
                    hasAnyFalse = true;

        return !hasAnyFalse;
    }
}
