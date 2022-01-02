package ua.edu.sumdu.j2se.savostian.tasks.controller;

import ua.edu.sumdu.j2se.savostian.tasks.services.InputCheck;
import ua.edu.sumdu.j2se.savostian.tasks.services.ProgramStatus;
import ua.edu.sumdu.j2se.savostian.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.savostian.tasks.view.ShowAllTasksView;


public class ChangeTaskController extends EditTaskController {

    /**
     * Method that controls the process of changing the selected task
     * @param taskList collection with tasks
     * @param status constant that indicates the action to be performed for the changed task
     * @param controller controller to restart the notification thread
     * @return constant indicating which next action to perform in the program
     */
    @Override
    public ProgramStatus process(AbstractTaskList taskList,
                                 ProgramStatus status,
                                 ThreadController controller) {
        if (getTempTask() == null) {
            if (InputCheck.checkCollection(taskList)) {
                ShowAllTasksView allTasksView = new ShowAllTasksView();
                allTasksView.printAllTasksWithIndex(taskList);
                view.printTaskSelection();
                try {
                    choiceTask(taskList, Integer.parseInt(in.nextLine()));
                } catch (CloneNotSupportedException e) {
                    logger.error(e+"\n");
                    return ProgramStatus.MAINMENU;
                }

            } else {
                logger.error("Collection is empty " + taskList+"\n");
                view.printSomeTitle("You do not have any tasks. You must create them to change the tasks.");
                return ProgramStatus.MAINMENU;
            }
        }

        if (status.equals(ProgramStatus.SETNAME)) {
            view.printRequestNewTitle();
            title = InputController.nextString();
            editTitle();
            logger.debug("Set new task title for task " + getTempTask().toString());

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
        }else if(status.equals(ProgramStatus.DELETE)){
            removeTempTask();

            return controller.process(taskList);
        } else {
            logger.error("Program status is incorrect! status -"+status +"\n");
            throw new IllegalStateException("Program status is incorrect!");
        }

        return ProgramStatus.CHANGE;
    }
}
