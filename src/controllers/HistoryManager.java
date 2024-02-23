package controllers;

import model.Task;

import java.util.ArrayList;

public interface HistoryManager {

    void add(Task task);

    void remove(Long id);

    ArrayList<Task> getHistory();
}
