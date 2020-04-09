package model;


import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import view.SettingsWindowDrawer;


public class SettingsWindow extends JFrame {

  private JTextField widthField;
  private JTextField heightField;
  private JTextField counterField;
  private JTextField tightnessField;
  private JTextField lonelinessField;
  private JButton confirmButton = new JButton("Confirm");
  private GameField gameField;
  private final int MAX_SIZE = 90;
  private final int MIN_SIZE = 10;
  private final int MIN_COUNT = 1;


  public JButton getConfirmButton() {
    return confirmButton;
  }

  public JTextField getWidthField() {
    return widthField;
  }

  public void setWidthField(JTextField widthField) {
    this.widthField = widthField;
  }

  public JTextField getHeightField() {
    return heightField;
  }

  public void setHeightField(JTextField heightField) {
    this.heightField = heightField;
  }

  public JTextField getCounterField() {
    return counterField;
  }

  public void setCounterField(JTextField counterField) {
    this.counterField = counterField;
  }

  public JTextField getTightnessField() {
    return tightnessField;
  }

  public void setTightnessField(JTextField tightnessField) {
    this.tightnessField = tightnessField;
  }

  public JTextField getLonelinessField() {
    return lonelinessField;
  }

  public void setLonelinessField(JTextField lonelinessField) {
    this.lonelinessField = lonelinessField;
  }

  public SettingsWindow(GameField gameField) {
    this.gameField = gameField;
  }


  public void checkSettingsConditions() {
    if ((!widthField.getText().trim().equals("")
        && !heightField.getText().trim().equals("")
        && !counterField.getText().trim().equals("")
        && !tightnessField.getText().trim().equals("")
        && !lonelinessField.getText().trim().equals("")
        && Integer.parseInt(tightnessField.getText()) > Integer.parseInt(lonelinessField.getText()))) {
      confirmButton.setEnabled(true);
    } else {
      confirmButton.setEnabled(false);
    }
  }

  public void reopenSettingsWindow() {
    GameField gameField = new GameField();
    SettingsWindow settingsWindow = new SettingsWindow(gameField);
    SettingsWindowDrawer settingsWindowDrawer = new SettingsWindowDrawer();
    settingsWindowDrawer.drawSettingsWindow(settingsWindow);
  }

  public boolean checkSettingsValuesCorrectness() {
    if (Integer.parseInt(tightnessField.getText().trim()) <= Integer.parseInt(lonelinessField.getText().trim())) {
      JOptionPane.showMessageDialog(null, "Tightness level must be higher than lonileness", "ERROR", 2);
      return false;
    } else if (Integer.parseInt(widthField.getText().trim()) > MAX_SIZE
        || Integer.parseInt(widthField.getText().trim()) < MIN_SIZE
        || Integer.parseInt(heightField.getText().trim()) > MAX_SIZE
        || Integer.parseInt(heightField.getText().trim()) < MIN_SIZE) {
      JOptionPane.showMessageDialog(null, "Area sides must be between 10 and 90", "ERROR", 2);
      return false;
    } else if (Integer.parseInt(counterField.getText().trim()) < MIN_COUNT) {
      JOptionPane.showMessageDialog(null, "Number of iterations must be higher than zero", "ERROR", 2);
      return false;
    } else {
      return true;
    }
  }


  public void setConfigurationOfGameField() {

    if (checkSettingsValuesCorrectness()) {
      this.gameField.setRow(Integer.parseInt(widthField.getText().trim()));
      this.gameField.setColumn(Integer.parseInt(heightField.getText().trim()));
      this.gameField.setTightnessLevel(Integer.parseInt(tightnessField.getText().trim()));
      this.gameField.setLonelinessLevel(Integer.parseInt(lonelinessField.getText().trim()));
      this.gameField.setCountOfIterations(Integer.parseInt(counterField.getText().trim()));
      this.gameField.initGameField();
    } else {
      reopenSettingsWindow();
    }
  }
}

