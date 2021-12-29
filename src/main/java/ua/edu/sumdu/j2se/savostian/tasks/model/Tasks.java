package ua.edu.sumdu.j2se.savostian.tasks.model;

import java.time.LocalDateTime;
import java.util.*;

public class Tasks {
    /**
     * Method for finding tasks that will be performed in a certain period of time.
     * @param tasks collection with which the tasks will take place
     * @param start start time
     * @param end end time
     * @throws NullPointerException if tasks or start or end is null
     * @throws IllegalArgumentException if start parameter is greater than end
     * @return Iterable<Task> collection with the necessary tasks
     */
    static public Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start,
                                          LocalDateTime end) {
        if (tasks == null) {
            throw new NullPointerException(
                    "Iterable<Task> parameter has null value!"
            );
        }

        if (start == null || end == null) {
            throw new NullPointerException(
                    "LocalDateTime parameter has null value!"
            );
        }

        if (start.isAfter(end)) {
            throw new IllegalArgumentException(
                    "The value of the start parameter is greater than or equal to the value of the end parameter!"
            );
        }

        List<Task> resArr = new LinkedList<Task>();
        LocalDateTime nextTime;

        for (Task task : tasks) {
            nextTime = task.nextTimeAfter(start);

            if (nextTime != null && !nextTime.isAfter(end)) {
                resArr.add(task);
            }
        }

        return resArr;
    }

    /**
     * Method that returns a schedule of tasks for a specified period of time.
     * @param tasks collection with which the tasks will take place
     * @param start start start time
     * @param end end time
     * @throws NullPointerException if tasks or start or end is null
     * @throws IllegalArgumentException if start parameter is greater than end
     * @return SortedMap<LocalDateTime, Set<Task>> collection where the key is the task execution time
     */
    static public SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start,
                                                               LocalDateTime end) {
        TreeMap<LocalDateTime, Set<Task>> newCalendar = new TreeMap<>();
        LocalDateTime begin;
        Set<Task> ptrSet;

        tasks = incoming(tasks, start, end);

        for (Task task : tasks) {
            begin = start;

            while (true) {
                begin = task.nextTimeAfter(begin);

                if (begin == null || begin.isAfter(end))
                    break;

                if (newCalendar.containsKey(begin)) {
                    ptrSet = newCalendar.get(begin);
                } else {
                    ptrSet = new HashSet<>();
                    newCalendar.put(begin, ptrSet);
                }

                ptrSet.add(task);
            }
        }

        return newCalendar;
    }
}