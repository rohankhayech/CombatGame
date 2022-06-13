/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.model.item;

import rohankhayech.combatgame.controller.Randomizer;
import rohankhayech.combatgame.view.Colour;

/** 
 * Weapon enchantment that adds 5-10 damage.
 * 
 * @author Rohan Khayech
 */
public class FireDamage extends EnchantmentItem {

    /** Name of the enchantment. */
    public static final String NAME = Colour.YELLOW+"Fire Damage"+Colour.RESET;
    /** Cost of the enchantment. */
    public static final int COST = 20;
    /** Minimum damage increase of the enchantment. */
    public static final int MIN_DAMAGE_INC = 5;
    /** Maximum damage increase of the enchantment. */
    public static final int MAX_DAMAGE_INC = 10;

    /**
     * Constructs a new Fire Damage enchantment item.
     */
    public FireDamage() {
        super(NAME, COST);
    }

    /** @return A copy of the specified item. */
    public Item clone() {
        return new FireDamage();
    }

    /** @return The minimum effect of the enchantment. */
    @Override
    public int getMinEffect() {
        return MIN_DAMAGE_INC;
    }

    /** @return The maximum effect of the enchantment. */
    @Override
    public int getMaxEffect() {
        return MAX_DAMAGE_INC;
    }

    /**
     * Returns the accumulative minimum damage of the base weapon and all its enchantments.
     * Adds to the minimum damage of the next weapon. 
     * @param next The next weapon/decorator in the chain. 
     * @return The accumulative minimum damage of the weapon.
     */
    @Override
    public int getWeaponMinEffect(Weapon next) {
        return next.getMinEffect() + MIN_DAMAGE_INC;
    }

    /**
     * Returns the accumulative maximum damage of the base weapon and all its enchantments. 
     * Adds to the maximum damage of the next weapon.
     * 
     * @param next The next weapon/decorator in the chain.
     * @return The accumulative maximum damage of the weapon.
     */
    @Override
    public int getWeaponMaxEffect(Weapon next) {
        return next.getMaxEffect() + MAX_DAMAGE_INC;
    }

    /**
     * Rolls a random damage value between the minimum and maximum damage of a weapon with this enchantment applied.
     * 
     * @param next The next weapon/decorator in the chain.
     * @return An integer representing the damage that the weapon inflicts.
     */
    @Override
    public int rollDamage(Weapon next) {
        Randomizer rand = new Randomizer();
        return next.rollDamage() + rand.randomRange(MIN_DAMAGE_INC,MAX_DAMAGE_INC);
    }

    /**
     * Produces a String description of the enchantment. 
     * 
     * @return A string desribing the enchantment.
     */
    @Override
    public String getDescription() {
        return Colour.BRIGHT_RED+"[+"+MIN_DAMAGE_INC+"-"+MAX_DAMAGE_INC+" ATT]"+Colour.RESET;
    }

}