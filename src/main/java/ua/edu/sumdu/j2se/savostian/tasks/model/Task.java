package ua.edu.sumdu.j2se.savostian.tasks.model;

import org.apache.log4j.Logger;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Class Task for objects task.
 * @author Savostian Vitalii
 * @version 1.0
 */
public class Task implements Cloneable, Serializable {
    private static final Logger logger =
            Logger.getLogger(Task.class);

    /**
     * Stores a title of task.
     */
    private String title;

    /**
     * Stores a state of task activity.
     */
    private boolean isActive;

    /**
     * If task is repeated ({@link Task#isPeriodical}) stores a time of task start.
     * If task is not repeated stores a time of task completion
     */
    private LocalDateTime start;

    /**
     * Stores the end time of non-repeated task completion.
     */
    private LocalDateTime end;

    /**
     * Stores an interval of task repeat.
     */
    private int interval;

    /**
     * Indicates whether the task is repeated.
     */
    private boolean isPeriodical;

    /**
     * Constructor for empty tasks.
     * @see Task#Task() constructor for tasks
     */
    public Task() {
        title = "My empty task";
        isActive = false;
        isPeriodical = false;
        setTime(LocalDateTime.MIN);

        logger.debug(
                "Create new task" + this
        );
    }

    /**
     * Constructor for non repeated tasks (task activity defaults as false).
     * @param title title of the task
     * @param time time of completion of the task
     * @exception IllegalArgumentException if LocalDateTime object has null value
     * @see Task#Task(String, LocalDateTime, LocalDateTime, int) constructor for repeated tasks
     */
    public Task(String title, LocalDateTime time) {
        setTitle(title);
        setTime(time);

        logger.debug(
                "Create new non-repeated task" + this
        );
    }

    /**
     * Constructor for repeated tasks (task activity defaults as false).
     * @param title title of the task
     * @param start start time of completion period
     * @param end end time of completion period
     * @param interval interval of task completion
     * @exception IllegalArgumentException if start time more or equal end time or any LocalDateTime object has null value
     * @see Task#Task(String, LocalDateTime) constructor for non-repeated tasks
     */
    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) {
        setTitle(title);
        setTime(start, end, interval);

        logger.debug(
                "Create new repeated task" + this
        );
    }

    /**
     * Getter for title of task.
     * @return title of task
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Setter for title task.
     * @param title title of task
     */
    public void setTitle(String title) {
        this.title = title;
        logger.debug(
                "Saved new title of task" + this
        );
    }

    /**
     * Getter for completion time of task.
     * @return start time for repeated task and completion time for non-repeated
     */
    public LocalDateTime getTime() {
        return this.start;
    }

    /**
     * Setter for activity state of task.
     * @param active Sets the activity of task
     */
    public void setActive(boolean active) {
        this.isActive = active;

        logger.debug(
                "Set new activity of task " + this
        );
    }

    /**
     * Getter for activity of task.
     * @return is the task active
     */
    public boolean isActive() {
        return this.isActive;
    }

    /**
     * Setter for completion time of non-repeated task (sets task as non-repeated).
     * @param time completion time for non-repeated task
     * @throws IllegalArgumentException if time parameter has negative value
     * @see Task#setTime(LocalDateTime) setter for repeated task
     */
    public void setTime(LocalDateTime time) {
        if (time == null) {
            throw new IllegalArgumentException(
                    "LocalDateTime parameter has null value!"
            );
        }

        this.start = time;

        if (this.isPeriodical) {
            this.isPeriodical = false;
        }

        logger.debug(
                "Set new start time of task " + this
        );
    }

    /**
     * Setter for time parameters of repeated task (sets task as repeated).
     * @param start    start time of completion period
     * @param end      end time of completion period
     * @param interval interval of task completion
     * @throws IllegalArgumentException if start or end  parameter has negative value
     *                                  or the start parameter is greater than or equal to the value of the end
     *                                  or Interval parameters is not positive
     * @see Task#setTime(LocalDateTime, LocalDateTime, int) setter for non-repeated task
     */
    public void setTime(LocalDateTime start, LocalDateTime end, int interval) {
        if (start == null || end == null) {
            throw new IllegalArgumentException(
                    "LocalDateTime parameter has null value!"
            );
        }

        if (start.isAfter(end) || start.equals(end)) {
            throw new IllegalArgumentException(
                    "The value of the start parameter is greater than or equal to the value of the end parameter!"
            );
        }

        if (interval <= 0) {
            throw new IllegalArgumentException(
                    "Interval parameters is not positive!"
            );
        }

        this.start = start;
        this.end = end;
        this.interval = interval;

        if (!this.isPeriodical) {
            this.isPeriodical = true;
        }

        logger.debug(
                "Set new start time, end time and interval of " +
                        "task " + this
        );
    }

    /**
     * Getter for start time of repeated task.
     * @return start time for repeated task and time of completion for non-repeated
     */
    public LocalDateTime getStartTime() {
        return this.start;
    }

    /**
     * Getter for end time of repeated task.
     * @return end time for repeated task and time of completion for non-repeated
     */
    public LocalDateTime getEndTime() {
        if (this.isPeriodical) {
            return this.end;
        } else {
            return this.start;
        }
    }

    /**
     * Getter for interval of repeated task.
     * @return interval for repeated task and 0 for non-repeated
     */
    public int getRepeatInterval() {
        if (this.isPeriodical) {
            return this.interval;
        } else {
            return 0;
        }
    }

    /**
     * Getter for repeat state of task.
     * @return repeat state of task
     */
    public boolean isRepeated() {
        return this.isPeriodical;
    }

    /**
     * Method that returns next time of task completion after given time.
     * @param current the time relative to which you want to find the completion time
     * @return time of next task completion after current time. If task is not active returns 0. If task will not run after current time returns 0
     * @throws IllegalArgumentException if current parameter has negative value
     */
    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        if (current == null) {
            throw new IllegalArgumentException(
                    "Сurrent parameter is null!"
            );
        }

        if (!this.isActive) {
            return null;
        }

        if (this.isPeriodical) {
            LocalDateTime nextTime = this.start;

            while (!current.isBefore(nextTime)) {
                if (nextTime.plusSeconds(this.interval).isAfter(this.end)) {
                    return null;
                } else {
                    nextTime = nextTime.plusSeconds(this.interval);
                }
            }
            return nextTime;
        } else {
            if (current.isBefore(this.start)) {
                return this.start;
            } else {
                return null;
            }
        }
    }

    /**
     * Equals method for Task class objects.
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
        return title.equals(((Task) otherObject).title) &&
                start == ((Task) otherObject).start &&
                end == ((Task) otherObject).end &&
                interval == ((Task) otherObject).interval &&
                isActive == ((Task) otherObject).isActive &&
                isPeriodical == ((Task) otherObject).isPeriodical;
    }

    /**
     * Hash code method for Task class objects.
     * @return Unique hash code for Task class object (there may be collisions)
     */
    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), isActive(), start, end, interval, isPeriodical);
    }

    /**
     * To string method for Task class objects.
     * @return string that consists of class name and all object fields through the symbol #
     */
    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", isActive=" + isActive +
                ", start=" + start +
                ", end=" + end +
                ", interval=" + interval +
                ", isPeriodical=" + isPeriodical +
                '}';
    }

    /**
     * Clone method for the Task class objects.
     * @return clone of the object (pointer on this object)
     * @throws CloneNotSupportedException if class is not implements Cloneable() interface
     */
    @Override
    public Task clone() throws CloneNotSupportedException {
        return (Task) super.clone();
    }
}