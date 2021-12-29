package ua.edu.sumdu.j2se.savostian.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.savostian.tasks.model.Task;
import ua.edu.sumdu.j2se.savostian.tasks.model.Tasks;
import ua.edu.sumdu.j2se.savostian.tasks.services.ProgramStatus;
import ua.edu.sumdu.j2se.savostian.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.savostian.tasks.view.ShowCalendarView;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public class ShowCalendarController extends Controller {
    protected static final Logger logger =
            Logger.getLogger(ShowCalendarController.class);

    /**
     * Collection with calendar
     */
    protected SortedMap<LocalDateTime, Set<Task>> taskMap;

    /**
     * Cass to display information in the console
     */
    protected ShowCalendarView showCalendarView = new ShowCalendarView();

    /**
     * Buffer end time for calendar
     */
    protected LocalDateTime start;

    /**
     * Buffer end time for calendar
     */
    protected LocalDateTime end;

    /**
     * Method is responsible for setting the end time
     * @param end end time
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
        logger.debug("Setting the end time for calendar " + end);
    }

    /**
     * Method is responsible for setting the start time
     * @param start start time
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
        logger.debug("Setting the start time for calendar " + start);
    }

    /**
     * Method that controls the creation and display of tasks for a certain period of time
     * @param taskList collection with tasks
     * @return constant indicating which next action to perform in the program
     */
    @Override
    public ProgramStatus process(AbstractTaskList taskList) {
        showCalendarView.printChoiceStartTime();
        setStart(InputController.nextTime());
        showCalendarView.printChoiceEndTime();
        setEnd(InputController.nextTime());

        taskMap = Tasks.calendar(taskList, start, end);

        if (taskMap.isEmpty()) {
            showCalendarView.printSomeTitle("They are no tasks");
            logger.debug("Collection is empty " + taskMap);

        } else {
            showCalendarView.printSomeTitle("Your tasks from " + start + " to " + end + ":");
            showCalendarView.printCalendarTasks(taskMap);
            logger.debug("Display of tasks for a certain period of time from " + start + " to " + end + "|");

        }

        return ProgramStatus.MAINMENU;
    }
}
