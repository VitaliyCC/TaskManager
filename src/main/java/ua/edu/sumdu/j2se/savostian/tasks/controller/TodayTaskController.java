package ua.edu.sumdu.j2se.savostian.tasks.controller;

import ua.edu.sumdu.j2se.savostian.tasks.model.Tasks;
import ua.edu.sumdu.j2se.savostian.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.savostian.tasks.services.ProgramStatus;


import java.time.LocalDateTime;

public class TodayTaskController extends ShowCalendarController {

    /**
     * Method that controls the creation and display the to-do list for today
     * @param taskList collection with tasks
     * @return constant indicating which next action to perform in the program
     */
    @Override
    public ProgramStatus process(AbstractTaskList taskList) {
        setStart(LocalDateTime.now());
        setEnd(LocalDateTime.now().plusHours(24 - LocalDateTime.now().getHour()));

        taskMap = Tasks.calendar(taskList, start, end);

        showCalendarView.printSeparator();
        showCalendarView.printSeparator();

        if (taskMap.isEmpty()) {
            showCalendarView.printSomeTitle("They are no tasks for today");
            logger.debug("Collection is empty " + taskMap);

        } else {
            showCalendarView.printSomeTitle("Your tasks for today:");
            showCalendarView.printCalendarTasks(taskMap);
            logger.debug("Display the to-do list for today");

        }
        return ProgramStatus.MAINMENU;
    }
}
