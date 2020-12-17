/**
 * Model interface.
 *
 * @author Franc Lleshaj
 *
 */
public interface TicTacToeModel {

    /**
     * Reports board contents.
     *
     * @return this.top
     * @aliases reference returned by {@code board}
     * @ensures board = this.board
     */
    char[][] board();

    /**
     * Sets game status
     */
    void setGameStatus(String text);

    /**
     * Reports game status
     */
    String gameStatus();

    /**
     * Sets previous game status
     */
    void setLastGameStatus(String text);

    /**
     * Reports previous game status
     */
    String lastGameStatus();

}