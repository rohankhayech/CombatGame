/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.model.item;

import rohankhayech.combatgame.controller.Randomizer;
import rohankhayech.combatgame.view.Colour;

/** 
 * Class representing a Potion item.
 * 
 * @author Rohan Khayech
 */
public class Potion extends Item {

    /** The minimum effect of the potion. */
    private int minEffect;
    /** The maximum effect of the potion. */
    private int maxEffect;
    /**
     * String desribing type of potion. Must be set to 
     * 'H' for Healing or 'D' for Damage.
     */
    private char type; 

    /**
     * Constructs a new Potion item.
     * @param name The name of the potion.
     * @param cost The cost of the item.
     * @param minEffect The minimum effect of the potion.
     * @param maxEffect The maximum effect of the potion.
     * @param type The type of potion, 'H' for Healing or 'D' for Damage.
     * @throws IllegalArgumentException If potion type is invalid (not equal to 'H' or 'D').
     */
    public Potion(String name, int cost, int minEffect, int maxEffect, char type) throws IllegalArgumentException
    {
        super(name, cost);
        if (type == 'H' || type == 'D')
        {
            this.type = type;
            this.minEffect = minEffect;
            this.maxEffect = maxEffect;
        }
        else
        {
            throw new IllegalArgumentException("Illegal Potion Type");
        }
    }

    /**
     * Constructs a copy of the specified Potion item.
     * @param p The potion to copy.
     */
    public Potion(Potion p) 
    {
        super(p.getName(), p.getCost());
        this.type = p.getType();
        this.minEffect = p.getMinEffect();
        this.maxEffect = p.getMaxEffect();
    }

    /** @return A copy of the specified item. */
    public Item clone() {
        return new Potion(this);
    }

    /** 
     * Returns the name of the object, coloured based on potion type.
     * 
     * @return The name of the item. 
     */
    @Override
    public String getName()
    {
        String nameStr = name;
        switch (type) {
            case 'H':
                nameStr = Colour.BRIGHT_GREEN+name+Colour.RESET;
            break;
            case 'D':
                nameStr = Colour.BRIGHT_RED+name+Colour.RESET;
            break;
        }
        return nameStr;
    }

    /** @return The minimum effect of the potion. */
    public int getMinEffect() { return minEffect; }

    /** @return The maximum effect of the potion. */
    public int getMaxEffect() { return maxEffect; }

    /** @return The type of potion. */
    public char getType() { return type; }

    /**
     * Rolls a random effect value between the minimum and maximum effect of the potion.
     * 
     * @return An integer representing the effect of the potion inflicts.
     */
    public int rollEffect() 
    {
        Randomizer rand = new Randomizer();
        return rand.randomRange(minEffect, maxEffect);
    }  

    /** @return A String representation of the item. */
    @Override
    public String toString() {
        String prefix = null;
        
        switch (type) {
            case 'H':
                prefix = " | " + Colour.BRIGHT_GREEN + "HEL: ";
            break;
            case 'D':
                prefix = " | " + Colour.BRIGHT_RED + "ATT: ";
            break;
        }

        return getName() + prefix + minEffect + "-" + maxEffect + Colour.RESET + " | " + Colour.BRIGHT_YELLOW+cost+"G"+Colour.RESET;   
    }
}