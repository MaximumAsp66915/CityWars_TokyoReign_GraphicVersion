package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.App;
import model.Color;
import model.Game;
import model.Player;
import model.cards.Spell;
import model.cards.SpellType;
import model.cards.Troop;
import model.field.GameField;
import model.field.Slot;
import view.AppMenu;
import view.UpdateMenu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameFieldController extends AppMenu implements Initializable {
    public static void main(String[] args) {
        //(new AppView()).run();
        launch(args);}

    @Override
    public void start(Stage stage) throws IOException {
        UpdateMenu.stage = stage;
        UpdateMenu.toGameField();
    }
    @FXML public HBox FirstPlayerCardContainer;
    @FXML private HBox FirstPlayerPlacedCard;
    @FXML public HBox SecondPlayerCardContainer;
    @FXML private HBox SecondPlayerPlacedCard;
    @FXML private Label Hp1 ;
    @FXML private Label Hp2 ;
    @FXML private ImageView Profile1;
    @FXML private ImageView Profile2;
    public static Player redPlayer ;
    public static Player bluePlayer ;
    public static int counter1 = 0 ;
    public static int counter2 = 0 ;
    public static boolean gameHasBeenStarted = false ;

    @FXML
    private ProgressBar progressBar;

    @Override
    public void check(String string) {}

    @Override
    public void MenuDetails() {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameHasBeenStarted=false;

        ShowSlotInPlaceController.gameFieldController = this ;
        updateStats();
    }
    public void updateStats(){
        Hp1.setText("Hp : " + bluePlayer.getHp());
        Hp2.setText("Hp : " + redPlayer.getHp());
        Profile1.setImage(new Image(getClass().getResourceAsStream(bluePlayer.getImage())));
        Profile2.setImage(new Image(getClass().getResourceAsStream(redPlayer.getImage())));
        try {
            if(gameHasBeenStarted) {
                FirstPlayerCardContainer.getChildren().remove(0, counter1);
                SecondPlayerCardContainer.getChildren().remove(0, counter2);
                FirstPlayerPlacedCard.getChildren().remove(0, 21);
                SecondPlayerPlacedCard.getChildren().remove(0, 21);
            }
            gameHasBeenStarted = true ;
            counter1 = 0 ;
            counter2 = 0 ;
            for(Slot slot : GameField.blueField){
                FXMLLoader loader = new FXMLLoader(UpdateMenu.class.getResource("/FXML/ShowCardsInPlace.fxml"));
                Scene scene = new Scene(loader.load());
                VBox vBox = new VBox(scene.getRoot());
                ShowSlotInPlaceController showCardInPlaceController = loader.getController();
                showCardInPlaceController.createSlotBox(slot, 57);
                FirstPlayerPlacedCard.getChildren().add(vBox);
            }
            for(Slot slot : GameField.redField){
                FXMLLoader loader = new FXMLLoader(UpdateMenu.class.getResource("/FXML/ShowCardsInPlace.fxml"));
                Scene scene = new Scene(loader.load());
                VBox vBox = new VBox(scene.getRoot());
                ShowSlotInPlaceController showCardInPlaceController = loader.getController();
                showCardInPlaceController.createSlotBox(slot, 57);
                SecondPlayerPlacedCard.getChildren().add(vBox);
            }
            for(Troop troop : Troop.troopHashMap.values()){
                if(ShopMenuController.getCardByNameInPlayerCards(troop.getName())!=null) {
                    boolean found = false ;
                    for(Slot slot : GameField.blueField){
                        if(!slot.isEmpty() && slot.getCard().getName().equals(troop.getName())){
                            found = true ;
                            break;
                        }
                    }
                    if(!found) {
                        FXMLLoader loader = new FXMLLoader(UpdateMenu.class.getResource("/FXML/ShowCardsInDeck.fxml"));
                        Scene scene = new Scene(loader.load());
                        VBox vBox = new VBox(scene.getRoot());
                        ShowCardInDeckController showCardInDeckController = loader.getController();
                        showCardInDeckController.createCardBox(troop, 67);
                        FirstPlayerCardContainer.getChildren().add(vBox);
                        counter1++ ;
                    }
                }
            }
            for(Spell spell : Spell.spellHashMap.values()){
                if(ShopMenuController.getCardByNameInPlayerCards(spell.getName())!=null) {
                    boolean found = false;
                    for (Slot slot : GameField.blueField) {
                        if (!slot.isEmpty() && slot.getCard().getName().equals(spell.getName())) {
                            System.out.println(slot.getCard().getName() + "  " + spell.getName());
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        FXMLLoader loader = new FXMLLoader(UpdateMenu.class.getResource("/FXML/ShowCardsInDeck.fxml"));
                        Scene scene = new Scene(loader.load());
                        VBox vBox = new VBox(scene.getRoot());
                        ShowCardInDeckController showCardInDeckController = loader.getController();
                        showCardInDeckController.createCardBox(spell, 67);
                        FirstPlayerCardContainer.getChildren().add(vBox);
                        counter1++;
                    }
                }
            }

            for(Troop troop : Troop.troopHashMap.values()){
                if(redPlayer.cards.get(troop.getName())!=null) {
                    boolean found = false ;
                    for(Slot slot : GameField.redField){
                        if(!slot.isEmpty() && slot.getCard().getName().equals(troop.getName())){
                            found = true ;
                            break;
                        }
                    }
                    if(!found) {
                        FXMLLoader loader = new FXMLLoader(UpdateMenu.class.getResource("/FXML/ShowCardsInDeck.fxml"));
                        Scene scene = new Scene(loader.load());
                        VBox vBox = new VBox(scene.getRoot());
                        ShowCardInDeckController showCardInDeckController = loader.getController();
                        showCardInDeckController.createCardBox(troop, 67);
                        SecondPlayerCardContainer.getChildren().add(vBox);
                        counter2++;
                    }
                }
            }
            for(Spell spell : Spell.spellHashMap.values()){
                if(ShopMenuController.getCardByNameInPlayerCards(spell.getName())!=null) {
                    boolean found = false;
                    for (Slot slot : GameField.redField) {
                        if (!slot.isEmpty() && slot.getCard().getName().equals(spell.getName())) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        FXMLLoader loader = new FXMLLoader(UpdateMenu.class.getResource("/FXML/ShowCardsInDeck.fxml"));
                        Scene scene = new Scene(loader.load());
                        VBox vBox = new VBox(scene.getRoot());
                        ShowCardInDeckController showCardInDeckController = loader.getController();
                        showCardInDeckController.createCardBox(spell, 67);
                        SecondPlayerCardContainer.getChildren().add(vBox);
                        counter2++;
                    }
                }
            }
        } catch (IOException e) {e.printStackTrace();}
    }
}
