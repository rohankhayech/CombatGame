/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.model.character;

/**
 * Informative class used to return the stats and description of an attack
 * event.
 * 
 * @author Rohan Khayech
 */

public class Attack
{
    /** Integer defining the damage to inflict */
    private int damage;
    /** String containing all attack information to be displayed. */
    private String desc;
   
    /**
     * Constructs a new Attack object with the specified damage.
     * @param damage The damage to inflict.
     */
    public Attack(int damage) 
    {
        this.damage = damage;
        this.desc = "";
    }
    
    /**
     * Constructs a new Attack object with the specified damage.
     * 
     * @param damage The damage to inflict.
     * @param desc Description of the attack information to be displayed.
     */
    public Attack(int damage, String desc)
    {
        this.damage = damage;
        this.desc = desc + "\n";
    }

    /**
     * @return Integer defining the damage to inflict.
     */
    public int getDamage() { return damage; }

    /**
     * @return String containing all attack information to be displayed.
     */
    public String getDescription() { return desc; }

    /**
     * Sets the damage of the Attack.
     * @param damage The damage to inflict
     */
    public void setDamage(int damage)
    {
        this.damage = damage;
    }

    /**
     * Adds a line describing an attack event to the description.
     * 
     * @param str The line to add.
     */
    public void addToDescription(String str)
    {
        desc += str + "\n";
    }

    /**
     * Merges the specified Attack object into this Attack.
     * @param att2 The attack to merge. 
     */
    public void mergeAttack(Attack att2)
    {
        damage += att2.getDamage();
        addToDescription(att2.getDescription());
    }
}