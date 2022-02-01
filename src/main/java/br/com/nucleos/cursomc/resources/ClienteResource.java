package br.com.nucleos.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.nucleos.cursomc.domain.Cliente;
import br.com.nucleos.cursomc.dto.ClienteDTO;
import br.com.nucleos.cursomc.dto.ClienteNewDTO;
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

   @PostMapping
   public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO clienteDTO) {
      Cliente novoCliente = this.service.criar(Cliente.fromDTO(clienteDTO));
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoCliente.getId())
            .toUri();
      return ResponseEntity.created(uri).build();
   }

   @PutMapping("/{id}")
   public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Long id) {
      clienteDTO.setId(id);
      this.service.atualizar(Cliente.fromDTO(clienteDTO));
      return ResponseEntity.noContent().build();
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {
      this.service.deletar(id);
      return ResponseEntity.noContent().build();
   }

   @GetMapping
   public ResponseEntity<List<ClienteDTO>> findAll() {
      List<ClienteDTO> clientesList = this.service.listarTodos().stream().map(cat -> new ClienteDTO(cat))
            .collect(Collectors.toList());
      return ResponseEntity.ok(clientesList);
   }

   @GetMapping("/page")
   public ResponseEntity<Page<ClienteDTO>> findAllPageable(@RequestParam(defaultValue = "0") Integer page,
         @RequestParam(defaultValue = "24") Integer size,
         @RequestParam(defaultValue = "ASC") String direction,
         @RequestParam(defaultValue = "nome") String orderBy) {

      Page<ClienteDTO> clientesPaginados = this.service.buscarPaginado(page, size, direction, orderBy)
            .map(cat -> new ClienteDTO(cat));
      return ResponseEntity.ok(clientesPaginados);
   }

}
