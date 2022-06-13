/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.model.item;

import rohankhayech.combatgame.view.Colour;

/** Abstract class representing an enchantment in item form. Can be used as a decorator in an EnchantedItem wrapper object.
 * 
 * @author Rohan Khayech 
*/
public abstract class EnchantmentItem extends Item {

    /**
     * Superclass constructor for enchantment items.
     * @param name Name of the enchantment item.
     * @param cost Cost of the enchantment item.
     */
    public EnchantmentItem(String name, int cost) {
        super(name, cost);
    }

    /** @return A copy of the specified item. */
    public abstract Item clone();
    
    /** @return The minimum effect of the enchantment. */
    @Override
    public abstract int getMinEffect();

    /** @return The maximum effect of the enchantment. */
    @Override
    public abstract int getMaxEffect();

    /** @return A String representation of the enchantment item. */
    @Override
    public String toString()
    {
        return name + " " + getDescription() +" | "+Colour.BRIGHT_YELLOW+getCost()+"G"+Colour.RESET;
    }

    /**
     * Returns the accumulative minimum damage of the base weapon and all its enchantments.
     * Modifies the minimum damage of the next weapon. 
     * @param next The next weapon/decorator in the chain. 
     * @return The accumulative minimum damage of the weapon.
     */
    public abstract int getWeaponMinEffect(Weapon next);

    /**
     * Returns the accumulative maximum damage of the base weapon and all its enchantments. 
     * Modifies the minimum damage of the next weapon.
     * 
     * @param next The next weapon/decorator in the chain.
     * @return The accumulative maximum damage of the weapon.
     */
    public abstract int getWeaponMaxEffect(Weapon next);

    /**
     * Rolls a random damage value between the minimum and maximum damage of a weapon with this enchantment applied.
     * 
     * @param next The next weapon/decorator in the chain.
     * @return An integer representing the damage that the weapon inflicts.
     */
    public abstract int rollDamage(Weapon next);

    /**
     * Produces a String description of the enchantment.
     * Used by toString() to get description of each decorator in the chain.
     * 
     * @return A string desribing the enchantment.
     */
    public abstract String getDescription();

}
