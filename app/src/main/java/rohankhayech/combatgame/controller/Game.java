/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.controller;

import java.util.*;

import rohankhayech.combatgame.model.character.*;
import rohankhayech.combatgame.model.item.*;
import rohankhayech.combatgame.view.*;
import rohankhayech.combatgame.view.View.HUD;

/**
 * Controller class responsible for running the game, including switching between the main menu and battles.
 * 
 * @author Rohan Khayech
 */
public class Game {

    /** The view for the game. */
    private View view;

    /** The main menu of the game. */
    private MainMenuController mMenu;

    /** The item shop. */
    private Shop shop;

    /** The player. */
    private Player plr;

    /** The factory object used to spawn enemies. */
    private EnemyFactory spawner;

    /**
     * Constructs a new Game object. All dependencies are injected here so the game can be tested/mocked.
     * 
     * @param mMenu The main menu controller.
     * @param shop The shop.
     * @param plr The player.
     * @param spawner The factory object used to spawn enemies.
     */
    public Game(MainMenuController mMenu, Shop shop, Player plr, EnemyFactory spawner) {
        this.mMenu = mMenu;
        this.shop = shop;
        this.plr = plr;
        this.spawner = spawner;
        this.view = View.getInstance();
    }

    /**
     * Initizilizes the game, loads the shop items from file using the specified
     * loader, sets up the view for the game and gives the player starter items.
     * @param loader The ItemLoader object.
     * @throws GameInitException if the shop item data could not be loaded.
     */
    public void init(ItemLoader loader) throws GameInitException {
        // Load the shop items from file and stock the shop inventory with them.
        loadShopItems(loader);

        // Setup the view
        setupGameView();

        // Give the player the cheapest weapon and armour from the shop.
        setStarterItems();

    }


    /**
     * Runs the game. This method in turn runs the main menu and starts battles when prompted.
     */
    public void run() {
        Boolean exit = false;

        while (!exit) {
            // Run the main menu.
            exit = mMenu.run();

            // Exit game if exit selected.

            if (!exit) {
                Battle battle = new Battle(plr, spawner);
                exit = battle.run();
            }

            // Exit game if player died.
        }
        
        //End the game.
        end();
    }

    /**
     * Ends the game and resets the view.
     */
    private void end() {
        try {
            view.setHUD(HUD.NONE);
        } catch (UninitializedViewException e) {
            view.println(e.getMessage());
        }
        view.setPlayer(null);
    }

    /**
     * Load the shop items from file and stock the shop inventory with them.
     * @param loader The ItemLoader object.
     * @throws GameInitException if the shop item data could not be loaded.
     */
    private void loadShopItems(ItemLoader loader) throws GameInitException {
        List<Item> shopItems = null;
        try {
            shopItems = loader.fileLoadItems();
        } catch (ItemLoadException e) {
            String msg = "Could not load shop item data:\n"+ e.getMessage();
            throw new GameInitException(msg);
        }

        shop.stockInventory(shopItems);
    }

    /**
     * Sets the view to display the main HUD and sets the specifed player in the view.
     */
    private void setupGameView() {
        
        view.setPlayer(plr);
        try {
            view.setHUD(HUD.MAIN);
        } catch (ViewException e2) {
            view.println(e2.toString());
        }
    }

    /**
     * Give the player the cheapest weapon and armour from the shop.
     */
    private void setStarterItems() {
        //Get the list of weapons from the shop and sort the list by cost.
        List<Weapon> w = shop.getInventory().getAllWeapons();
        w.sort(Item.getCostComparator());
        
        //Give the player the cheapest weapon and set it as their selected weapon.
        plr.giveItem(w.get(0));
        plr.setWeapon(w.get(0));

        //Get the list of armour from the shop and sort the list by cost.
        List<Armour> a = shop.getInventory().getAllArmour();
        a.sort(Item.getCostComparator());

        //Give the player the cheapest armour and set it as their selected armour.
        plr.giveItem(a.get(0));
        plr.setArmour(a.get(0));
    }

}