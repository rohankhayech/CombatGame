/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.view.menu;

import java.util.*;

import rohankhayech.combatgame.view.View;

/**
 * A text based menu containing any menu element, such as options or submenus. 
 * A menu item can be selected by the user by typing the corresponding option number. 
 * Any invalid input is handled and the user is prompted to reinput their selection. 
 * By default it includes a back option which closes the menu which can be optionally removed. 
 * The menu can also be dynamically populated by an observer.
 * 
 * @author Rohan Khayech
 * @see MenuItem
 * @see MenuOption
 * @see MenuObserver
 */
public class Menu extends MenuItem
{

    /** The list of elements contained in the menu. */
    LinkedList<MenuItem> items;
    /** A back option that closes the menu.*/
    MenuOption backOption;
    /** Whether to include the back option at the end of the menu.*/
    boolean includeBack;
    /** Closes the menu when true. */
    boolean close;
    /** View to update. */
    View view;

    /**
     * Constructs a new menu.
     * @param title The display name of the menu.
     */
    public Menu(String title) {
        super(title,0,0);
        items = new LinkedList<MenuItem>();
        includeBack = true;
        backOption = new ExitOption(level+1, 1);
        items.add(backOption);
        close = false;
        view = View.getInstance();
    }

    /**
     * Constructs a new menu.
     * 
     * @param title  The display name of the menu.
     * @param level  Heirarchal level. Higher level elements are within deeper menus.
     * @param selNum The option number when displayed in a menu.
     */
    public Menu(String title,int level,int selNum) {
        super(title, level, selNum);
        items = new LinkedList<MenuItem>();
        includeBack = true;
        backOption = new ExitOption(level + 1, 1);
        if (level == 0) {
            backOption.setTitle("Exit");
        }
        items.add(backOption);
        close = false;
        view = View.getInstance();
    }

    /**
     * Selects and opens the menu, displaying all elements inside and allows the user to select a sub-element. 
     * * @return A {@code MenuReturn} object with {@code selection} equal to the option picked, or 0 if Back/Exit is selected.
     */
    @Override
    public MenuReturn select() {
        return openMenu();
    }

    /**
     * Opens and runs the menu. Loops until triggered to close. Notifies observers before each display/after each input so that the menu can be refreshed or repopulated by an observer where needed. 
     */
    private MenuReturn openMenu()
    {
        MenuReturn ret = new MenuReturn();
        while (!close) {
            notifyObservers(ret);
            displayMenu();
            ret = getSelection();
        }
        close = false;
        return ret;
    }

    /**
     * Prints all elements in the menu and their option number to the screen. 
     */
    private void displayMenu()
    {
        view.clear();
        
        for(int l=0; l<level; l++)
        {
            System.out.print(" ") ;
        }
        System.out.println(title);
        for(MenuItem i : items)
        {
            for(int l=0; l<level; l++)
            {
               System.out.print(" ");
            }
            System.out.println("> "+i.getSelNum()+". "+i.getTitle());
        }
    }

    /**
     * Takes user input to select a menu element. Validates user input.
     */
    private MenuReturn getSelection()
    {
        MenuReturn ret = null;
        Scanner sc = new Scanner(System.in);
        try {
            int selection = sc.nextInt();
            if (selection <= 0 || selection > items.size()) {
                view.println("Please select a valid option...");
            } else {
                ret = items.get(selection - 1).select();
                if (ret != null) {
                    handleReturn(ret);
                }

            }
        } 
        catch (InputMismatchException e)
        {
            view.println("Please select a valid option..."); 
        }
        return ret;
    }

    /**
     * Handles the return data of a selected menu element. If {@code MenuReturn.closeParent} is set to true, {@code close} is set to true causing the menu to close. {@code MenuReturn.closeParent} is then set to false so that the parent menu does not close when passing the data on.
     * @param ret The MenuReturn object
     */
    private void handleReturn(MenuReturn ret) 
    {
        if(ret.getCloseParent()==true)
        {
            close = true;
            ret.setCloseParent(false);
        }
    }

    /**
     * Adds the specified menu element to the end of the menu.
     * @param item The menu element to add.
     */
    public void addItem(MenuItem item)
    {
        //Temporarily remove the back option from the end of the list.
        if (includeBack)
        {
            items.remove(backOption);
        }

        //Add the new menu item to the list.
        item.setSelNum(items.size()+1);
        item.setLevel(level + 1);
        items.add(item);
        
        //Add the backOption back to the back of the list.
        if (includeBack)
        {
            backOption.setSelNum(items.size()+1);
            items.add(backOption);
        }
    }

    /** 
     * Creates a new menu option with the specified title and adds it to the end of the menu.
     * @param title The title of the new option.
     * @return The newly created option.
      */
    public MenuOption addOption(String title) {
        MenuOption option = new MenuOption(title);
        addItem(option);
        return option;
    }

    /**
     * Removes the menu element at the specified index.
     * @param index Index of the menu element to remove, starting at index 0 / option number 1.
     */
    public void removeItem(int index)
    {
        items.remove(index);
        updateAllItems();
    }

    /**
     * Removes the specified menu element.
     * @param i The element to remove.
     */
    public void removeItem(MenuItem i) 
    {
        items.remove(i);
        updateAllItems();
    }

    /**
     * Ensures all menu elements have the correct option number and level. 
     */
    private void updateAllItems() {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).setSelNum(i + 1);
            items.get(i).setLevel(level+1);
        }
    }

    /**
     * Removes all elements from the menu. If the back option is enabled this will persist.
     * Useful when repopulating the menu with fresh data.
     */
    public void clear()
    {
        items.clear();
        if (includeBack)
        {
            backOption.setSelNum(1);
            items.add(backOption);
        }
    }

    
    /**
     * Sets whether to automatically include the back button in the menu. If this
     * state is changed the back button will be added/removed by this function.
     * 
     * @param includeBack Whether to include the back option at the end of the menu.
     */
    public void includeBackOption(boolean includeBack)
    {
        if (this.includeBack && !includeBack)
        {
            removeItem(backOption);
        }
        if (!(this.includeBack) && includeBack)
        {
            addItem(backOption);
        }
        
        this.includeBack = includeBack;
    }
}