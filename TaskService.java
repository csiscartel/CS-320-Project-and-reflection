package task;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;


 // Service class 
 // Gives in-memory storage and to perform CRUD operations. Requirements: adding tasks that has unique ID, deleting tasks by thier IDs, updating task fields (name, description)

public class TaskService {
    
    private final Map<String, Task> tasks = new HashMap<>();
    

    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task should not be null");
        }
        
        String id = task.getTaskId();
        
        if (tasks.containsKey(id)) {
            throw new IllegalArgumentException("Task ID exists: " + id);
        }
        
        tasks.put(id, task);
    }
    
    // Deleting task by ID
 
    public void deleteTask(String taskId) {
        if (taskId == null) {
            throw new IllegalArgumentException("Task should not be null");
        }
        
        if (!tasks.containsKey(taskId)) {
            throw new IllegalArgumentException("Not found: " + taskId);
        }
        
        tasks.remove(taskId);
    }
    

    public void updateName(String taskId, String name) {
        Task task = getTask(taskId);
        if (task == null) {
            throw new IllegalArgumentException("Not found: " + taskId);
        }
        task.setName(name);
    }
    

    public void updateDescription(String taskId, String description) {
        Task task = getTask(taskId);
        if (task == null) {
            throw new IllegalArgumentException("Not found: " + taskId);
        }
        task.setDescription(description);
    }
    

    public Task getTask(String taskId) {
        if (taskId == null) {
            return null;
        }
        return tasks.get(taskId);
    }
    

    public Set<String> getAllTaskIds() {
        return new HashSet<>(tasks.keySet());
    }
    

    public int getTaskCount() {
        return tasks.size();
    }
    

    public boolean taskExists(String taskId) {
        if (taskId == null) {
            return false;
        }
        return tasks.containsKey(taskId);
    }
}
