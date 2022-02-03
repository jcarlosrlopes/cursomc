package br.com.nucleos.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.nucleos.cursomc.domain.Categoria;
import br.com.nucleos.cursomc.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

   @Transactional(readOnly = true)
   @Query("SELECT DISTINCT p FROM Produto p INNER JOIN p.categorias cat WHERE p.nome LIKE %:nome% AND cat IN :categorias")
   Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias,
         Pageable pageRequest);

}
