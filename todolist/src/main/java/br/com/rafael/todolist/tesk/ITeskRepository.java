package br.com.rafael.todolist.tesk;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ITeskRepository extends JpaRepository<TaskModel, UUID> {
  
}
