package com.example.trelloBoard.Service;

import com.example.trelloBoard.Models.Task;
import com.example.trelloBoard.Models.TaskHistory;
import com.example.trelloBoard.Models.User;
import com.example.trelloBoard.Repository.TaskHistoryRepository;
import com.example.trelloBoard.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/*
Class to implement service methods for tasks
 */
@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskHistoryRepository taskHistoryRepository;

    /*
    Method to add a new task to repository. The function also involves logic to save task history along with task creation
    returns id of newly created task
     */
    public int createTask(Task t){
        try{
        taskRepository.save(t);
        TaskHistory taskHistory =new TaskHistory();
        taskHistory.setUpdationTimestamp(t.getCreationTime());
        taskHistory.setTask(t);
        taskHistoryRepository.save(taskHistory);
        return t.getId();
        }
        catch(Exception e){
            return -1;
        }
    }

    /*
    Service method to modify a task depending on the input parameters sent for a task object. The function also involves logic to save task history along with modification
    input: int id of task to be modified
            Task t consisting of the task object with all required parameters to edit
     */
    public void modifyTask(int id,Task t){
        try {
            Task t1 = taskRepository.findById(id).get();
            User user = new User(); // this step is needed as assignedTo of "User" type
            user.setId(t.getAssignedTo().getId());
            t1.setAssignedTo(user);
            t1.setState(t.getState());
            System.out.println("Comments: " + t1.getComments());
            t1.getComments().add(t.getComments().toString());
            t1.setDescription(t.getDescription());
            taskRepository.save(t1);
            System.out.println(t1.toString());
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            TaskHistory taskHistory = new TaskHistory();
            taskHistory.setUpdationTimestamp(timestamp);
            taskHistory.setTask(t1);
            taskHistoryRepository.save(taskHistory);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
    Method to fetch all tasks from repository
    returns Iterable<Task> of all lists present
     */
    public Iterable<Task> getTasks(){
        return taskRepository.findAll();
    }

    /*
    Method to delete a task from repository
    input: integer id of a task which has to be deleted
     */
    public void deleteTask(int id){
        try{
            List<TaskHistory> historyList = new ArrayList<>();
            List<TaskHistory> fullHistory = (List<TaskHistory>) taskHistoryRepository.findAll();
                for (int i = 0; i < fullHistory.size(); i++) {
                    if (fullHistory.get(i).getTask().getId() == id) {
                        taskHistoryRepository.deleteById(fullHistory.get(i).getHistory_id());
                    }
                }
            taskRepository.deleteById(id);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
    Method to retrieve task history for a task id
    id: int value of task for which details od task history have to be deleted
    returns: List <TaskHistory>
     */
    public List<TaskHistory> getTaskHistory(int id) {
        List<TaskHistory> historyList = new ArrayList<>();
        List<TaskHistory> fullHistory = (List<TaskHistory>) taskHistoryRepository.findAll();
        try {
            for (int i = 0; i < fullHistory.size(); i++) {
                if (fullHistory.get(i).getTask().getId() == id) {
                    historyList.add(fullHistory.get(i));
                }
            }
            return historyList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}