package ua.edu.sumdu.j2se.savostian.tasks.services;

import java.time.LocalDateTime;

public class Formatter {
    /**
     * Method for converting LocalDateTime to a formatted string
     * @param dateTime variable to be converted
     * @throws NullPointerException if dateTime is null
     * @return a variable transformed into a string
     */
    static public StringBuilder createFormatDate(LocalDateTime dateTime) {
        StringBuilder result = new StringBuilder();
        StringBuilder tempRes = new StringBuilder();

        if (dateTime == null) {
            throw new NullPointerException(
                    "Method's parameter has null value"
            );
        }

        tempRes.append("Date :");
        tempRes.append(dateTime.getYear());
        tempRes.append("-");
        tempRes.append(dateTime.getMonth());
        tempRes.append("-");
        tempRes.append(dateTime.getDayOfMonth());

        for (int i = 0; i < 26; i++) {
            if (i < tempRes.length()) {

            } else {
                tempRes.append(" ");
            }
        }

        result.append(tempRes);
        result.append("|Time :");
        result.append(dateTime.getHour());
        result.append(":");
        result.append(dateTime.getMinute());

        return result;
    }

    /**
     * Method for converting text to a specific format
     * @param title variable to be converted
     * @param fieldSize size of the field is highlighted for the text
     * @throws NullPointerException if title is null
     * @return formatted string
     */
    static public StringBuilder createFormatTitle(String title, int fieldSize){
        StringBuilder result = new StringBuilder();

        if (InputCheck.checkEmpty(title)) {
            throw new NullPointerException(
                    "Method's parameter has null value"
            );
        }

        for (int i = 0; i < fieldSize; i++) {
            if (i < title.length())
                result.append(title.charAt(i));
            else
                result.append(' ');
        }

        return result;
    }
}
