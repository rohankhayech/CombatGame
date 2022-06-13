/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.model.character;

import rohankhayech.combatgame.controller.Randomizer;

/**
 * Abstract class defining an enemy character in the game.
 * 
 * @author Rohan Khayech
 */
public abstract class Enemy extends GameCharacter
{   
    /**
     * Superclass constructor for all enemy characters.
     * @param name Name of the enemy.
     */
    public Enemy(String name)
    {
        super(name);
    }
    
    /**
     * Performs an attack with the enemy.
     * @return An {@code Attack} object containing the damage and description of the attack.
     */
    @Override
    public Attack attack()
    {
        notifyOnAttack(); //nothing currently observes an enemies attack, but added here for further extensibility

        Randomizer rand = new Randomizer();
        
        int damage = rand.randomRange(getMinAttack(), getMaxAttack());
        Attack att = new Attack(damage, name+" attacks!");

        att = specialAbility(att);

        att.addToDescription(name+" attacked, dealing "+att.getDamage()+"DP.");

        return att;
    }

    /** @return The minimum defence of the enemy. */
    @Override
    public abstract int getMinDefence();

    /** @return The maximum defence of the enemy. */
    @Override
    public abstract int getMaxDefence();
    
    /** @return The minimum damage the enemy can inflict. */
    @Override
    public abstract int getMinAttack();

    /** @return The maximum damage the enemy can inflict. */
    @Override
    public abstract int getMaxAttack();

    /** @return The maximum health of the enemy. */
    @Override
    public abstract int getMaxHealth();

    /** @return The amount of gold the enemy drops on death. */
    @Override
    public abstract int getGold();

    /**
     * Perform the enemy's special ability.
     * 
     * @param att The enemy's attack to modify.
     * @return The enemy's attack with the special ability applied.
     */
    protected abstract Attack specialAbility(Attack att);
}