import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * View class.
 *
 * @author Franc Lleshaj
 */
public final class TicTacToeView1 extends JFrame implements TicTacToeView {

    /**
     * Controller object registered with this view to observe user-interaction
     * events.
     */
    private TicTacToeController controller;

    /**
     * Panels and buttons that need global scope.
     */
    private final JButton[] buttons;
    private final JLabel gameStatus;
    private final JButton exit;
    private final JButton reset;
    private final JButton singleplayer;
    private final JButton multiplayer;
    private final CardLayout cl;
    private final JPanel panels;

    /**
     * Default constructor.
     */
    public TicTacToeView1() {
        /*
         * Calls the JFrame (superclass) constructor with a String parameter to
         * name the window in its title bar
         */
        super("Tic-Tac-Toe");

        //-----------------------------Main Menu------------------------------

        // game title
        JLabel title = new JLabel(
                "<html><center><body><p style=\"font-size:40px\"><b>Tic Tac T"
                        + "oe</b></p><br><br><br><br><br><br><br><br>by Franc"
                        + " Lleshaj</body></center></html>");
        // image that is scaled appropriately and combined with title
        ImageIcon image = new ImageIcon("data/ticTacToeLogo.jpg");
        Image scaleImage = image.getImage().getScaledInstance(300, 300,
                Image.SCALE_DEFAULT);
        ImageIcon logo = new ImageIcon(scaleImage);
        title.setIcon(logo);
        // title and logo are centered, font is applied
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setVerticalTextPosition(JLabel.CENTER);
        title.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        title.setBackground(Color.WHITE);
        title.setOpaque(true);

        // title panel with title and logo is created
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.white);
        titlePanel.setOpaque(true);
        titlePanel.add(title);

