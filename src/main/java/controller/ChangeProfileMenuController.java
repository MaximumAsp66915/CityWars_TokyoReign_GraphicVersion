package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.App;
import model.Player;
import model.Result;
import view.AppMenu;
import view.PasswordBuilderMenu;
import view.ProfileMenu;
import view.UpdateMenu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChangeProfileMenuController extends AppMenu implements Initializable {
    public static void main(String[] args) {
        //(new AppView()).run();
        launch(args);}

    @Override
    public void start(Stage stage) throws IOException {
        UpdateMenu.stage = stage;
        UpdateMenu.toChangeProfileMenu();
    }
    private static Player player ;
    public static ProfileMenu profileMenu ;
    public Result result ;
    @FXML private PasswordField CurrentPassword;
    @FXML private ComboBox<String> Date;
    @FXML private TextField Email;
    @FXML private ComboBox<String> Gender;
    @FXML private ComboBox<String> Month;
    @FXML private PasswordField NewPassword;
    @FXML private TextField Nickname;
    @FXML private TextField Username;
    @FXML private ComboBox<String> Year;
    @FXML public Label Error ;
    @Override public void check(String string) {}
    @Override public void MenuDetails() {}


    @FXML
    private void saveProfile(ActionEvent event) {
        UpdateMenu.soundEffectPlay(DataController.button3);
        ProfileMenu.changeProfileMenuController = this ;
        if(!preventNullText(Username.getText()).equals("") && !Username.getText().equals(player.getUsername())){
            profileMenu.check("change username " + Username.getText());
            if(!result.isSuccessful()){
                Error.setText(result.getMessage());
                return;
            }
        }
        if(!preventNullText(Nickname.getText()).equals("") && !Nickname.getText().equals(player.getNickname())){
            profileMenu.check("change nickname " + Nickname.getText());
            if(!result.isSuccessful()){
                Error.setText(result.getMessage());
                return;
            }
        }
        if(!preventNullText(Email.getText()).equals("") && !Email.getText().equals(player.getEmail())){
            profileMenu.check("change email " + Email.getText());
            if(!result.isSuccessful()){
                Error.setText(result.getMessage());
                return;
            }
        }
        if(!preventNullText(NewPassword.getText()).equals("") && !preventNullText(CurrentPassword.getText()).equals("")){
            profileMenu.check("change password " + CurrentPassword.getText() + " " + NewPassword.getText());
            if(!result.isSuccessful()){
                Error.setText(result.getMessage());
                return;
            }
        }
        if(!preventNullText(Gender.getValue()).equals("")){
            player.setGender(Gender.getValue());
        }
        if(!preventNullText(Date.getValue()).equals("")){
            player.setDateOfBirth(Date.getValue());
        }
        if(!preventNullText(Month.getValue()).equals("")){
            player.setMonthOfBirth(Month.getValue());
        }
        if(!preventNullText(Year.getValue()).equals("")){
            player.setYearOfBirth(Year.getValue());
        }
        Error.setText("Profile updated successfully");
        UpdateMenu.toProfileMenu();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player = App.getLoggedInUser() ;
        ObservableList<String> genderOptions = FXCollections.observableArrayList(
                "Male",
                "Female",
                "Rather not to say"
        );
        Gender.setItems(genderOptions);

        ObservableList<String> monthOptions = FXCollections.observableArrayList(
                "January" ,
                "February" ,
                "March" ,
                "April" ,
                "May" ,
                "June" ,
                "July" ,
                "August" ,
                "September" ,
                "October" ,
                "November" ,
                "December"
        );
        Month.setItems(monthOptions);

        ObservableList<String> dateOptions = FXCollections.observableArrayList(
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "10",
                "11",
                "12",
                "13",
                "14",
                "15",
                "16",
                "17",
                "18",
                "19",
                "20",
                "21",
                "22",
                "23",
                "24",
                "25",
                "26",
                "27",
                "28",
                "29",
                "30"
        );
        Date.setItems(dateOptions);

        ObservableList<String> yearOptions = FXCollections.observableArrayList(
                "1900",
                "1901",
                "1902",
                "1903",
                "1904",
                "1905",
                "1906",
                "1907",
                "1908",
                "1909",
                "1910",
                "1911",
                "1912",
                "1913",
                "1914",
                "1915",
                "1916",
                "1917",
                "1918",
                "1919",
                "1920",
                "1921",
                "1922",
                "1923",
                "1924",
                "1925",
                "1926",
                "1927",
                "1928",
                "1929",
                "1930",
                "1931",
                "1932",
                "1933",
                "1934",
                "1935",
                "1936",
                "1937",
                "1938",
                "1939",
                "1940",
                "1941",
                "1942",
                "1943",
                "1944",
                "1945",
                "1946",
                "1947",
                "1948",
                "1949",
                "1950",
                "1951",
                "1952",
                "1953",
                "1954",
                "1955",
                "1956",
                "1957",
                "1958",
                "1959",
                "1960",
                "1961",
                "1962",
                "1963",
                "1964",
                "1965",
                "1966",
                "1967",
                "1968",
                "1969",
                "1970",
                "1971",
                "1972",
                "1973",
                "1974",
                "1975",
                "1976",
                "1977",
                "1978",
                "1979",
                "1980",
                "1981",
                "1982",
                "1983",
                "1984",
                "1985",
                "1986",
                "1987",
                "1988",
                "1989",
                "1990",
                "1991",
                "1992",
                "1993",
                "1994",
                "1995",
                "1996",
                "1997",
                "1998",
                "1999",
                "2000",
                "2001",
                "2002",
                "2003",
                "2004",
                "2005",
                "2006",
                "2007",
                "2008",
                "2009",
                "2010",
                "2011",
                "2012",
                "2013",
                "2014",
                "2015",
                "2016",
                "2017",
                "2018",
                "2019",
                "2020",
                "2021",
                "2022",
                "2023",
                "2024"
        );
        Year.setItems(yearOptions);
        if(!player.getDateOfBirth().equals("")){
            Date.setPromptText(player.getDateOfBirth());
        }
        if(!player.getMonthOfBirth().equals("")){
            Month.setPromptText(player.getMonthOfBirth());
        }
        if(!player.getYearOfBirth().equals("")){
            Year.setPromptText(player.getYearOfBirth());
        }
        if(!player.getGender().equals("")){
            Gender.setPromptText(player.getGender());
        }
        Username.setText(player.getUsername());
        Nickname.setText(player.getNickname());
        Email.setText(player.getEmail());
    }
    private static String preventNullText(Object object){
        if(object==null){
            return "";
        }
        return (String) object;
    }
}
