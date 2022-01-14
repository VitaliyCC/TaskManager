package ua.edu.sumdu.j2se.savostian.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.savostian.tasks.view.MenuView;
import ua.edu.sumdu.j2se.savostian.tasks.services.ProgramStatus;


public class MenuController extends Controller {
    protected static final Logger logger =
            Logger.getLogger(MenuController.class);

    /**
     * Selected menu item
     */
    private Integer options;

    /**
     * Cass to display information in the console
     */
    private MenuView menuView = new MenuView();

    /**
     * Method that controls the creation and display all menus of the program
     * @param programStatus constant indicating which menu to display
     * @return constant indicating which next action to perform in the program
     */
    @Override
    public ProgramStatus process(ProgramStatus programStatus) {

        if (programStatus.equals(ProgramStatus.MAINMENU)) {
            menuView.printMainMenu();
            options = InputController.nextIntInRange(1, 5);

            logger.debug("Selected menu item " +options);

            switch (options) {
                case 1:
                    return ProgramStatus.ADD;
                case 2:
                    return ProgramStatus.CHANGE;
                case 3:
                    return ProgramStatus.CALENDAR;
                case 4:
                    return ProgramStatus.VIEWALL;
                case 5:
                    return ProgramStatus.EXIT;
            }


        } else if (programStatus.equals(ProgramStatus.CHANGE)) {
            menuView.printChangeMenu();
            options = InputController.nextIntInRange(1, 6);

            logger.debug("Selected menu item " +options);

            switch (options) {
                case 1:
                    return ProgramStatus.SETNAME;
                case 2:
                    return ProgramStatus.SETACTIVE;
                case 3:
                    return ProgramStatus.SETREPEATETTIME;
                case 4:
                    return ProgramStatus.SETNONREPEATETTIME;
                case 5:
                    return ProgramStatus.DELETE;
                case 6:
                    return ProgramStatus.SAVE;
            }

        }

        throw new IllegalStateException("Program status is incorrect!");
    }

}
