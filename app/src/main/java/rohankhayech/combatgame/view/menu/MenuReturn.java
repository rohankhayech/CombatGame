/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.view.menu;

/**
 * Informative class containing multiple pieces of return information from a
 * selected menu element. Contains the option number of the selected element,
 * and can also include an {@code Object} (useful if the menu is providing a
 * selection between Objects) and a boolean describing whether to close the
 * parent menu upon return.
 * 
 * @author Rohan Khayech
 * @see MenuItem
 */
public class MenuReturn 
{
    /** Default value of closeParent */
    public static final boolean DEF_CLOSE_PAR = false;
    /** Default value of selection */
    public static final int DEF_SELECTION = 0;
    /** Default value of returnObj */
    public static final Object DEF_OBJ = null;
    
    /** Closes the parent menu upon return if set to true. */
    public boolean closeParent;
    /** The option number of the selected element. */
    public int selection;
    /** Object to return. */
    public Object returnObj;

    /** Constructs a new {@code MenuReturn} object with default values. */
    public MenuReturn()
    {
        this.closeParent = DEF_CLOSE_PAR;
        this.selection = DEF_SELECTION;
        this.returnObj = DEF_OBJ;
    }

    /**
     * Constructs a new {@code MenuReturn} object with the specified values.
     * 
     * @param closeParent Closes the parent menu upon return if set to true.
     * @param selection   The option number of the selected element.
     * @param returnObj   Object to return.
     */
    public MenuReturn(boolean closeParent, int selection, Object returnObj)
    {
        this.closeParent = closeParent;
        this.selection = selection;
        this.returnObj = returnObj;
    }

    /** @return Whether to close the parent menu upon return.*/
    public boolean getCloseParent() { return closeParent; }
    /** @return The option number of the selected element. */
    public int getSelection() { return selection; }
    /** @return The object to return.*/
    public Object getReturnObj() { return returnObj; }

    /**
     * Sets whether to close the parent menu after this option is selected.
     * 
     * @param closeParent Closes the parent menu if set to true.
     */
    public void setCloseParent(boolean closeParent)
    {
        this.closeParent = closeParent;
    }

    /**
     * Sets the option number of the selected element.
     * 
     * @param selection The option number of the selected element.
     */
    public void setSelection(int selection)
    {
        this.selection = selection;
    }

    /**
     * Set the object to return.
     * 
     * @param returnObj Object to return.
     */
    public void setReturnObj(Object returnObj)
    {
        this.returnObj = returnObj;
    }
}