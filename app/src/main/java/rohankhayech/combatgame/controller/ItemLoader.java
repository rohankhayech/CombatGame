/*
 * Copyright (c) 2022 Rohan Khayech
 */

package rohankhayech.combatgame.controller;

import java.io.*;
import java.util.*;

import rohankhayech.combatgame.model.item.*;

/**
 * Class responsible for loading items into the game.
 * 
 * @author Rohan Khayech
 */
public class ItemLoader {

    /** Default filename to load items from. */
    public static final String DEF_FILENAME = "shop.txt";

    /** Name of the file to load items from. */
    public String filename; 
    
    /**
     * Constructs a new ItemLoader using the default filename.
     */
    public ItemLoader()
    {
        this.filename = DEF_FILENAME;
    }

    /**
     * Constructs a new ItemLoader using the specified filename.
     * @param filename The filename to use.
     */
    public ItemLoader(String filename) 
    { 
        this.filename = filename;
    }

    /**
     * Reads a list of inventory items from a file.
     * 
     * @return A list of inventory items.
     * @throws ItemLoadException Thrown when the ItemLoader fails to read items from the file.
     */
    public List<Item> fileLoadItems() throws ItemLoadException
    {
        ClassLoader classLdr;
        InputStream inputStream;
        InputStreamReader streamRdr;
        BufferedReader buffRdr;
        List<Item> items = null;

        try
        {
            //Open the file in a buffered reader.
            classLdr = Thread.currentThread().getContextClassLoader();
            inputStream = classLdr.getResourceAsStream(filename);
            streamRdr = new InputStreamReader(inputStream);
            buffRdr = new BufferedReader(streamRdr);
            
            //Read the items.
            items = readItems(buffRdr);

            //Close the file.
            buffRdr.close();
        }
        catch (IOException e)
        {
            throw new ItemLoadException("Error loading items from "+filename+": "+e.getMessage());
        }
        
        return items;
    }

    /** 
     * Reads items described by lines of input strings from a {@code BufferedReader}.
     * This allows item data to be added from other input sources in the future if necessary.
     * Calling class must close the reader after use.
     * 
     * @param source A {@code BufferedReader} object used to read lines of text from the input source.
     * @return A list of inventory items.
     */
    private List<Item> readItems(BufferedReader source) throws IOException
    {
        List<Item> items = new ArrayList<Item>();
        String line;
        String[] data;
        int lineNum;
        Item item = null;

        lineNum = 1;

        //Read each line from the file and load in the item if the line is valid.
        line = source.readLine();
        while (line != null)
        {
            data = line.split(", ");
            char type = data[0].charAt(0);
            switch (type)
            {
                case 'W':
                    item = readWeapon(data);
                break;

                case 'A':
                    item = readArmour(data);
                break;

                case 'P':
                    item = readPotion(data);
                break;

                default:
                    item = null;
            }

            if (item != null)
            {
                items.add(item);
            }
            else
            {
                System.err.println("Line " + lineNum + "doesn't contain a valid item, skipping...");
            }
            line = source.readLine();
            lineNum++;
        }


        return items;

    }

    /**
     * Reads a weapon from the given line.
     * @param data Array containing the fields in the line of text.
     * @return The weapon in item form.
     */
    private Item readWeapon(String[] data)
    {
        Item item;
        if (data.length == 7)
        {
            try
            {
                //Read each field into the correct data type
                String name = data[1];
                int minDamage = Integer.parseInt(data[2]);
                int maxDamage = Integer.parseInt(data[3]);
                int cost = Integer.parseInt(data[4]);
                String damageType = data[5];
                String weaponType = data[6];

                // Construct the item
                item = new GenericWeapon(name, cost, minDamage, maxDamage, weaponType, damageType);
            } catch (NumberFormatException e)
            {
                item = null;
            }
        }
        else
        {
            item = null;
        }

        return item;
    }

    /**
     * Reads an Armour object from the given line.
     * 
     * @param data Array containing the fields in the line of text.
     * @return The armour in item form.
     */
    private Item readArmour(String[] data) {
        Item item;
        if (data.length == 6) {
            try
            {
                // Read each field into the correct data type
                String name = data[1];
                int minDefence = Integer.parseInt(data[2]);
                int maxDefence = Integer.parseInt(data[3]);
                int cost = Integer.parseInt(data[4]);
                String material = data[5];

                //Construct the item
                item = new Armour(name, cost, minDefence, maxDefence, material);
            } catch (NumberFormatException e)
            {
                item = null;
            }
        } else {
            item = null;
        }

        return item;
    }

    /**
     * Reads a potion from the given line.
     * 
     * @param data Array containing the fields in the line of text.
     * @return The potion in item form.
     */
    private Item readPotion(String[] data) {
        Item item;
        if (data.length == 6) {
            try
            {
                // Read each field into the correct data type
                String name = data[1];
                int minEffect = Integer.parseInt(data[2]);
                int maxEffect = Integer.parseInt(data[3]);
                int cost = Integer.parseInt(data[4]);
                char type = data[5].charAt(0);
                try {
                    // Construct the item
                    item = new Potion(name, cost, minEffect, maxEffect, type);
                } catch (IllegalArgumentException e) {
                    //potion type invalid
                    item = null;
                }
            } catch (NumberFormatException e)
            {
                item = null;
            }
        } else {
            item = null;
        }

        return item;
    }
}