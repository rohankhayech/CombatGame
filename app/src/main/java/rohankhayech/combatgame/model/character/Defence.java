/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.model.character;

/**
 * Informative class used to return the stats and description of a defence
 * event.
 * 
 * @author Rohan Khayech
 */
public class Defence
{
    /** Integer defining the damage taken after defence. */
    private int damage;
    /** Integer defining defence applied. */
    private int defence;
    /** String containing all defence information to be displayed. */
    private String desc;
    
    /**
     * Constructs a new Defence object.
     * 
     * @param damage The damage taken after defence.
     * @param defence The defence applied.
     * @param desc Description of the defence information.
     */
    public Defence(int damage, int defence, String desc)
    {
        this.damage = damage;
        this.defence = defence;
        this.desc = desc + "\n";
    }
    
    /**
     * @return Integer defining the damage taken after defence
     */
    public int getDamage() {
        return damage;
    }

    /**
     * @return Integer defining defence applied.
     */
    public int getDefence() {
        return defence;
    }

    /**
     * @return String containing all attack information to be displayed.
     */
    public String getDescription() {
        return desc;
    }

    /**
     * Sets the damage taken after defence is applied.
     * 
     * @param damage The damage taken.
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Sets the defence applied.
     * 
     * @param defence The defence applied.
     */
    public void setDefence(int defence) {
        this.defence = defence;
    }

    /**
     * Adds a line describing an attack event to the description.
     * 
     * @param str The line to add.
     */
    public void addToDescription(String str) {
        desc += str + "\n";
    }
}