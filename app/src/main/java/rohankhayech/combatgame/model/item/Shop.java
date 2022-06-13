/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.model.item;

import java.util.List;

import rohankhayech.combatgame.model.character.Player;
import rohankhayech.combatgame.view.*;

/**
 * Class representing the shop. Includes functions to buy and sell items from/to the shop and list items for sale.
 * 
 * @author Rohan Khayech
 */
public class Shop {

    /** Percentage of an items cost that is given when sold to the shop. */
    public static final double SELL_RATE = 0.5;
    
    /** The shops inventory of listed items for sale. */
    Inventory inv;

    /**
     * Constructs a new Shop object.
     */
    public Shop()
    {
        inv = new Inventory(100);
    }

    /**
     * Replaces the currently listed sale items with the items in the specified list.
     * @param items List of items to list for sale.
     */
    public void stockInventory(List<Item> items)
    {
        inv.clear();
        inv.addAll(items);
        inv.addItem(new DamageII());
        inv.addItem(new DamageV());
        inv.addItem(new FireDamage());
        inv.addItem(new Power());
    }

    /**
     * Buys an item from the store if the given player has enough gold and inventory space.
     * @param i The item to buy.
     * @param plr The player buying the item.
     * @return {@code true} if the purchase was successful, {@code false} otherwise.
     */
    public boolean buyItem(Item i, Player plr)
    {
        boolean sold = false;

        /** Obtain a reference to the view. */
        View view = View.getInstance();

        if (plr.getGold()>=i.getCost())
        {
            if (plr.hasInventorySpace())
            {
                plr.modifyGold(-i.getCost());
                plr.giveItem(i.clone()); //give the player a copy so any duplicates are seperate items.
                sold = true;
                view.println("Bought "+i.getName());
            }
            else
            {
                view.println("Not enough inventory space. Sell an item to make room.");
            }
        } 
        else
        {
            view.println("Not enough gold.");
        }
        return sold;
    }

    /**
     * Sells an item to the shop, removing it from the given player's inventory.
     * @param i The item to sell.
     * @param plr The player selling the item.
     */
    public void sellItem(Item i, Player plr)
    {
        /** Obtain a reference to the view. */
        View view = View.getInstance();
        
        plr.takeItem(i);
        plr.modifyGold(getSellPrice(i));
        view.println("Sold " + i.getName()+". It can be bought back from the shop.");
    }

    /**
     * Calculates the price an item can be sold to the shop for.
     * @param i The item to sell.
     * @return The sell price of the item, equal to half its cost, rounded down.
     */
    public int getSellPrice(Item i)
    {
        return (int)Math.floor((double)i.getCost()*SELL_RATE);
    }

    /**
     * @return The inventory of the shop.
     */
    public Inventory getInventory()
    {
        return inv;
    }
}