import managers.Managers;
import managers.TaskManager;
import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class TaskManagerTest {

    TaskManager taskManager = Managers.getDefault();

    //============================ Тесты на методы с TASK ============================

    @Test
    void emptyResultOnWrongIDForGetTask() { // c неверным идентификатором TASK
        Task task1 = new Task("1Task", "1TaskDescription", 0L, TaskStatus.NEW);
        taskManager.createTask(task1);

        final Task taskInCorrectId = taskManager.getTaskById(2L);
        assertNull(taskInCorrectId, "Несущетвующая задача найдена");
    }

    @Test
    void successCreateAndGetTaskById() { // получение TASK через ID
        Task task1 = new Task("1Task", "1TaskDescription", 0L, TaskStatus.NEW);
        taskManager.createTask(task1);

        final Task savedTask = taskManager.getTaskById(1L);
        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task1, savedTask, "Задачи не совпадают.");
    }

    @Test
    void successGetAllTasks() { // получение всех TASK
        Task task1 = new Task("1Task", "1TaskDescription", 0L, TaskStatus.NEW);
        taskManager.createTask(task1);
        Task task2 = new Task("2Task", "2TaskDescription", 0L, TaskStatus.NEW);
        taskManager.createTask(task2);

        final List<Task> tasks = taskManager.getAllTasks();
        assertNotNull(tasks, "Задачи на возвращаются.");
        assertEquals(2, tasks.size(), "Неверное количество задач.");
        assertEquals(task1, tasks.get(0), "Задачи не совпадают.");
    }

    //============================ Тесты на методы с SUBTASK ============================
    @Test
    void emptyResultOnWrongIDForGetSubTask() { // c неверным идентификатором SUBTASK
        Epic epic1 = new Epic("1Epic", "1EpicDescription", 0L, TaskStatus.NEW, new ArrayList<>());
        taskManager.createEpic(epic1);
        Subtask subtask13 = new Subtask("13SubTask", "13SubTaskDescription", 0L, TaskStatus.NEW, 1L);
        taskManager.createSubtask(subtask13);

        final Subtask subTaskInCorrectID = taskManager.getSubtaskById(3L); // С неверным идентификатором SUBTASK
        assertNull(subTaskInCorrectID, "Несуществующая подзадача найдена.");
    }

    @Test
    void successCreateAndGetSubTaskById() { // получение SUBTASK через ID
        Epic epic1 = new Epic("1Epic", "1EpicDescription", 0L, TaskStatus.NEW, new ArrayList<>());
        taskManager.createEpic(epic1);
        Subtask subtask13 = new Subtask("13SubTask", "13SubTaskDescription", 0L, TaskStatus.NEW, 1L);
        taskManager.createSubtask(subtask13);

        final Subtask savedSubTask = taskManager.getSubtaskById(2L);
        assertNotNull(savedSubTask, "Подзадача не найдена.");
        assertEquals(subtask13, savedSubTask, "Подзадачи не совпадают.");
    }

    @Test
    void successGetAllSubTask() { // получение всех SUBTASK
        Epic epic1 = new Epic("1Epic", "1EpicDescription", 0L, TaskStatus.NEW, new ArrayList<>());
        taskManager.createEpic(epic1);
        Subtask subtask13 = new Subtask("13SubTask", "13SubTaskDescription", 0L, TaskStatus.NEW, 1L);
        taskManager.createSubtask(subtask13);

        final List<Subtask> subtasks = taskManager.getAllSubtasks();
        assertNotNull(subtasks, "Подзадачи на возвращаются.");
        assertEquals(1, subtasks.size(), "Неверное количество подзадач.");
        assertEquals(subtask13, subtasks.get(0), "Задачи не совпадают.");
    }

    @Test
    void successFromSubTaskGetEpic() { // получение ID Epic через SUBTASK
        Epic epic1 = new Epic("1Epic", "1EpicDescription", 0L, TaskStatus.NEW, new ArrayList<>());
        taskManager.createEpic(epic1);
        Subtask subtask13 = new Subtask("13SubTask", "13SubTaskDescription", 0L, TaskStatus.NEW, 1L);
        taskManager.createSubtask(subtask13);

        final long epicId = subtask13.getIdEpic();
        assertEquals(epic1.getId(), epicId, "ID эпиков не совпадают.");
    }

    //============================ Тесты на методы с EPIC ============================
    @Test
    void emptyResultOnWrongIDForGetEpic() { // c неверным идентификатором EPIC
        final Epic epicInCorrectId = taskManager.getEpicById(2L); // С неверным идентификатором задачи
        assertNull(epicInCorrectId, "Несуществующий Epic найден.");
    }

    @Test
    void successCreateAndGetEpicById() { // получение EPIC через ID
        Epic epic1 = new Epic("1Epic", "1EpicDescription", 0L, TaskStatus.NEW, new ArrayList<>());
        taskManager.createEpic(epic1);
        Subtask subtask11 = new Subtask("11SubTask", "11SubTaskDescription", 0L, TaskStatus.NEW, 1L);
        taskManager.createSubtask(subtask11);

        final Epic savedEpic = taskManager.getEpicById(1L);
        assertNotNull(savedEpic, "Эпик не найден.");
        assertEquals(epic1, savedEpic, "Эпики не совпадают.");
    }

    @Test
    void successGetAllEpics() { // получение всех EPIC
        Epic epic1 = new Epic("1Epic", "1EpicDescription", 0L, TaskStatus.NEW, new ArrayList<>());
        taskManager.createEpic(epic1);
        Epic epic2 = new Epic("2Epic", "2EpicDescription", 0L, TaskStatus.NEW, new ArrayList<>());
        taskManager.createEpic(epic2);

        final List<Epic> epics = taskManager.getAllEpics();
        assertNotNull(epics, "Эпики на возвращаются.");
        assertEquals(2, epics.size(), "Неверное количество Эпиков.");
        assertEquals(epic1, epics.get(0), "Эпики не совпадают.");
    }


    @Test
    void successGetAllSubTaskFromEpic() { // получение всех SUBTASK у EPIC
        Epic epic1 = new Epic("1Epic", "1EpicDescription", 0L, TaskStatus.NEW, new ArrayList<>());
        taskManager.createEpic(epic1);
        Subtask subtask11 = new Subtask("11SubTask", "11SubTaskDescription", 0L, TaskStatus.NEW, 1L);
        taskManager.createSubtask(subtask11);
        Subtask subtask12 = new Subtask("12SubTask", "12SubTaskDescription", 0L, TaskStatus.NEW, 1L);
        taskManager.createSubtask(subtask12);
        Subtask subtask13 = new Subtask("13SubTask", "13SubTaskDescription", 0L, TaskStatus.NEW, 1L);
        taskManager.createSubtask(subtask13);

        final List<Subtask> subtasks = taskManager.getSubtasksByEpicId(epic1.getId());
        final List<Subtask> allSubtasks = taskManager.getAllSubtasks();
        assertEquals(allSubtasks, subtasks, "Подзадачи у Эпика не совпадают.");
    }
}