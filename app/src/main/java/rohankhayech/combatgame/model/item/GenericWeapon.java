/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.model.item;

import rohankhayech.combatgame.controller.Randomizer;
import rohankhayech.combatgame.view.Colour;

/** 
 * Class representing a base Weapon item. 
 * 
 * @author Rohan Khayech
 */
public class GenericWeapon extends Weapon 
{

    /** Type of weapon. */
    private String type;
    /** Type of damage the weapon inflicts. */
    private String damageType;
    /** Minimum damage of the weapon. */
    private int minEffect;
    /** Maximum damage of the weapon. */
    private int maxEffect;

    /** Constructs a new Generic Weapon item.
     * 
     * @param name The name of the weapon.
     * @param cost The cost of the weapon in gold.
     * @param minEffect The minimum damage of the weapon.
     * @param maxEffect The maximum damage of the weapon.
     * @param type The type of weapon.
     * @param damageType The type of damage the weapon inflicts.
     */
    public GenericWeapon(String name, int cost, int minEffect, int maxEffect, String type, String damageType) 
    {
        super(name, cost);
        this.type = type;
        this.minEffect = minEffect;
        this.maxEffect = maxEffect;
        this.damageType = damageType;
    }

    /**
     * Constructs a copy of the specified Generic Weapon item.
     * 
     * @param w The weapon to copy.
     */
    private GenericWeapon(GenericWeapon w) {
        super(w.getName(), w.getCost());
        this.type = w.getType();
        this.minEffect = w.getMinEffect();
        this.maxEffect = w.getMaxEffect();
        this.damageType = w.getDamageType();
    }

    /** @return A copy of the specified item. */
    public Item clone() {
        return new GenericWeapon(this);
    }

    /** 
     * Produces a String description of the weapon's attributes. 
     * @return A string desribing the weapon.
     */
    @Override
    protected String getDescription() 
    {
        return name+" | "+damageType+" "+type+" | "+Colour.BRIGHT_RED+"ATT: "+minEffect+"-"+maxEffect+Colour.RESET;
    }

    /** @return The type of weapon. */
    @Override
    public String getType() { return type; }

    /** @return The type of damage the weapon inflicts. */
    @Override
    public String getDamageType() { return damageType; }

    /** @return The cost of the weapon. */
    @Override
    public int getCost() { return cost; }

    /** @return The minimum damage of the weapon. */
    @Override
    public int getMinEffect() { return minEffect; }

    /** @return The maximum damage of the weapon. */
    @Override
    public int getMaxEffect() { return maxEffect; }

    /**
     * Rolls a random damage value between the minimum and maximum damage of the weapon.
     * 
     * @return An integer representing the damage that the weapon inflicts.
     */
    @Override
    public int rollDamage() 
    {
        Randomizer rand = new Randomizer();
        return rand.randomRange(minEffect, maxEffect);
    }    
}