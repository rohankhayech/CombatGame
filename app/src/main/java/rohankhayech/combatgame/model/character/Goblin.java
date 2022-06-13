/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.model.character;

import rohankhayech.combatgame.controller.Randomizer;
import rohankhayech.combatgame.view.Colour;

/**
 * Class representing a Goblin enemy.
 * 
 * @author Rohan Khayech
 */
public class Goblin extends Enemy
{
    /** Name of the enemy. */
    public static final String NAME = Colour.GREEN + "Goblin" + Colour.RESET;
    /** Minimum defence of a goblin. */
    public static final int MIN_DEFENCE = 4;
    /** Maximum defence of a goblin. */
    public static final int MAX_DEFENCE = 8;
    /** Maximum health of a goblin. */
    public static final int MAX_HEALTH = 30;
    /** Minimum damage a goblin can inflict. */
    public static final int MIN_ATTACK = 3;
    /** Maximum damage a goblin can inflict. */
    public static final int MAX_ATTACK = 8;
    /** Amount of gold dropped on death. */
    public static final int GOLD_DROPPED = 20;

    /** String describing the goblin's special attack. */
    public static final String SPECIAL_STR = " swung harder, gaining 3DP";

    /**
     * Spawns a new Goblin enemy.
     */
    public Goblin()
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
     * Goblins have a 50% chance to gain 3 damage.
     * 
     * @param att The enemy's attack to modify.
     * @return The enemy's attack with the special ability applied.
     */
    @Override
    public Attack specialAbility(Attack att) 
    {
        Randomizer rand = new Randomizer();
        if (rand.chance(0.5))
        {
            att.setDamage(att.getDamage()+3);
            att.addToDescription(name + SPECIAL_STR);
        }

        return att;
    }

}