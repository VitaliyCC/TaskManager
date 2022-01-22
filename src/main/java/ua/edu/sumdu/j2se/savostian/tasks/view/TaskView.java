package ua.edu.sumdu.j2se.savostian.tasks.view;

import ua.edu.sumdu.j2se.savostian.tasks.model.Task;
import ua.edu.sumdu.j2se.savostian.tasks.services.Formatter;

import java.time.LocalDateTime;

public class TaskView implements ConsoleView {
    public void printShortTaskInf(Task task, LocalDateTime time) {
        StringBuilder printTask = new StringBuilder();

        printTask.append('|');
        printTask.append(Formatter.createFormatTitle(task.getTitle(), 30));
        printTask.append('|');

        if (task.isActive()) {
            printTask.append(Formatter.createFormatTitle("active", 10));
        } else {
            printTask.append(Formatter.createFormatTitle("non-active", 10));
        }

        printTask.append('|');
        printTask.append(Formatter.createFormatDate(time));

        System.out.println(printTask);
    }

    public void printAllTaskInf(Task task) {
        StringBuilder printTask = new StringBuilder();

        printTask.append('|');
        printTask.append(Formatter.createFormatTitle(task.getTitle(), 30));
        printTask.append('|');

        if (task.isActive()) {
            printTask.append(Formatter.createFormatTitle("active", 10));
        } else {
            printTask.append(Formatter.createFormatTitle("non-active", 10));
        }

        printTask.append('|');

        if (task.isRepeated()) {
            printTask.append(Formatter.createFormatTitle("repeated", 12));
        } else {
            printTask.append(Formatter.createFormatTitle("non-repeated", 12));
        }

        printTask.append('|');
        printTask.append(Formatter.createFormatTitle(String.valueOf(task.getRepeatInterval() / 60), 5));
        printTask.append('|');
        printTask.append(Formatter.createFormatDate(task.getStartTime()));
        printTask.append('|');
        printTask.append(Formatter.createFormatDate(task.getEndTime()));
        printTask.append('|');

        System.out.println(printTask);
    }

}
