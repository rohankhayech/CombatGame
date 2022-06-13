/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.controller;

import java.util.Random;

/**
 * Helper class containing methods to randomise game stats and outcomes.
 * 
 * @author Rohan Khayech
 */
public class Randomizer 
{
    /** Constructs a new Randomizer object. */
    public Randomizer() {}
    
    /**
     * Generates a random number within the given range (inclusive).
     * @param min The lower end of the range.
     * @param max The upper end of the range.
     * @return A random int between min and max (inclusive).
     */   
    public int randomRange(final int min, final int max)
    {
        final Random r = new Random((long)(Math.random()*1000));
        final int diff = max-min;

        return (r.nextInt(diff+1) + min);
    }

    /**
     * This function has a certain chance to return true equal to the given percentage.
     * @param percent The percentage chance the function should return true.
     * @return A random boolean.
     */
    public boolean chance(final double percent)
    {
        return Math.random() < percent;
    }

    /**
     * Takes a number of probabilities and returns the number of the probability that won. Consider each argument is named a,b,c... if a = 0.2, b = 0.5 and c = 0.3, the function would return 0 20% of the time, 1 50% of the time and 2 30% of the time.
     * @param probs A number of probabilities.
     * @return An integer representing which argument was picked.
     */
    public int pickProbability(final double ... probs)
    {
        final double s = sum(probs); //sum of probs
        double c = 0.0; //cumulative prob
        final double r = Math.random(); //random number between 0 and 1
        int n = 0; //prob to pick

        for(final double p : probs) 
        {
            c += p/s; //add the next prob to the cumulative sum
            if (r<c) { break; }
            n++;
        }

        return n;
    }

    /**
     * Calculates the sum of the doubles in the array.
     * @param nums An array of doubles to sum.
     * @return The final sum.
     */
    private double sum(final double[] nums)
    {
        double sum = 0.0;
        for(final double n : nums)
        {
            sum+=n;
        }
        return sum;
    }

}