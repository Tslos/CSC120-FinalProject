import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.common.graph.*;

public class Game {
    protected ArrayList<Item> inventory;
    public MutableGraph<Place> map;
    public Place currentPlace;
    public ArrayList<Place> currentConnections;
    public boolean stillPlaying;

    public Game() {
        this.inventory = new ArrayList<Item>();
        Place outside = new Place("Outside", "Outside the tower.", "long description of the outside", "null");
        outside.addItem(new Item("BRASS KEY", "A gleaming brass key", false, 0));
        this.currentPlace = outside;
        this.map = this.ConstructMap(outside);
        this.findConnections();
        this.stillPlaying = true;
    }

    public MutableGraph<Place> ConstructMap(Place startPlace) {
        MutableGraph<Place> map = GraphBuilder.undirected().build();
        Place entryway = new Place("ENTRYWAY", "A triangular room with three painted doors.",
                "On the wall to your left is a red door. It seems newly painted. On the middle of the right wall is a newly painted blue door. To the right of the blue door is a yellow door. Freshly painted seems to be the theme here.",
                null);
        entryway.addItem(new Item("RED KEY",
                "A painted red key, with a string through its handle so that it can be hung from a keyhook", false, 0));
        Place kitchen = new Place("KITCHEN", "A triangular room with a table, cabinets, cauldron, and trap door.",
                "You are in a kitchen of sorts. Directly to your right is a table and two chairs. On the table are three bottles of liquid and some parchment. Across from you is a bubbling cauldron and on the left wall is a counter and several cabinets. To your immediate left is a trap door with a black lock. The only exit is the red door.",
                "RED");
        Place basement = new Place("BASEMENT", "short desc", "long desc", "BLACK");
        Place study = new Place("STUDY", "short desc", "long desc", "BLUE");
        Place bedroom = new Place("BEDROOM", "short desc", "long desc", "ORANGE");
        Place bathroom = new Place("BATHROOM", "short desc", "long desc", "WHITE");
        Place stairs = new Place("STAIRCASE", "short desc", "long desc", "YELLOW");
        Place balcony = new Place("BALCONY", "short desc", "long desc", "BRASS");
        map.putEdge(startPlace, entryway);
        map.putEdge(entryway, kitchen);
        map.putEdge(kitchen, basement);
        map.putEdge(entryway, study);
        map.putEdge(study, bedroom);
        map.putEdge(bedroom, bathroom);
        map.putEdge(entryway, stairs);
        map.putEdge(stairs, balcony);
        return (map);
    }

    public void findConnections() {
        this.currentConnections = new ArrayList<Place>(this.map.adjacentNodes(this.currentPlace));
    }

    /**
     * A helpful function while coding to easily see which rooms are currently
     * "connected" to the currentPlace
     */
    public void printConnections() {
        System.out.println("You are currently in " + this.currentPlace.getName()
                + "\n\nThe following places are connected to this location:");
        for (Place room : this.currentConnections) {
            System.out.println(room.getName());
        }
    }


    public void executeAction(String action){
        if (action.contains("move")){
            String placeName =
        }
        else if (action.contains("look aroud")) {
            //look around
        }
        else if (action.contains("examine")) {
            //look at
        }
        else if (action.contains("take")) {
            //take item
        }
        else if (action.contains("drop")) {
            //drop item
        }
        else if (action.contains("use potion")) {
            //cry
        }
        else{
            System.out.println("I can't understand that, please try again.");
            System.out.println(this.currentPlace.actionOptions);
        }
    }

    public void move(String placeName) {
        //System.out.println("You entered this place to go to: " + placeName);
        Place newPlace = null;
        // TO DO: decide what this returns for gameplay loopage
        // For each room next to the current one: is it the place the user wants to go?
        for (Place place : this.currentConnections) {
            String name = place.getName();
            //System.out.println("Checking to see if place.getName() (" + name + ") is the same as the entered placeName (" +placeName + ")");
            //System.out.println(name.equals(placeName));
            if (placeName.contains(name)) {
                // loop through each inventory item
                for (Item item : this.inventory) {
                    // is it the key that we need? if yes, overwrite newPlace, because we can go there!
                    if (item.shortDesc.toUpperCase().contains(place.needsKey)) {
                        newPlace = place;
                    }
                }
                //For the entryway, no key is needed, so this extra step is necessary because the inventory at the moment is 0
                if (place.needsKey == null) {
                    newPlace = place;
                }
            }
        }
        // After all the looping, check if newPlace was overwritten (we had a key and were next to a door)
        if (newPlace != null) {
            // If we are good to go, change current places and rewrite current connections
            this.currentPlace = newPlace;
            this.findConnections();
        }
    }

