package esi.atlg3.g51999.othello.controller.commands;

/**
 * Generates a command from a console text input.
 *
 * @author Andre Pereira
 */
public class ConsoleCommandFactory implements CommandFactory {

    private final String command;

    /**
     * Initializates the factory.
     *
     * @param command The user text input.
     */
    public ConsoleCommandFactory(String command) {
        this.command = command;
    }

    /**
     * Parses the text input into an command. If the command in invalid it
     * returns null.
     *
     * @return The command according the user input.
     * @exception ArrayIndexOutOfBoundsException If there is not enought
     * arguments for one command.
     */
    @Override
    public Command generateCommand() {
        this.command.toLowerCase();
        String[] tokens = this.command.split(" +");
        switch (tokens[0]) {
            case "help":
                return new Help();
            case "show":
                return new Show();
            case "score":
                return new Score();
            case "play":
            case "put":
                if (validatePlayArgs(tokens)) {
                    return new Play(Integer.parseInt(tokens[2]) - 1,
                            convertColumn(tokens[1]));
                }
                break;
        }
        return null;
    }

    /**
     * Verifies the arguments for the Play command. The first argument must be a
     * letter between [A-H], and the second must be a number. The rules of
     * Othello are not verified here.
     *
     * @param tokens
     * @return
     */
    private static boolean validatePlayArgs(String[] tokens) {
        return isAcolumn(tokens[1]) && isNumeric(tokens[2]);
    }

    /**
     * Verifies if a String is numeric value.
     *
     * @param strNum The String to verify.
     * @return True if it can be parsed.
     */
    private static boolean isNumeric(String strNum) {
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    /**
     * Verifies if the column letter is between [A-H]
     *
     * @param arg The letter to verify (only checks the first letter).
     * @return True if it as column.
     */
    private static boolean isAcolumn(String arg) {
        int binValue = (int) arg.toLowerCase().charAt(0);
        return binValue >= 97 && binValue <= 104;

    }

    /**
     * Converts a column into an integer coordinate.
     *
     * @param arg The column to convert.
     * @return An integer coordinate for the attribute column in Position.
     */
    private static int convertColumn(String arg) {
        int binValue = (int) arg.toLowerCase().charAt(0);
        return binValue - 97;
    }

}
