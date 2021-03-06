package br.com.nucleos.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.nucleos.cursomc.domain.Cliente;
import br.com.nucleos.cursomc.exceptions.DataIntegrityException;
import br.com.nucleos.cursomc.exceptions.ObjectNotFoundException;
import br.com.nucleos.cursomc.repositories.ClienteRepository;
import br.com.nucleos.cursomc.repositories.EnderecoRepository;

@Service
public class ClienteService {

   @Autowired
   private ClienteRepository repository;

   @Autowired
   private EnderecoRepository enderecoRepository;

   @Autowired
   private BCryptPasswordEncoder passwordEncoder;

   public Cliente buscar(Long id) {
      Optional<Cliente> cliente = this.repository.findById(id);
      return cliente.orElseThrow(() -> new ObjectNotFoundException(
            "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
   }

   public Cliente criar(Cliente novoCliente) {
      novoCliente.setId(null);
      novoCliente.setSenha(passwordEncoder.encode(novoCliente.getSenha()));
      novoCliente = this.repository.save(novoCliente);
      this.enderecoRepository.saveAll(novoCliente.getEnderecos());
      return novoCliente;
   }

   public Cliente atualizar(Cliente cliente) {
      Cliente clienteBanco = buscar(cliente.getId());
      updateData(clienteBanco, cliente);
      return this.repository.save(clienteBanco);
   }

   public void deletar(Long id) {
      buscar(id);

      try {
         repository.deleteById(id);
      } catch (DataIntegrityViolationException e) {
         throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados.");
      }
   }

   public List<Cliente> listarTodos() {
      return this.repository.findAll();
   }

   public Page<Cliente> buscarPaginado(Integer page, Integer size, String direction, String orderBy) {
      PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
      return this.repository.findAll(pageRequest);
   }

   private void updateData(Cliente clienteBanco, Cliente cliente) {
      clienteBanco.setNome(cliente.getNome());
      clienteBanco.setEmail(cliente.getEmail());
   }

}
