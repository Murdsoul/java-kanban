package managers;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {

    List<Task> getAllTasks();

    List<Epic> getAllEpics();

    List<Subtask> getAllSubtasks();


    void deleteAllTasks();

    void deleteAllEpics();

    void deleteAllSubtasks();

    Task getTaskById(Long id);

    Epic getEpicById(Long id);

    Subtask getSubtaskById(Long id);

    void createTask(Task task);

    void createEpic(Epic epic);

    void createSubtask(Subtask subTask);

    void deleteTaskById(Long id);

    void deleteEpicById(Long id);

    void deleteSubtaskById(Long id);

    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateSubtask(Subtask subTask);

    List<Task> getHistory();

    ArrayList<Subtask> getSubtasksByEpicId(Long id);

}
