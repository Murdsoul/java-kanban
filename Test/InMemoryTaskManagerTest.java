import controllers.Managers;

import org.junit.jupiter.api.BeforeEach;
public class InMemoryTaskManagerTest extends TaskManagerTest {

    @BeforeEach
    void BeforeEach(){
        taskManager = Managers.getDefault();
    }
}
