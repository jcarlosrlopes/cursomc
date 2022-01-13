package br.com.nucleos.cursomc.domain;

import javax.persistence.Entity;

import br.com.nucleos.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoCartao extends Pagamento {

   private Integer numeroParcelas;

   public PagamentoCartao() {
   }

   public PagamentoCartao(Long id, EstadoPagamento estado, Pedido pedido, Integer numeroParcelas) {
      super(id, estado, pedido);
      this.numeroParcelas = numeroParcelas;
   }

   public Integer getNumeroParcelas() {
      return numeroParcelas;
   }

   public void setNumeroParcelas(Integer numeroParcelas) {
      this.numeroParcelas = numeroParcelas;
   }

}
