package view;

import controller.AdminMenuController;
import controller.StaticMethods;
import enums.AdminMenuCommands;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Set;

public class AdminMenu extends AppMenu implements Initializable {
    public static void main(String[] args) {
        //(new AppView()).run();
        launch(args);}

    @Override
    public void start(Stage stage) throws IOException {
        UpdateMenu.stage = stage;
        UpdateMenu.toAdminMenu();
    }
    private final AdminMenuController controller = new AdminMenuController();
    @FXML private Button Settings;

    @FXML
    void logout(ActionEvent event) {
        check("logout");
    }

    @FXML
    void playerMenu(ActionEvent event) {
        check("player menu");
    }

    @FXML
    void settings(ActionEvent event) {}

    @FXML
    void cardMenu(ActionEvent event) {
        check("card menu");
    }

    @Override
    public void check(String input) {
        input = StaticMethods.find(input , Arrays.asList("card" , "player" , "logout"));
        if (AdminMenuCommands.cardMenu.getMatcher(input) != null) {
            System.out.println(controller.navigateToCardMenu());
        } else if (AdminMenuCommands.playerMenu.getMatcher(input) != null) {
            System.out.println(controller.navigateToPlayerMenu());
        } else if (AdminMenuCommands.Exit.getMatcher(input) != null) {
            controller.exit();
        } else if (AdminMenuCommands.Logout.getMatcher(input) != null) {
            System.out.println(controller.logout());
        } else {
            System.out.println("invalid command!");
        }
    }

    @Override
    public void MenuDetails() {
        System.out.println("""
                -card menu as: \s
                card menu\s
                -player menu as: \s
                player menu\s
                -logout as: \s
                logout\s""");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Settings.setDisable(true);
    }
}
