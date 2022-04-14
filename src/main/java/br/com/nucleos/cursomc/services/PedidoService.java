package br.com.nucleos.cursomc.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nucleos.cursomc.domain.ItemPedido;
import br.com.nucleos.cursomc.domain.PagamentoBoleto;
import br.com.nucleos.cursomc.domain.Pedido;
import br.com.nucleos.cursomc.domain.enums.EstadoPagamento;
import br.com.nucleos.cursomc.exceptions.ObjectNotFoundException;
import br.com.nucleos.cursomc.repositories.ItemPedidoRepository;
import br.com.nucleos.cursomc.repositories.PagamentoRepository;
import br.com.nucleos.cursomc.repositories.PedidoRepository;

@Service
public class PedidoService {

   @Autowired
   private PedidoRepository pedidoRepository;

   @Autowired
   private BoletoService boletoService;

   @Autowired
   private PagamentoRepository pagamentoRepository;

   @Autowired
   private ProdutoService produtoService;

   @Autowired
   private ItemPedidoRepository itemPedidoRepository;

   @Autowired
   private ClienteService clienteService;

   public Pedido buscar(Long id) {
      Optional<Pedido> pedido = this.pedidoRepository.findById(id);
      return pedido.orElseThrow(() -> new ObjectNotFoundException(
            "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
   }

   public Pedido criar(Pedido pedido) {
      pedido.setId(null);
      pedido.setInstante(LocalDateTime.now());
      pedido.setCliente(clienteService.buscar(pedido.getCliente().getId()));
      pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
      pedido.getPagamento().setPedido(pedido);

      if (pedido.getPagamento() instanceof PagamentoBoleto) {
         PagamentoBoleto pgto = (PagamentoBoleto) pedido.getPagamento();
         // Implementação simulando um calculo mais complexo, como a chamada de um web
         // service, por exemplo
         boletoService.preencherPagagamentoBoleto(pgto, pedido.getInstante());
      }

      pedido = pedidoRepository.save(pedido);
      pagamentoRepository.save(pedido.getPagamento());

      for (ItemPedido item : pedido.getItens()) {
         item.setDesconto(BigDecimal.ZERO);
         item.setProduto(produtoService.buscar(item.getProduto().getId()));
         item.setPreco(item.getProduto().getPreco());
         item.setPedido(pedido);
      }
      itemPedidoRepository.saveAll(pedido.getItens());
      System.out.println(pedido);

      return pedido;
   }

}
