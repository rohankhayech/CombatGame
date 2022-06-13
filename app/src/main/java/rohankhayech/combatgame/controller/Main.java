/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.controller;

import rohankhayech.combatgame.model.character.*;
import rohankhayech.combatgame.model.item.*;
import rohankhayech.combatgame.view.*;
import rohankhayech.combatgame.view.menu.*;

/**
 * Main class for the program.
 * Runs the start menu.
 * 
 * @author Rohan Khayech
 */
public class Main {

    /**
     * Main line for the game program.
     * @param args Command line arguments.
     */
    public static void main(String[] args) 
    {
        try
        {
            //Initilize the view
            View.getInstance();
            
            // Read the filename from the command line argument if specified.
            // Then construct an Item Loader to load shop item data.
            ItemLoader loader;
            if (args.length >= 1) {
                loader = new ItemLoader(args[0]);
            } else {
                loader = new ItemLoader();
            }

            //Run the start menu, which in turn runs the game.
            runStartMenu(loader);
        } 
        catch (Exception e)
        {
            System.err.println(Colour.RED+"\nAn error occured causing the program to close." + Colour.RESET);
            System.err.println("Details:\n");
            e.printStackTrace(System.err);
        }

        System.out.println();
    }

    /**
     * Runs the start menu for the game. 
     * 
     * @param loader The item loader object.
     */
    private static void runStartMenu(ItemLoader loader) 
    {
        //Create the start menu
        Menu startMenu = new Menu("Start Menu",0,0);
        MenuReturn ret = null;

        //Add an option for starting the game.
        MenuOption startOption = new MenuOption("Start New Game");
        startOption.setCloseParent(true);
        startMenu.addItem(startOption);

        //Run the menu and perform the chosen action.
        do
        {
            ret = startMenu.select();
            if (ret.getSelection() == 1)
            {
                startNewGame(loader);
            }

        } while (ret.getSelection() != 2);

        System.out.println("Program exiting...");
    }

    /**
     * Initilizes and starts a new game.
     * @param loader The ItemLoader object.
     */
    private static void startNewGame(ItemLoader loader) 
    {
        //Initilize the game objects.
        Player plr = new Player("Player");
        PlayerController pc = new PlayerController(plr);
        Shop shop = new Shop();
        MainMenuController mMenu = new MainMenuController(plr, shop);
        EnemyFactory spawner = new EnemyFactory();

        //Construct a new game and pass in the dependencies.
        Game game = new Game(mMenu, shop, plr, spawner);
        try {
            game.init(loader);

            game.run();

        } catch (GameInitException e) {
            View view = View.getInstance();
            view.println(e.getMessage());
            view.println(Colour.RED+"Game failed to start."+Colour.RESET);
        }
    }
}