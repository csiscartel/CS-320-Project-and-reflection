package task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Task class unit test

class TaskTest {
    
    private static final String VALID_ID = "TASK12345";
    private static final String VALID_NAME = "Complete Assignment";
    private static final String VALID_DESCRIPTION = "Finish CS320 project one submission";
    
    @Test
    void testValidTaskCreation() {
        Task task = new Task(VALID_ID, VALID_NAME, VALID_DESCRIPTION);
        
        assertEquals(VALID_ID, task.getTaskId());
        assertEquals(VALID_NAME, task.getName());
        assertEquals(VALID_DESCRIPTION, task.getDescription());
    }
    
    // Testing Task ID 
    
    @Test
    void testTaskIdNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task(null, VALID_NAME, VALID_DESCRIPTION);
        });
    }
    
    @Test
    void testTaskIdEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("", VALID_NAME, VALID_DESCRIPTION);
        });
    }
    
    @Test
    void testTaskIdTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("12345678901", VALID_NAME, VALID_DESCRIPTION);
        });
    }
    
    @Test
    void testTaskIdMaxLength() {
        String maxId = "1234567890"; // 10 chars
        Task task = new Task(maxId, VALID_NAME, VALID_DESCRIPTION);
        assertEquals(maxId, task.getTaskId());
    }
    
    // Testing Name 
    
    @Test
    void testNameNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task(VALID_ID, null, VALID_DESCRIPTION);
        });
    }
    
    @Test
    void testNameEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task(VALID_ID, "", VALID_DESCRIPTION);
        });
    }
    
    @Test
    void testNameTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task(VALID_ID, "123456789012345678901", VALID_DESCRIPTION);
        });
    }
    
    @Test
    void testNameMaxLength() {
        String maxName = "12345678901234567890"; // 20 chars
        Task task = new Task(VALID_ID, maxName, VALID_DESCRIPTION);
        assertEquals(maxName, task.getName());
    }
    
    // Testing Description 
    
    @Test
    void testDescriptionNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task(VALID_ID, VALID_NAME, null);
        });
    }
    
    @Test
    void testDescriptionEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task(VALID_ID, VALID_NAME, "");
        });
    }
    
    @Test
    void testDescriptionTooLong() {
        String longDesc = "This description is too long for set the fifty character limit that I set";
        assertThrows(IllegalArgumentException.class, () -> {
            new Task(VALID_ID, VALID_NAME, longDesc);
        });
    }
    
    @Test
    void testDescriptionMaxLength() {
        String maxDesc = "12345678901234567890123456789012345678901234567890"; // 50 chars
        Task task = new Task(VALID_ID, VALID_NAME, maxDesc);
        assertEquals(maxDesc, task.getDescription());
    }
    
    // Testing Setter     
    @Test
    void testSetNameValid() {
        Task task = new Task(VALID_ID, VALID_NAME, VALID_DESCRIPTION);
        task.setName("Updated Name");
        assertEquals("Updated Name", task.getName());
    }
    
    @Test
    void testSetNameInvalid() {
        Task task = new Task(VALID_ID, VALID_NAME, VALID_DESCRIPTION);
        assertThrows(IllegalArgumentException.class, () -> {
            task.setName("This name is too long for the set limit");
        });
        assertEquals(VALID_NAME, task.getName());
    }
    
    @Test
    void testSetDescriptionValid() {
        Task task = new Task(VALID_ID, VALID_NAME, VALID_DESCRIPTION);
        task.setDescription("Updated description for testing");
        assertEquals("Updated description for testing", task.getDescription());
    }
    
    @Test
    void testSetDescriptionInvalid() {
        Task task = new Task(VALID_ID, VALID_NAME, VALID_DESCRIPTION);
        String longDesc = "This is more than the maximum character limit of 50 that I set in the requirements document";
        assertThrows(IllegalArgumentException.class, () -> {
            task.setDescription(longDesc);
        });
        assertEquals(VALID_DESCRIPTION, task.getDescription());
    }
    
    // Testing Object Method     
    @Test
    void testEqualsAndHashCode() {
        Task task1 = new Task("T1", "Name1", "Desc1");
        Task task2 = new Task("T1", "Name2", "Desc2");
        Task task3 = new Task("T2", "Name1", "Desc1");
        
        assertEquals(task1, task2);
        assertNotEquals(task1, task3);
        assertEquals(task1.hashCode(), task2.hashCode());
        assertNotEquals(task1.hashCode(), task3.hashCode());
    }
    
    @Test
    void testToString() {
        Task task = new Task(VALID_ID, VALID_NAME, VALID_DESCRIPTION);
        assertNotNull(task.toString());
        assertTrue(task.toString().contains(VALID_ID));
    }
}





