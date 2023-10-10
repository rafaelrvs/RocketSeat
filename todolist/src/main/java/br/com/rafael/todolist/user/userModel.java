package br.com.rafael.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;


@Data// adicionando getter e setters dos atributos  /*Lombook */ 

//tabela de usuario
@Entity(name = "tb_users")  // conceito de ORM precisa usar entidade 
public class UserModel {

  @Id // definindo chave primaria
  @GeneratedValue(generator = "UUID")// definindo geração automatica de id
  private UUID id;

  @Column(unique = true) // definindo nome como unico usuario
  private String username;
  private String name;
  private String password;

  @CreationTimestamp
  private LocalDateTime createdAt;  
}
