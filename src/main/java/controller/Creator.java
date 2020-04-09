package controller;

import model.GameField;

public class Creator implements Runnable {

  private GameField gameField;

  public Creator(GameField gameField) {
    this.gameField = gameField;
  }


  public synchronized void createNewBacterias() {
    for (int thisPosX = 0; thisPosX < gameField.getRow(); thisPosX++) {
      for (int thisPosY = 0; thisPosY < gameField.getColumn(); thisPosY++) {

        int neighbours = 0;
        int minX = 0;
        int minY = 0;
        int maxX = gameField.getRow() - 1;
        int maxY = gameField.getColumn() - 1;

        int startPosX = (thisPosX - 1 < minX) ? thisPosX : thisPosX - 1;
        int startPosY = (thisPosY - 1 < minY) ? thisPosY : thisPosY - 1;
        int endPosX = (thisPosX + 1 > maxX) ? thisPosX : thisPosX + 1;
        int endPosY = (thisPosY + 1 > maxY) ? thisPosY : thisPosY + 1;

        for (int rowNum = startPosX; rowNum <= endPosX; rowNum++) {
          for (int colNum = startPosY; colNum <= endPosY; colNum++) {
            if (!(rowNum == thisPosX && colNum == thisPosY) && gameField.getCurrentState()[rowNum][colNum]) {
              neighbours++;
            }
          }
        }

        if ((neighbours == 3) && !(gameField.getCurrentState()[thisPosX][thisPosY])) {
          gameField.getNextState()[thisPosX][thisPosY] = true;
        } else if ((gameField.getCurrentState()[thisPosX][thisPosY])
            && (neighbours >= gameField.getLonelinessLevel()
            && neighbours <= gameField.getTightnessLevel())) {
          gameField.getNextState()[thisPosX][thisPosY] = true;
        }
      }
    }
  }

  @Override
  public void run() {
    createNewBacterias();
  }
}