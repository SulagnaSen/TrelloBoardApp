package com.example.trelloBoard.Models;

import javax.persistence.*;
import java.sql.Timestamp;

/*
Entity class for maintaining TaskHistory
 */
@Entity
public class TaskHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int history_id;
    private Timestamp updationTimestamp;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Task task;

    public TaskHistory(int history_id, Timestamp updationTimestamp, Task task, Timestamp creationTime, String comments) {
        this.history_id = history_id;
        this.updationTimestamp = updationTimestamp;
        this.task = task;
    }

    public TaskHistory() {

    }

    public int getHistory_id() {
        return history_id;
    }

    public void setHistory_id(int history_id) {
        this.history_id = history_id;
    }

    public Timestamp getUpdationTimestamp() {
        return updationTimestamp;
    }
    public void setUpdationTimestamp(Timestamp updationTimestamp) {
        this.updationTimestamp = updationTimestamp;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

}
