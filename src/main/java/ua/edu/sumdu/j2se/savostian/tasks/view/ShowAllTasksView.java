package ua.edu.sumdu.j2se.savostian.tasks.view;

import ua.edu.sumdu.j2se.savostian.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.savostian.tasks.model.Task;

public class ShowAllTasksView extends TaskView {

    public void printAllTasks(AbstractTaskList taskList) {
        printSeparator();
        printSomeTitle("Tasks that you now have");

        for (Task task : taskList) {
            printAllTaskInf(task);

        }
        printSeparator();
        printSeparator();
    }

    public void printAllTasksWithIndex(AbstractTaskList taskList) {
        printSeparator();
        printSomeTitle("Tasks that you now have");

        for (int i = 0; i < taskList.size(); i++) {
            System.out.print(i + " ");
            printAllTaskInf(taskList.getTask(i));

        }
        printSeparator();
        printSeparator();
    }
}
