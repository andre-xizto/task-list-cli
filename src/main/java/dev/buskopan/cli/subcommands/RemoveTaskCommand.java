package dev.buskopan.cli.subcommands;

import dev.buskopan.cli.TaskListCLI;
import dev.buskopan.manager.TaskManager;
import picocli.CommandLine;

@CommandLine.Command(
        name = "remove",
        description = "remove a task"
)
public class RemoveTaskCommand implements Runnable {

    @CommandLine.Parameters(index = "0")
    private long id;

    @Override
    public void run() {
        TaskManager taskManager = TaskListCLI.taskManager;

        boolean success = taskManager.deleteTask(id);

        if (success) {
            System.out.println("Task removed!");
        }
    }
}
