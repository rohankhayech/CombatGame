/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.controller;

import rohankhayech.combatgame.model.character.*;
import rohankhayech.combatgame.view.*;
import rohankhayech.combatgame.view.View.HUD;

/**
 * Controller class handling the events of a battle between two game characters.
 * 
 * @author Rohan Khayech
 */
public class Battle {

    /** The player in the battle. */
    private Player plr;
    /** The enemy in the battle. */
    private Enemy en;
    /** The losing character of the battle. */
    private GameCharacter loser;

    /** Factory used to spawn a random enemy. */
    private EnemyFactory spawner;

    /** Boolean describing whether the battle should end. */
    private boolean end;
    
    /**
     * Constructs a new battle between the specified character and a random enemy.
     * @param plr The player character.
     * @param spawner The enemy spawner e
     */
    public Battle(Player plr, EnemyFactory spawner)
    {
        this.plr = plr;
        this.en = null;
        this.loser = null;
        this.spawner = spawner;
        this.end = false;
        
    }

    /**
     * Starts and runs the battle. Calls on each character to take a turn after the other until either dies.
     * On each turn one character will perform {@code attack()} and the other will perform {@code defend()}.
     * 
     * @return Boolean describing whether to end the game. Returns {@code true} when player dies.
     */
    public Boolean run()
    {   
        
        // Spawn an enemy using the factory
        en = spawner.spawnRandomEnemy();

        // Setup the view to display the battle.
        View view = View.getInstance();
        view.setBattle(this);
        try
        {
            view.setHUD(HUD.BATTLE);
        } catch (ViewException e)
        {
            view.println(e.toString());
        }
        
        // Construct a new Character observer that ends the battle and sets the loser when triggered. 
        CharacterObserver deathObserver = new CharacterObserver(){
            @Override
            public void onDeath(GameCharacter gChar) {
                loser = gChar;
                end = true;
            }

            @Override
            public void onAttack(GameCharacter gChar) { /*Do nothing*/ }
        };
        
        // Add the observer to both characters.
        plr.addObserver(deathObserver);
        en.addObserver(deathObserver);
        
        // Alternate each character taking a turn until the battle ends.
        while (!end)
        {
            turn(plr, en);
            if (!end)
            {
                turn(en, plr);
            }
        }

        // Call the end method when the battle ends and return the result.
        return end();
    }

    /**
     * Calls the attacking character to perform an attack against the defending character.
     * @param a The attacking character.
     * @param d The defending character.
     */
    private void turn(GameCharacter a, GameCharacter d)
    {
        //Retrieve a reference to the view.
        View view = View.getInstance();
        
        //Get an attack from the attacking character and print this to the view.
        Attack att = a.attack();
        view.printAttack(att);

        //If the attack deals any damage, 
        //allow the defending player to defend and print the information to the view.
        if (att.getDamage()>0)
        {
            Defence def = d.defend(att.getDamage());
            view.printDefence(def);
        }
    }

    /**
     * Ends the battle. If an enemy died, the player is awarded its gold and heals.
     * If the player dies, the game over screen is displayed and function returns
     * {@code true} to signify that the game should end.
     * 
     * @return Boolean describing whether to end the game. Returns {@code true}
     *         when player dies.
     */
    private boolean end()
    {
        /**
         * Boolean describing whether to end the game.
         */
        boolean playerLost = false;
        
        // Retrieve a reference to the view.
        View view = View.getInstance();

        //print the death message for the losing character.
        view.printDeathMessage(loser);

        
        if (loser == plr) // If the player lost the battle
        {
            //print the gameover screen
            view.printGameOver();
            playerLost = true;
        }
        else //If the player won the battle
        {
            //award the player the enemy's gold and heal them by 1.5x
            plr.modifyGold(loser.getGold());
            int incHealth = (int)Math.ceil((double)(plr.getHealth())*0.5);
            plr.modifyHealth(incHealth);

            //print the victory screen if the enemy was a dragon
            if (loser instanceof Dragon)
            {
                view.printGameWon();
            }
        }
        
        //Reset the HUD to the main HUD
        try {
            view.setHUD(HUD.MAIN);
        } catch (ViewException e)
        {
            view.println(e.toString());
        }

        //Return whether the player lost the battle.
        return playerLost;
    }

    /** @return The player in the battle. */
    public Player getPlayer()
    {
        return plr;
    }

    /** @return The enemy in the battle.  */
    public Enemy getEnemy() {
        return en;
    }
}