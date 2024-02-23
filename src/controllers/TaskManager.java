package controllers;
import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;

public interface TaskManager {

    ArrayList<Task> getAllTasks();
    ArrayList<Epic> getAllEpics();
    ArrayList<Subtask> getAllSubtasks();


    void deleteAllTasks();
    void deleteAllEpics();
    void deleteAllSubtasks();

    Task getTaskById(Long id); // используется для корректной работы кода, на history не влияют
    Epic getEpicById(Long id); // используется для корректной работы кода, на history не влияют
    Subtask getSubtaskById(Long id); // используется для корректной работы кода, на history не влияют

    Task getTaskUser(Long id); // используется пользователем, именно эти запросы сохраняются в history
    Epic getEpicUser(Long id); // используется пользователем, именно эти запросы сохраняются в history
    Subtask getSubtaskUser(Long id); // используется пользователем, именно эти запросы сохраняются в history

    void createTask(Task task);
    void createEpic(Epic epic);
    void createSubtask(Subtask subTask);

    void deleteTaskById(Long id);
    void deleteEpicById(Long id);
    void deleteSubtaskById(Long id);

    void updateTask(Task task);
    void updateEpic(Epic epic);
    void updateSubtask(Subtask subTask);

    ArrayList<Task> getHistory();

    ArrayList<Subtask> getSubtasksByEpicId(Long id);

}
