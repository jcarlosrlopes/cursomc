package br.com.nucleos.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nucleos.cursomc.domain.Cliente;
import br.com.nucleos.cursomc.exceptions.ObjectNotFoundException;
import br.com.nucleos.cursomc.repositories.ClienteRepository;

@Service
public class ClienteService {

   @Autowired
   private ClienteRepository repository;

   public Cliente buscar(Long id) {
      Optional<Cliente> cliente = this.repository.findById(id);
      return cliente.orElseThrow(() -> new ObjectNotFoundException(
            "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
   }

}
