package model;

import java.util.Objects;

public class Subtask extends Task {
    private final Long idEpic;

    public Subtask(String name, String description, Long id, TaskStatus status, Long idEpic) {
        super(name, description, id, status);
        this.idEpic = idEpic;
    }

    public Long getIdEpic() {
        return idEpic;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Subtask subtask = (Subtask) obj;
        return Objects.equals(getName(), subtask.getName()) &&
                Objects.equals(getDescription(), subtask.getDescription()) &&
                Objects.equals(getStatus(), subtask.getStatus()) &&
                (Objects.equals(idEpic, subtask.idEpic));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), getStatus(), idEpic);
    }
}
