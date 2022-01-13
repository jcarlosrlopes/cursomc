package br.com.nucleos.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nucleos.cursomc.domain.Cliente;
import br.com.nucleos.cursomc.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

   @Autowired
   private ClienteService service;

   @GetMapping("/{id}")
   public ResponseEntity<?> find(@PathVariable Long id) {
      Cliente cliente = this.service.buscar(id);
      return ResponseEntity.ok(cliente);
   }

}
