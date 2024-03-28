package managers;

import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {
    private final Map<Long, Node> historyMap = new HashMap<>();

    private Node<Task> head = null;

    private Node<Task> tail = null;

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
            Node<Task> nextNode = node.next;
            Node<Task> prevNode = node.prev;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }
        historyMap.remove(node);
    }

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
    public List<Task> getHistory() {
        List<Task> historyTaskList = new ArrayList<>();
        Node<Task> node = head;
        while (node != null) {
            historyTaskList.add(node.getData());
            node = node.next;
        }
        return historyTaskList;
    }

    @Override
    public void remove(Long id) {
        removeNode(historyMap.get(id));
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
    }
}
