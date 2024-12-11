package dev.buskopan.cli.subcommands;

import dev.buskopan.cli.TaskListCLI;
import dev.buskopan.manager.TaskManager;
import picocli.CommandLine;

@CommandLine.Command(
        name = "add",
        description = "adds a new task"
)
public class AddTaskCommand implements Runnable{

    @CommandLine.Parameters(index = "0")
    private String taskName;

    @Override
    public void run() {
        TaskManager taskManager = TaskListCLI.taskManager;
        long createdId = taskManager.createTask(taskName);
        System.out.println("Task created, ID: "+ createdId);
    }
}
