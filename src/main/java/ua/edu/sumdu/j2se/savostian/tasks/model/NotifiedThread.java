package ua.edu.sumdu.j2se.savostian.tasks.model;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.savostian.tasks.view.ShowCalendarView;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public class NotifiedThread extends Thread {
    private static final Logger logger =
            Logger.getLogger(NotifiedThread.class);

    /**
     * Name of Thread
     */
    private String threadName;

    /**
     * View class for print message
     */
    private ShowCalendarView view = new ShowCalendarView();

    /**
     * Collection for tasks
     */
    private AbstractTaskList tasks;

    /**
     * Constructor for creating and launching a new thread.
     * @param threadName name of Thread
     * @param tasks      collection with which the tasks will take place
     */
    public NotifiedThread(String threadName, AbstractTaskList tasks) {
        super(threadName);
        this.tasks = tasks;
        this.threadName = threadName;
        setPriority(8);
        setDaemon(true);
        logger.debug("New thread is creating and launching " + threadName + " " + this);
        start();
    }

    /**
     * Order of actions to be performed by the thread
     */
    @Override
    public void run() {
        try {//120000ms=2min
            sleep(120000);
        } catch (InterruptedException e) {
            logger.error("Thread interrupt error ",e);
        }

        while (true) {
            SortedMap<LocalDateTime, Set<Task>> taskMap = Tasks.calendar(tasks, LocalDateTime.now(), LocalDateTime.now().plusMinutes(5));

            if (!taskMap.isEmpty()) {
                System.out.println();
                view.printSeparator();
                view.printSomeTitle(threadName);
                view.printCalendarTasks(taskMap);
                view.printSeparator();
                System.out.print("Your choice - ");
                logger.debug("Thread woke up and found tasks for the near future " + threadName);
            }
            try {//300000ms=5min
                sleep(300000);
            } catch (InterruptedException e) {
                logger.error("Thread interrupt error ",e);
            }
            logger.debug("Thread woke up and found no tasks for the near future " + threadName);
        }
    }
}
