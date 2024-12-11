package dev.buskopan.cli;

import dev.buskopan.cli.subcommands.AddTaskCommand;
import dev.buskopan.cli.subcommands.CompleteTaskCommand;
import dev.buskopan.cli.subcommands.RemoveTaskCommand;
import dev.buskopan.cli.subcommands.ListTasksCommand;
import dev.buskopan.manager.TaskManager;
import picocli.CommandLine;

@CommandLine.Command(
        name = "task-cli",
        subcommands = {
                ListTasksCommand.class,
                AddTaskCommand.class,
                RemoveTaskCommand.class,
                CompleteTaskCommand.class
        }
)
public class TaskListCLI implements Runnable{

    public static TaskManager taskManager;

    public static void main(String[] args) {
        taskManager = new TaskManager();
        new CommandLine(new TaskListCLI()).execute(args);
    }

    @Override
    public void run() {
        System.out.println("Use: list, add, remove ou complete");
    }
}
