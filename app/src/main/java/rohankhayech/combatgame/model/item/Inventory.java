/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.model.item;

import java.util.*;

/**
 * Wrapper class for a collection of items with a fixed number of slots.
 * Contains methods to retrieve all items of a given subtype.
 * 
 * @author Rohan Khayech
 */
public class Inventory implements Iterable<Item>
{
    private List<Item> items;
    private int slots;

    /**
     * Constructs an empty Inventory object with the given amount of slots.
     * @param slots Maximum number of slots in the inventory.
     */
    public Inventory(int slots)
    {
        items = new LinkedList<Item>();
        this.slots = slots;
    }

    /**
     * Adds an item to the inventory if there is a free slot. 
     * @param i The Item to add.
     */
    public void addItem(Item i)
    {
        if (items.size()<slots)
        {
            items.add(i);
        }
    }

    /**
     * Adds all the items in the given list to the inventory.
     * If the inventory is or becomes full only a subset of the list equal
     * to the amount of free slots will be added.
     * @param newItems A list of Items to add.
     */
    public void addAll(List<Item> newItems)
    {
        int spacesLeft = Math.min(slots - items.size(), newItems.size());
        newItems = newItems.subList(0, spacesLeft-1);
        items.addAll(newItems);
    }

    /**
     * Removes the given item from the inventory if it exists. 
     * @param i The Item to remove.
     */
    public void removeItem(Item i)
    {
        items.remove(i);
    }

    /**
     * Removes the item at the specified index of the inventory.
     * @param index The index of the item to remove.
     * @return The item previously at the specified index.
     */
    public Item removeItem(int index)
    {
        return items.remove(index);
    }

    /**
     * @return An iterator over the Items in the inventory.
     */
    public Iterator<Item> iterator()
    {
        return items.iterator();
    }

    /**
     * @return A list of all the Items in the inventory.
     */
    public List<Item> getAllItems()
    {
        return List.copyOf(items);
    }

    /**
     * @return A list of all the Weapons in the inventory.
     */
    public List<Weapon> getAllWeapons()
    {
        List<Weapon> weapons = new ArrayList<Weapon>(); 
        for (Item i : items)
        {
            if(i instanceof Weapon)
            {
                weapons.add((Weapon)i);
            }
        }
        return weapons;
    }

    /**
     * @return A list of all the Armour in the inventory.
     */
    public List<Armour> getAllArmour() {
        List<Armour> armour = new ArrayList<Armour>();
        for (Item i : items) {
            if (i instanceof Armour) {
                armour.add((Armour) i);
            }
        }
        return armour;
    }

    /**
     * @return A list of all the Potions in the inventory.
     */
    public List<Potion> getAllPotions() {
        List<Potion> potions = new ArrayList<Potion>();
        for (Item i : items) {
            if (i instanceof Potion) {
                potions.add((Potion) i);
            }
        }
        return potions;
    }

    /**
     * @return A list of all the Enchantments in the inventory.
     */
    public List<EnchantmentItem> getAllEnchantments() {
        List<EnchantmentItem> enchantments = new ArrayList<EnchantmentItem>();
        for (Item i : items) {
            if (i instanceof EnchantmentItem) {
                enchantments.add((EnchantmentItem) i);
            }
        }
        return enchantments;
    }

    /**
     * Returns whether the inventory is full (out of free slots).
     * @return {@code true} when the inventory is full, {@code false} otherwise.
     */
    public boolean isFull()
    {
        return items.size() >= slots;
    }

    /**
     * Removes all the items from the inventory, resulting in an empty inventory.
     */
    public void clear()
    {
        items.clear();
    }
}