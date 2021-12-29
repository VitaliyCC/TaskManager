package ua.edu.sumdu.j2se.savostian.tasks.controller;

import ua.edu.sumdu.j2se.savostian.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.savostian.tasks.model.NotifiedThread;
import ua.edu.sumdu.j2se.savostian.tasks.services.InputCheck;
import ua.edu.sumdu.j2se.savostian.tasks.services.ProgramStatus;

public class ThreadController extends Controller {
    /**
     * Notified thread
     */
    public NotifiedThread notified;

    /**
     * Method that controls the process of creating and launching a notified thread
     * @param taskList collection with tasks
     * @return constant indicating which next action to perform in the program4
     */
    @Override
    public ProgramStatus process(AbstractTaskList taskList) {
        if (InputCheck.checkCollection(taskList)) {
            notified = new NotifiedThread("Tasks for the near future:", taskList);
        } else {
            throw new NullPointerException("TaskList is empty");
        }

        return ProgramStatus.MAINMENU;
    }

}
