package ua.edu.sumdu.j2se.savostian.tasks.controller;

import ua.edu.sumdu.j2se.savostian.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.savostian.tasks.services.ProgramStatus;


public class AddTaskController extends EditTaskController {

    /**
     * Method that controls the process of adding a new task
     * @param taskList collection for tasks
     * @param status constant that indicates the action to be performed for the created task
     * @param controller controller to restart the notification thread
     * @return constant indicating which next action to perform in the program
     */
    @Override
    public ProgramStatus process(AbstractTaskList taskList,
                                 ProgramStatus status,
                                 ThreadController controller) {
        if (getTempTask() == null) {
            createNewTask();
        }

        if (status.equals(ProgramStatus.SETNAME)) {
            view.printRequestNewTitle();
            title = InputController.nextString();

            editTitle();

        } else if (status.equals(ProgramStatus.SETACTIVE)) {
            view.printRequestNewActivity();
            activity = InputController.nextBoolean();
            editActivity();

        } else if (status.equals(ProgramStatus.SETREPEATETTIME)) {
            view.printRequestNewStartTime();
            start = InputController.nextTime();
            view.printRequestNewEndTime();
            end = InputController.nextTime();
            view.printRequestNewInterval();
            interval = InputController.nextInt();
            editTimeRepeatedTasks();

        } else if (status.equals(ProgramStatus.SETNONREPEATETTIME)) {
            view.printRequestNewStartTime();
            start = InputController.nextTime();
            editTimeNonRepeatedTasks();

        } else if (status.equals(ProgramStatus.SAVE)) {
            saveController.process(taskList, this);

            return controller.process(taskList);
        } else {
            logger.error("Program status is incorrect! status -"+status +"\n");
            throw new IllegalStateException("Program status is incorrect!");
        }

        return ProgramStatus.ADD;
    }
}
