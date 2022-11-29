package com.cuevasdeayllon.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RequeridoValidador implements ConstraintValidator<Requerido, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(value==null||value.isEmpty()) {
			return false;
			
		}
		
		return true;
	}

}
