package dev.buskopan.model;

import java.util.Objects;

public class Task {
    private long id;
    private String name;
    private boolean completed;

    public Task(long id, String name, boolean completed) {
        this.id = id;
        this.name = name;
        this.completed = completed;
    }

    public Task(String name, boolean completed) {
        this.name = name;
        this.completed = completed;
    }

    public Task() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "==============" +
                "\nID: "+ getId() + "\nTask: "+getName() + "\nCompleted: "+isCompleted();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return id == task.id && completed == task.completed && Objects.equals(name, task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, completed);
    }
}