        // buttons that let you choose game modes are created
        this.singleplayer = new JButton("Player vs Computer");
        this.singleplayer.setFocusable(false);
        this.singleplayer.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        this.singleplayer.setBackground(Color.white);
        // button becomes interactive
        this.singleplayer.addActionListener(this);
        this.multiplayer = new JButton(" Player vs Player ");
        this.multiplayer.setFocusable(false);
        this.multiplayer.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        this.multiplayer.setBackground(Color.white);
        // button becomes interactive
        this.multiplayer.addActionListener(this);
        // panel with buttons is created, buttons are added and size is set
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.white);
        buttonPanel.add(this.multiplayer);
        buttonPanel.add(this.singleplayer);
        buttonPanel.setPreferredSize(new Dimension(200, 200));

        // menu panel with title, logo, and button panels is created
        JPanel mainMenu = new JPanel();
        mainMenu.setLayout(new BorderLayout());
        mainMenu.add(titlePanel);
        mainMenu.add(buttonPanel, BorderLayout.SOUTH);

        //---------------------------Board------------------------------------

        // Text for game status is created
        this.gameStatus = new JLabel();
        this.gameStatus.setFont(new Font("Times New Roman", Font.BOLD, 65));
        this.gameStatus.setHorizontalAlignment(JLabel.CENTER);
        this.gameStatus.setBackground(Color.white);
        this.gameStatus.setOpaque(true);

        // exit and reset buttons are created
        this.exit = new JButton("Main Menu");
        this.exit.setBackground(Color.white);
        this.exit.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        this.exit.setPreferredSize(new Dimension(200, 100));
        this.exit.setFocusable(false);
        // button becomes interactive
        this.exit.addActionListener(this);
        this.reset = new JButton("Reset");
        this.reset.setBackground(Color.white);
        this.reset.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        this.reset.setPreferredSize(new Dimension(200, 100));
        this.reset.setFocusable(false);
        // button becomes interactive
        this.reset.addActionListener(this);

        // panel that is to be places above the tic tac toe board is created
        JPanel gameBar = new JPanel();
        gameBar.setLayout(new BorderLayout());
        gameBar.setBounds(0, 0, 800, 100);
        gameBar.add(this.exit, BorderLayout.WEST);
        gameBar.add(this.gameStatus);
        gameBar.add(this.reset, BorderLayout.EAST);

        // The board panel with the buttons is created
        JPanel boardButtons = new JPanel(new GridLayout(3, 3, 6, 6));
        boardButtons.setBackground(Color.black);
        // Buttons that represents Tic Tac Toe boxes are created
        this.buttons = new JButton[9];
        for (int i = 0; i < 9; i++) {
            this.buttons[i] = new JButton();
            this.buttons[i].setBackground(Color.white);
            this.buttons[i].setFocusable(false);
            // Buttons are set as observers of GUI events
            this.buttons[i].addActionListener(this);
            // Buttons are added to boardButtons
            boardButtons.add(this.buttons[i]);
        }

        //----------------------------JFrame----------------------------------

        // Panels are added to gamePanel, which is the panel that is viewed
        // while playing
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(gameBar, BorderLayout.NORTH);
        gamePanel.add(boardButtons);

        // The main menu and game panels are stacked, that way they can be
        // switched back and forth
        this.panels = new JPanel();
        this.cl = new CardLayout();
        this.panels.setLayout(this.cl);
        this.panels.add(mainMenu, "Main Menu");
        this.panels.add(gamePanel, "Game Panel");
        this.cl.show(this.panels, "Main Menu");

        // panels are added to JFrame
        this.add(this.panels);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 700);
        this.setVisible(true);
        // symbol for JFrame
        ImageIcon symbolX = new ImageIcon("data/symbolX.png");
        this.setIconImage(symbolX.getImage());
    }

    // Controller is set as the observer of the view
    @Override
    public void registerObserver(TicTacToeController controller) {
        this.controller = controller;
    }

    // Button text is updated depending on the index and symbol
    @Override
    public void updateDisplay(char symbol, int index) {
        if (symbol == 'X') {
            // image is rescaled
            ImageIcon symbolX = new ImageIcon("data/symbolX.png");
            Image scaleX = symbolX.getImage().getScaledInstance(100, 100,
                    Image.SCALE_DEFAULT);
            ImageIcon X = new ImageIcon(scaleX);
            // button has image added to it, and when it is disabled the graying
            // effect is removed
            this.buttons[index].setIcon(X);
            this.buttons[index].setDisabledIcon(X);
            this.buttons[index].setEnabled(false);
        } else if (symbol == 'O') {
            // image is rescaled
            ImageIcon symbolO = new ImageIcon("data/symbolO.png");
            Image scaleO = symbolO.getImage().getScaledInstance(100, 100,
                    Image.SCALE_DEFAULT);
            ImageIcon O = new ImageIcon(scaleO);
            // button has image added to it, and when it is disabled the graying
            // effect is removed
            this.buttons[index].setIcon(O);
            this.buttons[index].setDisabledIcon(O);
            this.buttons[index].setEnabled(false);
        } else {
            // if there is no symbol for the box, the button for the box is
            // enabled and the symbol picture is removed.
            this.buttons[index].setEnabled(true);
            this.buttons[index].setIcon(null);
        }

    }

    // Updates the game status that is at the top of the game panel
    @Override
    public void updateGameStatus(String text) {
        this.gameStatus.setText(text);
    }

    // Determines if buttons are usable
    @Override
    public void updateInputAllowed(boolean allowed) {
        for (int i = 0; i < 9; i++) {
            this.buttons[i].setEnabled(allowed);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        /*
         * Determine which event has occurred that we are being notified of by
         * this callback; in this case, the source of the event (i.e, the widget
         * calling actionPerformed) is all we need because only buttons are
         * involved here, so the event must be a button press; in each case,
         * tell the controller to do whatever is needed to update the model and
         * to refresh the view
         */
        Object source = event.getSource();
        if (source == this.singleplayer) {
            this.cl.show(this.panels, "Game Panel");
            this.controller.startSingleplayer();
        } else if (source == this.multiplayer) {
            this.cl.show(this.panels, "Game Panel");
            this.controller.startMultiplayer();
        } else if (source == this.exit) {
            this.cl.show(this.panels, "Main Menu");
            this.controller.resetGame();
        } else if (source == this.reset) {
            this.controller.resetGame();
        } else {
            for (int index = 0; index < 9; index++) {
                if (source == this.buttons[index]) {
                    this.controller.processBoxSelectionEvent(index);
                    break;
                }
            }
        }
    }

}