package controller;

import static org.junit.Assert.*;

import model.GameField;
import org.junit.Test;

public class CreatorTest {

  GameField gameField = new GameField();
  Creator creator = new Creator(gameField);


  public void setUp() {
    gameField.setRow(3);
    gameField.setColumn(4);
    boolean[][] cells = {
        {true, true, true, true},
        {false, false, true, true},
        {true, false, true, false}
    };
    gameField.setFullCurrentState(cells);
    gameField.setFullNextState(new boolean[gameField.getRow()][gameField.getColumn()]);
    gameField.setTightnessLevel(3);
    gameField.setLonelinessLevel(2);

    for (int i = 0; i < gameField.getRow(); i++) {
      for (int j = 0; j < gameField.getColumn(); j++) {
        gameField.setNextState(i, j, false);
      }
    }

  }

  @Test
  public void createNewBacterias() {
    setUp();
    creator.createNewBacterias();
    assertTrue(gameField.getNextState()[1][0]);
    assertFalse(gameField.getNextState()[1][1]);
    assertTrue(gameField.getNextState()[2][3]);
  }
}