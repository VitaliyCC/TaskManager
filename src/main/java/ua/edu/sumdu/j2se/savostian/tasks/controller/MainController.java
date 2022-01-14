package ua.edu.sumdu.j2se.savostian.tasks.controller;

import ua.edu.sumdu.j2se.savostian.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.savostian.tasks.services.ProgramStatus;


public class MainController extends Controller {
    /**
     * Main list of tasks that exists during the program
     */
    private AbstractTaskList globalTasks;

    /**
     * Controller that controls the creation and display of today’s task list
     */
    private final Controller todayTaskController = new TodayTaskController();

    /**
     * Controller that controls the creation and display of today’s task list
     */
    private final Controller menuController = new MenuController();

    /**
     * Controller that controls the creation and display of the process of adding a new task
     */
    private Controller addTaskController = new AddTaskController();

    /**
     * Controller that controls the creation and display of the process of changing the selected task
     */
    private Controller changeTaskController = new ChangeTaskController();

    /**
     * Controller that controls the creation and display of tasks for a specific period
     */
    private Controller showCalendarController = new ShowCalendarController();

    /**
     * Controller that manages the creation and display of all existing tasks
     */
    private final Controller showAllTasksController = new ShowAllTasksController();

    /**
     * Controller that controls the process of reading and writing a collection of tasks to a file
     */
    private final Controller fileController = new FileController();

    /**
     * Controller that controls the process of creating and launching a notified thread
     */
    private ThreadController threadController = new ThreadController();

    ProgramStatus status = ProgramStatus.MAINMENU;

    /**
     * Method that manages program and delegation, sub-task to other controllers
     */
    @Override
    public void process() {

        globalTasks = fileController.readFileProcess();

        if (globalTasks.size() != 0 && globalTasks != null)
            threadController.process(globalTasks);

        while (!status.equals(ProgramStatus.EXIT)) {

            if (status.equals(ProgramStatus.MAINMENU)) {
                status = todayTaskController.process(globalTasks);
                status = menuController.process(status);
            }

            if (status.equals(ProgramStatus.ADD)) {
                status = addTaskController.process(globalTasks, status, threadController);

            } else if (status.equals(ProgramStatus.CHANGE)) {
                status = menuController.process(status);
                status = changeTaskController.process(globalTasks, status, threadController);

            } else if (status.equals(ProgramStatus.CALENDAR)) {
                status = showCalendarController.process(globalTasks);

            } else if (status.equals(ProgramStatus.VIEWALL)) {
                status = showAllTasksController.process(globalTasks);

            }

        }
        fileController.writeFileProcess(globalTasks);
    }
}
