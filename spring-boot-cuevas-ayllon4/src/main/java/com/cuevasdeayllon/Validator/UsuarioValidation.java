package com.cuevasdeayllon.Validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cuevasdeayllon.entity.Usuario;





@Component
public class UsuarioValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

//		Usuario usuario = (Usuario) target;
//
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "requerido.usuario.nombre");
//		
//		if (usuario.getNombre().isEmpty()) {
//
//			errors.rejectValue("nombre", "NotEmpty.usuario.nombre");
//
//		}
//		if (usuario.getIdentificador().matches("[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][a-zA-Z]{1}")==false) {
//
//			errors.rejectValue("identificador", "pattern.usuario.identificador");
//
//		}

	}
}
