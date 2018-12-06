package edu.bsu.cs222;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;


class DieRollerController {
    @FXML private javafx.scene.control.TextField sides;
    @FXML private TextArea result;
    @FXML private AnchorPane rootPane;
    private boolean isDarkTheme;

    @FXML void onRollDie(){
        if(validateDieRoll()) {
            Die die = new Die(Integer.parseInt(sides.getText()));
            result.setText(die.rollDie().toString());
        }
    }

    void toDarkTheme(){
        rootPane.getStylesheets().add(getClass().getResource("/Stylesheets/DarkThemeWealthModificationMenu.css").toExternalForm());
        rootPane.getStyleClass().add("DarkThemeWealthModificationMenu");
        isDarkTheme = true;
    }

    private boolean validateDieRoll(){
        StringBuilder errors = new StringBuilder();
        try {
            if (sides.getText().trim().isEmpty()) {
                errors.append(" - Please input how many sides are on the die you would like to roll.\n");
            }
            else if (sides.getText().matches(".*[a-zA-Z]+.*")) {
                errors.append(" - Die cannot have a number of sides equal to ").append(sides.getText()).append("\n");
            }
            else if (Integer.parseInt(sides.getText()) > 20) {
                errors.append(" - Die cannot have a number of sides larger than 20.\n");
            }
        }catch(NumberFormatException e){
            errors.append(" - You entered a number that was too big for our program to handle and for this you must pay\n");
        }
        if(errors.length() != 0){
            Alert alert = new Alert(Alert.AlertType.ERROR,errors.toString());
            alert.setHeaderText("Die roll errors");
            if(isDarkTheme) {
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/Stylesheets/DarkTheme.css").toExternalForm());
                dialogPane.getStyleClass().add("DarkTheme");
            }
            alert.showAndWait();
            return false;
        }
        return true;
    }
}
