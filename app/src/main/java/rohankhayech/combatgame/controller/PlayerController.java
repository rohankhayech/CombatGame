/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.controller;

import rohankhayech.combatgame.model.character.Attack;
import rohankhayech.combatgame.model.character.CharacterObserver;
import rohankhayech.combatgame.model.character.GameCharacter;
import rohankhayech.combatgame.model.character.Player;
import rohankhayech.combatgame.model.item.*;
import rohankhayech.combatgame.view.Colour;
import rohankhayech.combatgame.view.menu.*;

/**
 * Controller class handling user input for player characters.
 * 
 * @author Rohan Khayech
 */
public class PlayerController implements CharacterObserver {

    /** The player to control. */
    private Player plr = null;

    /** The attack chosen by the player. */
    private Attack nextAttack;

    public PlayerController(Player plr) 
    {
        this.plr = plr;

        //add ourselves 
        plr.addObserver(this);
    }

    public void chooseAttack() {
        nextAttack = null;
        Menu attackMenu = new Menu("Pick your move:");
        attackMenu.includeBackOption(false);
        attackMenu.addObserver(new MenuObserver() {
            @Override
            public void optionSelected(MenuReturn ret) {
                populateAttackMenu(attackMenu);
            }
        });

        attackMenu.select();
    
        plr.setNextAttack(nextAttack);
    }

    /**
     * Populates the player attack menu.
     * 
     * @param attackMenu
     */
    private void populateAttackMenu(Menu attackMenu) {
        // Add an option for using the current weapon.
        MenuOption weaponOption = attackMenu.addOption("Attack with " + plr.getWeapon().getName());
        weaponOption.setCloseParent(true);
        weaponOption.addObserver(new MenuObserver() {
            @Override
            public void optionSelected(MenuReturn ret) {
                nextAttack = useWeapon();
            }
        });

        // Add an option for each potion in the inventory.
        for (Potion p : plr.getInventory().getAllPotions()) {
            MenuOption potionOption = attackMenu.addOption("Use " + p.getName());
            potionOption.setCloseParent(true);
            potionOption.setReturnObj(p);
            potionOption.addObserver(new MenuObserver() {
                @Override
                public void optionSelected(MenuReturn ret) {
                    nextAttack = usePotion((Potion) ret.getReturnObj());
                }
            });
        }
    }

    /**
     * Performs an attack using the current weapon.
     * 
     * @return An {@code Attack} object containing the damage and description of the
     *         attack.
     */
    private Attack useWeapon() {
        int damage = plr.getWeapon().rollDamage();
        Attack att = new Attack(damage,
                plr.getName() + " attacked with " + plr.getWeapon().toString() + ", dealing " + damage + "DP.");

        return att;
    }

    /**
     * Uses a potion from the player's inventory, either as an attack or to heal.
     * 
     * @param potion The potion to use.
     * @return An {@code Attack} object containing the damage and description of the
     *         attack.
     */
    private Attack usePotion(Potion potion) {
        Attack att = null;
        plr.takeItem((Item) potion);
        switch (potion.getType()) {
            case 'H':
                att = useHealingPotion(potion);
                break;
            case 'D':
                att = useDamagePotion(potion);
                break;
            default:
                att = new Attack(0, plr.getName() + " used " + potion.getName() + ". It was not very effective.");
        }
        return att;
    }

    /**
     * Uses a damage potion from the player's inventory as an attack.
     * 
     * @param potion The potion to use.
     * @return An {@code Attack} object containing the damage and description of the
     *         attack.
     */
    private Attack useDamagePotion(Potion potion) {
        int damage = potion.rollEffect();
        Attack att = new Attack(damage, plr.getName() + " attacked with " + potion.getName() + ", dealing "
                + Colour.BRIGHT_RED + damage + "DP" + Colour.RESET + ".");

        return att;
    }

    /**
     * Uses a healing potion from the player's inventory to recover health.
     * 
     * @param potion The potion to use.
     * @return An {@code Attack} object containing the damage and description of the
     *         attack.
     */
    private Attack useHealingPotion(Potion potion) {
        int healing = potion.rollEffect();
        plr.modifyHealth(healing);
        Attack att = new Attack(0, plr.getName() + " used " + potion.getName() + ", gaining " + Colour.BRIGHT_GREEN
                + healing + "HP" + Colour.RESET + ".");

        return att;
    }

    //Character observer methods

    /**
     * When Player attacks, prompt the user to choose an attack.
     */
    @Override
    public void onAttack(GameCharacter gChar) {
        chooseAttack();
    }


    @Override
    public void onDeath(GameCharacter gChar) {
        // Do nothing
    }
}
