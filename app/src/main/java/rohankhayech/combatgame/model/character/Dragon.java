/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.model.character;

import rohankhayech.combatgame.controller.Randomizer;
import rohankhayech.combatgame.view.Colour;

/**
 * Class representing a Dragon enemy.
 * 
 * @author Rohan Khayech
 */
public class Dragon extends Enemy
{
    /** Name of the enemy. */
    public static final String NAME = Colour.BRIGHT_MAGENTA+"Dragon"+Colour.RESET;
    /** Minimum defence of a goblin. */
    public static final int MIN_DEFENCE = 15;
    /** Maximum defence of a goblin. */
    public static final int MAX_DEFENCE = 20;
    /** Maximum health of a goblin. */
    public static final int MAX_HEALTH = 100;
    /** Minimum damage a goblin can inflict. */
    public static final int MIN_ATTACK = 15;
    /** Maximum damage a goblin can inflict. */
    public static final int MAX_ATTACK = 30;
    /** Amount of gold dropped on death. */
    public static final int GOLD_DROPPED = 100;

    /** String describing the enemy's special attack. */
    public static final String SPECIAL1_STR = "'s attack doubled.";
    /** String describing the enemy's special attack. */
    public static final String SPECIAL2_STR = " recovered 10HP.";

    /** 
     * Spawns a new Dragon enemy.
     */
    public Dragon()
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
     * Dragons have a 25% chance to double their damage
     * or a 10% chance to recover 10 health.
     * 
     * @param att The enemy's attack to modify.
     * @return The enemy's attack with the special ability applied.
     */
    @Override
    protected Attack specialAbility(Attack att) {
        Randomizer rand = new Randomizer();
        if (rand.chance(0.35)) {
            if (rand.chance(0.25 / 0.35)) {
                att.setDamage(att.getDamage() * 2);
                att.addToDescription(name + SPECIAL1_STR);
            } else {
                modifyHealth(10);
                att.addToDescription(name + SPECIAL2_STR);
            }
        }

        return att;
    }

}