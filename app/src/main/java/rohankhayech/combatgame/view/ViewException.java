/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.view;

/**
 * Base exception class for exceptions thrown by the View.
 * 
 * @author Rohan Khayech
 */
public class ViewException extends Exception
{

    public ViewException()
    {
        super();
    }    

    public ViewException(String message)
    {
        super(message);
    }
}