package view;

import enums.Menu;
import model.App;
import model.Player;
import model.cards.Spell;
import model.cards.SpellType;
import model.cards.Troop;

import java.util.Scanner;

public class AppView {

    public void run() {
        do {
            App.getCurrentMenu().showCurrentMenuDetails();
            App.getCurrentMenu().checkCommand();
        } while (App.getCurrentMenu() != Menu.Exit);
    }
}
