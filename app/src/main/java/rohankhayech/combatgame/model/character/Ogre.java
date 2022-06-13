/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.model.character;

import rohankhayech.combatgame.controller.Randomizer;
import rohankhayech.combatgame.view.Colour;

/**
 * Class representing a Ogre enemy.
 * 
 * @author Rohan Khayech
 */
public class Ogre extends Enemy
{
    /** Name of the enemy. */
    public static final String NAME = Colour.CYAN+"Ogre"+Colour.RESET;
    /** Minimum defence of a ogre. */
    public static final int MIN_DEFENCE = 6;
    /** Maximum defence of a ogre. */
    public static final int MAX_DEFENCE = 12;
    /** Maximum health of a ogre. */
    public static final int MAX_HEALTH = 40;
    /** Minimum damage a ogre can inflict. */
    public static final int MIN_ATTACK = 5;
    /** Maximum damage a ogre can inflict. */
    public static final int MAX_ATTACK = 10;
    /** Amount of gold dropped on death. */
    public static final int GOLD_DROPPED = 40;

    /** String describing the ogre's special attack. */
    public static final String SPECIAL_STR = " attacks again!";

    /**
     * Spawns a new Ogre enemy.
     */
    public Ogre()
    {
        super(NAME);
    }

    /** @return The minimum defence of the enemy. */
    @Override
    public int getMinDefence() { return MIN_DEFENCE; }

    /** @return The maximum defence of the enemy. */
    @Override
    public int getMaxDefence() { return MAX_DEFENCE; }

    /** @return The maximum health of the enemy. */
    @Override
    public int getMaxHealth() { return MAX_HEALTH; }

    /** @return The minimum damage the enemy can inflict. */
    @Override
    public int getMinAttack() { return MIN_ATTACK; }

    /** @return The maximum damage the enemy can inflict. */
    @Override
    public int getMaxAttack() { return MAX_ATTACK; }
    
    /** @return The amount of gold the enemy drops on death. */
    @Override
    public int getGold() { return GOLD_DROPPED; }

    /**
     * Perform the enemy's special ability.
     * Ogres have a 20% chance to attack again.
     * 
     * @param att The enemy's attack to modify.
     * @return The enemy's attack with the special ability applied.
     */
    @Override
    public Attack specialAbility(Attack att) 
    {
        Randomizer rand = new Randomizer();
        if (rand.chance(0.2))
        {
            att.addToDescription("\n"+ name + SPECIAL_STR);
            Attack att2 = attack();
            att.mergeAttack(att2);
        }

        return att;
    }

}