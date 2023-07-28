package com.bonjour.cursospring.services.validation;

import java.util.ArrayList;
import java.util.List;

import com.bonjour.cursospring.domain.enums.TipoCliente;
import com.bonjour.cursospring.dto.ClienteNewDTO;
import com.bonjour.cursospring.resources.exception.FieldMessage;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO>{
	@Override
	public void initialize(ClienteInsert ann) {
		
	}
	
	@Override
	public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<FieldMessage>();
		
		if(TipoCliente.toEnum(objDTO.getTipo()).equals(TipoCliente.PESSOA_FISICA)) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));
		}
		
		if(TipoCliente.toEnum(objDTO.getTipo()).equals(TipoCliente.PESSOA_JURIDICA)) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido"));
		}
		
		for(FieldMessage field : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(field.getMessage()).addPropertyNode(field.getFieldName()).addConstraintViolation();
		}
		
		return list.isEmpty();
	}
}
