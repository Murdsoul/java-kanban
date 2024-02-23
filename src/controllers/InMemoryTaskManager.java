package controllers;

import model.Task;
import model.Epic;
import model.Subtask;
import model.TaskStatus;


import java.util.ArrayList;
import java.util.HashMap;


public class InMemoryTaskManager implements TaskManager {
    private Long id = 0L;
    private final HashMap<Long, Task> tasks;
    private final HashMap<Long, Epic> epics;
    private final HashMap<Long, Subtask> subtasks;

    private static final HistoryManager historyManager = Managers.getDefaultHistory();

    public InMemoryTaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subtasks = new HashMap<>();
    }

    @Override
    public ArrayList<Task> getHistory(){
        return historyManager.getHistory();
    }

    //Методы для получения всех подзадач
    @Override
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }
    @Override
    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }
    @Override
    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    //Методы для удаления всех задач
    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public void deleteAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    @Override
    public void deleteAllSubtasks() {
        subtasks.clear();
    }

    //Методы для получения задачи по Id
    @Override
    public Task getTaskById(Long id) {
        return tasks.get(id);
    }
    @Override
    public Epic getEpicById(Long id) {
        return epics.get(id);
    }
    @Override
    public Subtask getSubtaskById(Long id) {
        return subtasks.get(id);
    }

    @Override
    public Task getTaskUser(Long id){
        Task task = tasks.get(id);
        historyManager.add(task);
        return task;
    }
    @Override
    public Epic getEpicUser(Long id){
        Epic epic = epics.get(id);
        historyManager.add(epic);
        return epic;
    }
    @Override
    public Subtask getSubtaskUser(Long id){
        Subtask subtask = subtasks.get(id);
        historyManager.add(subtask);
        return subtask;
    }

    //Медоты для создания задач
    @Override
    public void createTask(Task task) {
        id++;
        task.setId(id);
        tasks.put(task.getId(), task);
    }
    @Override
    public void createEpic(Epic epic) {
        id++;
        epic.setId(id);
        epics.put(epic.getId(), epic);
    }
    @Override
    public void createSubtask(Subtask subtask) {
        id++;
        subtask.setId(id);
        subtasks.put(subtask.getId(), subtask);
        Long idEpic = subtask.getIdEpic();
        Epic epic = getEpicById(idEpic);
        if (epic != null) {
            epic.getIdListSubtask().add(subtask.getId());
            updateEpic(epic);
        }
    }


    //Методы удаления задач по id
    @Override
    public void deleteTaskById(Long id) {
        tasks.remove(id);
        historyManager.remove(id);
    }
    @Override
    public void deleteEpicById(Long id) {
        Epic epic = getEpicById(id);
        epics.remove(id);
        ArrayList<Long> subTasks = epic.getIdListSubtask();
        for (Long idSubTask : subTasks) {
            deleteSubtaskById(idSubTask);
        }
        historyManager.remove(id);
    }
    @Override
    public void deleteSubtaskById(Long id) {
        Subtask subTask = getSubtaskById(id);
        Long idEpic = subTask.getIdEpic();
        subtasks.remove(id);
        historyManager.remove(id);
        if (getEpicById(idEpic) != null) {
            Epic epic = getEpicById(idEpic);
            epic.getIdListSubtask().remove(id);
            updateEpic(epic);
        }
    }

    //Методы обновления статусов
    @Override
    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }
    @Override
    public void updateEpic(Epic epic) {
        boolean isDone = true;
        boolean isNew = true;
        if (epic.getIdListSubtask().isEmpty()) {
            epic.setStatus(TaskStatus.NEW);
            return;
        } else {
            for (Long idSubTask : epic.getIdListSubtask()) {
                if (subtasks.get(idSubTask).getStatus() == TaskStatus.NEW) {
                    isDone = false;
                }
                if (subtasks.get(idSubTask).getStatus() == TaskStatus.DONE) {
                    isNew = false;
                }
                if (subtasks.get(idSubTask).getStatus() == TaskStatus.IN_PROGRESS) {
                    epic.setStatus(TaskStatus.IN_PROGRESS);
                    return;
                }
            }
        }

        if (isDone && !isNew) {
            epic.setStatus(TaskStatus.DONE);
        } else if (!isDone && isNew) {
            epic.setStatus(TaskStatus.NEW);
        }
        epic.setStatus(TaskStatus.IN_PROGRESS);
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        Long idEpic = subtask.getIdEpic();
        Epic epic = getEpicById(idEpic);
        if (epic != null) {
            updateEpic(epic);
        }
    }



    //Метод получения списка всех задач определённого эпика
    @Override
    public ArrayList<Subtask> getSubtasksByEpicId(Long id) {
        Epic epic = getEpicById(id);
        ArrayList<Subtask> subTasksArrayList = new ArrayList<>();
        for (long idSubTask : epic.getIdListSubtask()) {
            Subtask subtask = subtasks.get(idSubTask);
            if (subtask != null) {
                subTasksArrayList.add(subtask);
            }
        }
        return subTasksArrayList;
    }
}
