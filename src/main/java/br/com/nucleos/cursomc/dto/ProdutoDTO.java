package br.com.nucleos.cursomc.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.nucleos.cursomc.domain.Produto;

public class ProdutoDTO implements Serializable {

   private static final long serialVersionUID = 1L;

   private Long id;
   private String nome;
   private BigDecimal preco;

   public ProdutoDTO() {
   }

   public ProdutoDTO(Produto prod) {
      this.id = prod.getId();
      this.nome = prod.getNome();
      this.preco = prod.getPreco();
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getNome() {
      return nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public BigDecimal getPreco() {
      return preco;
   }

   public void setPreco(BigDecimal preco) {
      this.preco = preco;
   }

}
