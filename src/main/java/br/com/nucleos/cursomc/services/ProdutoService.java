package br.com.nucleos.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.nucleos.cursomc.domain.Categoria;
import br.com.nucleos.cursomc.domain.Produto;
import br.com.nucleos.cursomc.exceptions.ObjectNotFoundException;
import br.com.nucleos.cursomc.repositories.CategoriaRepository;
import br.com.nucleos.cursomc.repositories.ProdutoRepository;

@Service
public class ProdutoService {

   @Autowired
   private ProdutoRepository produtoRepository;

   @Autowired
   private CategoriaRepository categoriaRepository;

   public Produto buscar(Long id) {
      Optional<Produto> produto = this.produtoRepository.findById(id);
      return produto.orElseThrow(() -> new ObjectNotFoundException(
            "Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
   }

   public Page<Produto> search(String nome, List<Long> categoriasId, Integer page, Integer size, String direction,
         String orderBy) {
      PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
      List<Categoria> categorias = this.categoriaRepository.findAllById(categoriasId);
      return produtoRepository.search(nome, categorias, pageRequest);
   }

}