    public void examine(String item) {
        String itemDesc = null;
        for (Item obj : this.inventory) {
            if (obj.name == item) {
                itemDesc = obj.shortDesc;
                break;
            }
        }
        if (itemDesc == null) {
            for (Item obj : this.currentPlace.inventory) {
                if (obj.name == item) {
                    itemDesc = obj.shortDesc;
                    break;
                }
            }
        }
        itemDesc = itemDesc == null ? "This item does not appear to be nearby. Make sure you typed its name correctly!"
                : itemDesc;
        System.out.println(itemDesc);
    }

    public void play() {
        Scanner sc = new Scanner(System.in);
        System.out.println("What would you like to do?");
        //System.out.println(this.currentPlace.actionOptions);
        sc.close();
    }

    public void printInventory() {
        System.out.println("You have the following items in your inventory:");
        for (Item item : this.inventory) {
            System.out.println(item.shortDesc);
        }
    }

    public void take(String item) {
        boolean itemFound = false;
        for (Item obj : this.inventory) {
            if (item.contains(obj.name)) {
                System.out.println("The item " + obj.name
                        + " is already in your inventory! If you meant a different item, make sure you've typed its full name in correctly (i.e., \"RED KEY\" instead of \"KEY\")");
                itemFound = true;
                break;
            }
        }
        if (itemFound == false) {
            for (Item obj : this.currentPlace.inventory) {
                if (item.contains(obj.name)) {
                    this.currentPlace.removeItem(obj);
                    this.inventory.add(obj);
                    System.out.println("You have taken " + item);
                    itemFound = true;
                    break;
                }
            }
        }
        if (!itemFound) {
            System.out.println("This item does not appear to be nearby... make sure you typed it in correctly! (Hint: Type \'take [item]\')");
        }
    }

    public void drop(String item) {
        boolean itemFound = false;
        for (Item obj : this.inventory) {
            if (item.contains(obj.name)) {
                this.inventory.remove(obj);
                this.currentPlace.addItem(obj);
                System.out.println("You have dropped the " + obj.name);
                itemFound = true;
                break;
            }
        }
        if (!itemFound) {
            System.out.println("There doesn't appear to be a " + item
                    + "in your inventory. If you think this is an error, make sure you type in the full name of an object!");
        }
    }

    public void executeAction(String action) {
        Pattern pattern = null;
        Matcher matcher = null;
        if (action.contains("move ")) {
            pattern = Pattern.compile("(?<=move )\\w+$");
            matcher = pattern.matcher(action);
            if (matcher.find()) {
                String placeName = matcher.group();
                this.move(placeName);
            } else {
                System.out.println(
                        "I don't understand... Reprinting action options. Did you mean to say \'move to [place]\'?");
            }

        } else if (action.contains("look around")) {
            System.out.println(this.currentPlace.longDesc);

        } else if (action.contains("examine ")) {
            pattern = Pattern.compile("(?<=examine )\\w+$");
            matcher = pattern.matcher(action);
            if (matcher.find()) {
                String item = matcher.group();
                this.examine(item);
            } else {
                System.out.println(
                        "I don't understand... Reprinting action options. Did you mean to say \'examine [item]\'?");
            }

        } else if (action.contains("take ")) {
            this.take(action);
        } else if (action.contains("drop ")) {
            this.drop(action);
    }
}

    public static void main(String[] args) {
        Game game = new Game();
        System.out.println(game.currentPlace.shortDesc);
        // works! game.move("ENTRYWAY");
        for (Item item : game.currentPlace.inventory) {
            System.out.println(item.name);
        }
        game.take("BRASS KEY");
        //game.drop("BRASS KEY");
        game.examine("BRASS KEY");
        game.executeAction("drophjabdkfawelfnjkbvz  BRASS KEY");
        game.printInventory();
        game.move("to the OUTSIDE");
        System.out.println(game.currentPlace.name);



        System.out.println("\nIt's a sunny day and you are surrounded by beautiful grassland and forest to the North, West, and South. Ahead is a tower, similar to one most mages build, and beyond that is your destination: Wildeshore City. Due to excellent travel conditions, you're running two days early and should arrive by nightfall. \n\nAs you begin to pass by the mage's tower you hear a voice from above: \n\"What ho there, traveler! Do you think you could lend an old man a hand?\" \nLooking up, you see a weathered man with a long beard waving down at you from the tower's balcony. When he sees that he has your attention, he calls out again:\n \"I seem to be rather stuck up here! You see, I've locked myself at the top of my tower and dropped the key!\"\nCasting a look around, you indeed see a brass key in the grass next to the tower. \n\"If you could come up and unlock this door, I'd be forever grateful,\" the mage continues, \"I can even give you a reward if you'd like.\" \nA reward does sound nice... and you're running early. \n");



    }



}
