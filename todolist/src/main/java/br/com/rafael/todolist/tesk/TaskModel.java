package br.com.rafael.todolist.tesk;
/**
 * ID
 * Usuario
 * Descrição
 * Titulo
 * Data de inicio
 * Data de Termino
 * Prioridade
*/

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity(name = "tb_tasks")
public class TaskModel {
  @Id
  @GeneratedValue(generator = "UUID") // GERANDO ID ALEATORIO
  private UUID id;
  private String description;
  @Column(length = 50)
  private String title;
  private LocalDateTime startAt;
  private LocalDateTime endAt;
  private String priority;

  @CreationTimestamp
  private LocalDateTime createAt;

  private UUID idUser;
}
