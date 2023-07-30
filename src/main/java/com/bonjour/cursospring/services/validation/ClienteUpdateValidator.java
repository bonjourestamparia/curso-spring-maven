package com.bonjour.cursospring.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.bonjour.cursospring.domain.Cliente;
import com.bonjour.cursospring.domain.enums.TipoCliente;
import com.bonjour.cursospring.dto.ClienteDTO;
import com.bonjour.cursospring.dto.ClienteNewDTO;
import com.bonjour.cursospring.repositories.ClienteRepository;
import com.bonjour.cursospring.resources.exception.FieldMessage;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO>{
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void initialize(ClienteUpdate ann) {
		
	}
	
	@Override
	public boolean isValid(ClienteDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<FieldMessage>();
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uid = Integer.parseInt(map.get("id"));
		
		Cliente aux = clienteRepository.findByEmail(objDTO.getEmail());
		
		if(aux != null && !uid.equals(aux.getId()))
			list.add(new FieldMessage("email", "Email já cadastrado"));
		
		for(FieldMessage field : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(field.getMessage()).addPropertyNode(field.getFieldName()).addConstraintViolation();
		}
		
		return list.isEmpty();
	}
}
