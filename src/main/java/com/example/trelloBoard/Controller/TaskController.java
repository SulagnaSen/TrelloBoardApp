package com.example.trelloBoard.Controller;

import com.example.trelloBoard.Models.Task;
import com.example.trelloBoard.Models.TaskHistory;
import com.example.trelloBoard.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class TaskController {

    @Autowired
    private TaskService taskService;

    /* Method to add new task to the trello board. The new task will have default State set as "TODO"*/
    @PostMapping(path="/addTask") // Map ONLY POST Requests
    public @ResponseBody String addNewTask () {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        Task t=new Task();
        String state="TODO";
        t.setState(state);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        t.setCreationTime(timestamp);
        return "Task created with id:"+taskService.createTask(t);
    }

    /*
    Method to modify task, by assigning to user, adding comments and updating description
    @PathVariable: takes an int value of task id which has to be updated
    @RequestBody: takes a task object in the form of JSON and saves it to the database
    returns String signifying success or failure of task

    Sample postman input for url http://localhost:8080/demo/modifyTask/19 is given below
    {
    "comments": ["Assigning task to user"],
    "description" : "This is first task of trello clone board",
    "state":"DOING",
    "assignedTo":{
        "id":15
        }
    }
     */
    @PutMapping (path ="/modifyTask/{id}")
    public @ResponseBody String modifyTask(@PathVariable int id, @RequestBody Task t){
        try {
            taskService.modifyTask(id, t);
            return "Success";
        } catch( Exception e){
            return "Failure";
        }
    }

    /*
    Method to delete a task
    @RequestParam: takes an int value of task id which has to be deleted
    returns String to denote success or failure of delete operation
    */
    @DeleteMapping (path="/deleteTask")
    public @ResponseBody String deleteTask(@RequestParam int id){
        try {
            taskService.deleteTask(id);
            return "Success";
        } catch( Exception e){
            return "Failure";
        }
    }

    /*
       Method to fetch complete history of all changes made to a task
       @PathVariable: Takes an integer value of task id whose history has to be fetched
       returns List<TaskHistory> of all history corresponding to a task id
        */
    @GetMapping(path="/showHistory/{id}")
    public @ResponseBody List<TaskHistory> getTaskHistory(@PathVariable int id){
        System.out.println(taskService.getTaskHistory(id).toString());
        return taskService.getTaskHistory(id);
    }

    /*
    Method to display all tasks present in a trello board
    returns List<Task> consisting of all the tasks in trello board
     */
    @GetMapping(path="/showBoard")
    public @ResponseBody List<Task> showBoard(){
        return (List<Task>) taskService.getTasks();
    }


}