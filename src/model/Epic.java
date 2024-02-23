package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Epic extends Task {
    private List<Long> idListSubtask;

    public Epic(String name, String description, Long id, TaskStatus status, List<Long> idListSubtask) {
        super(name, description, id, status);
        this.idListSubtask = new ArrayList<>();
    }

    public List<Long> getIdListSubtask() {
        return idListSubtask;
    }

    public void setIdListSubtask(List<Long> idListSubtask) {
        this.idListSubtask = idListSubtask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Epic epic = (Epic) o;
        return Objects.equals(getName(), epic.getName()) &&
                Objects.equals(getDescription(), epic.getDescription()) &&
                Objects.equals(getStatus(), epic.getStatus()) &&
                Objects.equals(idListSubtask, epic.idListSubtask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), getStatus(), idListSubtask);
    }
}
