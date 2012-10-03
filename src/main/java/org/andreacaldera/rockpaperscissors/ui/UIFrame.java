package org.andreacaldera.rockpaperscissors.ui;

import org.andreacaldera.rockpaperscissors.data.GameResult;
import org.andreacaldera.rockpaperscissors.data.Weapon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * User: calderaa
 * Date: 22/09/2012
 */
public class UIFrame extends JFrame {

    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;

    private static final int NUMBER_OF_COLUMNS = 10;
    private static final int NUMBER_OF_ROWS = 10;

    private static final String NO_WEAPON_SELECTED = "Select your weapon";

    private UIController uiController;

    private JComboBox weaponsCombo;
    private JButton playButton;
    private JButton newGameButton;
    private JTextArea notificationArea;

    public UIFrame(UIController uiController) {
        this.uiController = uiController;
        this.setTitle(":: Rock-Paper-Scissors ::");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addUIComponents();
    }

    private void addUIComponents() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        panel.setLayout(new GridBagLayout());

        GridBagConstraints c1 = new GridBagConstraints();
        c1.fill = GridBagConstraints.HORIZONTAL;
        c1.gridx = 0;
        c1.gridy = 0;
        weaponsCombo = new JComboBox();
        panel.add(weaponsCombo, c1);

        playButton = new JButton("Play!");
        playButton.setEnabled(false);
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (weaponsCombo.getSelectedItem() != null && weaponsCombo.getSelectedItem() instanceof Weapon) {
                    GameResult gameResult = uiController.play((Weapon) weaponsCombo.getSelectedItem());
                    showGameResult(gameResult);
                }
            }
        });
        GridBagConstraints c2 = new GridBagConstraints();
        c2.fill = GridBagConstraints.HORIZONTAL;
        c2.gridx = 1;
        c2.gridy = 0;
        panel.add(playButton, c2);

        notificationArea = new JTextArea();
        notificationArea.setColumns(NUMBER_OF_COLUMNS);
        notificationArea.setRows(NUMBER_OF_ROWS);
        notificationArea.setEditable(false);
        GridBagConstraints c3 = new GridBagConstraints();
        c3.fill = GridBagConstraints.HORIZONTAL;
        c3.gridx = 0;
        c3.gridy = 1;
        c3.gridwidth = 2;
        panel.add(notificationArea, c3);

        newGameButton = new JButton("New game");
        newGameButton.setVisible(false);
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                notificationArea.setText("");
                weaponsCombo.setSelectedIndex(0);
                weaponsCombo.setEnabled(true);
                playButton.setEnabled(false);
                newGameButton.setVisible(false);
            }
        });
        GridBagConstraints c4 = new GridBagConstraints();
        c4.fill = GridBagConstraints.HORIZONTAL;
        c4.gridx = 0;
        c4.gridy = 2;
        c4.gridwidth = 2;
        panel.add(newGameButton, c4);
    }

    void addWeapons(List<Weapon> weapons) {
        weaponsCombo.addItem(NO_WEAPON_SELECTED);
        for (Weapon weapon : weapons) {
            weaponsCombo.addItem(weapon);
        }
        weaponsCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                playButton.setEnabled(weaponsCombo.getSelectedIndex() > 0);
            }
        });
    }

    private void showGameResult(GameResult gameResult) {
        if (gameResult.isEven()) {
            notificationArea.setText("Both you and computer choose " +
                    ((Weapon) weaponsCombo.getSelectedItem()).getName() + " as weapon.\nResult even.");
        } else {
            if (gameResult.getWinner().equals(weaponsCombo.getSelectedItem())) {
                notificationArea.setText("Congrats, you won :) " + gameResult.getMessage());
            } else {
                notificationArea.setText("Sorry, you lost :( " + gameResult.getMessage());
            }
        }
        weaponsCombo.setEnabled(false);
        playButton.setEnabled(false);
        newGameButton.setVisible(true);
    }


}
