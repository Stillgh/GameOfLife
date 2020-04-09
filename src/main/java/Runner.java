import model.GameField;
import model.SettingsWindow;
import view.GameFieldDrawer;
import view.SettingsWindowDrawer;

public class Runner {

  public static void main(String[] args) {
    GameField gameField = new GameField();
    SettingsWindow settingsWindow = new SettingsWindow(gameField);
    SettingsWindowDrawer settingsWindowDrawer = new SettingsWindowDrawer();
    settingsWindowDrawer.drawSettingsWindow(settingsWindow);
   
  }
}
