package br.com.rafael.todolist.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

   @PostMapping("/")
  public void create (@RequestBody UserModel userModel){
      System.out.println(userModel.name);
  }
  
}
