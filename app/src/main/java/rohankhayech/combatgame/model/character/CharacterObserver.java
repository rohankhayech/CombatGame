/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.model.character;

/**
 * A {@code CharacterObserver} can be attached to a {@code GameCharacter} to be notified
 * on certain character events.
 * 
 * @author Rohan Khayech
 */
public interface CharacterObserver {
    /**
     * Called when the character's health reaches zero and dies.
     * @param gChar The character triggering the event.
     */
    public void onDeath(GameCharacter gChar);

    /**
     * Called when the character attacks.
     * 
     * @param gChar The character triggering the event.
     */
    public void onAttack(GameCharacter gChar);
}