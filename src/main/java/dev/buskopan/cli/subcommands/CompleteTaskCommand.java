package dev.buskopan.cli.subcommands;

import dev.buskopan.cli.TaskListCLI;
import dev.buskopan.manager.TaskManager;
import picocli.CommandLine;

@CommandLine.Command(
        name = "complete",
        description = "mark as complete a task"
)
public class CompleteTaskCommand implements Runnable{

    @CommandLine.Parameters(
            index = "0"
    )
    private long id;

    @Override
    public void run() {
        TaskManager taskManager = TaskListCLI.taskManager;

        boolean success = taskManager.completeTask(id);

        if (success) {
            System.out.println("Task marked as complete!");
        }
    }
}
