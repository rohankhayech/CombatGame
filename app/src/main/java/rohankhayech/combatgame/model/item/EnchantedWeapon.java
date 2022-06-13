/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.model.item;

/**
 * Wrapper class representing an enchantment applied to a weapon.
 * Wraps a Weapon object with an EnchantmentItem object.
 * 
 * @author Rohan Khayech
 */
public class EnchantedWeapon extends Weapon {

    /** The weapon to wrap. */
    private Weapon next;
    /** The enchantment wrapping the weapon. */
    private EnchantmentItem enchant;

    /**
     * Constructs a new EnchantedWeapon wrapper object.
     * 
     * @param next The weapon to wrap.
     * @param enchant The enchantment wrapping the object.
     */
    public EnchantedWeapon(Weapon next, EnchantmentItem enchant) {
        super(enchant.getName(), enchant.getCost());
        this.next = next;
        this.enchant = enchant;
    }

    /**
     * Constructs a copy of the specified EnchantedWeapon object.
     * 
     * @param next    The weapon to wrap.
     * @param enchant The enchantment wrapping the object.
     */
    private EnchantedWeapon(EnchantedWeapon w) {
        super(w.name, w.enchant.getCost());
        this.next = (Weapon)w.next.clone();
        this.enchant = w.enchant; //enchants are immutable
    }

    /** @return A copy of the specified item. */
    public Item clone() {
        return new EnchantedWeapon(this);
    }

    /** @return The type of the base weapon. */
    @Override
    public String getType() {
        return next.getType();
    }

    /** @return The type of damage that the base weapon inflicts. */
    @Override
    public String getDamageType() {
        return next.getDamageType();
    }

    /** @return The accumulated cost of the base weapon and all previous enchantments (including this one). */
    @Override
    public int getCost()
    {
        return cost + next.getCost();
    }

    /** @return The name of the base weapon. */
    @Override
    public String getName()
    {
        return next.getName();
    }

    /** @return The minimum damage of the weapon after enchantments are applied. */
    @Override
    public int getMinEffect()
    {
        return enchant.getWeaponMinEffect(next);
    }

    /** @return The maximum damage of the weapon after enchantments are applied. */
    @Override
    public int getMaxEffect()
    {
        return enchant.getWeaponMaxEffect(next);
    }

    /**
     * Rolls a random damage value between the minimum and maximum damage of the weapon.
     * 
     * @return An integer representing the damage that the weapon inflicts.
     */
    @Override
    public int rollDamage()
    {
        return enchant.rollDamage(next);
    }

    /**
     * Produces a String description of the weapon's attributes.
     *  Used by toString() to get description of each decorator in the chain.
     * 
     * @return A string desribing the weapon.
     */
    @Override
    protected String getDescription() 
    {
        return next.getDescription() + " | " + enchant.getName() + " "+ enchant.getDescription();
    }
}