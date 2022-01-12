package br.com.nucleos.cursomc.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.nucleos.cursomc.domain.Categoria;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

   @RequestMapping(method = RequestMethod.GET)
   public List<Categoria> listar() {
      Categoria cat1 = new Categoria(1L, "Informática");
      Categoria cat2 = new Categoria(2L, "Escritório");

      List<Categoria> categorias = Arrays.asList(cat1, cat2);

      return categorias;
   }

}
