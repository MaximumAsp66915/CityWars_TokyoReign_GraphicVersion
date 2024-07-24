package view;

import controller.*;
import enums.AdminCardMenuCommands;
import enums.MainMenuCommands;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.App;
import model.Player;
import model.cards.Card;
import model.cards.Spell;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;

public class AdminCardMenu extends AppMenu implements Initializable {
    public static void main(String[] args) {
        //(new AppView()).run();
        launch(args);}

    @Override
    public void start(Stage stage) throws IOException {
        UpdateMenu.stage = stage;
        UpdateMenu.toAdminCardMenu();
    }
    public List<String> data = new ArrayList<>();
    public static ShowCardsVboxAdminController showCardsVboxAdminController ;
    private final AdminCardMenuController controller = new AdminCardMenuController();
    @FXML private HBox CardHBox;
    @FXML private Label Error ;
    @FXML private Label Result;
    @FXML private TextField SearchBox ;
    private static Card searchedCard ;
    private static String result ;
    public static int cardCounter;

    @FXML
    void findResult(MouseEvent event) {
        if(result!=null) {
            searchedCard = Card.cardHashMap.get(result);
            DataController.updateData();
            CardHBox.getChildren().remove(0, cardCounter);
            initialize();
            showCardsVboxAdminController.cardHasBeenSelected = false ;
        }
    }

    @FXML
    void saveChanges(ActionEvent event) {
        if(showCardsVboxAdminController==null || !showCardsVboxAdminController.cardHasBeenSelected){
            Error.setText("No selection!");
        } else {
            if (showCardsVboxAdminController.SpellTypeT.getText().equals("")) {
                if (showCardsVboxAdminController.NameT.getText().equals("")) {
                    if (showCardsVboxAdminController.AttackT.getText().equals("")) {
                        showCardsVboxAdminController.AttackT.setText(showCardsVboxAdminController.AttackT.getPromptText());
                    }
                    if (showCardsVboxAdminController.DurationT.getText().equals("")) {
                        showCardsVboxAdminController.DurationT.setText(showCardsVboxAdminController.DurationT.getPromptText());
                    }
                    if (showCardsVboxAdminController.DamageT.getText().equals("")) {
                        showCardsVboxAdminController.DamageT.setText(showCardsVboxAdminController.DamageT.getPromptText());
                    }
                    if (showCardsVboxAdminController.UpgradeLevelT.getText().equals("")) {
                        showCardsVboxAdminController.UpgradeLevelT.setText(showCardsVboxAdminController.UpgradeLevelT.getPromptText());
                    }
                    if (showCardsVboxAdminController.UpgradeCostT.getText().equals("")) {
                        showCardsVboxAdminController.UpgradeCostT.setText(showCardsVboxAdminController.UpgradeCostT.getPromptText());
                    }
                    if (showCardsVboxAdminController.CostT.getText().equals("")) {
                        showCardsVboxAdminController.CostT.setText(showCardsVboxAdminController.CostT.getPromptText());
                    }
                    if (showCardsVboxAdminController.ImageT.getText().equals("")) {
                        showCardsVboxAdminController.ImageT.setText(showCardsVboxAdminController.ImageT.getPromptText());
                    }
                    if (showCardsVboxAdminController.ImageBackgroundT.getText().equals("")) {
                        showCardsVboxAdminController.ImageBackgroundT.setText(showCardsVboxAdminController.ImageBackgroundT.getPromptText());
                    }
                    check("edit card " + showCardsVboxAdminController.CardName.getText() + " " + showCardsVboxAdminController.AttackT.getText() + " " + showCardsVboxAdminController.DurationT.getText() + " " + showCardsVboxAdminController.DamageT.getText() + " " + showCardsVboxAdminController.UpgradeLevelT.getText() + " " + showCardsVboxAdminController.UpgradeCostT.getText() + " " + showCardsVboxAdminController.CostT.getText() + " " + showCardsVboxAdminController.ImageT.getText() + " " + showCardsVboxAdminController.ImageBackgroundT.getText());
                } else {
                    check("add card " + showCardsVboxAdminController.NameT.getText() + " " + showCardsVboxAdminController.AttackT.getText() + " " + showCardsVboxAdminController.DurationT.getText() + " " + showCardsVboxAdminController.DamageT.getText() + " " + showCardsVboxAdminController.UpgradeLevelT.getText() + " " + showCardsVboxAdminController.UpgradeCostT.getText() + " " + showCardsVboxAdminController.CostT.getText() + " " + showCardsVboxAdminController.ImageT.getText() + " " + showCardsVboxAdminController.ImageBackgroundT.getText());
                }
            } else {
                if (showCardsVboxAdminController.SpellTypeT.getText().equals("")) {
                    if (showCardsVboxAdminController.CostT.getText().equals("")) {
                        showCardsVboxAdminController.CostT.setText(showCardsVboxAdminController.CostT.getPromptText());
                    }
                    if (showCardsVboxAdminController.ImageT.getText().equals("")) {
                        showCardsVboxAdminController.ImageT.setText(showCardsVboxAdminController.ImageT.getPromptText());
                    }
                    if (showCardsVboxAdminController.ImageBackgroundT.getText().equals("")) {
                        showCardsVboxAdminController.ImageBackgroundT.setText(showCardsVboxAdminController.ImageBackgroundT.getPromptText());
                    }
                    check("edit card " + showCardsVboxAdminController.CardName.getText() + " " + showCardsVboxAdminController.CostT.getText() + " " + showCardsVboxAdminController.ImageT.getText() + " " + showCardsVboxAdminController.ImageBackgroundT.getText());
                } else {
                    check("add card " + showCardsVboxAdminController.SpellTypeT.getText() + " " + showCardsVboxAdminController.CostT.getText() + " " + showCardsVboxAdminController.ImageT.getText() + " " + showCardsVboxAdminController.ImageBackgroundT.getText());
                }
            }
            DataController.updateData();
            CardHBox.getChildren().remove(0, cardCounter);
            initialize();
            showCardsVboxAdminController.cardHasBeenSelected = false ;
        }
    }

