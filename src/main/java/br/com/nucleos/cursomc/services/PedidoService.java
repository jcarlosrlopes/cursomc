package br.com.nucleos.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nucleos.cursomc.domain.Pedido;
import br.com.nucleos.cursomc.exceptions.ObjectNotFoundException;
import br.com.nucleos.cursomc.repositories.PedidoRepository;

@Service
public class PedidoService {

   @Autowired
   private PedidoRepository pedidoRepository;

   public Pedido buscar(Long id) {
      Optional<Pedido> pedido = this.pedidoRepository.findById(id);
      return pedido.orElseThrow(() -> new ObjectNotFoundException(
            "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
   }

}
