package com.example.trelloBoard.Repository;

import com.example.trelloBoard.Models.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer> {

}