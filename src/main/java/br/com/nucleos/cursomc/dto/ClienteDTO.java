package br.com.nucleos.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.nucleos.cursomc.domain.Cliente;
import br.com.nucleos.cursomc.services.validations.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable {
   private static final long serialVersionUID = 1L;

   private Long id;

   @NotEmpty(message = "Preenchimento obrigatório")
   @Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
   private String nome;

   @NotEmpty(message = "Preenchimento obrigatório")
   @Email(message = "Email inválido")
   private String email;

   public ClienteDTO() {
   }

   public ClienteDTO(Long id, String nome, String email) {
      this.id = id;
      this.nome = nome;
      this.email = email;
   }

   public ClienteDTO(Cliente cliente) {
      this.id = cliente.getId();
      this.nome = cliente.getNome();
      this.email = cliente.getEmail();
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

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

}
