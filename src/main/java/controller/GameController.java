package controller;

import java.util.Random;
import model.GameField;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import sun.nio.ch.ThreadPool;
import view.ButtonPanel;
import view.Painter;

public class GameController {

  private ButtonPanel buttonPanel;
  private ScheduledExecutorService scheduledExecutorService;
  private GameField gameField;
  private Creator creator;
  private Killer killer;
  private Painter painter;

  public ScheduledExecutorService getScheduledExecutorService() {
    return scheduledExecutorService;
  }

  public Painter getPainter() {
    return painter;
  }

  public ButtonPanel getButtonPanel() {
    return buttonPanel;
  }

  public GameController(ButtonPanel buttonPanel, GameField gameField, Creator creator,
      Killer killer, Painter painter) {
    this.gameField = gameField;
    this.buttonPanel = buttonPanel;
    this.creator = creator;
    this.killer = killer;
    this.painter = painter;
  }

  public GameController(GameField gameField) {
    this.gameField = gameField;
  }

  public boolean isEmpty() {
    for (int i = 0; i < gameField.getRow(); i++) {
      for (int j = 0; j < gameField.getColumn(); j++) {
        if (gameField.getCurrentState()[i][j]) {
          return false;
        }
      }
    }
    return true;
  }

  public void fillBoard() {
    for (int i = 0; i < gameField.getRow(); i++) {
      for (int j = 0; j < gameField.getColumn(); j++) {
        Random random = new Random();
        gameField.setCurrentState(i, j, random.nextBoolean());
      }
    }
  }

  public synchronized void clearBoard(GameField gameField) {
    for (int i = 0; i < gameField.getRow(); i++) {
      for (int j = 0; j < gameField.getColumn(); j++) {
        gameField.setCurrentState(i, j, false);
        gameField.setNextState(i, j, false);
      }
    }
  }

  public void stopIfAllCellsAreDead() {
    if (gameField.getCountOfIterations() != 0
        && gameField.getActualAterations() != gameField.getCountOfIterations()
        && isEmpty()
        && !gameField.getGameController().getButtonPanel().getStart().isEnabled()) {
      gameField.getGameController().getButtonPanel().getStop().doClick();
    }
  }

  public boolean checkNeedForStopIfAmountOfIterationsAchieved() {
    if (gameField.getActualAterations() == gameField.getCountOfIterations()) {
      return true;
    } else {
      return false;
    }
  }

  public void copyNextGenerationToCurrent() {
    for (int m = 0; m < gameField.getCurrentState().length; m++) {
      for (int j = 0; j < gameField.getCurrentState()[m].length; j++) {
        gameField.getCurrentState()[m][j] = gameField.getNextState()[m][j];
      }
    }
  }

  public void fillFirstTime() {
    if (this.scheduledExecutorService != null) {
      this.scheduledExecutorService.shutdown();
    }
    fillBoard();

    this.scheduledExecutorService = Executors.newScheduledThreadPool(3);
    scheduledExecutorService.scheduleWithFixedDelay(creator, 1, 200, TimeUnit.MILLISECONDS);
    scheduledExecutorService.scheduleWithFixedDelay(killer, 1, 200, TimeUnit.MILLISECONDS);
    scheduledExecutorService.scheduleWithFixedDelay(painter, 1, 200, TimeUnit.MILLISECONDS);
  }

  public void activate() {
    this.scheduledExecutorService = Executors.newScheduledThreadPool(3);
    scheduledExecutorService.scheduleWithFixedDelay(creator, 1, 200, TimeUnit.MILLISECONDS);
    scheduledExecutorService.scheduleWithFixedDelay(killer, 1, 200, TimeUnit.MILLISECONDS);
    scheduledExecutorService.scheduleWithFixedDelay(painter, 1, 200, TimeUnit.MILLISECONDS);
  }

  public void stop() {
    this.scheduledExecutorService.shutdown();
  }

  public void showCurrentState() {
    this.scheduledExecutorService = Executors.newScheduledThreadPool(1);
    scheduledExecutorService.scheduleWithFixedDelay(painter, 0, 200, TimeUnit.MILLISECONDS);
  }

}
