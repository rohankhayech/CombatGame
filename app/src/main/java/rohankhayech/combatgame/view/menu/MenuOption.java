/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.view.menu;

/**
 * A menu option. The default MenuOption class only returns its return
 * information when selected. To perform additional functionality when selected
 * it can be with a concrete subclass that overrides the {@code select} method.
 * An observer can also be attached which will be notified when the option is
 * selected, allowing for further controller code to be called when the option
 * is selected.
 * 
 * @author Rohan Khayech
 * @see Menu
 * @see MenuItem
 * @see MenuObserver
 */
public class MenuOption extends MenuItem {

    /** Closes the parent menu after this option is selected if set to true. */
    protected boolean closeParent;
    /** Object to return upon selection. */
    protected Object returnObj;

    /**
     * Constructs a new menu option.
     * @param title Display name of the option.
     */
    public MenuOption(String title) {
        super(title);
        closeParent = MenuReturn.DEF_CLOSE_PAR;
        returnObj = MenuReturn.DEF_OBJ;
    }

    /**
     * Constructs a new menu option.
     * 
     * @param title  The display name of the menu.
     * @param level  Heirarchal level. Higher level elements are within deeper
     *               menus.
     * @param selNum The option number when displayed in a menu.
     */
    public MenuOption(String title, int level, int selNum) {
        super(title,level,selNum);
        closeParent = MenuReturn.DEF_CLOSE_PAR;
        returnObj = MenuReturn.DEF_OBJ;
    }

    /**
     * Selects the option, returning its return information and notfiying any
     * attached observers. Can be overriden by subclasses to provide additional
     * functionality when selected.
     */
    @Override
    public MenuReturn select()
    {
        MenuReturn ret = new MenuReturn(closeParent,selNum,returnObj);
        notifyObservers(ret);
        return ret;
    }

    /**
     * Sets whether to close the parent menu after this option is selected.
     * 
     * @param closeParent Closes the parent menu if
     *                    set to true.
     */
    public void setCloseParent(boolean closeParent) {
        this.closeParent = closeParent;
    }

    /**
     * Set the object to return when this option is selected.
     * 
     * @param returnObj Object to return upon selection.
     */
    public void setReturnObj(Object returnObj) {
        this.returnObj = returnObj;
    }
    

}