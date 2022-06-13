/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.model.item;

import rohankhayech.combatgame.view.Colour;

/**
 * Weapon enchantment that multiplies damage by 1.1.
 * 
 * @author Rohan Khayech
 */
public class Power extends EnchantmentItem {

    /** Name of the enchantment. */
    public static final String NAME = Colour.MAGENTA+"Power"+Colour.RESET;
    /** Cost of the enchantment. */
    public static final int COST = 10;
    /** Damage multiplier of the enchantment. */
    public static final double DAMAGE_MULT = 1.1;

    /**
     * Constructs a new Power enchantment item.
     */
    public Power() {
        super(NAME, COST);
    }

    /** @return A copy of the specified item. */
    public Item clone() {
        return new Power();
    }

    /** @return The minimum effect of the enchantment. */
    @Override
    public int getMinEffect() {
        return 0;
    }

    /** @return The maximum effect of the enchantment. */
    @Override
    public int getMaxEffect() {
        return 0;
    }

    /**
     * Returns the accumulative minimum damage of the base weapon and all its enchantments.
     * Multiplies the minimum damage of the next weapon. 
     * @param next The next weapon/decorator in the chain. 
     * @return The accumulative minimum damage of the weapon.
     */
    @Override
    public int getWeaponMinEffect(Weapon next) {
        return (int)Math.round(((double)next.getMinEffect()*DAMAGE_MULT));
    }

    /**
     * Returns the accumulative maximum damage of the base weapon and all its enchantments. 
     * Multiplies the maximum damage of the next weapon.
     * 
     * @param next The next weapon/decorator in the chain.
     * @return The accumulative maximum damage of the weapon.
     */
    @Override
    public int getWeaponMaxEffect(Weapon next) {
        return (int)Math.round(((double)next.getMaxEffect()*DAMAGE_MULT));
    }

    /**
     * Rolls a random damage value between the minimum and maximum damage of a weapon with this enchantment applied.
     * 
     * @param next The next weapon/decorator in the chain.
     * @return An integer representing the damage that the weapon inflicts.
     */
    @Override
    public int rollDamage(Weapon next) {
        return (int)Math.round(((double)next.rollDamage()*DAMAGE_MULT));
    }

    /**
     * Produces a String description of the enchantment.
     * 
     * @return A string desribing the enchantment.
     */
    @Override
    public String getDescription() {
        return Colour.BRIGHT_RED+"[x" + DAMAGE_MULT + " ATT]"+Colour.RESET;
    }

}