import java.util.ArrayList;

/**
 * The place class creates the rooms that the player can exist in. These places will be added to the map. The places
 * have a name, descriptions, and a boolean stating if it needs a key to be entered. Each place also has an 
 * inventory that can be added to and taken away from. 
 */
public class Place {
    public ArrayList<Item> inventory; // an arraylist of interactable objects within a given place
    public String name;
    public String shortDesc;
    public String longDesc;
    public String needsKey;


    public Place() {
        this.inventory = new ArrayList<Item>(); 
        this.name = "name";
        this.shortDesc = "shortDesc";
        this.longDesc = "longDesc";
        this.needsKey = "none";
    }


    public Place(String name, String shortDesc, String longDesc, String needsKey) {
        this.inventory = new ArrayList<Item>(); 
        this.name = name;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.needsKey = needsKey;
    }

    /**
     * a getter method that returns the name of the place
     * @return a String containing the name of the place
     */
    public String getName() {
        return(this.name);
    }
    
    /**
     * a getter method that returns the color of the key needed to enter a room.
     * @return a string containing the color of the key needed to enter a place. 
     */
    public String getKeyColor() {
        return(this.needsKey);
    }

   /**
    * a method that allows an item to be added to the place's inventory
    * @param item an item that will be added to the place's inventory
    *note: there is a check that makes sure an item won't be added to a place's inventory twice.
    */
    public void addItem(Item item) {
        this.inventory.add(item);
    }

    /**
     * a method that removes an item from the place's inventory.
     * @param item an item that will be added to the place's inventory. 
     */
    public void removeItem(Item item) {
        this.inventory.remove(item);
    }


    // public void moveOptions() {
    //     //this will output a printed list of all the potential actions that a player can make within the room. any other actions will throw an error
    //     System.out.println("blank");
    //     // yellow, red, blue
    // }

}
