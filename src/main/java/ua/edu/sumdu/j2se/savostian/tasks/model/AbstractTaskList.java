package ua.edu.sumdu.j2se.savostian.tasks.model;

import ua.edu.sumdu.j2se.savostian.tasks.services.ListTypes;

import java.io.Serializable;
import java.util.Iterator;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable, Serializable {
    /**
     * Size of saved collection
     */
    protected int sizeTaskList;

    /**
     * Constant value for all objects of successor's classes. Initializes in successors.
     */
    static protected ListTypes.types type;

    /**
     * Method responsible for adding a new task to the collection.
     * @param task tasks that need to be added to the collection.
     * @throws NullPointerException if task is null
     */
    public abstract void add(Task task);

    /**
     * Method for removing a task from a collection.
     * @param task what you need to remove from the collection.
     * @returntrue if the task was successfully deleted, false if the task was not in the collection.
     * @throws NullPointerException if task is null
     */
    public abstract boolean remove(Task task);

    /**
     * Method that returns the size of the collection.
     * @return size of the collection
     */
    public abstract int size();

    /**
     * Method that finds and returns a task in a collection by its index.
     * @param index index of the required task.
     * @return tasks according to the specified index.
     * @throws IndexOutOfBoundsException if index is outside the collection.
     */
    public abstract Task getTask(int index);

    /**
     * Method that returns a stream based on a collection.
     * @return stream based on a collection
     */
    public abstract Stream<Task> getStream();

    /**
     * Equals method for AbstractTaskList class objects.
     * @param otherObject task class object for comparison
     * @return true if tasks have same field values, else return false
     */
    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == null) {
            return false;
        }
        if (this == otherObject) {
            return true;
        }
        if (getClass() != otherObject.getClass()) {
            return false;
        }
        if (sizeTaskList != ((AbstractTaskList) otherObject).sizeTaskList) {
            return false;
        }

        Iterator otherIt = ((AbstractTaskList) otherObject).iterator();
        for (Task thisIt : this) {
            if (!thisIt.equals(otherIt.next())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Hash code method for AbstractTaskList class objects.
     * @return Unique hash code for Task class object (there may be collisions)
     */
    @Override
    public int hashCode() {
        int result = sizeTaskList;

        for (Task task : this) {
            result ^= task.hashCode();
        }

        if (type == ListTypes.types.ARRAY) {
            result = ~result;
        }

        return result;
    }

    /**
     * To string method for AbstractTaskList class objects.
     * @return string that consists of class name and all object fields through the symbol #
     */
    @Override
    public String toString() {
        Iterator<Task> itr = this.iterator();
        StringBuilder returnStr = new StringBuilder();
        int objNum = 0;

        if (type == ListTypes.types.ARRAY) {
            returnStr.append("ArrayTaskList.class#");
        } else {
            returnStr.append("LinkedTaskList.class#");
        }
        returnStr.append(sizeTaskList);

        while (itr.hasNext()) {
            returnStr.append("#Object");
            returnStr.append(objNum);
            returnStr.append("#");
            returnStr.append(itr.next().toString());
            objNum++;
        }

        return new String(returnStr);
    }
}
