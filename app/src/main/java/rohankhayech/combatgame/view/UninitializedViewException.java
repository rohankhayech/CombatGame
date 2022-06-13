/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.view;

/** 
 *  Exception class for exceptions thrown by the View.
 */

	
public class UninitializedViewException extends ViewException
{
    public UninitializedViewException()
    {
        super();
    }
    
    public UninitializedViewException(String message)
    {
        super(message);
    }
}
