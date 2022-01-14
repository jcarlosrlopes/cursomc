package br.com.nucleos.cursomc.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
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
   private Set<ItemPedido> items = new HashSet<ItemPedido>();

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

   public Set<ItemPedido> getItems() {
      return items;
   }

   public void setItems(Set<ItemPedido> items) {
      this.items = items;
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

}