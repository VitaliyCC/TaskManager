package ua.edu.sumdu.j2se.savostian.tasks.view;

public class MenuView extends TaskView {
    public void printMainMenu() {
        printSeparator();
        printSeparator();
        printSomeTitle("You are on the main menu. Select one of the program functions: ");
        printSomeTitle("1. Add a new task");
        printSomeTitle("2. Modify the task");
        printSomeTitle("3. View tasks for a specific period");
        printSomeTitle("4. View all tasks");
        printSomeTitle("5. Exit and save");
        printSeparator();
        System.out.print("Your choice - ");

    }

    public void printAddMenu() {
        printSeparator();
        printSeparator();
        printSomeTitle("You are in the add menu. Select one of the program functions: ");
        printSomeTitle("1. Set task name");
        printSomeTitle("2. Activate task");
        printSomeTitle("3. Set time for repeated task");
        printSomeTitle("4. Set time for non-repeated task");
        printSomeTitle("5. Add and exit to main menu");
        printSeparator();
        System.out.print("Your choice - ");

    }

    public void printChangeMenu() {
        printSeparator();
        printSeparator();
        printSomeTitle("You are on the changing menu. Select one of the program functions: ");
        printSomeTitle("1. Set name");
        printSomeTitle("2. Activate task");
        printSomeTitle("3. Set time for repeated task");
        printSomeTitle("4. Set time for non-repeated task");
        printSomeTitle("5. Save and exit to main menu");
        printSeparator();
        System.out.print("Your choice - ");

    }
}
