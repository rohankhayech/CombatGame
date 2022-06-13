/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.view.menu;

import java.util.*;

/** 
    Abstract class representing a menu element. A MenuItem can be included inside a Menu and could be a Menu or a MenuOption.

    @author Rohan Khayech
    @see Menu
    @see MenuOption
*/
public abstract class MenuItem 
{
    
    /** Display name of the element. */
    protected String title; 
    /** Hierarchal level. Higher level elements are within deeper menus. */
    protected int level;
    /** The option number when displayed in a menu. */
    protected int selNum;
    /** List of observers attached to the element. */
    protected List<MenuObserver> observers;

    /**
     * Constructs a new menu element.
     * @param title Display name of the element.
     */
    public MenuItem(String title)
    {
        this.title = title;
        this.level = 0;
        this.selNum = 0;
        observers = new ArrayList<MenuObserver>();
    }

    /**
     * Constructs a new menu element.
     * 
     * @param title  Display name of the element.
     * @param level  Hierarchal level. Higher level elements are within deeper menus.
     * @param selNum The option number when displayed in a menu.
     */
    public MenuItem(String title, int level, int selNum)
    {
        this.title = title;
        this.level = level;
        this.selNum = selNum;
        observers = new ArrayList<MenuObserver>();
    }

    /**
     * Selects the element and performs it's function. 
     * This should call {@code notifyObservers} so that the calling class can handle the function if needed.
     * @return A {@code MenuReturn} object that describes information returned from the element.
     */
    public abstract MenuReturn select();

    /**
     * @return Display name of the element.
     */
    public String getTitle() { return title; }

    /**
     * Sets the title of the element.
     * 
     * @param title Display name of the element.
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * @return Hierarchal level. Higher level elements are within deeper menus.
     */
    public int getLevel() { return level; }

    /**
     * Sets the level of the element.
     * @param level Hierarchal level. Higher level elements are within deeper menus.
     */
    public void setLevel(int level)
    {
        this.level = level;
    }

    /**
     * @return The option number when displayed in a menu.
     */
    public int getSelNum() { return selNum; }

    /**
     * Sets the option number.
     * 
     * @param selNum The option number when displayed in a menu.
     */
    public void setSelNum(int selNum) {
        this.selNum = selNum;
    }

    /**
     * Adds an observer to the menu element. The observing object is notified when
     * {@code notifyObservers} is called. This can be used to implement/call
     * controller code triggered when the menu element is selected etc.
     * 
     * @param ob An object that implements the {@code MenuObserver} interface. This
     *           can be a concrete class or an anonymous class created inside the
     *           function.
     */
    public void addObserver(MenuObserver ob)
    {
        observers.add(ob);
    }

    /**
     * Removes an observer.
     * @param ob The observer to remove.
     */
    public void removeObserver(MenuObserver ob)
    {
        observers.remove(ob);
    }

    /**
     * Notifies any observers attached to the object. This can be used to
     * implement/call controller code triggered when the menu element is selected
     * etc.
     * 
     * @param ret A {@code MenuReturn} object that describes information returned
     *            from the element. @see MenuReturn
     */
    public void notifyObservers(MenuReturn ret)
    {
        for(MenuObserver ob : observers)
        {
            ob.optionSelected(ret);
        }
    }
}