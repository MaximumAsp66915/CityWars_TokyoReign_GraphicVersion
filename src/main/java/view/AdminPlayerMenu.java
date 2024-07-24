package view;

import controller.*;
import enums.AdminPlayerMenuCommands;
import enums.MainMenuCommands;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.App;
import model.Player;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;

public class AdminPlayerMenu extends AppMenu implements Initializable {
    public static void main(String[] args) {
        //(new AppView()).run();
        launch(args);}

    @Override
    public void start(Stage stage) throws IOException {
        UpdateMenu.stage = stage;
        UpdateMenu.toSignInMenu();
    }
    public static ShowPlayersVboxAdminController showPlayersVboxAdminController ;
    public List<String>data = new ArrayList<>();
    private final AdminPlayerMenuController controller = new AdminPlayerMenuController();
    @FXML private Label Error;
    @FXML private VBox PlayerVBox;
    @FXML private Label Result;
    @FXML private TextField SearchBox ;
    private static Player searchedPlayer ;
    private static String result ;
    public static int cardCounter;

    @FXML
    void findResult(MouseEvent event) {
        if(result!=null) {
            searchedPlayer = App.players.get(result);
            DataController.updateData();
            PlayerVBox.getChildren().remove(0, cardCounter);
            initialize();
            showPlayersVboxAdminController.playerHasBeenSelected = false ;
        }
    }

