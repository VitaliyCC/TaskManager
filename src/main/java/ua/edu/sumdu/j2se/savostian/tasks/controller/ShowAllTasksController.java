package ua.edu.sumdu.j2se.savostian.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.savostian.tasks.services.InputCheck;
import ua.edu.sumdu.j2se.savostian.tasks.services.ProgramStatus;
import ua.edu.sumdu.j2se.savostian.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.savostian.tasks.view.ShowAllTasksView;

public class ShowAllTasksController extends Controller {
    protected static final Logger logger =
            Logger.getLogger(ShowAllTasksController.class);

    /**
     * Cass to display information in the console
     */
    private final ShowAllTasksView showAllTasksView = new ShowAllTasksView();

    /**
     * Method that manages the creation and display of all existing tasks
     * @param taskList collection with tasks
     * @return constant indicating which next action to perform in the program
     */
    @Override
    public ProgramStatus process(AbstractTaskList taskList) {
        if (InputCheck.checkCollection(taskList)) {
            logger.debug("Display of all existing tasks " +taskList);
            showAllTasksView.printAllTasks(taskList);

        } else {
            logger.debug("Collection is empty " +taskList);
            showAllTasksView.printSomeTitle("You do not have any tasks. You must create them to view tasks.");

        }

        return ProgramStatus.MAINMENU;
    }
}
