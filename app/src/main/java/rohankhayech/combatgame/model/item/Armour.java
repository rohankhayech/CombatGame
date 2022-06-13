/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.model.item;

import rohankhayech.combatgame.view.Colour;

/**
 * Class representing an Armour item in the game.
 * 
 * @author Rohan Khayech
 */
public class Armour extends Item {

    /** Material of the armour. */
    private String material;
    /** Minimum defence of the armour. */
    private int minEffect;
    /** Maximum defence of the armour. */
    private int maxEffect;

    /**
     * Construct a new Armour item.
     * @param name The name of the armour.
     * @param cost The cost of the armour in gold.
     * @param minEffect The minimum defence of the armour.
     * @param maxEffect The maximum defence of the armour.
     * @param material The material of the armour.
     */
    public Armour(String name, int cost, int minEffect, int maxEffect, String material) 
    {
        super(name, cost);
        this.minEffect = minEffect;
        this.maxEffect = maxEffect;
        this.material = material;
    }

    /**
     * Constructs a copy of the specified Armour item.
     * 
     * @param a The armour to copy.
     */
    private Armour(Armour a) {
        super(a.getName(),a.getCost());
        this.minEffect = a.getMinEffect();
        this.maxEffect = a.getMaxEffect();
        this.material = a.getMaterial();
    }

    /** @return A copy of the specified item. */
    public Item clone() 
    {
        return new Armour(this);
    }

    /** @return The material of the armour. */
    public String getMaterial() { return material; }

    /** @return The minimum defence of the armour. */
    public int getMinEffect() { return minEffect; }
    
    /** @return The maximum defence of the armour. */
    public int getMaxEffect() { return maxEffect; }

    /**
     * Returns a string representation of the Armour item.
     * @return String describing the Armour item.
     */
    @Override
    public String toString()
    {
        return name+" | "+material+" | "+Colour.BRIGHT_CYAN+"DEF: "+minEffect+"-"+maxEffect+Colour.RESET + " | " + Colour.BRIGHT_YELLOW + getCost() + "G" + Colour.RESET;
    }


    

}