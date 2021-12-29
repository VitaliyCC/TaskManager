package ua.edu.sumdu.j2se.savostian.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.savostian.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.savostian.tasks.model.Task;
import ua.edu.sumdu.j2se.savostian.tasks.view.EditTaskView;

import java.time.LocalDateTime;

public class EditTaskController extends Controller {
    protected static final Logger logger =
            Logger.getLogger(EditTaskController.class);

    /**
     * Buffer task
     */
    private Task tempTask;

    /**
     * Class that controls the preservation of the processed task to the collection
     */
    protected Controller saveController = new SaveController();

    /**
     * Class responsible for displaying the required information in the console
     */
    protected EditTaskView view= new EditTaskView();

    /**
     * Buffer start time for task
     */
    protected LocalDateTime start;

    /**
     * Buffer end time for task
     */
    protected LocalDateTime end;

    /**
     * Buffer interval for task
     */
    protected Integer interval;

    /**
     * Buffer title for task
     */
    protected String title;

    /**
     * Buffer activity for task
     */
    protected Boolean activity;

    /**
     * Methods responsible for creating an empty task
     */
    public void createNewTask() {
        tempTask = new Task();
    }

    /**
     * Method responsible for selecting a task to adjust from the collection
     * @param taskList collection with tasks
     * @param index index of the required task
     * @throws CloneNotSupportedException if the cloning failed
     */
    public void choiceTask(AbstractTaskList taskList, int index) throws CloneNotSupportedException {
        tempTask = taskList.getTask(index).clone();
        taskList.remove(taskList.getTask(index));
        logger.debug("Task selected for editing " +tempTask.toString());
    }

    /**
     * Method is responsible for setting a new name for the task
     */
    public void editTitle() {
        tempTask.setTitle(title);
    }

    /**
     * Method responsible for adjusting the activity of the task
     */
    public void editActivity() {
        tempTask.setActive(activity);
    }

    /**
     * Method responsible for setting time for non-repeated task
     */
    public void editTimeNonRepeatedTasks() {
        tempTask.setTime(start);
    }

    /**
     * Method responsible for setting time frames and intervals for repeated task
     */
    public void editTimeRepeatedTasks() {
        tempTask.setTime(start,end,interval);
    }

    /**
     * Method that returns a buffer task
     * @return buffer task
     */
    public Task getTempTask() {
        return tempTask;
    }

    /**
     * Method that clears buffer task
     */
    public void removeTempTask(){
        logger.debug("Clear buffer task" +tempTask.toString());
        tempTask=null;
    }
}
