package ua.edu.sumdu.j2se.savostian.tasks.model;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.savostian.tasks.services.ListTypes;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class ArrayTaskList extends AbstractTaskList {
    private static final Logger logger =
            Logger.getLogger(ArrayTaskList.class);

    /**
     * Amount of added or deleted tasks after which the array memory is changed.
     */
    private final int INTERVAL = 10;

    static {
        type = ListTypes.types.ARRAY;
    }

    /**
     * Array in which the collection is stored.
     */
    private Task[] arrTask;

    /**
     * Constructor for empty ArrayTaskList.
     */
    public ArrayTaskList() {
        arrTask = new Task[INTERVAL];
        logger.debug("Create new ArrayTaskList "+this);
    }

    /**
     * Implementation through the array of the add method of the parent class.
     * @param task tasks that need to be added to the collection.
     * @throws NullPointerException if task is null
     */
    @Override
    public void add(Task task) {
        if (task == null) {
            throw new NullPointerException(
                    "Attempt to add null to ArrayTaskList!"
            );
        }

        if (arrTask.length == sizeTaskList) {
            Task[] temp = new Task[sizeTaskList + INTERVAL];

            System.arraycopy(arrTask, 0, temp, 0, sizeTaskList);
            arrTask = temp;
        }

        arrTask[sizeTaskList] = task;
        sizeTaskList++;
        logger.debug("Task is added to the collection "+ task);
    }

    /**
     * Implementation through the array of the remove method of the parent class.
     * @param task what you need to remove from the collection.
     * @return if the task was successfully deleted, false if the task was not in the collection.
     */
    @Override
    public boolean remove(Task task) {
        if (task == null) {
            throw new NullPointerException(
                    "Attempt to remove null from ArrayTaskList!"
            );
        }

        boolean searchStat = false;
        int indexDel;

        for (indexDel = 0; indexDel < sizeTaskList; indexDel++) {
            if (arrTask[indexDel].equals(task)) {
                searchStat = true;
                break;
            }
        }

        if (!searchStat) {
            return false;
        }

        arrTask[indexDel] = null;
        sizeTaskList--;

        if (indexDel != sizeTaskList) {
            System.arraycopy(arrTask, indexDel + 1, arrTask, indexDel,
                    sizeTaskList - indexDel);
        }

        if (arrTask.length - INTERVAL == sizeTaskList &&
                sizeTaskList != 0) {
            Task[] temp = new Task[sizeTaskList];

            System.arraycopy(arrTask, 0, temp, 0, sizeTaskList);
            arrTask = temp;
        }
        logger.debug("Task is removed from the collection "+task);

        return true;
    }

    /**
     * Implementation through the array of the size method of the parent class.
     * @return size of the collection
     */
    @Override
    public int size() {
        return sizeTaskList;
    }

    /**
     * Implementation through the array of the getTask method of the parent class.
     * After using remove task method indexes of specific objects can change.
     * @param index index of the required task.
     * @return tasks according to the specified index.
     * @throws IndexOutOfBoundsException if index is outside the collection.
     */
    @Override
    public Task getTask(int index) {
        if (index < 0 || index >= sizeTaskList) {
            throw new IndexOutOfBoundsException(
                    "Invalid ArrayTaskList index!"
            );
        }
        return arrTask[index];
    }

    /**
     * Implementation of Iterator patterns for an array-based collection.
     * @return Iterator for this collection
     */
    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < sizeTaskList;
            }

            @Override
            public Task next() {
                if (index == sizeTaskList) {
                    throw new NoSuchElementException(
                            "Iterator reached last position!"
                    );
                }
                return arrTask[index++];
            }

            @Override
            public void remove() {
                if (index == 0) {
                    throw new IllegalStateException(
                            "Needs calling of next() iterator method!"
                    );
                }

                index--;
                arrTask[index] = null;
                sizeTaskList--;

                if (index != sizeTaskList) {
                    System.arraycopy(arrTask, index + 1, arrTask, index,
                            sizeTaskList - index);
                }

                if (arrTask.length - INTERVAL == sizeTaskList &&
                        sizeTaskList != 0) {
                    Task[] temp = new Task[sizeTaskList];

                    System.arraycopy(arrTask, 0, temp, 0, sizeTaskList);
                    arrTask = temp;
                }
            }
        };
    }

    /**
     * Clone method for the ArrayTaskList class objects.
     * @return clone of the object
     */
    @Override
    public ArrayTaskList clone() {
        ArrayTaskList retObj = new ArrayTaskList();
        for (int counter = 0; counter < sizeTaskList; counter++) {
            retObj.add(arrTask[counter]);
        }
        return retObj;
    }

    /**
     *Implementation through the array of the getStream method of the parent class.
     * @return stream based on a collection
     */
    @Override
    public Stream<Task> getStream() {
        return Arrays.stream(arrTask, 0, sizeTaskList);
    }
}