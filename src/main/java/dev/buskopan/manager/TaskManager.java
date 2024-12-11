package dev.buskopan.manager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dev.buskopan.model.Task;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TaskManager {

    private final Gson gson;
    private static final String FILE_PATH = "tasks.json";

    public TaskManager() {
        gson = new Gson();
    }

    private void saveTasks(List<Task> tasks) throws IOException {
        Optional<File> file = getFile();
        file.orElseThrow(() -> new FileNotFoundException("tasks.json not found in classpath"));

        System.out.println(file.get().getAbsolutePath());
        try (FileWriter fw = new FileWriter(file.get())){
            gson.toJson(tasks, fw);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public long createTask(String taskName) {
        List<Task> tasks = readTasks();
        Task task = new Task(tasks.getLast().getId() + 1, taskName, false);
        tasks.add(task);
        try {
            saveTasks(tasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return task.getId();
    }

    public boolean deleteTask(long id) {
        List<Task> tasks = readTasks();

        Optional<Task> taskToRemove = tasks.stream().filter(task -> task.getId() == id).findFirst();

        if (taskToRemove.isEmpty()) {
            System.out.println("Task not found with this id");
            return false;
        }

        tasks.remove(taskToRemove.get());
        try {
            saveTasks(tasks);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Optional<Task> getTask(long id) {
        List<Task> tasks = readTasks();
        return tasks.stream().filter(task -> task.getId() == id).findFirst();
    }

    public boolean completeTask(long id) {
        Optional<Task> OPTask = getTask(id);

        if (OPTask.isEmpty()) {
            System.out.println("Task not found with this id");
            return false;
        }

        if (OPTask.get().isCompleted()) {
            System.out.println("Task has already been completed");
            return false;
        }

        List<Task> tasks = readTasks();

        Optional<Task> first = tasks.stream().filter(task -> task.getId() == OPTask.get().getId()).findFirst();

        first.ifPresent(task -> task.setCompleted(true));

        try {
            saveTasks(tasks);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    private Optional<File> getFile() {
        URL urlJson = getClass().getClassLoader().getResource("tasks.json");
        if (urlJson != null) {
            try {
                return Optional.of(new File(urlJson.toURI().getPath()));
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
        return Optional.empty();
    }

    public List<Task> readTasks() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(FILE_PATH);
        if (inputStream == null) {
            try {
                throw new FileNotFoundException(FILE_PATH + " não encontrado no classpath");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        try (Reader reader = new InputStreamReader(inputStream)) {
            Type type = new TypeToken<List<Task>>(){}.getType();
            return gson.fromJson(reader,type);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }

    }
}
