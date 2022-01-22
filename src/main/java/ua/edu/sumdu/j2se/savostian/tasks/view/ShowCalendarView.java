package ua.edu.sumdu.j2se.savostian.tasks.view;

import ua.edu.sumdu.j2se.savostian.tasks.model.Task;
import ua.edu.sumdu.j2se.savostian.tasks.services.Formatter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public class ShowCalendarView extends TaskView {
    public void printChoiceStartTime() {
        printSeparator();
        printSomeTitle("Enter the start date of the period (YYYY-MM-DDTHH:MM for example - 2022-01-14T15:15)");
        System.out.print("-->");

    }

    public void printChoiceEndTime() {
        printSeparator();
        printSomeTitle("Enter the end date of the period (YYYY-MM-DDTHH:MM for example - 2022-01-14T15:15)");
        System.out.print("-->");

    }

    public void printCalendarTasks(SortedMap<LocalDateTime, Set<Task>> taskMap) {
        printSeparator();

        printSomeTitle(Formatter.createFormatTitle("The name of the task ",30).toString() + " Task execution time");

        for (LocalDateTime time : taskMap.keySet()) {
            for(Task task:taskMap.get(time)){
                printShortTaskInf(task,time);
            }
        }

        printSeparator();
        printSeparator();
    }
}