    @FXML
    void saveChanges(ActionEvent event) {
        if(showPlayersVboxAdminController==null || !showPlayersVboxAdminController.playerHasBeenSelected){
            Error.setText("No selection!");
        } else {
            if(showPlayersVboxAdminController.Username.getText().equals("")){
                showPlayersVboxAdminController.Username.setText(showPlayersVboxAdminController.Username.getPromptText());
            }
            if(showPlayersVboxAdminController.Password.getText().equals("")){
                showPlayersVboxAdminController.Password.setText(showPlayersVboxAdminController.Password.getPromptText());
            }
            if(showPlayersVboxAdminController.Nickname.getText().equals("")){
                showPlayersVboxAdminController.Nickname.setText(showPlayersVboxAdminController.Nickname.getPromptText());
            }
            if(showPlayersVboxAdminController.Email.getText().equals("")){
                showPlayersVboxAdminController.Email.setText(showPlayersVboxAdminController.Email.getPromptText());
            }
            if(showPlayersVboxAdminController.Month.getText().equals("")){
                showPlayersVboxAdminController.Month.setText(showPlayersVboxAdminController.Month.getPromptText());
            }
            if(showPlayersVboxAdminController.Date.getText().equals("")){
                showPlayersVboxAdminController.Date.setText(showPlayersVboxAdminController.Date.getPromptText());
            }
            if(showPlayersVboxAdminController.Year.getText().equals("")){
                showPlayersVboxAdminController.Year.setText(showPlayersVboxAdminController.Year.getPromptText());
            }
            if(showPlayersVboxAdminController.Gender.getText().equals("")){
                showPlayersVboxAdminController.Gender.setText(showPlayersVboxAdminController.Gender.getPromptText());
            }
            if(showPlayersVboxAdminController.Level.getText().equals("")){
                showPlayersVboxAdminController.Level.setText(showPlayersVboxAdminController.Level.getPromptText());
            }
            if(showPlayersVboxAdminController.Hp.getText().equals("")){
                showPlayersVboxAdminController.Hp.setText(showPlayersVboxAdminController.Hp.getPromptText());
            }
            if(showPlayersVboxAdminController.Gold.getText().equals("")){
                showPlayersVboxAdminController.Gold.setText(showPlayersVboxAdminController.Gold.getPromptText());
            }
            if(showPlayersVboxAdminController.Image.getText().equals("")){
                showPlayersVboxAdminController.Image.setText(showPlayersVboxAdminController.Image.getPromptText());
            }
            if(showPlayersVboxAdminController.RecoveryQ.getText().equals("")){
                showPlayersVboxAdminController.RecoveryQ.setText(showPlayersVboxAdminController.RecoveryQ.getPromptText());
            }
            if(showPlayersVboxAdminController.RecoveryA.getText().equals("")){
                showPlayersVboxAdminController.RecoveryA.setText(showPlayersVboxAdminController.RecoveryA.getPromptText());
            }
            if(App.players.get(showPlayersVboxAdminController.Username.getText())==null){
                check("add player " + showPlayersVboxAdminController.Username.getText() + " " + showPlayersVboxAdminController.Password.getText() + " " + showPlayersVboxAdminController.Nickname.getText() + " " + showPlayersVboxAdminController.Email.getText() + " " + showPlayersVboxAdminController.Level.getText() + " " + showPlayersVboxAdminController.Hp.getText() + " " + showPlayersVboxAdminController.Gold.getText() + " " + showPlayersVboxAdminController.Date.getText() + " " + showPlayersVboxAdminController.Month.getText() + " " + showPlayersVboxAdminController.Year.getText() + " " + showPlayersVboxAdminController.Gender.getText() + " " + showPlayersVboxAdminController.Image.getText() + " " + showPlayersVboxAdminController.RecoveryQ.getText() + " " + showPlayersVboxAdminController.RecoveryA.getText());
            } else {
                check("edit player " + showPlayersVboxAdminController.Username.getText() + " " + showPlayersVboxAdminController.Password.getText() + " " + showPlayersVboxAdminController.Nickname.getText() + " " + showPlayersVboxAdminController.Email.getText() + " " + showPlayersVboxAdminController.Level.getText() + " " + showPlayersVboxAdminController.Hp.getText() + " " + showPlayersVboxAdminController.Gold.getText() + " " + showPlayersVboxAdminController.Date.getText() + " " + showPlayersVboxAdminController.Month.getText() + " " + showPlayersVboxAdminController.Year.getText() + " " + showPlayersVboxAdminController.Gender.getText() + " " + showPlayersVboxAdminController.Image.getText() + " " + showPlayersVboxAdminController.RecoveryQ.getText() + " " + showPlayersVboxAdminController.RecoveryA.getText());
            }
        }
        DataController.updateData();
        PlayerVBox.getChildren().remove(0, cardCounter);
        initialize();
        showPlayersVboxAdminController.playerHasBeenSelected = false ;
    }
    @FXML
    void delete(ActionEvent event) {
        if(showPlayersVboxAdminController==null || !showPlayersVboxAdminController.playerHasBeenSelected){
            Error.setText("No selection!");
        } else {
            if(showPlayersVboxAdminController.Username.getText().equals(""))
                showPlayersVboxAdminController.Username.setText(showPlayersVboxAdminController.Username.getPromptText());
            check("remove player " + showPlayersVboxAdminController.Username.getText());
        }
        DataController.updateData();
        PlayerVBox.getChildren().remove(0, cardCounter);
        initialize();
        showPlayersVboxAdminController.playerHasBeenSelected = false ;
    }
    @Override
    public void check(String input) {
        input = StaticMethods.find(input, Arrays.asList("add", "edit", "remove", "show", "back"));
        Matcher matcher;
        if ((matcher = AdminPlayerMenuCommands.AddPlayer.getMatcher(input)) != null) {
            Error.setText(controller.addPlayer(matcher.group("username"), matcher.group("password"), matcher.group("nickname"), matcher.group("email"), Integer.parseInt(matcher.group("level")), Double.parseDouble(matcher.group("hp")), Long.parseLong(matcher.group("gold")) , matcher.group("date") , matcher.group("month") , matcher.group("year") , matcher.group("gender"),matcher.group("image"),Integer.parseInt(matcher.group("recoveryQ")),matcher.group("recoveryA")).getMessage());
        } else if ((matcher = AdminPlayerMenuCommands.EditPlayer.getMatcher(input)) != null) {
            Error.setText(controller.editPlayer(matcher.group("username"), matcher.group("password"), matcher.group("nickname"), matcher.group("email"), Integer.parseInt(matcher.group("level")), Double.parseDouble(matcher.group("hp")), Long.parseLong(matcher.group("gold")) , matcher.group("date") , matcher.group("month") , matcher.group("year") , matcher.group("gender"),matcher.group("image"),Integer.parseInt(matcher.group("recoveryQ")),matcher.group("recoveryA")).getMessage());
        } else if ((matcher = AdminPlayerMenuCommands.RemovePlayer.getMatcher(input)) != null) {
            Error.setText(controller.removePlayer(matcher.group("username")).getMessage());
        } else if (AdminPlayerMenuCommands.ShowPlayers.getMatcher(input) != null) {
            System.out.println(controller.showPlayers());
        } else if (AdminPlayerMenuCommands.Back.getMatcher(input) != null) {
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
                -add player as: \s
                add player <username> <password> <nickname> <email> <level> <hp> <gold>\s
                -edit card as: \s
                edit player <username> <password> <nickname> <email> <level> <hp> <gold>\s
                -remove player as: \s
                remove player <username>\s
                -show players as:\s
                show players\s
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
        if(showPlayersVboxAdminController!=null)
            showPlayersVboxAdminController.playerHasBeenSelected = false ;
        Error.setText("");
        initialize();
    }
    private void initialize(){
        for(Player player : App.players.values()){
            data.add(player.getUsername());
        }
        if(searchedPlayer==null) {
            try {
                cardCounter = 0;
                for (Player player : App.players.values()) {
                    FXMLLoader loader = new FXMLLoader(UpdateMenu.class.getResource("/FXML/ShowPlayersVBoxAdmin.fxml"));
                    Scene scene = new Scene(loader.load());
                    VBox vBox = new VBox(scene.getRoot());
                    ShowPlayersVboxAdminController showPlayersVboxAdminController = loader.getController();
                    showPlayersVboxAdminController.createCardBox(player);
                    PlayerVBox.getChildren().add(vBox);
                    cardCounter++;
                }
                FXMLLoader loader = new FXMLLoader(UpdateMenu.class.getResource("/FXML/ShowPlayersVBoxAdmin.fxml"));
                Scene scene = new Scene(loader.load());
                VBox vBox = new VBox(scene.getRoot());
                ShowPlayersVboxAdminController showPlayersVboxAdminController = loader.getController();
                showPlayersVboxAdminController.createCardBox(null);
                PlayerVBox.getChildren().add(vBox);
                cardCounter++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                cardCounter = 0;
                FXMLLoader loader1 = new FXMLLoader(UpdateMenu.class.getResource("/FXML/ShowPlayersVBoxAdmin.fxml"));
                Scene scene1 = new Scene(loader1.load());
                VBox vBox1 = new VBox(scene1.getRoot());
                ShowPlayersVboxAdminController showPlayersVboxAdminController1 = loader1.getController();
                showPlayersVboxAdminController1.createCardBox(searchedPlayer);
                PlayerVBox.getChildren().add(vBox1);
                cardCounter++;
                for (Player player : App.players.values()) {
                    if(!player.equals(searchedPlayer)) {
                        FXMLLoader loader = new FXMLLoader(UpdateMenu.class.getResource("/FXML/ShowPlayersVBoxAdmin.fxml"));
                        Scene scene = new Scene(loader.load());
                        VBox vBox = new VBox(scene.getRoot());
                        ShowPlayersVboxAdminController showPlayersVboxAdminController = loader.getController();
                        showPlayersVboxAdminController.createCardBox(player);
                        PlayerVBox.getChildren().add(vBox);
                        cardCounter++;
                    }
                }
                FXMLLoader loader = new FXMLLoader(UpdateMenu.class.getResource("/FXML/ShowPlayersVBoxAdmin.fxml"));
                Scene scene = new Scene(loader.load());
                VBox vBox = new VBox(scene.getRoot());
                ShowPlayersVboxAdminController showPlayersVboxAdminController = loader.getController();
                showPlayersVboxAdminController.createCardBox(null);
                PlayerVBox.getChildren().add(vBox);
                cardCounter++;
                searchedPlayer = null ;
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