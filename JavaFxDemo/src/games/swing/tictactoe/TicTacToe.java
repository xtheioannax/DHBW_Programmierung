package swing.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static swing.tictactoe.UIConstants.*;

public class TicTacToe implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JButton[] buttons = new JButton[9];
    private boolean xTurn = true;

    public TicTacToe() {
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 40));
            buttons[i].setBackground(Color.white);
            buttons[i].setForeground(Color.black);
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(BOARD_WIDTH, BOARD_HEIGHT);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (xTurn) {
            button.setText(PLAYER_X);
        } else {
            button.setText(PLAYER_O);
        }
        button.setEnabled(false); // Disable used buttons
        xTurn = !xTurn;

        checkForWinner();
    }

    private void winningDialog(String winner) {
        JOptionPane.showMessageDialog(frame, winner + " wins!");
        resetGame();
    }

    public void checkForWinner() {
        // Check rows
        for (int i = 0; i < 9; i += 3) {
            if (    buttons[i].getText().equals(buttons[i+1].getText()) &&
                    buttons[i].getText().equals(buttons[i+2].getText()) &&
                    !buttons[i].isEnabled())
            {
                winningDialog(buttons[i].getText());
                return;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (    buttons[i].getText().equals(buttons[i+3].getText()) &&
                    buttons[i].getText().equals(buttons[i+6].getText()) &&
                    !buttons[i].isEnabled())
            {
                winningDialog(buttons[i].getText());
                return;
            }
        }

        // Check diagonals
        if (    buttons[0].getText().equals(buttons[4].getText()) &&
                buttons[0].getText().equals(buttons[8].getText()) &&
                !buttons[0].isEnabled())
        {
            winningDialog(buttons[0].getText());
            return;
        }
        if (    buttons[2].getText().equals(buttons[4].getText()) &&
                buttons[2].getText().equals(buttons[6].getText()) &&
                !buttons[2].isEnabled())
        {
            winningDialog(buttons[2].getText());
            return;
        }

        // Check for tie
        boolean tie = true;
        for (int i = 0; i < 9; i++) {
            if (buttons[i].isEnabled()) {
                tie = false;
                break;
            }
        }
        if (tie) {
            JOptionPane.showMessageDialog(frame, "The game ended in a tie!");
            resetGame();
        }
    }

    public void resetGame() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
        }
        xTurn = true;
    }


}