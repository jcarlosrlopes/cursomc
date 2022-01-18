package br.com.nucleos.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.nucleos.cursomc.domain.Categoria;
import br.com.nucleos.cursomc.dto.CategoriaDTO;
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

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {
      this.service.deletar(id);
      return ResponseEntity.noContent().build();
   }

   @GetMapping
   public ResponseEntity<List<CategoriaDTO>> findAll() {
      List<CategoriaDTO> categoriasList = this.service.listarTodos().stream().map(cat -> new CategoriaDTO(cat))
            .collect(Collectors.toList());
      return ResponseEntity.ok(categoriasList);
   }

   @GetMapping("/page")
   public ResponseEntity<Page<CategoriaDTO>> findAllPageable(@RequestParam(defaultValue = "0") Integer page,
         @RequestParam(defaultValue = "24") Integer size,
         @RequestParam(defaultValue = "ASC") String direction,
         @RequestParam(defaultValue = "nome") String orderBy) {

      Page<CategoriaDTO> categoriaPaginada = this.service.buscarPaginado(page, size, direction, orderBy)
            .map(cat -> new CategoriaDTO(cat));
      return ResponseEntity.ok(categoriaPaginada);
   }

}
