package br.com.nucleos.cursomc.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.nucleos.cursomc.domain.Cliente;
import br.com.nucleos.cursomc.dto.ClienteDTO;
import br.com.nucleos.cursomc.repositories.ClienteRepository;
import br.com.nucleos.cursomc.resources.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

   @Autowired
   private HttpServletRequest request;

   @Autowired
   private ClienteRepository repository;

   @Override
   public void initialize(ClienteUpdate ann) {
   }

   @Override
   public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
      @SuppressWarnings("unchecked")
      Map<String, String> params = (Map<String, String>) request
            .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
      Long id = Long.parseLong(params.get("id"));

      List<FieldMessage> list = new ArrayList<>();

      Cliente existente = this.repository.findByEmail(objDto.getEmail());
      if (existente != null && !existente.getId().equals(id)) {
         list.add(new FieldMessage("email", "Email já existente"));
      }

      for (FieldMessage e : list) {
         context.disableDefaultConstraintViolation();
         context.buildConstraintViolationWithTemplate(e.getMessage())
               .addPropertyNode(e.getFieldName()).addConstraintViolation();
      }
      return list.isEmpty();
   }
}
