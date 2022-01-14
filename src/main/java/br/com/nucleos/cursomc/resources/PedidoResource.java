package br.com.nucleos.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}