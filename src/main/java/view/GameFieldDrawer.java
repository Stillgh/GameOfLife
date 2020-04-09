package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.GameField;

public class GameFieldDrawer {

  private GameField gameField;
  private ButtonPanel buttonPanel;

  public void drawGameField(GameField gameField, ButtonPanel buttonPanel) {
    this.gameField = gameField;
    this.buttonPanel = buttonPanel;

    gameField.setjFrame(new JFrame("Game Of Life"));
    gameField.getjFrame().setLayout(new BorderLayout());
    gameField.getjFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gameField.getjFrame().setResizable(true);
    gameField.getjFrame().add(buttonPanel, BorderLayout.SOUTH);

    gameField.setjPanel(new JPanel());
    if (gameField.getRow() > gameField.getMaxAmountOfCellsBeforeFrameFixedSize()
        || gameField.getColumn() > gameField.getMaxAmountOfCellsBeforeFrameFixedSize()) {
      gameField.getjPanel().setPreferredSize(
          new Dimension(gameField.getPanelMaxSize(), gameField.getPanelMaxSize()));
    } else {
      gameField.getjPanel().setPreferredSize(
          new Dimension(gameField.getRow() * gameField.getPanelSizeCoef(),
              gameField.getColumn() * gameField.getPanelSizeCoef()));
    }
    gameField.getjPanel().setBackground(Color.BLUE);
    gameField.getjPanel().setLayout(new GridLayout(gameField.getRow(), gameField.getColumn(), 1, 1));

    gameField.getjFrame().add(gameField.getjPanel());
    gameField.getjFrame().setVisible(true);

    gameField.getjFrame().pack();

  }
}
