package br.com.nucleos.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.nucleos.cursomc.domain.Categoria;
import br.com.nucleos.cursomc.domain.Produto;
import br.com.nucleos.cursomc.dto.CategoriaDTO;
import br.com.nucleos.cursomc.dto.ProdutoDTO;
import br.com.nucleos.cursomc.resources.utils.URL;
import br.com.nucleos.cursomc.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

   @Autowired
   private ProdutoService produtoService;

   @GetMapping("/{id}")
   public ResponseEntity<?> find(@PathVariable Long id) {
      Produto produto = this.produtoService.buscar(id);
      return ResponseEntity.ok(produto);
   }

   @GetMapping
   public ResponseEntity<Page<ProdutoDTO>> findAllPageable(@RequestParam(defaultValue = "") String nome,
         @RequestParam(defaultValue = "") String categorias,
         @RequestParam(defaultValue = "0") Integer page,
         @RequestParam(defaultValue = "24") Integer size,
         @RequestParam(defaultValue = "ASC") String direction,
         @RequestParam(defaultValue = "nome") String orderBy) {

      String nomeDecoded = URL.decodeParam(nome);
      List<Long> categoriasId = URL.decodeLongList(categorias);

      Page<ProdutoDTO> categoriaPaginada = this.produtoService
            .search(nomeDecoded, categoriasId, page, size, direction, orderBy)
            .map(prod -> new ProdutoDTO(prod));
      return ResponseEntity.ok(categoriaPaginada);
   }

}