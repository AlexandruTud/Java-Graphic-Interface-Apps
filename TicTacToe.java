import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame {

  private static final long serialVersionUID = 1L;
  private JButton[] buttons = new JButton[9];
  private int turn = 0;

  public TicTacToe() {
    setLayout(new GridLayout(3, 3));
    for (int i = 0; i < 9; i++) {
      buttons[i] = new JButton();
      buttons[i].setFont(new Font("Arial", Font.BOLD, 50));
      buttons[i].addActionListener(new ButtonListener());
      add(buttons[i]);
    }
    setTitle("Tic Tac Toe");
    setSize(500, 500);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setVisible(true);
  }

  private class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      JButton buttonClicked = (JButton) e.getSource();
      if (turn % 2 == 0) {
        buttonClicked.setText("X");
        buttonClicked.setForeground(Color.RED);
      } else {
        buttonClicked.setText("O");
        buttonClicked.setForeground(Color.BLUE);
      }
      turn++;
      buttonClicked.setEnabled(false);
      checkForWin();
    }
  }

  private void checkForWin() {
    // verificare coloane
    for (int i = 0; i <= 6; i += 3) {
      if (buttons[i].getText().equals(buttons[i + 1].getText())
          && buttons[i + 1].getText().equals(buttons[i + 2].getText())
          && !buttons[i].getText().equals("")) {
        win(buttons[i].getText());
        return;
      }
    }
    // verificare linii
    for (int i = 0; i <= 2; i++) {
      if (buttons[i].getText().equals(buttons[i + 3].getText())
          && buttons[i + 3].getText().equals(buttons[i + 6].getText())
          && !buttons[i].getText().equals("")) {
        win(buttons[i].getText());
        return;
      }
    }
    // verificare diagonale principale
    if (buttons[0].getText().equals(buttons[4].getText())
        && buttons[4].getText().equals(buttons[8].getText())
        && !buttons[0].getText().equals("")) {
      win(buttons[0].getText());
      return;
    }
    // verificare diagonala secundara
    if (buttons[2].getText().equals(buttons[4].getText())
        && buttons[4].getText().equals(buttons[6].getText())
        && !buttons[2].getText().equals("")) {
            win(buttons[2].getText());
            return;
        }
    if (turn == 9) {
        JOptionPane.showMessageDialog(this, "Egalitate!", "Result", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
    }

    private void win(String player) {
        if (player.equals("X")) {
            JOptionPane.showMessageDialog(this, "X a castigat!", "Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "O a castigat!", "Result", JOptionPane.INFORMATION_MESSAGE);
        }
            System.exit(0);
        }

    public static void main(String[] args) {
        new TicTacToe();
    }
    }