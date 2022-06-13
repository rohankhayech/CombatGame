/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.model.character;

import java.util.*;

import rohankhayech.combatgame.controller.Randomizer;
import rohankhayech.combatgame.view.Colour;

/**
 * Abstract class defining a character in the game.
 * 
 * @author Rohan Khayech
 */
public abstract class GameCharacter
{
    /** Current health of the character. */
    protected int health;
    /** Name of the character. */
    protected String name;
    /** List of observers watching this character. */
    protected List<CharacterObserver> observers;

    /**
     * Superclass constructor for all game characters.
     * @param name The name of the character.
     */
    public GameCharacter(String name)
    {
        this.name = name;
        this.health = getMaxHealth();
        this.observers = new ArrayList<CharacterObserver>();
    }

    //Accessors

    /**
     * @return The character's health.
     */
    public int getHealth() { return health; }
    /**
     * @return The character's name.
     */
    public String getName() { return name; }
    
    /** @return The minimum defence of the character. */
    public abstract int getMinDefence();

    /** @return The maximum defence of the character. */
    public abstract int getMaxDefence();

    /** @return The minimum damage the character can inflict. */
    public abstract int getMinAttack();

    /** @return The maximum damage the character can inflict. */
    public abstract int getMaxAttack();

    /** @return The maximum health of the character. */
    public abstract int getMaxHealth();

    /** @return The current amount of gold the character holds. */
    public abstract int getGold();

    /**
     * Performs an attack with the character.
     * @return An {@code Attack} object containing the damage and description of the attack.
     */
    public abstract Attack attack();

    /**
     * Defends against an attack with the specified damage.
     * 
     * @param damage The damage to inflict on the character.
     * @return A {@code Defence} object containing the damage taken, and the amount and description of the defence applied.
     */
    public Defence defend(int damage)
    {
        Randomizer rand = new Randomizer();
        
        int defence = rand.randomRange(getMinDefence(), getMaxDefence());
        int damageTaken = Math.max(0, damage - defence);
        modifyHealth(damageTaken*-1);

        Defence def = new Defence(damage, defence, name+" deflected "+defence+"DP.");
        def.addToDescription(name+" lost "+Colour.RED+damageTaken+"HP"+Colour.RESET+".");

        return def;
    }

    /**
     * Kill the character.
     * Notifies any observers of the death.
     */
    protected void die()
    {
        for (CharacterObserver ob : observers)
        {
            ob.onDeath(this);
        }
    }

    /**
     * Notifies any observers of the character attacking.
     */
    protected void notifyOnAttack() {
        for (CharacterObserver ob : observers) {
            ob.onAttack(this);
        }
    }

    /**
     * Modifies the the character's health by the given amount.
     * This should only be used directly to grant the player health.
     * If the character is taking damage {@code defend()} should be used instead.
     * 
     * @param amount The amount of health to increase by.
     */
    public void modifyHealth(int amount) 
    {
        health = health + amount;

        if (health <= 0)
        {
            health = 0;
            die();
        } 
        else if (health > getMaxHealth()) 
        {
            health = getMaxHealth();
        }
    }

    /**
     * Adds an observer to notify on certain character events.
     * @param ob The observer to add.
     */
    public void addObserver(CharacterObserver ob)
    {
        observers.add(ob);
    }

    /**
     * Removes the given observer from this character.
     * 
     * @param ob The observer to remove.
     */
    public void removeObserver(CharacterObserver ob) {
        observers.remove(ob);
    }
}