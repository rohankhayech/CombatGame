/*
 * Copyright (c) 2020 Rohan Khayech
 */

package rohankhayech.combatgame.view;

/**
 * Class containing colour code constants for console colours.
 * 
 * @author Rohan Khayech
 * 
 * Colour code values obtained from:
 * https://www.lihaoyi.com/post/BuildyourownCommandLinewithANSIescapecodes.html
 */
public class Colour
{
    //Reset to default colour
    public static final String RESET = "\u001b[0m";
    
    //Colours
    public static final String RED = "\u001b[31m";
    public static final String GREEN = "\u001b[32m";;
    public static final String YELLOW = "\u001b[33m";
    public static final String BLUE = "\u001b[34m";
    public static final String MAGENTA = "\u001b[35m";
    public static final String CYAN = "\u001b[36m";
    public static final String WHITE = "\u001b[37m";

    //Bright colours
    public static final String BRIGHT_RED = "\u001b[31;1m";
    public static final String BRIGHT_GREEN = "\u001b[32;1m";
    public static final String BRIGHT_YELLOW = "\u001b[33;1m";
    public static final String BRIGHT_BLUE = "\u001b[34;1m";
    public static final String BRIGHT_MAGENTA = "\u001b[35;1m";
    public static final String BRIGHT_CYAN = "\u001b[36;1m";
    public static final String BRIGHT_WHITE = "\u001b[37;1m";
}