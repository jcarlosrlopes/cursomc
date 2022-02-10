package br.com.nucleos.cursomc.domain;

import java.time.LocalDate;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

import br.com.nucleos.cursomc.domain.enums.EstadoPagamento;

@Entity
@JsonTypeName("pagamentoBoleto")
public class PagamentoBoleto extends Pagamento {

   private LocalDate dataVencimento;
   private LocalDate dataPagamento;

   public PagamentoBoleto() {
   }

   public PagamentoBoleto(Long id, EstadoPagamento estado, Pedido pedido, LocalDate dataVencimento,
         LocalDate dataPagamento) {
      super(id, estado, pedido);
      this.dataVencimento = dataVencimento;
      this.dataPagamento = dataPagamento;
   }

   public LocalDate getDataVencimento() {
      return dataVencimento;
   }

   public void setDataVencimento(LocalDate dataVencimento) {
      this.dataVencimento = dataVencimento;
   }

   public LocalDate getDataPagamento() {
      return dataPagamento;
   }

   public void setDataPagamento(LocalDate dataPagamento) {
      this.dataPagamento = dataPagamento;
   }

}
