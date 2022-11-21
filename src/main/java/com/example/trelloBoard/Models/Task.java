package com.example.trelloBoard.Models;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/*
Entity class for Task
 */
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Column(columnDefinition = "TEXT")
    private String description;
    @ElementCollection
    private List<String> comments = new ArrayList<String>();
    private String state;
    private Timestamp creationTime;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    public User assignedTo;
    public Timestamp getCreationTime() {
        return creationTime;
    }
    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public Task(int id, String description, List<String> comments, String state, Timestamp creationTime, User assignedTo) {
        this.id = id;
        this.description = description;
        this.comments = comments;
        this.state = state;
        this.creationTime = creationTime;
        this.assignedTo = assignedTo;
    }

    public Task() {

    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public String getState() {
        return state;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", comments=" + comments +
                ", state='" + state + '\'' +
                ", creationTime=" + creationTime +
                ", assignedTo=" + assignedTo +
                '}';
    }
}