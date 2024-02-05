import model.Task;
import model.Epic;
import model.Subtask;
import model.TaskStatus;


import java.util.ArrayList;
import java.util.HashMap;


public class TaskManager {
    private Long id = 0L;
    private final HashMap<Long, Task> tasks;
    private final HashMap<Long, Epic> epics;
    private final HashMap<Long, Subtask> subtasks;

    public TaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subtasks = new HashMap<>();
    }

    //Методы для получения всех подзадач
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    //Методы для удаления всех задач
    public void deleteAllTask() {
        tasks.clear();
    }

    public void deleteAllEpics() {
        epics.clear();
    }

    public void deleteAllSubtasks() {
        subtasks.clear();
    }

    //Методы для получения задачи по Id
    public Task getTaskById(Long id) {
        return tasks.get(id);
    }

    public Epic getEpicById(Long id) {
        return epics.get(id);
    }

    public Subtask getSubtaskById(Long id) {
        return subtasks.get(id);
    }

    //Медоты для создания задач
    public void createTask(Task task) {
        if (task.getId() == 0L) {
            id++;
            task.setId(id);
            tasks.put(id, task);
        }
        tasks.put(task.getId(), task);
    }

    public void createEpic(Epic epic) {
        if (epic.getId() == 0L) {
            id++;
            epic.setId(id);
        }
        epics.put(epic.getId(), epic);
    }

    public void createSubtask(Subtask subtask) {
        if (subtask.getId() == 0L) {
            id++;
            subtask.setId(id);
        }
        subtasks.put(subtask.getId(), subtask);
        Long idEpic = subtask.getIdEpic();
        Epic epic = getEpicById(idEpic);
        if (epic != null) {
            ArrayList<Long> idListSubtask = epic.getIdListSubtask();
            if (idListSubtask == null) {
                idListSubtask = new ArrayList<>();
                epic.setIdListSubtask(idListSubtask);
            }
            idListSubtask.add(subtask.getId());
        }
    }

    //Методы удаления задач по id
    public void deleteTaskById(Long id) {
        tasks.remove(id);
    }

    public void deleteEpicById(Long id) {
        Epic epic = getEpicById(id);
        epics.remove(id);
        ArrayList<Long> subTasks = epic.getIdListSubtask();
        for (Long idSubTask : subTasks) {
            deleteSubtaskById(idSubTask);
        }
    }

    public void deleteSubtaskById(Long id) {
        Subtask subTask = getSubtaskById(id);
        Long idEpic = subTask.getIdEpic();
        subtasks.remove(id);
        if (getEpicById(idEpic) != null) {
            Epic epic = getEpicById(idEpic);
            epic.getIdListSubtask().remove(id);
            updateEpic(epic);
        }
    }

    //Методы обновления статусов
    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void updateEpic(Epic epic) {
        boolean isDone = true;
        boolean isNew = true;
        if (epic.getIdListSubtask().isEmpty()) {
            isDone = false;
        } else {
            for (Long idSubTask : epic.getIdListSubtask()) {
                if (subtasks.get(idSubTask).getStatus() == TaskStatus.NEW) {
                    isDone = false;
                }
                if (subtasks.get(idSubTask).getStatus() == TaskStatus.DONE) {
                    isNew = false;
                }
            }
        }


        if (isDone && !isNew) {
            epic.setStatus(TaskStatus.DONE);
        } else if (!isDone && isNew) {
            epic.setStatus(TaskStatus.NEW);
        } else {
            epic.setStatus(TaskStatus.IN_PROGRESS);
        }
        epics.put(epic.getId(), epic);
    }

    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        Long idEpic = subtask.getIdEpic();
        Epic epic = getEpicById(idEpic);
        if(epic != null) {
            updateEpic(epic);
        }
    }

    //Метод получения списка всех задач определённого эпика
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
