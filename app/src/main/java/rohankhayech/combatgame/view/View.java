/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.view;

import java.util.Scanner;

import rohankhayech.combatgame.controller.Battle;
import rohankhayech.combatgame.model.character.*;
import rohankhayech.combatgame.model.item.*;

/**
 * Display class that can display a HUD above menus and print other game information.
 * Implemented as a singleton, a reference to the current view can be obtained
 * by calling {@code View.getInstance()}.
 * 
 * @author Rohan Khayech
 */
public class View {
    
    /** Types of Heads-Up Display. */
    public enum HUD 
    {
        /** No HUD. Default option.*/ 
        NONE, 
        /** Displays player stats. */ 
        MAIN, 
        /** Displays battle information including player and enemy stats. */ 
        BATTLE, 
        /** Displays death HUD with minimal player stats. */ 
        DEAD, 
        /** Displays victory HUD with minimal player stats. */ 
        WON
    }

    /** Singleton object of the view. */
    private static View instance = null;

    /** The current HUD type displayed. */
    private HUD curHUD;
    /** The current message to be printed when the view is cleared. */
    private String message;
    /** The current player to be displayed. */
    private Player plr;
    /** The current battle to be displayed. */
    private Battle battle;

    /**
     * Returns a reference to the current {@code View} object 
     * or constructs it if it has not yet been initilized.
     * 
     * @return The current {@code View} object.
     */
    public static View getInstance()
    {
        if (instance == null)
        {
            instance = new View();
        }
        return instance;
    }

    /**
     * Constructs a new View object.
     */
    private View()
    {
        this.curHUD = HUD.NONE;
        this.message = "";
        this.plr = null;
        this.battle = null;
    }

    /**
     * Sets the current HUD for the view to display.
     * @param hud Type of HUD to display as defined in the {@code View.HUD} enum.
     * @throws UninitializedViewException When the specified HUD requires information that hasn't yet been initalized in the view.
     */
    public void setHUD(HUD hud) throws UninitializedViewException
    {
        switch (hud)
        {
            case MAIN: case DEAD: case WON:
                if (plr != null) {
                    this.curHUD = hud;
                }
                else throw new UninitializedViewException("Player must be set in the view before this HUD can display.");
            break;
            case BATTLE:
                if (plr != null && battle !=null) {
                    this.curHUD = hud;
                }
                else throw new UninitializedViewException("Player and Battle must be set in the view before this HUD can display.");
            break;
            default:
                this.curHUD = hud;
        }
    }

    /**
     * Prints the current HUD.
     */
    public void printHUD()
    {
        switch (curHUD)
        {
            case MAIN:
                printMainHUD();
            break;
            case BATTLE:
                printBattleHUD();
            break;
            case DEAD:
                printDeadHUD();
            break;
            case WON:
                printEndHUD();
            default:
        }
        System.out.println("\n");
    }

    /**
     * Prints the Main HUD.
     */
    private void printMainHUD() {
        System.out.println(plr.getName());
        //printHealthBar(plr.getHealth(), plr.getMaxHealth());
        System.out.println("HEALTH: "+Colour.BRIGHT_RED+plr.getHealth()+"/"+plr.getMaxHealth()+"HP"+Colour.RESET);
        System.out.println("GOLD: "+Colour.BRIGHT_YELLOW+plr.getGold()+Colour.RESET);
        System.out.println("ATT: "+Colour.BRIGHT_RED+plr.getMinAttack()+"-"+plr.getMaxAttack()+Colour.RESET);
        System.out.println("DEF: "+Colour.BRIGHT_CYAN+plr.getMinDefence()+"-"+plr.getMaxDefence()+Colour.RESET);
        printInventory();
    }

    /**
     * Prints the Battle HUD.
     */
    private void printBattleHUD() {
        System.out.println(plr.getName());
        //printHealthBar(plr.getHealth(),plr.getMaxHealth());
        System.out.println("HEALTH: "+Colour.BRIGHT_RED+plr.getHealth()+"/"+plr.getMaxHealth()+"HP"+Colour.RESET);
        System.out.println("GOLD: "+Colour.BRIGHT_YELLOW+plr.getGold()+Colour.RESET);
        System.out.println("ATT: "+Colour.BRIGHT_RED+plr.getMinAttack()+"-"+plr.getMaxAttack()+Colour.RESET);
        System.out.println("DEF: "+Colour.BRIGHT_CYAN+plr.getMinDefence()+"-"+plr.getMaxDefence()+Colour.RESET);
        System.out.println("");

        Enemy en = battle.getEnemy();
        System.out.println(en.getName());
        //printHealthBar(en.getHealth(), en.getMaxHealth());
        System.out.println("HEALTH: "+Colour.BRIGHT_RED+en.getHealth()+"/"+en.getMaxHealth()+"HP"+Colour.RESET);
        System.out.println("ATT: "+Colour.BRIGHT_RED+en.getMinAttack()+"-"+en.getMaxAttack()+Colour.RESET);
        System.out.println("DEF: "+Colour.BRIGHT_CYAN+en.getMinDefence()+"-"+en.getMaxDefence()+Colour.RESET);
    }

