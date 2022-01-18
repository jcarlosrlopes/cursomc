package br.com.nucleos.cursomc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.nucleos.cursomc.domain.Categoria;
import br.com.nucleos.cursomc.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

   @Autowired
   private CategoriaService service;

   @GetMapping("/{id}")
   public ResponseEntity<?> find(@PathVariable Long id) {
      Categoria categoria = this.service.buscar(id);
      return ResponseEntity.ok(categoria);
   }

   @PostMapping
   public ResponseEntity<Void> insert(@RequestBody Categoria categoria) {
      Categoria novaCategoria = this.service.criar(categoria);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novaCategoria.getId())
            .toUri();
      return ResponseEntity.created(uri).build();
   }

   @DeleteMapping("{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {
      this.service.deletar(id);
      return ResponseEntity.noContent().build();
   }

}
