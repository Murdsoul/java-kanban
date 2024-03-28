import managers.Managers;
import managers.TaskManager;
import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = Managers.getDefault();
        createAllTasks(manager);
        System.out.println("\n===Печать всех задач/эпиков/подзадач изначальный===");
        printAllTasks(manager);
    }


    public static void createAllTasks(TaskManager manager) {
        // создание 3-х задач
        Task task1 = new Task("1Task", "1TaskDescription", 0L, TaskStatus.NEW);
        manager.createTask(task1);
        Task task2 = new Task("2Task", "2TaskDescription", 0L, TaskStatus.NEW);
        manager.createTask(task2);
        Task task3 = new Task("3Task", "3TaskDescription", 0L, TaskStatus.NEW);
        manager.createTask(task3);

        // создание Эпика с 3-мя подзадачами
        Epic epic1 = new Epic("1Epic", "1EpicDescription", 0L, TaskStatus.NEW, new ArrayList<>());
        manager.createEpic(epic1);
        Subtask subTask11 = new Subtask("11SubTask", "11SubTaskDescription", 0L, TaskStatus.NEW, 4L);
        manager.createSubtask(subTask11);
        Subtask subTask12 = new Subtask("12SubTask", "12SubTaskDescription", 0L, TaskStatus.NEW, 4L);
        manager.createSubtask(subTask12);
        Subtask subTask13 = new Subtask("13SubTask", "13SubTaskDescription", 0L, TaskStatus.NEW, 4L);
        manager.createSubtask(subTask13);

        // создание Эпика без подзадач
        Epic epic2 = new Epic("2Epic", "2EpicDescription", 0L, TaskStatus.NEW, new ArrayList<>());
        manager.createEpic(epic2);

    }

    public static void checkHistory(TaskManager manager) {
        createHistory(manager);

        manager.deleteTaskById(1L);
        manager.deleteSubtaskById(5L);
        System.out.println("\n===Печать истории просмотров после удаления задачи и подзадачи===");
        System.out.println(manager.getHistory());

        manager.deleteEpicById(4L);
        System.out.println("\n===Печать истории просмотров после удаления эпика===");
        System.out.println(manager.getHistory()); // проверить, что подзадачи 6 и 7 тоже удалились
    }

    public static void createHistory(TaskManager manager) {
        manager.getSubtaskById(5L);
        manager.getEpicById(4L);
        manager.getTaskById(1L);
        manager.getSubtaskById(5L);
        manager.getEpicById(8L);
        manager.getTaskById(2L);
        manager.getTaskById(2L);
        manager.getSubtaskById(6L);
        manager.getEpicById(8L);
        manager.getSubtaskById(7L);
        manager.getTaskById(2L);
        manager.getTaskById(1L);
        manager.getEpicById(8L);

        System.out.println("\n===Печать истории просмотров===");
        System.out.println(manager.getHistory());
    }

    public static void printAllTasks(TaskManager manager) {
        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllSubtasks());
    }

}
