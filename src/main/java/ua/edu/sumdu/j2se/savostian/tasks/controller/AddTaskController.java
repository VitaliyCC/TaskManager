package ua.edu.sumdu.j2se.savostian.tasks.controller;

import ua.edu.sumdu.j2se.savostian.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.savostian.tasks.services.ProgramStatus;


public class AddTaskController extends EditTaskController {

    /**
     * Method that controls the process of adding a new task
     *
     * @param taskList   collection for tasks
     * @param status     constant that indicates the action to be performed for the created task
     * @param controller controller to restart the notification thread
     * @return constant indicating which next action to perform in the program
     */
    @Override
    public ProgramStatus process(AbstractTaskList taskList,
                                 ProgramStatus status,
                                 ThreadController controller) {
        Boolean typeTask;

        if (getTempTask() == null) {
            createNewTask();
        }

        view.printRequestNewTitle();
        title = InputController.nextString();
        editTitle();

        view.printRequestNewActivity();
        activity = InputController.nextBoolean();
        editActivity();

        view.printTypeTaskSelection();
        typeTask = InputController.nextBoolean();

        if (typeTask) {
            view.printRequestNewStartTime();
            start = InputController.nextTime();
            view.printRequestNewEndTime();
            end = InputController.nextTime();
            view.printRequestNewInterval();
            interval = InputController.nextInt();
            editTimeRepeatedTasks();
        } else {
            view.printRequestNewStartTime();
            start = InputController.nextTime();
            editTimeNonRepeatedTasks();
        }

        saveController.process(taskList, this);

        return controller.process(taskList);
    }
}
