package task;

import java.util.Objects;

//ask class Requirements: Unique task ID (1 to 10 chars, not null, and immutable), Name (1 to chars, not null),descriptint (1 to 50 chars, and not null)

public class Task {
    private final String taskId;
    private String name;
    private String description;
    

    public Task(String taskId, String name, String description) {
        validateTaskId(taskId);
        validateName(name);
        validateDescription(description);
        
        this.taskId = taskId;
        this.name = name;
        this.description = description;
    }
    
    // Validating methods
    
    private void validateTaskId(String taskId) {
        if (taskId == null) {
            throw new IllegalArgumentException("Task ID should not be null");
        }
        if (taskId.trim().isEmpty()) {
            throw new IllegalArgumentException("Task ID should be empty");
        }
        if (taskId.length() > 10) {
            throw new IllegalArgumentException("Task ID should not be more than 10 chars");
        }
    }
    
    private void validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Task name should not be null");
        }
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Task name should not be empty");
        }
        if (name.length() > 20) {
            throw new IllegalArgumentException("Task name should not be more than 20 chars");
        }
    }
    
    private void validateDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Task description should not be null");
        }
        if (description.trim().isEmpty()) {
            throw new IllegalArgumentException("Task description should not be empty");
        }
        if (description.length() > 50) {
            throw new IllegalArgumentException("Task description should not be more than 50 chars");
        }
    }
    
    // Getters 
    
    public String getTaskId() {
        return taskId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    // setters
    
    public void setName(String name) {
        validateName(name);
        this.name = name;
    }
    
    public void setDescription(String description) {
        validateDescription(description);
        this.description = description;
    }
    

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return taskId.equals(task.taskId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(taskId);
    }
    
    @Override
    public String toString() {
        return "Task{" +
                "ID='" + taskId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}