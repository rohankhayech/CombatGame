/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.model.character;

import rohankhayech.combatgame.model.item.*;

/**
 * Class representing a player character.
 * 
 * @author Rohan Khayech
 */
public class Player extends GameCharacter
{
    /** Maximum health of the player. */
    public static final int MAX_HEALTH = 30;
    /** Initial amount of gold the player starts with. */
    public static final int INIT_GOLD = 100;
    
    /** The player's inventory. */
    private Inventory inv;
    /** The current selected weapon. */
    private Weapon weapon;
    /** The current selected armour. */
    private Armour armour;
    /** The player's current amount of gold. */
    private int gold;
    
    /** The next attack chosen by the user.*/
    private Attack nextAttack; 

    /**
     * Constructs a new Player character.
     * 
     * @param name The player's name.
     */
    public Player(String name) {
        super(name);
        gold = INIT_GOLD;
        inv = new Inventory(15);
        
        //Set a placeholder weapon and item. These should be replaced by calling class but prevent the Player
        //being left in an invalid state if it doesn't.
        weapon = new GenericWeapon("Default Sword", 0, 0, 0, "Sword", "Useless");
        armour = new Armour("Default Armour", 0, 0, 0, "Scraps");

    }

    /**
     * Performs an attack chosen by the player.
     * @return An {@code Attack} object containing the damage and description of the attack.
     */
    @Override
    public Attack attack() {
        
        notifyOnAttack(); 
        //This notifies the player controller to run the player attack menu.
        //The player controller then takes the user choice, and calls setNextAttack(),
        //passing in the chosen attack. nextAttack can then be returned as below. 

        return nextAttack;
    }

    /** @return The minimum defence of the player's current armour. */
    @Override
    public int getMinDefence() { return armour.getMinEffect(); }

    /** @return The maximum defence of the player's current armour. */
    @Override
    public int getMaxDefence() { return armour.getMaxEffect(); }

    /** @return The maximum health of the player. */
    @Override
    public int getMaxHealth() { return MAX_HEALTH; }

    /** @return The minimum damage the player's current weapon can inflict. */
    @Override
    public int getMinAttack() { return weapon.getMinEffect(); }

    /** @return The maximum damage the player's current weapon can inflict. */
    @Override
    public int getMaxAttack() { return weapon.getMaxEffect(); }

    /** @return The current amount of gold the character holds. */
    @Override
    public int getGold() { return gold; }

    /** @return The player's current weapon. */
    public Weapon getWeapon() { return weapon; }

    /** @return The playe's current armour. */
    public Armour getArmour() { return armour; }

    /**
     * Increases the player's gold by the given amount.
     * If the amount is negative it will subtract that amount of gold.
     * @param amount The amount of gold to add.
     */
    public void modifyGold(int amount)
    {
        gold+=amount;
    }

    /**
     * Adds the specified item to the player's inventory.
     * @param i The item to give the player.
     */
    public void giveItem(Item i)
    {
        inv.addItem(i);
    }

    /**
     * Removes the given item from the player's inventory if it exists.
     * @param i The item to take from the player.
     */
    public void takeItem(Item i)
    {
        inv.removeItem(i);
    }

    /**
     * Returns whether the player has avaliable inventory space.
     * @return {@code true} if the player has a free slot in their inventory, {@code false} if the inventory is full. 
     */
    public boolean hasInventorySpace()
    {
        return !inv.isFull();
    }

    /** @return The player's inventory. */
    public Inventory getInventory() { return inv; }

    /**
     * Sets the name of the player.
     * @param n The player's new name.
     */
    public void setName(String n)
    {
        name = n; 
    }

    /**
     * Sets the player's current weapon.
     * @param w The weapon to use.
     */
    public void setWeapon(Weapon w)
    {
        weapon = w; 
    }

    /**
     * Sets the player's current armour.
     * @param a The armour to use.
     */
    public void setArmour(Armour a) {
        armour = a;
    }

    /**
     * Enchants the player's current weapon with the specified enchantment.
     * @param enchant The enchantment.
     */
    public void enchantWeapon(EnchantmentItem enchant)
    {
        Weapon enWeapon = new EnchantedWeapon(weapon, enchant);
        inv.removeItem(enchant);
        inv.removeItem(weapon);
        inv.addItem(enWeapon);
        setWeapon(enWeapon);
    }

    /**
     * Sets the next attack for the player to perform.
     * Should only be called by PlayerController
     * after getting user input on attack choice.
     * 
     * @param attack The attack to perform.
     */
    public void setNextAttack(Attack attack) 
    {
        this.nextAttack = attack;
	}

    
}