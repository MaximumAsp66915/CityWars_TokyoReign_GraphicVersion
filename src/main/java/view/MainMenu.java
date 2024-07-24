package view;

import controller.DataController;
import controller.MainMenuController;
import controller.StaticMethods;
import enums.MainMenuCommands;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import model.App;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MainMenu extends AppMenu implements Initializable {
    public static void main(String[] args) {
        //(new AppView()).run();
        launch(args);}

    @Override
    public void start(Stage stage) throws IOException {
        UpdateMenu.stage = stage;
        UpdateMenu.toMainMenu();
    }

    @FXML private Button GameMenu;
    @FXML private Button Logout;
    @FXML private Button ShopMenu;
    @FXML private Label Gold;
    @FXML private Label Level;
    @FXML private ImageView ProfileImage;

    @FXML
    void gameMenu(ActionEvent event) {
        UpdateMenu.soundEffectPlay(DataController.button3);
        check("game menu");
    }

    @FXML
    void settings(ActionEvent event) {
        UpdateMenu.soundEffectPlay(DataController.button3);
        UpdateMenu.toSettingsMenu();
    }


    @FXML
    void logout(ActionEvent event) {
        UpdateMenu.soundEffectPlay(DataController.button3);
        check("logout");
    }

    @FXML
    void profileMenu(MouseEvent event) {
        UpdateMenu.soundEffectPlay(DataController.button3);
        check("profile menu");
    }

    @FXML
    void shopMenu(ActionEvent event) {
        UpdateMenu.soundEffectPlay(DataController.button3);
        check("shop menu");
    }

    private final MainMenuController controller = new MainMenuController();



    @Override
    public void check(String input) {
        input = StaticMethods.find(input , Arrays.asList("logout" , "profile" , "shop" , "game"));
        if (MainMenuCommands.Logout.getMatcher(input) != null) {
            System.out.println(controller.logout());
        } else if (MainMenuCommands.ProfileMenu.getMatcher(input) != null) {
            System.out.println(controller.navigateToProfileMenu());
        } else if (MainMenuCommands.ShopMenu.getMatcher(input) != null) {
            System.out.println(controller.navigateToShopMenu());
        } else if (MainMenuCommands.GameMenu.getMatcher(input) != null) {
            System.out.println(controller.navigateToGameMenu());
        } else if (MainMenuCommands.Exit.getMatcher(input) != null) {
            controller.exit();
        } else {
            System.out.println("invalid command!");
        }
    }

    @Override
    public void MenuDetails() {
        System.out.println("""
                          -logout as:\s
                          logout\s
                          -profile menu as: \s
                          profile menu\s
                          -shop menu as: \s
                          shop menu\s
                          -game menu as:\s
                          game menu\s""");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProfileImage.setImage(new Image(getClass().getResourceAsStream(App.getLoggedInUser().getImage())));
        Gold.setText("Gold : " + App.getLoggedInUser().getGold());
        Level.setText("Level : " + App.getLoggedInUser().getLevel());
    }
}