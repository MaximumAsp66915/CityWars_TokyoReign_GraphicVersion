package controller;

import enums.Menu;
import model.App;
import model.Result;
import view.UpdateMenu;

public class AdminMenuController {//changed by mohammad

    public Result navigateToCardMenu() {
        App.setCurrentMenu(Menu.AdminCardMenu);
        App.setLoggedInUser(null);
        UpdateMenu.toAdminCardMenu();
        return new Result(true, "card menu: ");
    }

    public Result navigateToPlayerMenu() {
        App.setCurrentMenu(Menu.AdminPlayerMenu);
        App.setLoggedInUser(null);
        UpdateMenu.toAdminPlayerMenu();
        return new Result(true, "player menu:");
    }
    public Result logout() {
        App.setCurrentMenu(Menu.LoginMenu);
        App.setLoggedInUser(null);
        UpdateMenu.toSignInMenu();
        return new Result(true, "login menu: ");
    }
    public void exit(){
        App.setCurrentMenu(Menu.Exit);
    }
}
