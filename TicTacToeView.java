import java.awt.event.ActionListener;

/**
 * View interface.
 *
 * @author Franc Lleshaj
 *
 */
public interface TicTacToeView extends ActionListener {
    /**
     * Register argument as observer/listener of this; this must be done first,
     * before any other methods of this class are called.
     *
     * @param controller
     *            controller to register
     */
    void registerObserver(TicTacToeController controller);

    /**
     * Updates box based on symbol and the box's index.
     *
     * @param index
     *            index of box in tic tac toe board
     * @param symbol
     *            new value of display
     */
    void updateDisplay(char symbol, int index);

    /**
     * Updates display of whether inputting at a box is allowed
     *
     * @param allowed
     *            true iff inputting is allowed
     */
    void updateInputAllowed(boolean allowed);

    /**
     * Updates game status
     */
    void updateGameStatus(String text);
}