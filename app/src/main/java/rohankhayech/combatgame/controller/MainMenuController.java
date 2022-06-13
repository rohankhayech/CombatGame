/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.controller;

import java.util.Scanner;

import rohankhayech.combatgame.model.character.Player;
import rohankhayech.combatgame.model.item.*;
import rohankhayech.combatgame.view.*;
import rohankhayech.combatgame.view.menu.*;

/**
 * Sets up the main menu and handles selections.
 * 
 * @author Rohan Khayech
 */
public class MainMenuController 
{
    private Player plr;
    private Shop shop;
    private View view;
    
    /** Constructs a new main menu controller. 
     * @param plr The player.
     * @param shop The shop.
     */
    public MainMenuController(Player plr, Shop shop) 
    {
        this.plr = plr;
        this.shop = shop;
        this.view = View.getInstance();
    }

	/**
     * Sets up and runs the main menu.
     * 
     * @return Boolean describing whether to exit the program.
     */
    public boolean run()
    {
        MenuReturn ret;

        //Setup Main Menu
        
        Menu startMenu = new Menu("Start Menu", 0, 0);

        //Create the Shop menu
        Menu shopMenu = setupShopMenu();
        startMenu.addItem(shopMenu);

        //Add an option to change character name.
        MenuOption nameOption = new MenuOption("Change Character Name");
        nameOption.addObserver(new MenuObserver() { //add option functionality
        
            @Override
            public void optionSelected(MenuReturn ret) {
                changeName();
            }
        });
        startMenu.addItem(nameOption);

        //Add the switch weapon menu
        Menu weaponMenu = setupWeaponMenu();
        startMenu.addItem(weaponMenu);

        //Add the switch armour menu
        Menu armourMenu = setupArmourMenu();
        startMenu.addItem(armourMenu);

        //Add the start game option
        MenuOption startOption = new MenuOption("Start Game");
        startOption.setCloseParent(true); //closes the menu
        startMenu.addItem(startOption);

        //Run the menu and get selection on exit
        ret = startMenu.select();

        int exitOption = ret.getSelection();
        switch (exitOption)
        {
            case 5: //start game selected
                return false;
            case 6: //exit game selected
                return true;
            default: 
                System.err.println("Incorrect option caused menu to close.");
                return true;
        }
    }

    /**
     * Sets up the sub-menu for the shop. 
     * @return The shop menu.
     */
    private Menu setupShopMenu()
    {
        //create the shop menu
        Menu shopMenu = new Menu("Go to Shop");

        //add the buy items menu
        Menu buyItems = setupBuyMenu();
        shopMenu.addItem(buyItems);

        //add the sell items menu
        Menu sellItems = setupSellMenu();
        shopMenu.addItem(sellItems);

        //add the enchant item menu
        Menu enchantMenu = setupEnchantMenu();
        shopMenu.addItem(enchantMenu);

        //set up an observer to watch when the menu opens/refreshes
        shopMenu.addObserver(new MenuObserver(){ 
        
            @Override
            public void optionSelected(MenuReturn ret) { 
                //update the name of the enchant option
                enchantMenu.setTitle("Enchant " + plr.getWeapon().getName());
            }
        });

        //return the created menu
        return shopMenu;
    }

    /**
     * Sets up the sub-menu for buying items from the shop.
     * 
     * @return The sub-menu.
     */
    private Menu setupBuyMenu()
    {
        //create the menu
        Menu buyItems = new Menu("Buy Items");

        //add an observer to populate the menu when opened/refreshed
        buyItems.addObserver(new MenuObserver() {

            @Override
            public void optionSelected(MenuReturn ret) {
                populateBuyMenu(buyItems);
            }
        });
        
        return buyItems;
    }

    /**
     * Sets up the sub-menu for selling items to the shop.
     * 
     * @return The sub-menu.
     */
    private Menu setupSellMenu()
    {
        //create the menu
        Menu sellItems = new Menu("Sell Items");
        
        // add an observer to populate the menu when opened/refreshed
        sellItems.addObserver(new MenuObserver() {
            @Override
            public void optionSelected(MenuReturn ret) {
                populateSellMenu(sellItems);
            }
        });
        return sellItems;
    }
    
    /**
     * Sets up the sub-menu for switching weapons.
     * 
     * @return The sub-menu.
     */
    private Menu setupWeaponMenu()
    {
        //create the menu
        Menu weaponMenu = new Menu("Switch Weapon");
        
        // add an observer to populate the menu when opened/refreshed
        weaponMenu.addObserver(new MenuObserver() {
            @Override
            public void optionSelected(MenuReturn ret) {
                populateWeaponMenu(weaponMenu);
            }
        });

        return weaponMenu;
    }

    /**
     * Sets up the sub-menu for switching armour.
     * 
     * @return The sub-menu.
     */
    private Menu setupArmourMenu() {
        //create the menu
        Menu armourMenu = new Menu("Switch Armour");
        
        // add an observer to populate the menu when opened/refreshed
        armourMenu.addObserver(new MenuObserver() {
            @Override
            public void optionSelected(MenuReturn ret) {
                populateArmourMenu(armourMenu);
            }
        });

        return armourMenu;
    }

