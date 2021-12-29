package ua.edu.sumdu.j2se.savostian.tasks.model;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.savostian.tasks.services.ListTypes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class LinkedTaskList extends AbstractTaskList {
    private static final Logger logger =
            Logger.getLogger(LinkedTaskList.class);

    /**
     * Class for linked list node.
     */
    static class LinkedTaskListNode {
        Task task;
        LinkedTaskListNode next;
    }

    static {
        type = ListTypes.types.LINKED;
    }

    /**
     * Link to the first item in the list.
     */
    private LinkedTaskListNode first;

    /**
     * Constructor for LinkedTaskList.
     */
    public LinkedTaskList() {
        first = new LinkedTaskListNode();
        logger.debug("Create new LinkedTaskList "+this);
    }

    /**
     * Implementation through one coherent list of the add method of the parent class.
     * @param task tasks that need to be added to the collection.
     * @throws NullPointerException if task is null
     */
    @Override
    public void add(Task task) {
        if (task == null) {
            throw new NullPointerException(
                    "Attempt to add null to LinkedTaskList!"
            );
        }

        LinkedTaskListNode temp = first;

        temp.task = task;

        first = new LinkedTaskListNode();
        first.next = temp;

        sizeTaskList++;
        logger.debug("Task is added to the collection "+ task);
    }

    /**
     * Implementation through one coherent list of the remove method of the parent class.
     * @param task what you need to remove from the collection.
     * @return if the task was successfully deleted, false if the task was not in the collection.
     */
    @Override
    public boolean remove(Task task) {
        if (task == null) {
            throw new NullPointerException(
                    "Attempt to remove null from LinkedTaskList!"
            );
        }

        LinkedTaskListNode deletNode = first;

        if (sizeTaskList == 0)
            return false;

        while (deletNode.next != null) {
            if (deletNode.next.task.equals(task)) {
                deletNode.next = deletNode.next.next;

                sizeTaskList--;

                return true;
            }
            deletNode = deletNode.next;
        }
        logger.debug("Task is removed from the collection "+task);

        return false;
    }

    /**
     * Implementation through one coherent list of the size method of the parent class.
     * @return size of the collection
     */
    @Override
    public int size() {
        return sizeTaskList;
    }

    /**
     * Implementation through one coherent list of the getTask method of the parent class.
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

        index++;
        LinkedTaskListNode searchNode = first;

        for (int i = sizeTaskList; i > sizeTaskList - index; i--) {
            searchNode = searchNode.next;
        }

        return searchNode.task;
    }

    /**
     * Implementation of Iterator patterns for an array-based collection.
     * @return Iterator for this collection
     */
    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
            private LinkedTaskListNode node = first.next;
            private LinkedTaskListNode deleteNode = first;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Task next() {
                if (node == null) {
                    throw new NoSuchElementException(
                            "Iterator reached last position!"
                    );
                }

                if (deleteNode.next.next == node) {
                    deleteNode = deleteNode.next;
                }

                node = node.next;

                return deleteNode.next.task;
            }

            @Override
            public void remove() {
                if (node == first.next) {
                    throw new IllegalStateException(
                            "Needs calling of next() iterator method!"
                    );
                }

                deleteNode.next = node;
                sizeTaskList--;
            }
        };
    }

    /**
     * Clone method for the LinkedTaskList class objects.
     * @return clone of the object
     */
    @Override
    public LinkedTaskList clone() {
        LinkedTaskList returnObj = new LinkedTaskList();
        LinkedTaskListNode addPtr = first.next;

        while (addPtr != null) {
            returnObj.add(addPtr.task);

            addPtr = addPtr.next;
        }
        return returnObj;
    }

    /**
     *Implementation through one coherent list of the getStream method of the parent class.
     * @return stream based on a collection
     */
    @Override
    public Stream<Task> getStream() {
        ArrayList<Task> stream = new ArrayList<>(sizeTaskList);
        LinkedTaskListNode current = first.next;

        while (current != null) {
            stream.add(current.task);

            current = current.next;
        }

        return stream.stream();
    }
}