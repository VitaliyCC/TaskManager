package ua.edu.sumdu.j2se.savostian.tasks.services;

import ua.edu.sumdu.j2se.savostian.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.savostian.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.savostian.tasks.model.LinkedTaskList;

public class TaskListFactory {
    /**
     * Implementation of the pattern factory method
     * @param type the type of object you want to create
     * @return created object
     */
    static public AbstractTaskList createTaskList(ListTypes.types type) {
        if (type == ListTypes.types.ARRAY)
            return new ArrayTaskList();
        else
            return new LinkedTaskList();
    }
}
