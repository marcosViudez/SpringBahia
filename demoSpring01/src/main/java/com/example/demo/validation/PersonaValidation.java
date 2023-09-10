package com.example.demo.validation;

import com.example.demo.entity.Persona;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PersonaValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Persona.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Persona persona = (Persona) target;

        ValidationUtils.rejectIfEmpty(errors,"nombre","validation.error.nombre");

        // validaciones por partes
        // this.validaDatos(target, errors);
    }

    private void validaDatos(Object target, Errors errors)
    {

        if(errors.hasErrors())
        {
            // objeto parametro y el properties
            errors.reject("error.campos.formulario");
        }
    }
}
