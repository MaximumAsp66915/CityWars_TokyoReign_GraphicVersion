package view;

import controller.DataController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public abstract class AppMenu extends Application {
    public abstract void check(String string);
    public abstract void MenuDetails();
    @FXML
    void exit(ActionEvent event) {
        Platform.exit();
    }
    @FXML
    void back(ActionEvent event) {
        UpdateMenu.soundEffectPlay(DataController.button3);
        UpdateMenu.back();
    }
}
