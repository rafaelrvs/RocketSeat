package br.com.rafael.todolist.tesk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // dizendo para task que este é o controller

@RequestMapping("/tasks")
public class TaskController {
  // precisa passar @Request se não ele não sabe de onde vem a informação

  @Autowired //ciclo de vida
  private ITeskRepository taskRepository;
  @PostMapping("/")
  public TaskModel create(@RequestBody TaskModel taskModel){
    var task = this.taskRepository.save(taskModel); // gerando uma tarefa criada
    return task;
  }
  
}
