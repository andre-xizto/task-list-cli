package dev.buskopan.cli.subcommands;

import dev.buskopan.cli.TaskListCLI;
import dev.buskopan.manager.TaskManager;
import dev.buskopan.model.Task;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(
        name = "list",
        description = "List all tasks"
)
public class ListTasksCommand implements Runnable{

    @CommandLine.Option(
            names = {"-completed", "-c"},
            description = "List only completed tasks"
    )
    private boolean onlyCompleted;

    @Override
    public void run() {
        TaskManager taskManager = TaskListCLI.taskManager;
        List<Task> tasks = taskManager.readTasks();
        if (onlyCompleted) {
            tasks.stream().filter(Task::isCompleted).forEach(System.out::println);
            return;
        }
        tasks.forEach(System.out::println);
    }
}
