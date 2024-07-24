package view;

import controller.*;
import enums.MainMenuCommands;
import enums.ProfileMenuCommands;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import model.App;
import model.Player;
import model.cards.Card;
import model.cards.Spell;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu extends AppMenu implements Initializable {
    public static void main(String[] args) {
        //(new AppView()).run();
        launch(args);}

    @Override
    public void start(Stage stage) throws IOException {
        UpdateMenu.stage = stage;
        UpdateMenu.toSignInMenu();
    }
    @FXML private HBox CardsContainer;
    @FXML private Label Email;
    @FXML private Label Gold;
    @FXML private Label Hp;
    @FXML private Label Level;
    @FXML private Label Nickname;
    @FXML private Label Username;
    @FXML private ProgressBar XpBar;
    @FXML private ImageView ProfileImage ;

    @FXML
    void changeProfile(ActionEvent event) {
        UpdateMenu.soundEffectPlay(DataController.button3);
        UpdateMenu.toChangeProfileMenu();
        ChangeProfileMenuController.profileMenu = this ;
    }

    private final ProfileMenuController controller = new ProfileMenuController();
    public static ChangeProfileMenuController changeProfileMenuController ;

    @Override
    public void check(String input) {
        input = StaticMethods.find(input , Arrays.asList("show" , "back" , "change"));
        Matcher matcher;
        if (ProfileMenuCommands.ShowInformation.getMatcher(input) != null) {
            System.out.println(controller.showInformation());
        } else if ((matcher = ProfileMenuCommands.ChangeUsername.getMatcher(input)) != null) {
            changeProfileMenuController.result = controller.changeUsername(matcher.group("newUsername"));
        } else if ((matcher = ProfileMenuCommands.ChangeNickname.getMatcher(input)) != null) {
            changeProfileMenuController.result = controller.changeNickname(matcher.group("newNickname"));
        } else if ((matcher = ProfileMenuCommands.ChangePassword.getMatcher(input)) != null) {
            changeProfileMenuController.result = controller.changePassword(matcher.group("oldPassword") , matcher.group("newPassword"));
        } else if ((matcher = ProfileMenuCommands.ChangeEmail.getMatcher(input)) != null) {
            changeProfileMenuController.result = controller.changeEmail(matcher.group("newEmail"));
        }



        else if (ProfileMenuCommands.Back.getMatcher(input) != null) {
            System.out.println(controller.back());
        } else if (MainMenuCommands.Exit.getMatcher(input) != null) {
            controller.exit();
        } else {
            System.out.println("invalid command!");
        }
    }

    @Override
    public void MenuDetails() {
        System.out.println("""
                change username as: \s
                change username <newUsername>\s
                change nickname as: \s
                change nickname <newNickname>\s
                change password as: \s
                change password <oldPassword> <newPassword>\s
                change email as: \s
                change email <newEmail>\s
                show information as: \s
                show information\s
                -back as:\s
                back""");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Player player = App.getLoggedInUser();
        Username.setText("Username : " + player.getUsername());
        Nickname.setText("Nickname : " + player.getNickname());
        Email.setText("Email : " + player.getEmail());
        Level.setText("Level : " + player.getLevel());
        Hp.setText("Hp : " + player.getHp());
        Gold.setText("Gold : " + player.getGold());
        XpBar.setProgress((double)player.getXp()/(2*player.getLevel()* player.getLevel()));
        ProfileImage.setImage(new Image(getClass().getResourceAsStream(player.getImage())));
        try{
            for(Card card : Card.cardHashMap.values()){
                if(ShopMenuController.getCardByNameInPlayerCards(card.getName())!=null){
                    FXMLLoader loader = new FXMLLoader(UpdateMenu.class.getResource("/FXML/ShowCardsVBox.fxml"));
                    Scene scene = new Scene(loader.load());
                    VBox vBox = new VBox(scene.getRoot());
                    ShowCardsVboxController showCardsVboxController = loader.getController();
                    showCardsVboxController.createCardBox(card , 100 , false);
                    vBox.setMaxHeight(100);
                    CardsContainer.getChildren().add(vBox);

                }
            }

        } catch (IOException e) {e.printStackTrace();}
    }
}