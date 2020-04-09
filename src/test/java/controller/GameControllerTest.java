package controller;

import static org.junit.Assert.*;

import model.GameField;
import org.junit.Before;
import org.junit.Test;
import view.ButtonPanel;
import view.Painter;

public class GameControllerTest {

  GameField gameField = new GameField();
  ButtonPanel buttonPanel = new ButtonPanel(gameField);
  Creator creator = new Creator(gameField);
  Killer killer = new Killer(gameField);
  Painter painter = new Painter(gameField);
  GameController gameController = new GameController(buttonPanel, gameField, creator, killer, painter);

  @Before
  public void setUp() {
    gameField.setRow(10);
    gameField.setColumn(10);
    gameField.setFullCurrentState(new boolean[gameField.getRow()][gameField.getColumn()]);
    gameField.setFullNextState(new boolean[gameField.getRow()][gameField.getColumn()]);
    for (int i = 0; i < gameField.getRow(); i++) {
      for (int j = 0; j < gameField.getColumn(); j++) {
        gameField.setCurrentState(i, j, true);
      }
    }
  }

  @Test
  public void isEmpty() {
    assertEquals(false, gameController.isEmpty());
  }

  @Test
  public void fillBoard() {
    for (int i = 0; i < gameField.getRow(); i++) {
      for (int j = 0; j < gameField.getColumn(); j++) {
        gameField.setCurrentState(i, j, false);
      }
    }
    gameController.fillBoard();
    assertFalse(gameController.isEmpty());
  }

  @Test
  public void clearBoard() {
    gameController.clearBoard(gameField);
    assertEquals(true, gameController.isEmpty());
  }

  @Test
  public void stopIfAllCellsAreDead() {
    gameController.activate();
    gameController.clearBoard(gameField);
    gameController.stopIfAllCellsAreDead();
    assertFalse(gameController.getButtonPanel().getStop().isEnabled());
  }


  @Test
  public void checkNeedForStopIfAmountOfIterationsAchieved() {
    gameField.setCountOfIterations(3);
    gameField.setActualAterations(3);
    assertTrue(gameController.checkNeedForStopIfAmountOfIterationsAchieved());
  }

  @Test
  public void copyNextGenerationToCurrent() {
    gameController.copyNextGenerationToCurrent();
    assertArrayEquals(gameField.getCurrentState(), gameField.getNextState());
  }

  @Test
  public void fillFirstTime() {
    gameController.fillFirstTime();
    assertFalse(gameController.getScheduledExecutorService().isShutdown());
    assertFalse(gameController.isEmpty());
  }

  @Test
  public void activate() {
    gameController.activate();
    assertFalse(gameController.getScheduledExecutorService().isShutdown());
  }

  @Test
  public void stop() {
    gameController.activate();
    gameController.stop();
    assertTrue(gameController.getScheduledExecutorService().isShutdown());
  }

  @Test
  public void showCurrentState() {
    gameController.showCurrentState();
    assertFalse(gameController.getScheduledExecutorService().isShutdown());
  }

}