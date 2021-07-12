package com.kminfo.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.kminfo.cursomc.domain.Cliente;
import com.kminfo.cursomc.domain.enums.TipoCliente;
import com.kminfo.cursomc.dto.ClienteNewDto;
import com.kminfo.cursomc.repositories.ClienteRepository;
import com.kminfo.cursomc.resources.exceptions.FieldMessage;
import com.kminfo.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDto> {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDto objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDto.getTipo().equals(TipoCliente.PESSOA_FISICA.getCod()) && !BR.isValidCPF(objDto.getCpf_cnpj())) {
			list.add(new FieldMessage("cpf_cnpj","CPF invalido"));
		}
		

		if(objDto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpf_cnpj())) {
			list.add(new FieldMessage("cpf_cnpj","CNPJ invalido"));
		}
		
		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		// inclua os testes aqui, inserindo erros na lista

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}