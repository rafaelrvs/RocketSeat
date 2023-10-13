package br.com.rafael.todolist.tesk;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.procedure.internal.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafael.todolist.utils.utils;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@RestController // dizendo para task que este é o controller

@RequestMapping("/tasks")
public class TaskController {
  // precisa passar @Request se não ele não sabe de onde vem a informação

  @Autowired //ciclo de vida
  private ITeskRepository taskRepository;
  @PostMapping("/")
  public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request){
    var idUser = request.getAttribute("idUser");
    taskModel.setIdUser((UUID)idUser);
    //validação de data
    var currentDate = LocalDateTime.now();

    if(currentDate.isAfter(taskModel.getStartAt())|| currentDate.isAfter(taskModel.getEndAt())){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
      .body("A data de inicio ou data de termino deve ser maior do que a data atual");
    }

    if(taskModel.getStartAt().isAfter(taskModel.getEndAt())){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
      .body("A data de termino deve ser maior do que a data de inicio");
    }

    var task = this.taskRepository.save(taskModel); // gerando uma tarefa criada
    return  ResponseEntity.status(HttpStatus.OK).body(task);
  }
  @GetMapping("/")
  public List<TaskModel>list(HttpServletRequest request){
    var idUser = request.getAttribute("idUser");
    var tasks = this.taskRepository.findByIdUser((UUID)idUser);
    return tasks;
  }
  
  @PutMapping("/{id}")
  public ResponseEntity update(@RequestBody TaskModel taskModel, @PathVariable UUID id ,HttpServletRequest request){
    var idUser = request.getAttribute("idUser");
    var task =  this.taskRepository.findById(id).orElse(null);
    if(!task.getIdUser().equals(idUser)){
          return ResponseEntity.status(HttpStatus.BAD_REQUEST)
      .body("Usuario não tem permissão para alterar está tarefa");
    }
    utils.copyNonNullProperties(taskModel,task);
    taskModel.setId((UUID)idUser);
    taskModel.setId(id);
    var taskUpdated = this.taskRepository.save(taskModel);
    return ResponseEntity.ok().body(taskUpdated);
  }
  
}
