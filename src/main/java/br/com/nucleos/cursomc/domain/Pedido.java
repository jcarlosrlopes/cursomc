package br.com.nucleos.cursomc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Pedido implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private LocalDateTime instante;

   @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
   private Pagamento pagamento;

   @ManyToOne
   @JoinColumn(name = "cliente_id")
   private Cliente cliente;

   @ManyToOne
   @JoinColumn(name = "endereco_id")
   private Endereco enderecoEntrega;

   @OneToMany(mappedBy = "id.pedido")
   private Set<ItemPedido> itens = new HashSet<ItemPedido>();

   public Pedido() {
   }

   public Pedido(Long id, LocalDateTime instante, Cliente cliente, Endereco enderecoEntrega) {
      this.id = id;
      this.instante = instante;
      this.cliente = cliente;
      this.enderecoEntrega = enderecoEntrega;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public LocalDateTime getInstante() {
      return instante;
   }

   public void setInstante(LocalDateTime instante) {
      this.instante = instante;
   }

   public Pagamento getPagamento() {
      return pagamento;
   }

   public void setPagamento(Pagamento pagamento) {
      this.pagamento = pagamento;
   }

   public Cliente getCliente() {
      return cliente;
   }

   public void setCliente(Cliente cliente) {
      this.cliente = cliente;
   }

   public Endereco getEnderecoEntrega() {
      return enderecoEntrega;
   }

   public void setEnderecoEntrega(Endereco enderecoEntrega) {
      this.enderecoEntrega = enderecoEntrega;
   }

   public Set<ItemPedido> getItens() {
      return itens;
   }

   public void setItens(Set<ItemPedido> itens) {
      this.itens = itens;
   }

   public BigDecimal getTotalPedido() {
      return this.itens.stream().map(ItemPedido::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Pedido other = (Pedido) obj;
      if (id == null) {
         if (other.id != null)
            return false;
      } else if (!id.equals(other.id))
         return false;
      return true;
   }

   @Override
   public String toString() {
      NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
      DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");

      StringBuilder builder = new StringBuilder();
      builder.append("Pedido Número: ");
      builder.append(getId());
      builder.append(", Instante: ");
      builder.append(getInstante().format(df));
      builder.append(", Cliente: ");
      builder.append(getCliente().getNome());
      builder.append(", Situação Pagamento: ");
      builder.append(getPagamento().getEstado());
      builder.append("\nDetalhes:\n");

      for (ItemPedido ip : getItens()) {
         builder.append(ip.toString());
      }

      builder.append("Valor total: ");
      builder.append(nf.format(getTotalPedido()));

      return builder.toString();
   }

}
