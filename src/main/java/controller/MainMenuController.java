package controller;

import enums.Menu;
import model.App;
import model.Result;
import view.UpdateMenu;

public class MainMenuController {

    public Result logout() {
        App.setCurrentMenu(Menu.LoginMenu);
        App.setLoggedInUser(null);
        UpdateMenu.toSignInMenu();
        return new Result(true, "login menu: ");
    }

    public Result navigateToProfileMenu() {
        App.setCurrentMenu(Menu.ProfileMenu);
        UpdateMenu.toProfileMenu();
        return new Result(true, "profile menu:");
    }
    public Result navigateToShopMenu() {
        App.setCurrentMenu(Menu.ShopMenu);
        UpdateMenu.toShopMenu();
        return new Result(true, "shop menu:");
    }
    public Result navigateToGameMenu() {
        App.setCurrentMenu(Menu.GameMenu);
        UpdateMenu.toGameMenu();
        return new Result(true, "game menu:");
    }
    public void exit(){
        App.setCurrentMenu(Menu.Exit);
    }
}
