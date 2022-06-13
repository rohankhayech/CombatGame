/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.model.item;

import rohankhayech.combatgame.view.Colour;

/**
 * Weapon enchantment that adds 5 damage.
 * 
 * @author Rohan Khayech
 */
public class DamageV extends EnchantmentItem {

    /** Name of the enchantment. */
    public static final String NAME = Colour.RED+"Damage V"+Colour.RESET;
    /** Cost of the enchantment. */
    public static final int COST = 5;
    /** Damage increase of the enchantment. */
    public static final int DAMAGE_INC = 5;

    /**
     * Constructs a new Damage V enchantment item.
     */
    public DamageV() {
        super(NAME, COST);
    }

    /** @return A copy of the specified item. */
    public Item clone() {
        return new DamageV();
    }

    /** @return The minimum effect of the enchantment. */
    @Override
    public int getMinEffect() {
        return DAMAGE_INC;
    }

    /** @return The maximum effect of the enchantment. */
    @Override
    public int getMaxEffect() {
        return DAMAGE_INC;
    }

    /**
     * Returns the accumulative minimum damage of the base weapon and all its enchantments. Adds to the minimum damage of the next weapon.
     * 
     * @param next The next weapon/decorator in the chain.
     * @return The accumulative minimum damage of the weapon.
     */
    @Override
    public int getWeaponMinEffect(Weapon next) {
        return next.getMinEffect() + DAMAGE_INC;
    }

    /**
     * Returns the accumulative maximum damage of the base weapon and all its enchantments. Adds to the maximum damage of the next weapon.
     * 
     * @param next The next weapon/decorator in the chain.
     * @return The accumulative maximum damage of the weapon.
     */
    @Override
    public int getWeaponMaxEffect(Weapon next) {
        return next.getMaxEffect() + DAMAGE_INC;
    }

    /**
     * Rolls a random damage value between the minimum and maximum damage of a weapon with this enchantment applied.
     * 
     * @param next The next weapon/decorator in the chain.
     * @return An integer representing the damage that the weapon inflicts.
     */
    @Override
    public int rollDamage(Weapon next) {
        return next.rollDamage() + DAMAGE_INC;
    }

    /**
     * Produces a String description of the enchantment.
     * 
     * @return A string desribing the enchantment.
     */
    @Override
    public String getDescription() {
        return Colour.BRIGHT_RED + "[+" + DAMAGE_INC + " ATT]" + Colour.RESET;
    }

}