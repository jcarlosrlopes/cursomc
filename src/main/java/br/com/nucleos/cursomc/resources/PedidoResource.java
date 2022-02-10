package br.com.nucleos.cursomc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.nucleos.cursomc.domain.Pedido;
import br.com.nucleos.cursomc.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

   @Autowired
   private PedidoService pedidoService;

   @GetMapping("/{id}")
   public ResponseEntity<?> find(@PathVariable Long id) {
      Pedido pedido = this.pedidoService.buscar(id);
      return ResponseEntity.ok(pedido);
   }

   @PostMapping
   public ResponseEntity<Void> insert(@Valid @RequestBody Pedido pedido) {
      pedido = this.pedidoService.criar(pedido);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId())
            .toUri();
      return ResponseEntity.created(uri).build();
   }

}