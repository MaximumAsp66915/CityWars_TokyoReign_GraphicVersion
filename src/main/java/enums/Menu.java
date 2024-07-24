package enums;

import controller.DataController;
import view.*;

import java.util.Scanner;

public enum Menu {
    ProfileMenu(new ProfileMenu()),
    ShopMenu(new ShopMenu()),
    CardMenu(new CardMenu()),
    MainMenu(new MainMenu()),
    GameMenu(new GameMenu()),
    AdminMenu(new AdminMenu()),
    ForgotPasswordMenu(new ForgotPasswordMenu()),
    LoginMenu(new SignInMenu()),
    PasswordBuilderMenu(new PasswordBuilderMenu()),
    SecurityMenu(new SecurityMenu()),
    RegisterMenu(new SignUpMenu()),
    AdminCardMenu(new AdminCardMenu()),
    AdminPlayerMenu(new AdminPlayerMenu()),
    Exit(new ExitMenu());

    private final AppMenu menu;
    Menu(AppMenu menu) {
        this.menu = menu;
    }

    public void checkCommand() {
        this.menu.check("");
    }

    public void showCurrentMenuDetails() {
        this.menu.MenuDetails();
    }
}
