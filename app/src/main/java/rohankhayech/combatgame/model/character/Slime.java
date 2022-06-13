/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.model.character;

import rohankhayech.combatgame.controller.Randomizer;
import rohankhayech.combatgame.view.Colour;

/**
 * Class representing a Slime enemy.
 * 
 * @author Rohan Khayech
 */
public class Slime extends Enemy
{
    /** Name of the enemy. */
    public static final String NAME = Colour.BRIGHT_GREEN+"Slime"+Colour.RESET;
    /** Minimum defence of a slime. */
    public static final int MIN_DEFENCE = 0;
    /** Maximum defence of a slime. */
    public static final int MAX_DEFENCE = 2;
    /** Maximum health of a slime. */
    public static final int MAX_HEALTH = 10;
    /** Minimum damage a slime can inflict. */
    public static final int MIN_ATTACK = 3;
    /** Maximum damage a slime can inflict. */
    public static final int MAX_ATTACK = 5;
    /** Amount of gold dropped on death. */
    public static final int GOLD_DROPPED = 10;

    /** String describing the slime's special attack. */
    public static final String SPECIAL_STR = " slipped and their attack failed.";

    /**
     * Spawns a new Slime enemy.
     */
    public Slime()
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
     * Slimes have a 20% chance of not causing any damage.
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
            att.setDamage(0);
            att.addToDescription(name + SPECIAL_STR);
        }

        return att;
    }

}