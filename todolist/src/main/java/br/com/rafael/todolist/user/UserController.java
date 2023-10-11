package br.com.rafael.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * Modiicador
 * Public
 * Private
 * Protected
 */

 @RestController() //definindo controller
 @RequestMapping("/users") // definindo rota
public class UserController {
  /*
   * STRING
   * UNTEGER
   * DOUBLE
   * FLOAT
   * CHAR
   * DATE
   * VOID
   */
   @Autowired // gerenciando o ciclo de vida
   private IUSerRepository userRepository;

   @PostMapping("/")

   // ResponseEntity para mensagem de erro
  public ResponseEntity create (@RequestBody UserModel userModel){
      var user = this.userRepository.findByUsername(userModel.getUsername());
      if(user != null){
        System.out.println("Usuario já existe");

        // MEnsagem de erro
        // Status code
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("usuario já existe");
      }
      var passwordHashred =  BCrypt.withDefaults().hashToString(10, userModel.getPassword().toCharArray()/*transformando em um array de caracter */);// força da criptografia e oque vai ser criptografado
     
      userModel.setPassword(passwordHashred);
      var userCreated = this.userRepository.save(userModel);

      return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
  }
  
}
