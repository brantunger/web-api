package com.wildangelsguild.webapi.controller.validation;

import com.wildangelsguild.webapi.exception.ResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ValidationUtils {
    public static void processErrors(Errors errors) {
        if (!errors.hasErrors()) {
            return;
        }
        final StringBuilder builder = new StringBuilder();
        {
            boolean separate = false;
            if (errors.hasGlobalErrors()) {
                builder.append("Global Errors: ");
                for (ObjectError error : errors.getGlobalErrors()) {
                        builder.append(String.format((separate ? ", " : "") + "%s - %s", error.getObjectName(), error.getDefaultMessage()));
                    separate = true;
                }
            }
        }
        {
            boolean separate = false;
            if (errors.hasFieldErrors()) {
                builder.append("Field Errors: ");
                for (FieldError error : errors.getFieldErrors()) {
                    builder.append(String.format((separate ? ", " : "") + "%s - %s", error.getField(), error.getDefaultMessage()));
                    separate = true;
                }
            }
        }
        throw new ResourceException(HttpStatus.BAD_REQUEST, String.format("{\n \"success\": false, \n \"message\": \"%s\" \n}", builder.toString()));
    }
}
