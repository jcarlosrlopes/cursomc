package br.com.nucleos.cursomc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable {
   private static final long serialVersionUID = 1L;

   @JsonIgnore
   @EmbeddedId
   private ItemPedidoPK id = new ItemPedidoPK();
   private BigDecimal desconto;
   private Integer quantidade;
   private BigDecimal preco;

   public ItemPedido() {
   }

   public ItemPedido(Pedido pedido, Produto produto, BigDecimal desconto, Integer quantidade, BigDecimal preco) {
      this.id.setPedido(pedido);
      this.id.setProduto(produto);
      this.desconto = desconto;
      this.quantidade = quantidade;
      this.preco = preco;
   }

   public ItemPedidoPK getId() {
      return id;
   }

   public BigDecimal getSubtotal() {
      return preco.subtract(desconto).multiply(BigDecimal.valueOf(quantidade));
   }

   public void setId(ItemPedidoPK id) {
      this.id = id;
   }

   public BigDecimal getDesconto() {
      return desconto;
   }

   public void setDesconto(BigDecimal desconto) {
      this.desconto = desconto;
   }

   public Integer getQuantidade() {
      return quantidade;
   }

   public void setQuantidade(Integer quantidade) {
      this.quantidade = quantidade;
   }

   public BigDecimal getPreco() {
      return preco;
   }

   public void setPreco(BigDecimal preco) {
      this.preco = preco;
   }

   @JsonIgnore
   public Pedido getPedido() {
      return this.id.getPedido();
   }

   public void setPedido(Pedido pedido) {
      this.id.setPedido(pedido);
   }

   public Produto getProduto() {
      return this.id.getProduto();
   }

   public void setProduto(Produto produto) {
      this.id.setProduto(produto);
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
      ItemPedido other = (ItemPedido) obj;
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

      StringBuilder builder = new StringBuilder();
      builder.append(getProduto().getNome());
      builder.append(", QTDE: ");
      builder.append(getQuantidade());
      builder.append(", Pre??o Unit??rio: ");
      builder.append(nf.format(getPreco()));
      builder.append(", Total: ");
      builder.append(nf.format(getSubtotal()));
      builder.append("\n");

      return builder.toString();
   }

}
