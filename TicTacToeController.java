/**
 * Controller interface.
 *
 * @author Franc Lleshaj
 *
 */
public interface TicTacToeController {

    /**
     * Processes event to mark selected box with either an 'X' or an 'O'
     *
     * @param index
     *            index of buttons (each representing a Tic Tac Toe box)
     * @updates this.model.board
     * @requires this.model.board has boxes with spaces in them
     * @ensures <pre>
     * this.model.board[index / NUMBER OF ROWS][index % NUMBER OF COLUMNS] = 'X'
     * or 'O'
     * [this.view has been updated to match this.model]
     * </pre>
     */
    void processBoxSelectionEvent(int index);

    /**
     * Checks which player won and updates the game status accordingly
     *
     * @param emptyBoxes
     *            number of boxes with only spaces in them
     * @updates this.model.gameStatus
     * @ensures <pre>
     * this.model.gameStatus = 'X Won!' or 'O Won!' or 'Tie'
     * .</pre>
     */
    void checkWinner(int emptyBoxes);

    /**
     * Lets computer pick a spot to assign 'O' symbol when playing against the
     * player
     *
     * @param board
     *            tic tac toe board matrix
     * @updates this.model.board
     * @requires this.model.board has boxes with spaces in them
     * @ensures <pre> this.model.board has an extra 'O' symbol <pre>
     */
    void chooseSpotAI(char[][] board);

    /**
     * Removes all symbols from tic tac toe board and replaces them with spaces.
     * Updates the game status to the previous game status.
     *
     * @updates this.model.board, this.model.gameStatus
     * @requires this.model.lastGameStatus != null
     * @ensures <pre> this.model.board has spaces in every slot,
     *          this.model.gameStatus = this.model.lastGameStatus <pre>
     */
    void resetGame();

    /**
     * Sets the last and current game status to "P vs C" symbolizing player vs
     * computer.
     *
     * @replaces this.model.lastGameStatus, this.model.gameStatus
     * @ensures <pre> this.model.lastGameStatus, this.gameStatus = "P vs C"
     *          <pre>
     */
    void startSingleplayer();

    /**
     * Sets the last and current game status to "P vs P" symbolizing player vs
     * player
     *
     * @replaces this.model.lastGameStatus, this.model.gameStatus
     * @ensures <pre> this.model.lastGameStatus, this.gameStatus = "P vs P"
     *          <pre>
     */
    void startMultiplayer();

}