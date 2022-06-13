/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.controller;

import rohankhayech.combatgame.model.character.*;

/**
 * Factory class that constructs random Enemy objects based off of random probabilities.
 * 
 * @author Rohan Khayech
 */
public class EnemyFactory {

    /** Initial spawn rate of Slimes. */
    private static double INIT_PROB_SLIME = 0.50; 
    /** Initial spawn rate of Goblins. */
    private static double INIT_PROB_GOBLIN = 0.30;
    /** Initial spawn rate of Ogres. */ 
    private static double INIT_PROB_OGRE = 0.20; 
    /** Initial spawn rate of Dragons. */
    private static double INIT_PROB_DRAGON = 0.00; 

    /** Amount to decrease spawn rates by after each battle. */
    private static double PROB_DEC = 0.05;
    /** Minimum spawn probability. */
    private static double MIN_PROB = 0.05;

    /** Current spawn rate of Slimes. */
    private double probSlime;
    /** Current spawn rate of Goblins. */
    private double probGoblin;
    /** Current spawn rate of Ogres. */
    private double probOgre;
    /** Current spawn rate of Dragons. */
    private double probDragon;
    
    /** 
     * Constructs a new enemy factory with initial spawn rates. 
     */
    public EnemyFactory()
    {
        probSlime = INIT_PROB_SLIME;
        probGoblin = INIT_PROB_GOBLIN;
        probOgre = INIT_PROB_OGRE;
        probDragon = INIT_PROB_DRAGON;
    }

    /** 
     * Spawns a random enemy with the current spawn rates.
     * 
     * @return The enemy that was spawned.
     */
    public Enemy spawnRandomEnemy() {
        Enemy e = null;
        Randomizer rand = new Randomizer();

        //Get a random integer (representing the enemy to spawn) based on the current spawn rates.
        int n = rand.pickProbability(probSlime, probGoblin, probOgre, probDragon);
        
        //Construct a new enemy object based on the integer that was picked. 
        switch (n) {
            case 0:
                e = new Slime();
            break;
            
            case 1:
                e = new Goblin();
            break;
            
            case 2:
                e = new Ogre();
            break;
            
            case 3:
                e = new Dragon();
            break;
        }

        // Update the probabilities for each battle.
        updateProbabilities();

        //Return the enemy object
        return e;
    }

    /**
     * Decreases the probability of each regular enemy by {@code PROB_INC} 
     * and increases the probability of the dragon accordingly.
     * Clamps probabilities between {@code MIN_PROB} and 1.00.
     */
    private void updateProbabilities()
    {
        probSlime = Math.max(probSlime - PROB_DEC, MIN_PROB);
        probSlime = Math.max(probSlime - PROB_DEC, MIN_PROB);
        probSlime = Math.max(probSlime - PROB_DEC, MIN_PROB);
        probDragon = 1.00 - (probSlime + probGoblin + probOgre);
    }
    
}