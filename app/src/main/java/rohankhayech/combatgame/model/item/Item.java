/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.model.item;

import java.util.*;

/**
 * Abstract class representing an item in the game.
 * 
 * @author Rohan Khayech
 */
public abstract class Item 
{
    
    /* Name of the item. */
    protected String name;
    /** Cost of the item in gold.*/
    protected int cost;
    
    /**
     * Superclass constructor for Items.
     * 
     * @param name Name of the item.
     * @param cost Cost of the item.
     */
    public Item(String name, int cost)
    {
        this.name = name;
        this.cost = cost;
    }

    /** @return A copy of the specified item. */
    public abstract Item clone();

    /** @return The name of the item. */
    public String getName() { return name; }
    
    /** @return The cost of the item in gold. */
    public int getCost() { return cost; }
    
    /** @return The minimum effect of the item. */
    public abstract int getMinEffect();

    /** @return The maximum effect of the item. */
    public abstract int getMaxEffect();

    /** @return A String representation of the item. */
    public abstract String toString();

    /**
     * Returns a comparator that compares items by their cost.
     * @return The item comparator.
     */
    public static Comparator<Item> getCostComparator()
    {
        return new CostComparator();
    }

    /**
     * Comparator class that compares items by their cost.
     */
    private static class CostComparator implements Comparator<Item>
    {
        private CostComparator() {}
         
        @Override
        public int compare(Item item1, Item item2) {
            return item1.cost - item2.cost;
        }
    }
}