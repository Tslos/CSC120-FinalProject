import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
    // Attributes
    protected ArrayList<String> inventory;
    protected Place currentPlace;
    protected ArrayList<Place> map; // map of complete game
    protected int playerEnergy;

    public Player(Place currentPlace, ArrayList<Place> map) {
        this.currentPlace = currentPlace;
        this.map = map;
        this.inventory = new ArrayList<String>();
        this.playerEnergy = 100;
        System.out.println("You have made a Player!");
    }

    /**
     * Grab an item and store it in inventory
     * 
     * @param item the item to grab
     */
    public void grab(String item) {
        this.inventory.add(item);
        System.out.println("You have grabbed a " + item);
    }

    /**
     * Drop an item and remove it from inventory. Checks to see if the item is in
     * inventory first
     * 
     * @param item the item to drop
     * @return the text that the computer will print to the user after dropping.
     */
    public String drop(String item) {
        // TO DO: use Item() class instead of String().
        String speech = "";
        if (this.inventory.contains(item)) {
            this.inventory.remove(item);
            speech = "You have dropped " + item + ".";
        } else {
            speech = item + " is not in your inventory, you cannot drop it.";
        }
        System.out.println(speech);
        return (speech);
    }

    /**
     * OVERLOADED TO USE MY DEFINED CLASS: Examine an item.
     * 
     * @param item the item to examine
     */
    public void examine(Item item) {
        System.out.println(item.longDesc);
    }

    /**
     * OVERLOADED TO USE MY DEFINED CLASS:Use an item.
     * 
     * @param item the item to use
     */
    public void use(Item item) {
        System.out.println(item.use());
        //Under use, make it so that the item is removed from the inventory (arraylist?). 
    }

    /**
     * Change the currentPlace.
     * 
     * @param item the direction to walk
     */
    public boolean walk(String direction) {
        boolean validDirection = this.currentPlace.connections.containsKey(direction);
        Place newPlace = this.currentPlace.moveDirection(direction);
        this.currentPlace = newPlace;
        this.exhaust(1); // walking is somewhat tiring
        System.out.println("Your current location is: " + this.currentPlace.name);
        return (validDirection);
    }

    /**
     * Exhausts the player, called after certain taxing actions.
     * 
     * @param amt the amount to exhaust the player.
     */
    private void exhaust(int amt) {
        this.playerEnergy -= amt;
        if (this.playerEnergy < 5) {
        System.out.println("You are so tired that you can't stay awake. Sweet dreams!");
        this.rest();
        } else if (this.playerEnergy < 10) {
            System.out.println("Listen, you really need to sleep now.");
        } else if (this.playerEnergy < 20) {
            System.out.println("You are getting tired, please sleep.");
        }
    }

    /**
     * Rests the player (representative of sleeping), resetting their energy level
     * to full.
     */
    public void rest() {
        this.playerEnergy = 100;
    }

    public void lookAround() {
        System.out.println(this.currentPlace);
    }
    /**
     * Records an action to the list of precent recorded actions. If the list is 10
     * long, moves every item in the list over one and adds the action in the last
     * place, thus deleting the 10th-most-recent entry
     * 
     * @param args  the string name of the function that was called 
     */

    public static void main(String[] args) {
        Place livingRm = new Place(
                new ArrayList<Item>(
                        Arrays.asList(new Item("Coffee table"), new Item("Chair"),
                                new Item("Chair"), new Item("Chair"), new Item("Rug"),
                                new Item("Fireplace"))),
                "Living Room",
                "A living room with three chairs, a rug, and a table.",
                "A cozy living room that contains three faded red checkered chairs, a worn beige rug, and an oak coffee table, still stained with the remnants of someone's bad morning. A fireplace lies unlit against the east wall.");
        Place kitchenRm = new Place(
                new ArrayList<Item>(
                        Arrays.asList(new Item("Stove"), new Item("Sink"),
                                new Item("Pantry Cabinet"))),
                "Kitchen",
                "A kitchen with a stove, sink, and pantry cabinet",
                "A tile-floored kitchen. Against the west wall is a stove. The north wall is taken up by a large sink basin and an empty pantry cabinet");
        livingRm.addConnection("south", kitchenRm);
        Player me = new Player(livingRm, new ArrayList<Place>(Arrays.asList(kitchenRm, livingRm)));
        me.lookAround();
        me.walk("north");
        me.walk("south");
        me.grab("coffee");
        me.grab("cat!");
        me.drop("cat!");
    }

}
