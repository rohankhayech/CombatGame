/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.model.item;

import rohankhayech.combatgame.view.Colour;

/**
 * Abstract class representing a Weapon item.
 * 
 * @author Rohan Khayech
 */
public abstract class Weapon extends Item {

    /**
     * Superclass constructor for Weapon objects.
     * 
     * @param name The name of the Weapon.
     * @param cost The cost of the Weapon in gold.
     */
    public Weapon(String name, int cost) 
    {
        super(name, cost);
    }

    /** @return A copy of the specified item. */
    public abstract Item clone();

    /** @return The total cost of the weapon including any enchantments. */
    @Override
    public abstract int getCost();

    /** @return The minimum damage of the weapon. */
    @Override 
    public abstract int getMinEffect(); 

    /** @return The maximum damage of the weapon. */
    @Override
    public abstract int getMaxEffect();

    /** @return The type of weapon. */
    public abstract String getType();
    
    /** @return The type of damage the weapon inflicts. */
    public abstract String getDamageType();    

    /**
     * Rolls a random damage value between the minimum and maximum damage of the weapon.
     * @return An integer representing the damage that the weapon inflicts.
     */
    public abstract int rollDamage();

    /**
     * Produces a String description of the weapon's attributes.
     * Used by toString() to get description of each decorator in the chain.
     * @return A String describing the items attributes.
     */
    protected abstract String getDescription();

    /** @return A String representation of the weapon. */
    @Override
    public String toString()
    {
        return getDescription() +" | "+Colour.BRIGHT_YELLOW+getCost()+"G"+Colour.RESET;
    }
}