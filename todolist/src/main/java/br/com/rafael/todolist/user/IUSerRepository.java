package br.com.rafael.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


/* Interface: Contrato dentro da aplicação que não possui a implementação dos metodos,
 tem que usar uma classe que faça implementação
*/
//generator <> atributos dinamicos
public interface IUSerRepository extends JpaRepository<UserModel,UUID /*Classe que representa e id*/>{
UserModel findByUsername(String username); // Select buscando a coluna Username
}
