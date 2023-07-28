package com.bonjour.cursospring.services.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bonjour.cursospring.domain.Cliente;
import com.bonjour.cursospring.domain.enums.TipoCliente;
import com.bonjour.cursospring.dto.ClienteNewDTO;
import com.bonjour.cursospring.repositories.ClienteRepository;
import com.bonjour.cursospring.resources.exception.FieldMessage;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO>{
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteInsert ann) {
		
	}
	
	@Override
	public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<FieldMessage>();
		
		if(TipoCliente.toEnum(objDTO.getTipo()).equals(TipoCliente.PESSOA_FISICA)) {
			//list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));
		}
		
		if(TipoCliente.toEnum(objDTO.getTipo()).equals(TipoCliente.PESSOA_JURIDICA)) {
			//list.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido"));
		}
		
		Cliente aux = clienteRepository.findByEmail(objDTO.getEmail());
		if(aux != null)
			list.add(new FieldMessage("email", "Email já cadastrado"));
		
		for(FieldMessage field : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(field.getMessage()).addPropertyNode(field.getFieldName()).addConstraintViolation();
		}
		
		return list.isEmpty();
	}
}
