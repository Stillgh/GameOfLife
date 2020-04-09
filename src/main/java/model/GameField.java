package model;

import controller.Creator;
import controller.GameController;
import controller.Killer;
import javax.swing.*;
import view.ButtonPanel;
import view.GameFieldDrawer;
import view.Painter;



public class GameField {

  private int row;
  private int column;
  private boolean[][] currentState;
  private boolean[][] nextState;
  private GameController gameController;
  private JFrame jFrame;
  private JPanel jPanel;
  private int tightnessLevel;
  private int lonelinessLevel;
  private int countOfIterations;
  private int actualAterations = 0;
  private int panelMaxSize = 950;
  private int panelSizeCoef = 20;
  private int maxAmountOfCellsBeforeFrameFixedSize = 45;

  public int getMaxAmountOfCellsBeforeFrameFixedSize() {
    return maxAmountOfCellsBeforeFrameFixedSize;
  }

  public void setFullCurrentState(boolean[][] fullCurrentState) {
    this.currentState = fullCurrentState;
  }
  public void setFullNextState(boolean[][] fullNextState) {
    this.nextState = fullNextState;
  }

  public int getPanelSizeCoef() {
    return panelSizeCoef;
  }

  public int getPanelMaxSize() {
    return panelMaxSize;
  }

  public void setCurrentState(int x, int y, boolean status) {
    this.currentState[x][y] = status;
  }

  public void setNextState(int x, int y, boolean status) {
    this.nextState[x][y] = status;
  }

  public int getActualAterations() {
    return actualAterations;
  }

  public void setActualAterations(int actualAterations) {
    this.actualAterations = actualAterations;
  }

  public int getCountOfIterations() {
    return countOfIterations;
  }

  public void setCountOfIterations(int countOfIterations) {
    this.countOfIterations = countOfIterations;
  }

  public int getTightnessLevel() {
    return tightnessLevel;
  }

  public void setTightnessLevel(int tightnessLevel) {
    this.tightnessLevel = tightnessLevel;
  }

  public int getLonelinessLevel() {
    return lonelinessLevel;
  }

  public void setLonelinessLevel(int lonelinessLevel) {
    this.lonelinessLevel = lonelinessLevel;
  }

  public void setjFrame(JFrame jFrame) {
    this.jFrame = jFrame;
  }

  public JFrame getjFrame() {
    return jFrame;
  }

  public void setjPanel(JPanel jPanel) {
    this.jPanel = jPanel;
  }

  public JPanel getjPanel() {
    return jPanel;
  }

  public synchronized GameController getGameController() {
    return gameController;
  }

  public synchronized void setGameController(GameController gameController) {
    this.gameController = gameController;
  }

  public synchronized int getRow() {
    return row;
  }

  public synchronized int getColumn() {
    return column;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  public synchronized boolean[][] getCurrentState() {
    return currentState;
  }

  public synchronized boolean[][] getNextState() {
    return nextState;
  }


  public void initGameField() {

    currentState = new boolean[getRow()][getColumn()];
    nextState = new boolean[getRow()][getColumn()];

    GameFieldDrawer gameFieldDrawer = new GameFieldDrawer();
    ButtonPanel buttonPanel = new ButtonPanel(this);
    gameFieldDrawer.drawGameField(this, buttonPanel);

    Creator creator = new Creator(this);
    Killer killer = new Killer(this);
    Painter painter = new Painter(this);

    GameController gameController = new GameController(buttonPanel, this, creator, killer,
        painter);
    setGameController(gameController);
    gameController.showCurrentState();


  }
}