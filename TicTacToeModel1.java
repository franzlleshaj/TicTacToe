/**
 * Model class.
 *
 * @author Franc Lleshaj
 */
public final class TicTacToeModel1 implements TicTacToeModel {

    private char[][] board;
    private String gameStatus;
    private String lastGameStatus;

    /**
     * Default constructor.
     */
    public TicTacToeModel1() {
        this.board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = ' ';
            }
        }
    }

    @Override
    public char[][] board() {
        return this.board;
    }

    @Override
    public void setGameStatus(String text) {
        this.gameStatus = text;
    }

    @Override
    public String gameStatus() {
        return this.gameStatus;
    }

    @Override
    public void setLastGameStatus(String text) {
        this.lastGameStatus = text;
    }

    @Override
    public String lastGameStatus() {
        return this.lastGameStatus;
    }

}