    /**
     * Prints the Dead HUD
     */
    private void printDeadHUD() {
        System.out.println(plr.getName());
        System.out.println(Colour.BRIGHT_RED + "DEAD" + Colour.RESET);
        System.out.println("FINAL GOLD SCORE: " + Colour.BRIGHT_YELLOW + plr.getGold() + Colour.RESET);
    }

    /**
     * Prints the End HUD
     */
    private void printEndHUD() {
        System.out.println(plr.getName());
        System.out.println(Colour.BRIGHT_YELLOW + "HERO, SLAYER OF DRAGONS" + Colour.RESET);
        System.out.println("FINAL GOLD SCORE: " + Colour.BRIGHT_YELLOW + plr.getGold() + Colour.RESET);
    }

    /**
     * Prints out the player's inventory and their chosen weapon and armour.
     */
    private void printInventory()
    {
        //Print out the chosen weapon and armour.
        System.out.println("\nChosen Weapon:");
        System.out.println(plr.getWeapon().toString());
        System.out.println("\nChosen Armour:");
        System.out.println(plr.getArmour().toString());
        
        //Print the remaining items.
        System.out.println("\nInventory:");
        for (Item i : plr.getInventory())
        {
            if (i != plr.getWeapon() && i != plr.getArmour())
                System.out.println(i.toString());
        }
    }

    /**
     * Prints a health bar.
     * @param health The current health.
     * @param maxHealth The maximum health (width of the health bar).
     */
    private void printHealthBar(int health, int maxHealth)
    {
        int i;
        //print out remaining health
        System.out.print("|"+Colour.RED);
        for (i=0; i<health; i++)
        {
            
            System.out.print("-");
            
        }
        System.out.print(Colour.RESET);

        //print out lost health
        for (i=health; i<maxHealth; i++)
        {
            System.out.print("-");
        }
        System.out.print("|");
        System.out.println();
    }

    /**
     * Sets the player to display in the view.
     * @param plr The player object.
     */
    public void setPlayer(Player plr) {
        this.plr = plr;
    }

    /**
     * Sets the battle to display in the view.
     * @param battle The battle object.
     */
    public void setBattle(Battle battle)
    {
        this.battle = battle;
    }

    /**
     * Prints out the description of the given attack.
     * @param att The attack to print.
     */
    public void printAttack(Attack att)
    {
        clear();
        System.out.println(att.getDescription());
        waitForUser();
    }

    /**
     * Prints out the description of the given defence.
     * @param def The defence to print.
     */
    public void printDefence(Defence def)
    {
        clear();
        System.out.println(def.getDescription());
        waitForUser();
    }

    /**
     * Prints a death message for the given character.
     * 
     * @param gChar The game character.
     */
    public void printDeathMessage(GameCharacter gChar)
    {
        System.out.println(gChar.getName() + " died, dropping " + Colour.BRIGHT_YELLOW + gChar.getGold() + "G" + Colour.RESET + ".");
        waitForUser();
    }

    /**
     * Prints a game over screen.
     */
    public void printGameOver()
    {
        try {
            setHUD(HUD.DEAD);
        } catch (ViewException e) {
            System.out.println(e.toString());
        }

        clear();
        System.out.println(Colour.BRIGHT_RED+"G A M E   O V E R"+Colour.RESET);
        waitForUser("end the game");
    }

    /**
     * Prints a game won screen.
     */
    public void printGameWon() {
        try {
            setHUD(HUD.WON);
        } catch (ViewException e) {
            System.out.println(e.toString());
        }

        clear();
        System.out.println(Colour.BRIGHT_GREEN + "G A M E   C O M P L E T E" + Colour.RESET);
        waitForUser();
    }

    /**
     * Adds the given text to the view message. 
     * This is printed below the HUD the next time the view updates/clears.
     * @param str The text to add.
     */
    public void print(String str)
    {
        message += str;
    }

    /**
     * Adds the given line of text to the view message. 
     * This is printed below the HUD the next time the view updates/clears.
     * @param str The line of text to add.
     */
    public void println(String str)
    {
        print(str+"\n");
    }

    /**
     * Prints and then resets the current view message.
     */
    private void printMessage()
    {
        System.out.println(message);
        message = "";
    }

    /**
     * Clears the screen and redraws the current HUD and view message.
     */
    public void clear()
    {
        for (int i=0;i<40;i++)
        {
            System.out.println("");
        }
        printHUD();
        printMessage();
    }

    /** 
     * Causes the program to wait until the user presses the ENTER key.
     */
    public void waitForUser()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Press ENTER to continue...");
        sc.nextLine();
    }

    /**
     * Causes the program to wait until the user presses the ENTER key.
     * 
     * @param action The action prompt displayed to the user. 
     * Displayed as "Press ENTER to {@code action}..."
     */
    public void waitForUser(String action) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Press ENTER to "+action+"...");
        sc.nextLine();
    }
}