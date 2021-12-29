package ua.edu.sumdu.j2se.savostian.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.savostian.tasks.services.InputCheck;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class InputController extends Controller {
    private static final Logger logger =
            Logger.getLogger(InputController.class);

    /**
     * Methods responsible for the correct input of the string by the user
     * @return entered value
     */
    public static String nextString() {
        boolean wasError = false;
        String line;

        do {
            wasError = false;
            line = in.nextLine();

            if (InputCheck.checkEmpty(line)) {
                logger.debug(
                        "User's input error"
                );

                wasError = true;

            }

            if (wasError) {
                System.out.print("Entered value is not correct!\n Try again -");
            }

        } while (wasError);

        return line;
    }

    /**
     * Methods responsible for the correct input of the number by the user
     * @return entered value
     */
    public static int nextInt() {
        boolean wasError = false;
        int inValue = 0;
        String tempInt;

        do {
            wasError = false;
            tempInt = in.nextLine();

            if (InputCheck.checkEmpty(tempInt)) {
                logger.debug(
                        "User's input error. Input string is empty!"
                );

                wasError = true;

            }

            try {
                inValue = Integer.parseInt(tempInt);
            } catch (NumberFormatException e) {
                logger.debug(e);
                wasError = true;
            }

            if (inValue < 0) {
                wasError = true;
                logger.debug(
                        "User's input error. Int value is negative!"
                );
            }

            if (wasError) {
                System.out.print("Entered value is not correct!\n Try again -");
            }

        } while (wasError);

        return inValue;
    }

    /**
     * Methods responsible for the correct input of the number in range by the user
     * @return entered value
     */
    public static int nextIntInRange(int from, int to) {
        boolean wasError = false;
        int inValue = 0;
        String tempInt;

        do {
            wasError = false;
            tempInt = in.nextLine();

            if (InputCheck.checkEmpty(tempInt)) {
                logger.debug(
                        "User's input error. Input string is empty!"
                );

                wasError = true;

            }

            try {
                inValue = Integer.parseInt(tempInt);
            } catch (NumberFormatException e) {
                logger.debug(e);
                wasError = true;
            }

            if (inValue < from || inValue > to) {
                wasError = true;
                logger.debug(
                        "User's input error. Int value is negative!"
                );
            }

            if (wasError) {
                System.out.print("Entered value is not correct!\n Try again -");
            }

        } while (wasError);

        return inValue;
    }

    /**
     * Methods responsible for the correct input of the boolean by the user
     * @return entered value
     */
    public static boolean nextBoolean() {
        boolean wasError = false;
        boolean boolValue = false;
        String tempbool;

        do {
            wasError = false;
            tempbool = in.nextLine();

            if (InputCheck.checkEmpty(tempbool)) {
                logger.debug(
                        "User's input error. Input string is empty!"
                );

                wasError = true;
                continue;
            }

            if (tempbool.toLowerCase(Locale.ROOT).equals("true")) {

                boolValue = true;
            } else if (tempbool.toLowerCase(Locale.ROOT).equals("false")) {
                boolValue = false;
            } else {
                wasError = true;
            }

            if (wasError) {
                System.out.print("Entered value is not correct!\n Try again -");
            }

        } while (wasError);

        return boolValue;
    }

    /**
     * Methods responsible for the correct input of the time by the user
     * @return entered value
     */
    public static LocalDateTime nextTime() {
        LocalDateTime returnTime = null;
        boolean wasError = false;

        String tempDataTime;

        do {
            wasError = false;
            tempDataTime = in.nextLine();

            if (InputCheck.checkEmpty(tempDataTime)) {
                logger.debug(
                        "User's input error. Input string is empty!"
                );

                wasError = true;

            }

            try {
                returnTime = LocalDateTime.parse(tempDataTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            } catch (DateTimeParseException e) {
                logger.debug(e);
                wasError = true;
            }

            if (returnTime != null && returnTime.isBefore(LocalDateTime.now())) {
                wasError = true;
                logger.debug(
                        "User's input error. LocalDateTime value is in the past!"
                );
            }

            if (wasError) {
                System.out.print("Entered value is not correct!\n Try again -");
            }

        } while (wasError);

        return returnTime;
    }
}
