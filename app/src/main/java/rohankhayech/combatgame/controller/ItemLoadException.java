/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.controller;

/**
 * Exception thrown when the ItemLoader fails to load the items.
 * 
 * @author Rohan Khayech
 */
public class ItemLoadException extends Exception
{

    public ItemLoadException()
    {
        super();
    }    

    public ItemLoadException(String message)
    {
        super(message);
    }
}