package view;

import model.GameField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {

  private JButton start = new JButton("Start");
  private JButton stop = new JButton("Stop");
  private GameField gameField;
  private JButton clear = new JButton("Clear");
  private JLabel amountOfIterationsLabel;

  public JLabel getAmountOfIterationsLabel() {
    return amountOfIterationsLabel;
  }

  public JButton getStart() {
    return start;
  }

  public JButton getStop() {
    return stop;
  }

  public ButtonPanel(final GameField gameField) {
    this.gameField = gameField;

    setLayout(new GridLayout(1, 4, 0, 10));
    setBackground(Color.ORANGE);

    start.setActionCommand("Start");
    start.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        start.setEnabled(false);
        stop.setEnabled(true);
        clear.setEnabled(false);
        start.setText("Running");
        if (gameField.getGameController().isEmpty()) {
          gameField.getGameController().fillFirstTime();
        } else {
          gameField.getGameController().activate();
        }
      }
    });

    stop.setActionCommand("Stop");
    stop.setEnabled(false);
    stop.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        stop.setEnabled(false);
        start.setEnabled(true);
        start.setText("Start");
        clear.setEnabled(true);
        gameField.getGameController().stop();
      }
    });

    clear.setActionCommand("Clear");
    clear.setEnabled(false);
    clear.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        clear.setEnabled(true);
        start.setEnabled(true);
        start.setText("Start");
        stop.setEnabled(false);
        gameField.getGameController().stop();
        gameField.getGameController().clearBoard(gameField);
        gameField.setActualAterations(0);
        gameField.getGameController().showCurrentState();
      }
    });
    amountOfIterationsLabel = new JLabel("Iterations: ");
    amountOfIterationsLabel.setFont(new Font("Serif", Font.PLAIN, 20));

    add(start);
    add(stop);
    add(clear);
    add(amountOfIterationsLabel);
  }
}