package ua.edu.sumdu.j2se.savostian.tasks.services;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.savostian.tasks.model.AbstractTaskList;


public class InputCheck {
    private static final Logger logger =
            Logger.getLogger(InputCheck.class);

    /**
     * Method for checking the string for emptiness
     * @param str string to check
     * @return true if the string is empty false if it has a value
     */
    public static boolean checkEmpty(String str) {
        logger.debug(
                "Checking string for emptiness " +str
        );

        if (str.length() == 0) {
            return true;
        }

        char symbol;
        for (int counter = 0; counter < str.length(); counter++) {
            symbol = str.charAt(counter);
            if (symbol != ' ' && symbol != '\t') {
                return false;
            }
        }
        return true;
    }

    /**
     * Method for checking the collection for emptiness
     * @param collection collection to check
     * @return false if the collection is empty true if it has a value
     */
    public static Boolean checkCollection(AbstractTaskList collection) {
        logger.debug(
                "Checking collection for emptiness "+collection.getClass()
        );

        if (collection == null) {
            return false;

        } else if (collection.size() == 0) {
            return false;

        } else {
            return true;

        }
    }
}
