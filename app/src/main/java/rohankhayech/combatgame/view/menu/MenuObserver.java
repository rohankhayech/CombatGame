/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.view.menu;

/**
 * A {@code MenuObserver} can be attached to a {@code MenuItem} to be notified
 * whenever the element is selected. This can be used to implement option
 * functionality or dynamically populate a menu. This interface can be
 * implemented via an in-line anonymous class to keep menu element functionality
 * together with its structure or by a concrete class if large amounts of code
 * needs to be triggered.
 * 
 * @author Rohan Khayech
 */
public interface MenuObserver
{
    /**
     * Called when the subject {@code MenuItem} is selected.
     * @param ret Contains return information from the selected menu element.
     */
    public void optionSelected(MenuReturn ret);
}