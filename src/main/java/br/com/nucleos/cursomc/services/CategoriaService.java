package br.com.nucleos.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.nucleos.cursomc.domain.Categoria;
import br.com.nucleos.cursomc.exceptions.DataIntegrityException;
import br.com.nucleos.cursomc.exceptions.ObjectNotFoundException;
import br.com.nucleos.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

   @Autowired
   private CategoriaRepository repository;

   public Categoria buscar(Long id) {
      Optional<Categoria> categoria = this.repository.findById(id);
      return categoria.orElseThrow(() -> new ObjectNotFoundException(
            "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
   }

   public Categoria criar(Categoria categoria) {
      categoria.setId(null);
      return this.repository.save(categoria);
   }

   public void deletar(Long id) {
      buscar(id);

      try {
         repository.deleteById(id);
      } catch (DataIntegrityViolationException e) {
         throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos.");
      }
   }

   public List<Categoria> listarTodos() {
      return this.repository.findAll();
   }

   public Page<Categoria> buscarPaginado(Integer page, Integer size, String direction, String orderBy) {
      PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
      return this.repository.findAll(pageRequest);
   }

}
