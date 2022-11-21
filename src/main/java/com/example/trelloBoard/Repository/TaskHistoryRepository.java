package com.example.trelloBoard.Repository;

import com.example.trelloBoard.Models.TaskHistory;
import org.springframework.data.repository.CrudRepository;

public interface TaskHistoryRepository extends CrudRepository<TaskHistory, Integer> {
}
