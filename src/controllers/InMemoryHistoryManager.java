package controllers;

import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private HashMap<Long, Node> historyMap = new HashMap<>();
    private Node<Task> head = null;
    private Node<Task> tail = null;
    private int size = 0;

    @Override
    public void add(Task task) {
        if (task != null) {
            Long idMap = task.getId();
            if (historyMap.containsKey(idMap)) {
                removeNode(historyMap.get(idMap));
            }
            linkLast(task);
            Node<Task> nodeMap = tail;
            historyMap.put(idMap, nodeMap);
        }
    }

    @Override
    public void remove(Long id) {
        removeNode(historyMap.get(id));
    }

    @Override
    public ArrayList<Task> getHistory() {
        ArrayList<Task> historyTaskList = new ArrayList<>();
        Node<Task> node = head;
        while (node != null) {
            historyTaskList.add(node.getData());
            node = node.next;
        }
        return historyTaskList;
    }

    public void removeNode(Node node) {
        if (node == head && node == tail) {
            head = null;
            tail = null;
        } else if (node == head) {
            Node<Task> nextNode = node.next;
            head = nextNode;
            nextNode.prev = null;
        } else if (node == tail) {
            Node<Task> prevNode = node.prev;
            tail = prevNode;
            prevNode.next = null;
        } else {
            Node<Task> prevNode = node.prev;
            Node<Task> nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }
    }

    public void linkLast(Task task) {
        final Node<Task> oldTail = tail;
        final Node<Task> newNode = new Node<>(oldTail, task, null);
        tail = newNode;
        if (oldTail == null) {
            head = newNode;
        } else {
            oldTail.next = newNode;
        }
        size++;
    }

}
