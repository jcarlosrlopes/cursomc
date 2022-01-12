package br.com.nucleos.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nucleos.cursomc.domain.Categoria;
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

}
