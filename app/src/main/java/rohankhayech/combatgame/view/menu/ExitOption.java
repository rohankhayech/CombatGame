/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.view.menu;

/**
 * A type of {@code MenuOption} that closes the menu upon selected. This is automatically added to menus by default.
 * 
 * @author Rohan Khayech
 */
public class ExitOption extends MenuOption {

    /**
     * Constructs a new ExitOption.
     * 
     * @param level  Heirarchal level. Higher level elements are within deeper
     *               menus.
     * @param selNum The option number when displayed in a menu.
     */
    public ExitOption(int level,int selNum)
    {
        super("Back",level,selNum);
        closeParent = true;
    }
}