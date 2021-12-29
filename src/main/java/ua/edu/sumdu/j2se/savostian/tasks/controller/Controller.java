package ua.edu.sumdu.j2se.savostian.tasks.controller;

import ua.edu.sumdu.j2se.savostian.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.savostian.tasks.services.ProgramStatus;

import java.util.Scanner;

public abstract class Controller {
    protected static Scanner in = new Scanner(System.in);

    void process() {
        throw new NullPointerException("Method configuration is invalid.");
    }

    ProgramStatus process(AbstractTaskList taskList) {
        throw new NullPointerException("Method configuration is invalid.");
    }

    ProgramStatus process(ProgramStatus status) {
        throw new NullPointerException("Method configuration is invalid.");
    }

    ProgramStatus process(AbstractTaskList taskList, ProgramStatus status, ThreadController controller) {
        throw new NullPointerException("Method configuration is invalid.");
    }
    public ProgramStatus process(AbstractTaskList taskList, EditTaskController taskController) {
        throw new NullPointerException("Method configuration is invalid.");
    }
    AbstractTaskList readFileProcess() {
        throw new NullPointerException("Method configuration is invalid.");
    }

    void writeFileProcess(AbstractTaskList listToSave) {
        throw new NullPointerException("Method configuration is invalid.");
    }
}
