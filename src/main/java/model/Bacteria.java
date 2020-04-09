package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Bacteria extends JLabel {

  public Bacteria(final GameField gameField, final int coordinateX, final int coordinateY) {
    addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent me) {
        if (gameField.getGameController().getButtonPanel().getStart().isEnabled()) {
          gameField.getGameController().stop();
          if (getBackground() == Color.red) {
            setBackground(Color.blue);
            gameField.setCurrentState(coordinateX, coordinateY, false);
            gameField.setNextState(coordinateX, coordinateY, false);
          } else {
            setBackground(Color.red);
            gameField.setCurrentState(coordinateX, coordinateY, true);
            gameField.setNextState(coordinateX, coordinateY, true);
          }
        }
      }
    });
  }
}