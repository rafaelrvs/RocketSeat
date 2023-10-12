package br.com.rafael.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.rafael.todolist.user.IUSerRepository;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component // toda classe que o sprimg gerencia, precisa colocar anotation, classe generica
public class FilterTaskAuth extends OncePerRequestFilter {
  @Autowired
  private IUSerRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    var servletPath = request.getServletPath();
    if (servletPath.startsWith("/tasks/")) { //toda vez que a rota for = task ele valida

      var authorization = request.getHeader("Authorization");
      // Pegar a autenticação , usuario e senha

      // substring = extrai algum texto ou conteudo de uma string
      var authEncoded = authorization.substring("Basic".length()).trim();// cortando a palavra e espaço em branco
   
      byte[] authDecode = Base64.getDecoder().decode(authEncoded);

      var authString = new String(authDecode);
      String[] credentials = authString.split(":");

      String username = credentials[0];
      String password = credentials[1];

      // validar usuario
      var user = this.userRepository.findByUsername(username);
      if (user == null) {
        response.sendError(401);
      } else {
        // seguir viagen
        var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

        if (passwordVerify.verified) {
          request.setAttribute("idUser", user.getId());
          filterChain.doFilter(request, response);
        } else {
          response.sendError(401);
        }

      }
    }
    else{
      filterChain.doFilter(request, response);

    }
  }

}
