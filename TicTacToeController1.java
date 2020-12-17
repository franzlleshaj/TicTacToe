import java.util.Random;

/**
 * Controller class.
 *
 * @author Franc Lleshaj
 */
public final class TicTacToeController1 implements TicTacToeController {

    /**
     * Model object.
     */
    private final TicTacToeModel model;

    /**
     * View object.
     */
    private final TicTacToeView view;

    /**
     * Useful constants.
     */
    private static final int NUM_BOXES = 9, NUM_ROWS_OR_COLUMNS = 3;

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     * @ensures [view has been updated to be consistent with model]
     */
    private static void updateViewToMatchModel(TicTacToeModel model,
            TicTacToeView view) {
        // Tic tac toe board in view is modeled by a 3x3 character matrix
        char[][] board = model.board();
        String gameStatus = model.gameStatus();

        /*
         * Every element in the matrix is checked, and if it is not empty, then
         * that means that it is either 'X' or 'O', and the view is updated
         * according to that particular element.
         */

        for (int i = 0; i < 9; i++) {
            int row = i / 3;
            int column = i % 3;
            view.updateDisplay(board[row][column], i);
        }

        /*
         * view is updated to current game status, and if the status indicates a
         * win or a tie, the board is unable to be marked
         */
        view.updateGameStatus(gameStatus);
        if (gameStatus == "X Won!" || gameStatus == "O Won!"
                || gameStatus == "Tie!") {
            view.updateInputAllowed(false);
        }
    }

