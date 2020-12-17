/**
 * This is a Tic Tac Toe game with a GUI that was made using the Swing
 * framework. There is a player vs player and a player vs computer game mode,
 * either of which can be selected from the main menu. Moreover, during the game
 * the player can decide if they want to start over or return to the main menu
 * to change game modes.
 *
 * @author Franc Lleshaj
 */
public final class TicTacToe {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private TicTacToe() {
    }

    /**
     * Main program that sets up main application window and starts user
     * interaction.
     *
     * @param args
     *            command-line arguments; not used
     */
    public static void main(String[] args) {
        /*
         * Creates instances of the model, view, and controller objects;
         * controller needs to know about model and view, and view needs to know
         * about controller
         */
        TicTacToeModel model = new TicTacToeModel1();
        TicTacToeView view = new TicTacToeView1();
        TicTacToeController controller = new TicTacToeController1(model, view);

        view.registerObserver(controller);
    }

}
