package ua.edu.sumdu.j2se.savostian.tasks.view;


public class EditTaskView extends TaskView {

    public void printRequestNewTitle() {
        printSeparator();
        printSomeTitle("Enter a new task title");
        System.out.print("-->");

    }

    public void printRequestNewActivity() {
        printSeparator();
        printSomeTitle("Enter a new task activity (Yes - true /No - false)");
        System.out.print("-->");

    }

    public void printRequestNewStartTime() {
        printSeparator();
        printSomeTitle("Enter a new task start time (YYYY-MM-DDTHH:MM for example - 2022-01-14T15:15)");
        System.out.print("-->");

    }

    public void printRequestNewEndTime() {
        printSeparator();
        printSomeTitle("Enter a new task end time (YYYY-MM-DDTHH:MM for example - 2022-01-14T15:15)");
        System.out.print("-->");

    }

    public void printRequestNewInterval() {
        printSeparator();
        printSomeTitle("Enter a new task interval (number of milliseconds)");
        System.out.print("-->");

    }

    public void printTaskSelection() {
        printSeparator();
        printSomeTitle("Enter the number of the task you want to edit (index)");
        System.out.print("-->");

    }

    public void printTypeTaskSelection() {
        printSeparator();
        printSomeTitle("You want to create a repeated task (Yes - true /No - false)");
        System.out.print("-->");

    }
}
