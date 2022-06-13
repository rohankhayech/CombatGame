/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.controller;

/**
 * Exception thrown when the game fails to start.
 * 
 * @author Rohan Khayech
 */
public class GameInitException extends Exception
{

    public GameInitException()
    {
        super();
    }    

    public GameInitException(String message)
    {
        super(message);
    }
}