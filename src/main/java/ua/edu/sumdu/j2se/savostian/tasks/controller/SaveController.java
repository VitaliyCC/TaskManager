package ua.edu.sumdu.j2se.savostian.tasks.controller;

import ua.edu.sumdu.j2se.savostian.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.savostian.tasks.services.ProgramStatus;

public class SaveController extends Controller {

    /**
     * Method that controls the process of saving a new task to an existing task list
     * @param taskList collection for task
     * @param taskController controller to restart the notification thread
     * @return constant indicating which next action to perform in the program
     */
    @Override
    public ProgramStatus process(AbstractTaskList taskList,
                                 EditTaskController taskController) {
        taskList.add(taskController.getTempTask());
        taskController.removeTempTask();

        return ProgramStatus.MAINMENU;
    }
}
