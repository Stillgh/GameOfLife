package controller;

import static org.junit.Assert.*;

import model.GameField;
import org.junit.Test;

public class KillerTest {

  GameField gameField = new GameField();
  Killer killer = new Killer(gameField);


  public void setUp() {
    gameField.setRow(10);
    gameField.setColumn(10);
    gameField.setFullCurrentState(new boolean[gameField.getRow()][gameField.getColumn()]);
    gameField.setFullNextState(new boolean[gameField.getRow()][gameField.getColumn()]);
    gameField.setTightnessLevel(3);
    gameField.setLonelinessLevel(2);
    for (int i = 0; i < gameField.getRow(); i++) {
      for (int j = 0; j < gameField.getColumn(); j++) {
        gameField.setCurrentState(i, j, true);
        gameField.setNextState(i, j, true);
      }
    }
  }

  @Test
  public void killBacterias() {
    setUp();
    killer.killBacterias();
    assertFalse(gameField.getNextState()[2][2]);
    assertTrue(gameField.getNextState()[0][0]);
    assertTrue(gameField.getNextState()[9][9]);
  }


}