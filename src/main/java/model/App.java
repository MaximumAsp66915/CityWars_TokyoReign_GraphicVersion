package model;

import enums.Menu;

import java.util.HashMap;

public class App {

    public static final HashMap <String , Player> players = new HashMap<>();
    private static Admin admin = new Admin();
    private static Player loggedInUser = null;
    private static Menu currentMenu = Menu.RegisterMenu;

    public static Admin getAdmin() {
        return admin;
    }

    public static void setAdmin(Admin admin) {
        App.admin = admin;
    }

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }

    public static Player getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(Player loggedInUser) {
        App.loggedInUser = loggedInUser;
    }


}