    /**
     * Sets up the sub-menu for enchanting weapons.
     * 
     * @return The sub-menu.
     */
    private Menu setupEnchantMenu() {
        //create the menu
        Menu enchantMenu = new Menu("Enchant Weapon");

        // add an observer to populate the menu when opened/refreshed
        enchantMenu.addObserver(new MenuObserver() {
            @Override
            public void optionSelected(MenuReturn ret) {
                populateEnchantMenu(enchantMenu);
            }
        });

        return enchantMenu;
    }

    /**
     * Populates the shop menu with an option for each item in the shop.
     * 
     * @param buyItems The menu to populate.
     */
    private void populateBuyMenu(Menu buyItems)
    {
        buyItems.clear(); //clear the exisiting options

        //loop through all the items in the shop
        for (Item i : shop.getInventory().getAllItems()) {
            
            //add a menu option for the item
            MenuOption opt = buyItems.addOption(i.toString());
            
            //set the option to return the item when selected
            opt.setReturnObj(i);
            
            //add an observer to buy the item when the option is selected
            opt.addObserver(new MenuObserver() {
                @Override
                public void optionSelected(MenuReturn ret) {
                    shop.buyItem((Item) ret.getReturnObj(),plr);
                }
            });
        }
    }

    /**
     * Populates the sell items menu with an option for each item in the inventory.
     * 
     * @param sellItems The menu to populate.
     */
    private void populateSellMenu(Menu sellItems)
    {
        sellItems.clear(); // clear the exisiting options

        // loop through all the items in the inventory
        for (Item i : plr.getInventory().getAllItems()) {
            if (i != plr.getWeapon() && i != plr.getArmour()) { //if the current item is not the selected weapon/armour
                
                // add a menu option for the item
                MenuOption opt = sellItems.addOption(i.getName()+" | "+Colour.BRIGHT_YELLOW+shop.getSellPrice(i)+"G"+Colour.RESET);
                
                // set the option to return the item when selected
                opt.setReturnObj(i);
                
                // add an observer to sell the item when the option is selected
                opt.addObserver(new MenuObserver() {
                    @Override
                    public void optionSelected(MenuReturn ret) {
                        shop.sellItem((Item) ret.getReturnObj(),plr);
                    }
                });
            }
        }
    }

    /**
     * Populates the switch weapon menu with an option for each weapon in the inventory.
     * 
     * @param weaponMenu The menu to populate.
     */
    private void populateWeaponMenu(Menu weaponMenu)
    {
        weaponMenu.clear(); // clear the exisiting options

        // loop through all the weapons in the inventory
        for (Weapon w : plr.getInventory().getAllWeapons()) {

            // add a menu option for the item
            MenuOption opt = weaponMenu.addOption(w.toString());

            // set the option to return the weapon and close the menu when selected
            opt.setReturnObj(w);
            opt.setCloseParent(true);

            // add an observer to switch weapons when the option is selected
            opt.addObserver(new MenuObserver() {
                @Override
                public void optionSelected(MenuReturn ret) {
                    view.println("Switched to " + ((Weapon) ret.getReturnObj()).toString());
                    plr.setWeapon((Weapon) ret.getReturnObj());
                }
            });
        }
    }

    /**
     * Populates the switch armour menu with an option for each armour in the
     * inventory.
     * 
     * @param armourMenu The menu to populate.
     */
    private void populateArmourMenu(Menu armourMenu)
    {
        armourMenu.clear(); // clear the exisiting options

        // loop through all the armour in the inventory
        for (Armour a : plr.getInventory().getAllArmour()) {
            // add a menu option for the item
            MenuOption opt = armourMenu.addOption(a.toString());

            // set the option to return the armour and close the menu when selected
            opt.setReturnObj(a);
            opt.setCloseParent(true);

            // add an observer to switch armour when the option is selected
            opt.addObserver(new MenuObserver() {
                @Override
                public void optionSelected(MenuReturn ret) {
                    plr.setArmour((Armour) ret.getReturnObj());
                }
            });
        }
    }

    /**
     * Populates the enchant weapon menu with an option for each enchantment in the
     * inventory.
     * 
     * @param enhantMenu The menu to populate.
     */
    private void populateEnchantMenu(Menu enchantMenu) {

        enchantMenu.clear(); //clear the existing options

        // loop through all the enchantments in the inventory
        for (EnchantmentItem e : plr.getInventory().getAllEnchantments()) {
            // add a menu option for the item
            MenuOption opt = enchantMenu.addOption("Enchant with "+e.toString());

            // set the option to return the enhantment and close the menu when selected
            opt.setReturnObj(e);

            // add an observer to enchant weapon when the option is selected
            opt.addObserver(new MenuObserver() {
                @Override
                public void optionSelected(MenuReturn ret) {
                    view.println("Enchanted "+plr.getWeapon().getName()+" with " + ((EnchantmentItem) ret.getReturnObj()).toString());
                    plr.enchantWeapon((EnchantmentItem)ret.getReturnObj());
                }
            });
        }
    }

    /**
     * Takes user input to change the player's name.
     */
    private void changeName()
    {
        
        String name = "";
        while (name.equals(""))
        {
            view.clear();
            Scanner sc = new Scanner(System.in);
            System.out.println("What's your name?");
            name = sc.nextLine();
        }
        plr.setName(name);
        view.println("Changed name to "+name+".");
    }
}