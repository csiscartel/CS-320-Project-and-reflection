package task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// TaskService class unit tests.

class TaskServiceTest {
    
    private TaskService service;
    
    private static final String VALID_ID = "TASK001";
    private static final String VALID_NAME = "Complete Assignment";
    private static final String VALID_DESCRIPTION = "Finish CS320 project one";
    
    @BeforeEach
    void setUp() {
        service = new TaskService();
    }
    
    private Task createValidTask() {
        return new Task(VALID_ID, VALID_NAME, VALID_DESCRIPTION);
    }
    
    private Task createValidTask(String id) {
        return new Task(id, VALID_NAME, VALID_DESCRIPTION);
    }
    
    
    @Test
    void testAddTask() {
        Task task = createValidTask();
        service.addTask(task);
        
        assertEquals(task, service.getTask(VALID_ID));
        assertEquals(1, service.getTaskCount());
        assertTrue(service.taskExists(VALID_ID));
    }
    
    @Test
    void testAddNullTask() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.addTask(null);
        });
    }
    
    @Test
    void testAddDuplicateTask() {
        Task task1 = createValidTask("ID1");
        Task task2 = createValidTask("ID1");
        
        service.addTask(task1);
        
        assertThrows(IllegalArgumentException.class, () -> {
            service.addTask(task2);
        });
        
        assertEquals(task1, service.getTask("ID1"));
        assertEquals(1, service.getTaskCount());
    }
    
    @Test
    void testAddMultipleTasks() {
        Task task1 = createValidTask("ID1");
        Task task2 = createValidTask("ID2");
        Task task3 = createValidTask("ID3");
        
        service.addTask(task1);
        service.addTask(task2);
        service.addTask(task3);
        
        assertEquals(3, service.getTaskCount());
        assertEquals(task1, service.getTask("ID1"));
        assertEquals(task2, service.getTask("ID2"));
        assertEquals(task3, service.getTask("ID3"));
    }
    
    //delete Task Tests 
    
    @Test
    void testDeleteTask() {
        Task task = createValidTask();
        service.addTask(task);
        
        assertTrue(service.taskExists(VALID_ID));
        
        service.deleteTask(VALID_ID);
        
        assertNull(service.getTask(VALID_ID));
        assertFalse(service.taskExists(VALID_ID));
        assertEquals(0, service.getTaskCount());
    }
    
    @Test
    void testDeleteNonExistentTask() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteTask("NONEXISTENT");
        });
    }
    
    @Test
    void testDeleteTaskNullId() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteTask(null);
        });
    }
    
    
    @Test
    void testUpdateName() {
        Task task = createValidTask();
        service.addTask(task);
        
        service.updateName(VALID_ID, "Updated");
        assertEquals("Updated Task Name", service.getTask(VALID_ID).getName());
    }
    
    @Test
    void testUpdateDescription() {
        Task task = createValidTask();
        service.addTask(task);
        
        service.updateDescription(VALID_ID, "Updated test task description");
        assertEquals("Updated test task description", service.getTask(VALID_ID).getDescription());
    }
    
    @Test
    void testUpdateNonExistentTask() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateName("NONEXISTENT", "New Name");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateDescription("NONEXISTENT", "New Description");
        });
    }
    
    @Test
    void testUpdateWithInvalidData() {
        Task task = createValidTask();
        service.addTask(task);
        
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateName(VALID_ID, null);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateName(VALID_ID, "Name is long for the set 20 char limit that I set in the requirement");
        });
        
        assertEquals(VALID_NAME, service.getTask(VALID_ID).getName());
    }
    
    
    @Test
    void testGetTaskNullId() {
        assertNull(service.getTask(null));
    }
    
    @Test
    void testGetTaskNonExistent() {
        assertNull(service.getTask("NONEXISTENT"));
    }
    
    
    @Test
    void testGetAllTaskIds() {
        service.addTask(createValidTask("ID1"));
        service.addTask(createValidTask("ID2"));
        
        assertEquals(2, service.getAllTaskIds().size());
        assertTrue(service.getAllTaskIds().contains("ID1"));
        assertTrue(service.getAllTaskIds().contains("ID2"));
    }
    
    @Test
    void testTaskExists() {
        assertFalse(service.taskExists(VALID_ID));
        
        service.addTask(createValidTask());
        assertTrue(service.taskExists(VALID_ID));
        assertFalse(service.taskExists(null));
    }
    
    @Test
    void testServiceStartsEmpty() {
        assertEquals(0, service.getTaskCount());
        assertTrue(service.getAllTaskIds().isEmpty());
    }
}