    @FXML
    void delete(ActionEvent event) {
        if(showCardsVboxAdminController==null || !showCardsVboxAdminController.cardHasBeenSelected){
            Error.setText("No selection!");
        } else {
            check("remove card " + showCardsVboxAdminController.CardName.getText());
        }
        DataController.updateData();
        CardHBox.getChildren().remove(0, cardCounter);
        initialize();
        showCardsVboxAdminController.cardHasBeenSelected = false ;
    }

    @Override
    public void check(String input) {
        input = StaticMethods.find(input , Arrays.asList("add" , "edit" , "remove" , "show" , "back"));
        Matcher matcher;
        if ((matcher = AdminCardMenuCommands.AddCardTroop.getMatcher(input)) != null) {
            Error.setText(controller.addCardTroop(matcher.group("name") , Double.parseDouble(matcher.group("attack")) , Integer.parseInt(matcher.group("duration")) , Double.parseDouble(matcher.group("damage")) , Integer.parseInt(matcher.group("upgradeLevel")) , Long.parseLong(matcher.group("upgradeCost")) , Long.parseLong(matcher.group("cost")) , matcher.group("image") , matcher.group("imageBackground")).getMessage());
        } else if ((matcher = AdminCardMenuCommands.AddCardSpell.getMatcher(input)) != null) {
            Error.setText(controller.addCardSpell(matcher.group("spellType") , Long.parseLong(matcher.group("cost")) , matcher.group("image") , matcher.group("imageBackground")).getMessage());
        } else if ((matcher = AdminCardMenuCommands.EditCardTroop.getMatcher(input)) != null) {
            Error.setText(controller.editCardTroop(matcher.group("name") , Double.parseDouble(matcher.group("attack")) , Integer.parseInt(matcher.group("duration")) , Double.parseDouble(matcher.group("damage")) , Integer.parseInt(matcher.group("upgradeLevel")) , Long.parseLong(matcher.group("upgradeCost")) , Long.parseLong(matcher.group("cost")) , matcher.group("image") , matcher.group("imageBackground")).getMessage());
        } else if ((matcher = AdminCardMenuCommands.EditCardSpell.getMatcher(input)) != null) {
            Error.setText(controller.editCardSpell(matcher.group("spellType") , Long.parseLong(matcher.group("cost")) , matcher.group("image") , matcher.group("imageBackground")).getMessage());
        } else if ((matcher = AdminCardMenuCommands.RemoveCard.getMatcher(input)) != null) {
            Error.setText(controller.removeCard(matcher.group("input")).getMessage());
        } else if (AdminCardMenuCommands.ShowCards.getMatcher(input) != null) {
            System.out.println(controller.showCard());
        }



        else if (AdminCardMenuCommands.Back.getMatcher(input) != null) {
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
                              -add card as: \s
                              add card <name> <attack> <duration> <damage> <upgradeLevel> <upgradeCost> <cost>\s
                              or\s
                              add card <spellType> <cost>\s
                              -edit card as: \s
                              edit card <name> <attack> <duration> <damage> <upgradeLevel> <upgradeCost> <cost>\s
                              or\s
                              edit card <spellType> <cost>\s
                              -remove card as: \s
                              remove card <name>\s
                              -show cards as:\s
                              show cards\s
                              -back as: back\s""");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Result.setText("...");
        SearchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            result = search(newValue);
            Result.setText(result != null ? result + " ?" : "No match found");
            if(newValue==null || newValue.equals("")){
                SearchBox.setPromptText("Search");
            }
        });
        if(showCardsVboxAdminController!=null)
            showCardsVboxAdminController.cardHasBeenSelected = false ;
        Error.setText("");
       initialize();
    }
    private void initialize(){
        for(Card card : Card.cardHashMap.values()){
            data.add(card.getName());
        }
        if(searchedCard==null) {
            try {
                cardCounter = 0;
                for (Card card : Card.cardHashMap.values()) {
                    FXMLLoader loader = new FXMLLoader(UpdateMenu.class.getResource("/FXML/ShowCardsVBoxAdmin.fxml"));
                    Scene scene = new Scene(loader.load());
                    VBox vBox = new VBox(scene.getRoot());
                    ShowCardsVboxAdminController showCardsVboxAdminController = loader.getController();
                    showCardsVboxAdminController.createCardBox(card);
                    CardHBox.getChildren().add(vBox);
                    cardCounter++;
                }
                FXMLLoader loader = new FXMLLoader(UpdateMenu.class.getResource("/FXML/ShowCardsVBoxAdmin.fxml"));
                Scene scene = new Scene(loader.load());
                VBox vBox = new VBox(scene.getRoot());
                ShowCardsVboxAdminController showCardsVboxAdminController = loader.getController();
                showCardsVboxAdminController.createCardBox(null);
                CardHBox.getChildren().add(vBox);
                cardCounter++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                cardCounter = 0;
                FXMLLoader loader1 = new FXMLLoader(UpdateMenu.class.getResource("/FXML/ShowCardsVBoxAdmin.fxml"));
                Scene scene1 = new Scene(loader1.load());
                VBox vBox1 = new VBox(scene1.getRoot());
                ShowCardsVboxAdminController showCardsVboxAdminController1 = loader1.getController();
                showCardsVboxAdminController1.createCardBox(searchedCard);
                CardHBox.getChildren().add(vBox1);
                cardCounter++;
                for (Card card : Card.cardHashMap.values()) {
                    if(!card.getName().equals(searchedCard.getName())) {
                        FXMLLoader loader = new FXMLLoader(UpdateMenu.class.getResource("/FXML/ShowCardsVBoxAdmin.fxml"));
                        Scene scene = new Scene(loader.load());
                        VBox vBox = new VBox(scene.getRoot());
                        ShowCardsVboxAdminController showCardsVboxAdminController = loader.getController();
                        showCardsVboxAdminController.createCardBox(card);
                        CardHBox.getChildren().add(vBox);
                        cardCounter++;
                    }
                }
                FXMLLoader loader = new FXMLLoader(UpdateMenu.class.getResource("/FXML/ShowCardsVBoxAdmin.fxml"));
                Scene scene = new Scene(loader.load());
                VBox vBox = new VBox(scene.getRoot());
                ShowCardsVboxAdminController showCardsVboxAdminController = loader.getController();
                showCardsVboxAdminController.createCardBox(null);
                CardHBox.getChildren().add(vBox);
                searchedCard = null ;
                cardCounter++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private String search(String query) {
        Optional<String> closestMatch = data.stream()
                .min((s1, s2) -> Integer.compare(levenshteinDistance(s1.toLowerCase(), query.toLowerCase()),
                        levenshteinDistance(s2.toLowerCase(), query.toLowerCase())));

        return closestMatch.orElse(null);
    }

    private int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = min(dp[i - 1][j - 1] + costOfSubstitution(a.charAt(i - 1), b.charAt(j - 1)),
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1);
                }
            }
        }

        return dp[a.length()][b.length()];
    }

    private int costOfSubstitution(char a, char b) {return a == b ? 0 : 1;}

    private int min(int... numbers) {return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);}
}
