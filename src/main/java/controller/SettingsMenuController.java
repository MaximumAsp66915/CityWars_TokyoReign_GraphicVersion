package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.App;
import view.AppMenu;
import view.UpdateMenu;


import java.io.IOException;

public class SettingsMenuController extends AppMenu {
    public static void main(String[] args) {
        //(new AppView()).run();
        launch(args);}

    @Override
    public void start(Stage stage) throws IOException {
        UpdateMenu.stage = stage;
        UpdateMenu.toSettingsMenu();
    }

    @FXML private ProgressBar EffectBar;
    @FXML private ProgressBar SoundBar;

    public void initialize() {
        if(App.getLoggedInUser().getSoundVolume()>=0) {
            SoundBar.setProgress(App.getLoggedInUser().getSoundVolume());
        } else {
            SoundBar.setProgress(0);
        }

        if(App.getLoggedInUser().getEffectVolume()>=0) {
            EffectBar.setProgress(App.getLoggedInUser().getEffectVolume());
        } else {
            EffectBar.setProgress(0);
        }

        SoundBar.setOnMouseDragged(this::handleSoundVolumeDrag);
        SoundBar.setOnMouseClicked(this::handleSoundVolumeDrag);

        EffectBar.setOnMouseDragged(this::handleEffectVolumeDrag);
        EffectBar.setOnMouseClicked(this::handleEffectVolumeDrag);
    }

    private void handleSoundVolumeDrag(MouseEvent event) {
        double mouseX = event.getX();
        double totalWidth = SoundBar.getWidth();
        App.getLoggedInUser().setSoundVolume(mouseX / totalWidth); // Calculate volume from mouse position
        if(App.getLoggedInUser().getSoundVolume()>=0){
            SoundBar.setProgress(App.getLoggedInUser().getSoundVolume());
            UpdateMenu.menuSong.setVolume(App.getLoggedInUser().getSoundVolume());
        } else {
        SoundBar.setProgress(0);
        }
    }

    private void handleEffectVolumeDrag(MouseEvent event) {
        double mouseX = event.getX();
        double totalWidth = EffectBar.getWidth();
        App.getLoggedInUser().setEffectVolume(mouseX / totalWidth); // Calculate volume from mouse position
        if(App.getLoggedInUser().getEffectVolume()>=0){
            EffectBar.setProgress(App.getLoggedInUser().getEffectVolume());
            UpdateMenu.soundEffect.setVolume(App.getLoggedInUser().getEffectVolume());
        } else {
            EffectBar.setProgress(0);
        }
    }


    @Override
    public void check(String string) {}

    @Override
    public void MenuDetails() {}
}





