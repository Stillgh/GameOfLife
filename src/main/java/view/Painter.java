package view;

import model.Bacteria;
import model.GameField;
import java.awt.*;

public class Painter implements Runnable {

  private GameField gameField;

  public Painter(GameField gameField) {
    this.gameField = gameField;
  }

  public void drawBacterias() {
    for (int i = 0; i < gameField.getRow(); i++) {
      for (int j = 0; j < gameField.getColumn(); j++) {
        if (gameField.getNextState()[i][j]) {
          Bacteria jLabel = new Bacteria(gameField, i, j);
          jLabel.setSize(5, 5);
          jLabel.setOpaque(true);
          jLabel.setBackground(Color.red);
          gameField.getjPanel().add(jLabel);
        } else {
          Bacteria jLabel = new Bacteria(gameField, i, j);
          jLabel.setSize(5, 5);
          jLabel.setOpaque(true);
          jLabel.setBackground(Color.blue);
          gameField.getjPanel().add(jLabel);
        }
      }
    }
  }

  @Override
  public synchronized void run() {
    gameField.getjPanel().removeAll();
    gameField.getGameController().stopIfAllCellsAreDead();

    if (gameField.getGameController().checkNeedForStopIfAmountOfIterationsAchieved()) {
      gameField.getGameController().getButtonPanel().getStop().doClick();
    } else {

      drawBacterias();
      gameField.getjFrame().pack();
      gameField.getGameController().copyNextGenerationToCurrent();

      if (gameField.getGameController().isEmpty() || gameField.getGameController().getButtonPanel().getStart().isEnabled()) {
        gameField.getGameController().getButtonPanel().getAmountOfIterationsLabel().setText("Iterations: " + gameField.getActualAterations());
      } else {
        gameField.setActualAterations(gameField.getActualAterations() + 1);
        gameField.getGameController().getButtonPanel().getAmountOfIterationsLabel().setText("Iterations: " + gameField.getActualAterations());
      }
    }

  }
}