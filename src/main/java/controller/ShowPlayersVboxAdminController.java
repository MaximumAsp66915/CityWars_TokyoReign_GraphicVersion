package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Player;
import model.cards.Card;
import model.cards.CardType;
import model.cards.Spell;
import model.cards.Troop;
import view.AdminCardMenu;
import view.AdminPlayerMenu;
import view.UpdateMenu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowPlayersVboxAdminController implements Initializable {

    @FXML public AnchorPane AnchorPane;
    @FXML public ImageView PlayerImage;
    @FXML public VBox CardVbox;
    @FXML public TextField Date;
    @FXML public TextField Email;
    @FXML public TextField Gender;
    @FXML public TextField Gold;
    @FXML public TextField Hp;
    @FXML public TextField Image;
    @FXML public TextField Level;
    @FXML public TextField Month;
    @FXML public TextField Nickname;
    @FXML public TextField Password;
    @FXML public TextField RecoveryA;
    @FXML public TextField RecoveryQ;
    @FXML public TextField Username;
    @FXML public VBox VBox;
    @FXML public TextField Year;
    @FXML public GridPane GridPane ;
    @FXML public ScrollPane ScrollPane ;
    @FXML public Label Id ;

    public boolean addPlayerHasBeenActivated = false ;
    public static boolean playerHasBeenSelected = false ;

    @FXML
    void addCard(MouseEvent event) {
        if(!addPlayerHasBeenActivated && Id.getText().equals("ID : -1")){
            addPlayerHasBeenActivated = true ;
            AnchorPane.getChildren().add(GridPane);
            AnchorPane.getChildren().add(ScrollPane);
        }
        AdminPlayerMenu.showPlayersVboxAdminController = this ;
        playerHasBeenSelected = true;
    }

    public void createCardBox(Player player) {
        if (player == null) {
            PlayerImage.setImage(new Image(getClass().getResourceAsStream("/image/addIcon.png")));
            AnchorPane.getChildren().remove(GridPane);
            AnchorPane.getChildren().remove(ScrollPane);
        } else {
            Username.setDisable(true);
            Username.setPromptText(player.getUsername());
            Password.setPromptText(player.getPassword());
            Nickname.setPromptText(player.getNickname());
            Email.setPromptText(player.getEmail());
            Date.setPromptText(player.getDateOfBirth());
            Month.setPromptText(player.getMonthOfBirth());
            Year.setPromptText(player.getYearOfBirth());
            Gender.setPromptText(player.getGender());
            RecoveryQ.setPromptText(String.valueOf(player.getRecoveryQuestion()));
            RecoveryA.setPromptText(player.getRecoveryAnswer());
            Level.setPromptText(String.valueOf(player.getLevel()));
            Hp.setPromptText(String.valueOf(player.getHp()));
            Gold.setPromptText(String.valueOf(player.getGold()));
            Image.setPromptText(player.getImage());
            Id.setText("ID : " + (AdminPlayerMenu.cardCounter+1234));
            PlayerImage.setImage(new Image(getClass().getResourceAsStream(player.getImage())));

            try{
                for(Card card : player.cards.values()){
                    FXMLLoader loader = new FXMLLoader(UpdateMenu.class.getResource("/FXML/ShowCardsVBox.fxml"));
                    Scene scene = new Scene(loader.load());
                    VBox vBox = new VBox(scene.getRoot());
                    ShowCardsVboxController showCardsVboxController = loader.getController();
                    showCardsVboxController.createCardBox(card , 196.5 , false);
                    vBox.setMaxHeight(196.5);
                    CardVbox.getChildren().add(vBox);
                }

            } catch (IOException e) {e.printStackTrace();}
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
