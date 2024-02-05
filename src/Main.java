import model.*;


import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        Task task1 = new Task("1Task", "1TaskDescription", 0L, TaskStatus.NEW);
        manager.createTask(task1);
        Task task2 = new Task("2Task", "2TaskDescription", 0L, TaskStatus.IN_PROGRESS);
        manager.createTask(task2);


        Epic epic1 = new Epic ("1Epic", "1EpicDescription", 0L, TaskStatus.NEW, new ArrayList<>());
        manager.createEpic(epic1);
        Subtask subtask11 = new Subtask("11SubTask", "11SubTaskDescription", 0L, TaskStatus.NEW,3L);
        manager.createSubtask(subtask11);
        Subtask subtask12 = new Subtask("12SubTask", "12SubTaskDescription", 0L, TaskStatus.NEW,3L);
        manager.createSubtask(subtask12);

        Epic epic2 = new Epic("2Epic", "2EpicDescription", 0L, TaskStatus.NEW, new ArrayList<>());
        manager.createEpic(epic2);
        Subtask subtask21 = new Subtask("21SubTask", "21SubTaskDescription", 0L, TaskStatus.NEW,8L);
        manager.createSubtask(subtask21);

        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllSubtasks());

        task1.setStatus(TaskStatus.IN_PROGRESS);
        subtask11.setStatus(TaskStatus.IN_PROGRESS);
        subtask12.setStatus(TaskStatus.DONE);
        manager.updateTask(task1);
        manager.updateSubtask(subtask11);
        manager.updateSubtask(subtask12);

        System.out.println(manager.getTaskById(1L));
        System.out.println(manager.getSubtaskById(4L));
        System.out.println(manager.getSubtaskById(5L));

        manager.deleteTaskById(2L);
        manager.deleteEpicById(6L);

        manager.updateEpic(epic1);

        System.out.println(manager.getEpicById(3L));
        System.out.println(manager.getSubtasksByEpicId(3L));

    }
}
