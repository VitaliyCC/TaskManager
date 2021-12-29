package ua.edu.sumdu.j2se.savostian.tasks.view;

public interface ConsoleView {

    default void printSeparator() {
        System.out.println("-----------------------------------------------------------------------------------------------------");

    }

    default void printSomeTitle(String title) {
        System.out.println("|" + title + "|");

    }
}
