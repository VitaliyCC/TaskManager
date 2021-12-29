package ua.edu.sumdu.j2se.savostian.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.savostian.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.savostian.tasks.services.ListTypes;
import ua.edu.sumdu.j2se.savostian.tasks.services.TaskListFactory;
import ua.edu.sumdu.j2se.savostian.tasks.model.AbstractTaskList;

import java.io.File;

public class FileController extends Controller {
    protected static final Logger logger =
            Logger.getLogger(FileController.class);
    /**
     * File in which the task is stored in the time between sessions of the program
     */
    private final File file = new File("SaveTasks.gson");

    /**
     * Method reads a collection of tasks from gson file
     * @return collection with tasks
     */
    @Override
    public AbstractTaskList readFileProcess() {
        AbstractTaskList tasks = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
        TaskIO.readText(tasks, file);

        logger.debug("Reading a collection of tasks from gson file " + file.getAbsolutePath());
        return tasks;
    }

    /**
     * Method that writes a collection of tasks to gson file
     * @param listToSave collection with tasks
     */
    @Override
    public void writeFileProcess(AbstractTaskList listToSave) {
        TaskIO.writeText(listToSave, file);
        logger.debug("Writing a collection of tasks to gson file " + file.getAbsolutePath());
    }
}
