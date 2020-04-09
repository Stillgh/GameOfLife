package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import model.SettingsWindow;

public class SettingsWindowDrawer extends JFrame{

  private SettingsWindow settingsWindow;

  public void drawSettingsWindow(final SettingsWindow settingsWindow){
    this.settingsWindow = settingsWindow;
    setTitle("Game of Life");
    setSize(new Dimension(300, 200));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new GridLayout(6, 2, 0, 0));
    settingsWindow.getConfirmButton().setEnabled(false);


    final JLabel labelWidth = new JLabel("Enter width: ");
    add(labelWidth);
    settingsWindow.setWidthField(new JTextField());
    add(settingsWindow.getWidthField());
    settingsWindow.getWidthField().addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (Character.isLetter(c)||c=='-') {
          settingsWindow.getWidthField().setEditable(false);
          labelWidth.setText("Enter only numbers");
        } else {
          labelWidth.setText("Enter width: ");
          settingsWindow.getWidthField().setEditable(true);
          settingsWindow.checkSettingsConditions();
        }
      }
    });

    final JLabel labelHeight = new JLabel("Enter height: ");
    add(labelHeight);
    settingsWindow.setHeightField(new JTextField());
    add(settingsWindow.getHeightField());
    settingsWindow.getHeightField().addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (Character.isLetter(c)||c=='-') {
          settingsWindow.getHeightField().setEditable(false);
          labelHeight.setText("Enter only numbers");
        } else {
          labelHeight.setText("Enter height: ");
          settingsWindow.getHeightField().setEditable(true);
          settingsWindow.checkSettingsConditions();
        }
      }
    });

    final JLabel labelCounter = new JLabel("Enter iterations: ");
    add(labelCounter);
    settingsWindow.setCounterField(new JTextField());
    add(settingsWindow.getCounterField());
    settingsWindow.getCounterField().addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (Character.isLetter(c)||c=='-') {
          settingsWindow.getCounterField().setEditable(false);
          labelCounter.setText("Enter only numbers");
        } else {
          labelCounter.setText("Enter iterations: ");
          settingsWindow.getCounterField().setEditable(true);
          settingsWindow.checkSettingsConditions();
        }
      }
    });

    final JLabel labelTightness = new JLabel("Tightness level: ");
    add(labelTightness);
    settingsWindow.setTightnessField(new JTextField());
    add(settingsWindow.getTightnessField());
    settingsWindow.getTightnessField().addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (Character.isLetter(c)||c=='-') {
          settingsWindow.getTightnessField().setEditable(false);
          labelTightness.setText("Enter only numbers");
        } else {
          labelTightness.setText("Tightness level: ");
          settingsWindow.getTightnessField().setEditable(true);
          settingsWindow.checkSettingsConditions();
        }
      }
    });

    final JLabel labelLonileness = new JLabel("Loneliness level: ");
    add(labelLonileness);
    settingsWindow.setLonelinessField(new JTextField());
    add(settingsWindow.getLonelinessField());
    settingsWindow.getLonelinessField().addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (Character.isLetter(c)||c=='-') {
          settingsWindow.getLonelinessField().setEditable(false);
          labelLonileness.setText("Enter only numbers");
        } else {
          labelLonileness.setText("Loneliness level: ");
          settingsWindow.getLonelinessField().setEditable(true);
          settingsWindow.checkSettingsConditions();
        }
      }
    });

    add(settingsWindow.getConfirmButton());
    setVisible(true);

    settingsWindow.getConfirmButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        dispose();
        settingsWindow.setConfigurationOfGameField();
      }
    });
  }
}
