package ua.edu.sumdu.j2se.savostian.tasks.view;

public class EditTaskView extends TaskView {

    public void printRequestNewTitle() {
        printSeparator();
        printSomeTitle("Enter a new task title");
        System.out.print("-->");

    }

    public void printRequestNewActivity() {
        printSeparator();
        printSomeTitle("Enter a new task activity");
        System.out.print("-->");

    }

    public void printRequestNewStartTime() {
        printSeparator();
        printSomeTitle("Enter a new task start time");
        System.out.print("-->");

    }

    public void printRequestNewEndTime() {
        printSeparator();
        printSomeTitle("Enter a new task end time");
        System.out.print("-->");

    }

    public void printRequestNewInterval() {
        printSeparator();
        printSomeTitle("Enter a new task interval");
        System.out.print("-->");

    }

    public void printTaskSelection() {
        printSeparator();
        printSomeTitle("Enter the number of the task you want to edit");
        System.out.print("-->");

    }
}
