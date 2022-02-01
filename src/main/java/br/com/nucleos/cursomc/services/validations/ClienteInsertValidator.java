package br.com.nucleos.cursomc.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.nucleos.cursomc.domain.enums.TipoCliente;
import br.com.nucleos.cursomc.dto.ClienteNewDTO;
import br.com.nucleos.cursomc.resources.exception.FieldMessage;
import br.com.nucleos.cursomc.services.validations.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
   @Override
   public void initialize(ClienteInsert ann) {
   }

   @Override
   public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
      List<FieldMessage> list = new ArrayList<>();

      if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
         list.add(new FieldMessage("tipo", "CPF inválido"));
      }

      if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
         list.add(new FieldMessage("tipo", "CNPJ inválido"));
      }

      for (FieldMessage e : list) {
         context.disableDefaultConstraintViolation();
         context.buildConstraintViolationWithTemplate(e.getMessage())
               .addPropertyNode(e.getFieldName()).addConstraintViolation();
      }
      return list.isEmpty();
   }
}
