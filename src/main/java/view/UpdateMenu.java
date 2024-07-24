package view;

import controller.DataController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;
import model.App;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UpdateMenu {
    public static Stage stage ;
    private static List<String> pagePath = new ArrayList<>();
    private static boolean newRun = true ;
    public static MediaPlayer menuSong;
    public static MediaPlayer soundEffect;

    public static void toSignInMenu(){
        if(menuSong!=null)
            menuSong.stop();
        loadMenu("/FXML/SignInMenu.fxml");
        formingPagePath("/FXML/SignInMenu.fxml");
    }

    public static void toSignUpMenu(){
        loadMenu("/FXML/SignUpMenu.fxml");
        formingPagePath("/FXML/SignUpMenu.fxml");
    }

    public static void toSecurityMenu(){
        loadMenu("/FXML/SecurityMenu.fxml");
        formingPagePath("/FXML/SecurityMenu.fxml");
    }

    public static void toPasswordBuilderMenu(){
        loadMenu("/FXML/PasswordBuilderMenu.fxml");
        formingPagePath("/FXML/PasswordBuilderMenu.fxml");
    }

    public static void toForgotPasswordMenu(){
        loadMenu("/FXML/ForgotPasswordMenu.fxml");
        formingPagePath("/FXML/ForgotPasswordMenu.fxml");
    }

    public static void toMainMenu(){
        loadMenu("/FXML/MainMenu.fxml");
        formingPagePath("/FXML/MainMenu.fxml");
        menuSongPlay(DataController.MainMenu);
    }

    public static void toAdminMenu(){
        loadMenu("/FXML/AdminMenu.fxml");
        formingPagePath("/FXML/AdminMenu.fxml");
    }

    public static void toAdminCardMenu(){
        loadMenu("/FXML/AdminCardMenu.fxml");
        formingPagePath("/FXML/AdminCardMenu.fxml");
    }

    public static void toAdminPlayerMenu(){
        loadMenu("/FXML/AdminPlayerMenu.fxml");
        formingPagePath("/FXML/AdminPlayerMenu.fxml");
    }

    public static void toShopMenu(){
        loadMenu("/FXML/ShopMenu.fxml");
        formingPagePath("/FXML/ShopMenu.fxml");
        menuSongPlay(DataController.ShopMenu);
    }

    public static void toCardMenu(){
        loadMenu("/FXML/CardMenu.fxml");
        formingPagePath("/FXML/CardMenu.fxml");
    }

    public static void toProfileMenu(){
        loadMenu("/FXML/ProfileMenu.fxml");
        formingPagePath("/FXML/ProfileMenu.fxml");
    }

    public static void toChangeProfileMenu(){
        loadMenu("/FXML/ChangeProfileMenu.fxml");
        formingPagePath("/FXML/ChangeProfileMenu.fxml");
    }

    public static void toGameMenu(){
        loadMenu("/FXML/GameMenu.fxml");
        formingPagePath("/FXML/GameMenu.fxml");
    }

    public static void toGameField(){
        loadMenu("/FXML/GameField.fxml");
        formingPagePath("/FXML/GameField.fxml");
        menuSongPlay(DataController.GameField);
    }

    public static void toSettingsMenu(){
        loadMenu("/FXML/SettingsMenu.fxml");
        formingPagePath("/FXML/SettingsMenu.fxml");
    }

    public static void back(){
        try{
            for (String s : pagePath) System.out.println(s);
            FXMLLoader fxmlLoader = new FXMLLoader(SignInMenu.class.getResource(pagePath.get(pagePath.size()-2)));
            Scene scene = new Scene(fxmlLoader.load());
            String MenuName = (pagePath.get(pagePath.size()-2).replaceFirst("/FXML/" , "")).replaceAll("\\.fxml" , "") ;
            stage.setTitle(MenuName);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            pagePath.remove(pagePath.size()-1);
            if(newRun){
                DataController.readCards();
                DataController.readPlayers();
                newRun = false ;
            } else {
                DataController.updateData();
                if(SignInMenu.class.getResource("/media/"+MenuName) != null)
                    menuSongPlay(new Media(SignInMenu.class.getResource("/media/"+MenuName).toExternalForm()));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void loadMenu(String url){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(SignInMenu.class.getResource(url));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle((url.replaceFirst("/FXML/" , "")).replaceAll("\\.fxml" , ""));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            if(newRun){
                DataController.readCards();
                DataController.readPlayers();
                newRun = false ;
            } else {
                DataController.updateData();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private static void formingPagePath(String path){
        pagePath.add(path) ;
        for(int i=0 ; i< pagePath.size() ; i++){
            if(pagePath.get(i).equals(path)){
                while(pagePath.size()>i+1){
                    pagePath.remove(pagePath.size()-1);
                }
                break;
            }
        }
    }
    private static void menuSongPlay(Media media){
        if(menuSong == null){
            menuSong = new MediaPlayer(media);
            menuSong.play();
        } else if(!media.getSource().equals(menuSong.getMedia().getSource())) {
            menuSong.stop();
            menuSong = new MediaPlayer(media);
            menuSong.play();
        }
        menuSong.play();
        menuSong.setVolume(App.getLoggedInUser().getSoundVolume());
        stage.getScene().addPostLayoutPulseListener(new Runnable() {
            @Override
            public void run() {
                menuSong.setOnEndOfMedia(() -> {
                    menuSong.stop();
                    menuSong.play();
                });
            }
        });
    }
    public static void soundEffectPlay(Media media){
        if(soundEffect == null){
            soundEffect = new MediaPlayer(media);
            soundEffect.play();
        } else {
            soundEffect.stop();
            soundEffect = new MediaPlayer(media);
            soundEffect.play();
        }
        if(App.getLoggedInUser()==null){
            soundEffect.setVolume(1);
        } else {
            soundEffect.setVolume(App.getLoggedInUser().getEffectVolume());
        }
    }
}