    /**
     * Constructor.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public TicTacToeController1(TicTacToeModel model, TicTacToeView view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    @Override
    public void checkWinner(int emptyBoxes) {
        char[][] board = this.model.board();
        /*
         * Each check represents each way the method will scan the board. The
         * variables 'i' and 'j' are used to scan each box in every row, column
         * or diagonal in the box
         */
        int totalChecks = 4;
        for (int check = 0; check < totalChecks; check++) {
            for (int i = 0; i < 3; i++) {
                /*
                 * The variable 'rowColumnDiagonal' represents the current row,
                 * column, or diagonal's contents.
                 */
                String rowColumnDiagonal = "";
                for (int j = 0; j < 3; j++) {
                    // The first check up checks each row of the board
                    if (check == 0) {
                        /*
                         * The contents of each box in the row are added to
                         * 'rowColumnDiagonal'.
                         */
                        rowColumnDiagonal += board[i][j];
                    } else if (check == 1) { // checks each column of the board
                        /*
                         * The contents of each box in the column are added to
                         * 'rowColumnDiagonal'.
                         */
                        rowColumnDiagonal += board[j][i];
                        // checks top-left, bottom-right diagonal
                    } else if (check == 2) {
                        /*
                         * The contents of each box in the diagonal are added to
                         * 'rowColumnDiagonal'.
                         */
                        rowColumnDiagonal += board[j][j];
                        // checks top-right, bottom-left diagonal
                    } else if (check == 3) {
                        /*
                         * The contents of each box in the diagonal are added to
                         * 'rowColumnDiagonal'.
                         */
                        rowColumnDiagonal += board[2 - j][j];
                    }
                }
                /*
                 * each time a row, column, or diagonal is scanned, this if
                 * statement checks if the game is over.
                 */
                if (rowColumnDiagonal.equals("XXX")) {
                    this.model.setGameStatus("X Won!");
                } else if (rowColumnDiagonal.equals("OOO")) {
                    this.model.setGameStatus("O Won!");
                }
            }
        }
        // if there board is full and X is not the winner, the game is a tie
        if (emptyBoxes == 0 && this.model.gameStatus() != "X Won!") {
            this.model.setGameStatus("Tie!");
        }
    }

    @Override
    public void chooseSpotAI(char[][] board) {
        // evaluates to true once AI picks spot
        boolean spotChosen = false;
        /*
         * represent the 4 ways the AI can scan the board (rows, columns,
         * top-right diagonal and top-left diagonal)
         */
        int totalChecks = 4;
        outerloop: for (int check = 0; check < totalChecks; check++) {
            // i and j represent the box coordinates in the board
            for (int i = 0; i < 3; i++) {
                /*
                 * The variable 'rowColumnDiagonal' represents the current row,
                 * column, or diagonal's contents.
                 */
                String rowColumnDiagonal = "";
                for (int j = 0; j < 3; j++) {
                    // The first check up checks each row of the board
                    if (check == 0) {
                        /*
                         * The contents of each box in the row are added to
                         * 'rowColumnDiagonal'.
                         */
                        rowColumnDiagonal += board[i][j];
                        /*
                         * if the row, column, or diagonal contains 2 X symbols
                         * or 2 O symbols, O is assigned to the empty slot of
                         * the row, column, or diagonal, if there is an empty
                         * slot
                         */
                        if (rowColumnDiagonal.contains("XX")
                                || rowColumnDiagonal.equals("X X")
                                || rowColumnDiagonal.contains("OO")
                                || rowColumnDiagonal.equals("O O")) {
                            for (int k = 0; k < 3; k++) {
                                if (board[i][k] == ' ') {
                                    board[i][k] = 'O';
                                    spotChosen = true;
                                    break outerloop;
                                }
                            }
                        }
                        // The second check up checks each column of the board
                    } else if (check == 1) {
                        /*
                         * The contents of each box in the column are added to
                         * 'rowColumnDiagonal'.
                         */
                        rowColumnDiagonal += board[j][i];
                        // explanation above
                        if (rowColumnDiagonal.contains("XX")
                                || rowColumnDiagonal.equals("X X")
                                || rowColumnDiagonal.contains("OO")
                                || rowColumnDiagonal.equals("O O")) {
                            for (int k = 0; k < 3; k++) {
                                if (board[k][i] == ' ') {
                                    board[k][i] = 'O';
                                    spotChosen = true;
                                    break outerloop;
                                }
                            }
                        }
                        // checks top-left, bottom-right diagonal
                    } else if (check == 2) {
                        /*
                         * The contents of each box in the diagonal are added to
                         * 'rowColumnDiagonal'.
                         */
                        rowColumnDiagonal += board[j][j];
                        // explanation above
                        if (rowColumnDiagonal.contains("XX")
                                || rowColumnDiagonal.equals("X X")
                                || rowColumnDiagonal.contains("OO")
                                || rowColumnDiagonal.equals("O O")) {
                            for (int k = 0; k < 3; k++) {
                                if (board[k][k] == ' ') {
                                    board[k][k] = 'O';
                                    spotChosen = true;
                                    break outerloop;
                                }
                            }
                        }
                        // checks top-right, bottom-left diagonal
                    } else if (check == 3) {
                        /*
                         * The contents of each box in the diagonal are added to
                         * 'rowColumnDiagonal'.
                         */
                        rowColumnDiagonal += board[2 - j][j];
                        // explanation above
                        if (rowColumnDiagonal.contains("XX")
                                || rowColumnDiagonal.equals("X X")
                                || rowColumnDiagonal.contains("OO")
                                || rowColumnDiagonal.equals("O O")) {
                            for (int k = 0; k < 3; k++) {
                                if (board[2 - k][k] == ' ') {
                                    board[2 - k][k] = 'O';
                                    spotChosen = true;
                                    break outerloop;
                                }
                            }
                        }
                    }
                }
            }
        }
        /*
         * if the player marked one of the corners as 'X' in the beginning, then
         * 'O' is put in the middle of the board
         */
        if (!spotChosen
                && (board[0][0] == 'X' || board[0][2] == 'X'
                        || board[2][0] == 'X' || board[2][2] == 'X')
                && board[1][1] == ' ') {
            board[1][1] = 'O';
            spotChosen = true;
        }

        /*
         * if the spot was not chosen by the loop and the if statements above, a
         * random box is assigned the 'O' symbol
         */
        while (!spotChosen) {
            Random rand = new Random();
            int randInt = rand.nextInt(9);
            int row = randInt / 3;
            int column = randInt % 3;
            if (board[row][column] == ' ') {
                board[row][column] = 'O';
                spotChosen = true;
            }
        }
    }

    @Override
    public void startSingleplayer() {
        // sets last and current game status to "P vs C"
        this.model.setLastGameStatus("P vs C");
        this.model.setGameStatus("P vs C");
        // view is updated accordingly
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void startMultiplayer() {
        // sets last and current game status to "P vs P"
        this.model.setLastGameStatus("P vs P");
        this.model.setGameStatus("P vs P");
        // view is updated accordingly
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processBoxSelectionEvent(int index) {
        /*
         * Loop checks the number of empty boxes in the board, which determines
         * whether the choice will be an 'X' or an 'O', or if the game is a tie.
         * Specifically, if the number of empty boxes is even, then the box is
         * going to be filled with an 'O', or else it is going to be filled with
         * an 'X'.
         */
        char[][] board = this.model.board();
        int numEmptyBoxes = 0;
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (board[row][column] == ' ') {
                    numEmptyBoxes++;
                }
            }
        }
        /*
         * Index argument which represents which buttons was the source is
         * converted into row and column
         */
        int row = index / 3;
        int column = index % 3;
        /*
         * If the selected element is empty, then depending on if there was an
         * even or odd number of boxes left, 'O' or 'X' are assigned
         */
        if (board[row][column] == ' ') {
            if (numEmptyBoxes % 2 == 0) {
                board[row][column] = 'O';
            } else {
                board[row][column] = 'X';
            }
        }
        /*
         * Since new box was selected, the check winner function subtracts 1
         * from the number of empty boxes
         */
        this.checkWinner(numEmptyBoxes - 1);
        /*
         * If the game mode is singleplayer, and the board is not full, the
         * computer picks a move (always 'O' symbol).
         */
        if (this.model.gameStatus() == "P vs C" && numEmptyBoxes - 1 != 0) {
            /*
             * Once the computer picks, there are 2 less empty boxes than what
             * the loop earlier recorded, so 2 is subtracted
             */
            this.chooseSpotAI(board);
            this.checkWinner(numEmptyBoxes - 2);
        }
        // view is updated to match model
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void resetGame() {
        char[][] board = this.model.board();
        // assigns every box in board with spaces
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                board[row][column] = ' ';
            }
        }
        // updates gameStatus to previous game status, updates view accordingly
        this.model.setGameStatus(this.model.lastGameStatus());
        updateViewToMatchModel(this.model, this.view);
    }
}