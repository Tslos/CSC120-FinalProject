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
        Place outside = new Place("Outside", "Outside the tower.", "long description of the outside", "TBD");
        this.currentPlace = outside;
        this.map = this.ConstructMap(outside);
        this.findConnections();
        this.stillPlaying = true;
    }

    public MutableGraph<Place> ConstructMap(Place startPlace) {
        MutableGraph<Place> map = GraphBuilder.undirected().build();
        Place entryway = new Place("Entry Way", "A triangular room with three painted doors.",
                "On the wall to your left is a red door. It seems newly painted. On the middle of the right wall is a newly painted blue door. To the right of the blue door is a yellow door. Freshly painted seems to be the theme here.",
                "TBD");
        Place kitchen = new Place("Kitchen", "A triangular room with a table, cabinets, cauldron, and trap door.",
                "You are in a kitchen of sorts. Directly to your right is a table and two chairs. On the table are three bottles of liquid and some parchment. Across from you is a bubbling cauldron and on the left wall is a counter and several cabinets. To your immediate left is a trap door with a black lock. The only exit is the red door.",
                "TBD");
        Place basement = new Place("Basement", "short desc", "long desc", "TBD");
        Place study = new Place("Study", "short desc", "long desc", "TBD");
        Place bedroom = new Place("Bedroom", "short desc", "long desc", "TBD");
        Place bathroom = new Place("bathroom", "short desc", "long desc", "TBD");
        Place stairs = new Place("Staircase", "short desc", "long desc", "TBD");
        Place balcony = new Place("Balcony", "short desc", "long desc", "TBD");
        map.putEdge(startPlace, entryway);
        map.addNode(kitchen);
        map.addNode(basement);
        map.addNode(study);
        map.addNode(bedroom);
        map.addNode(bathroom);
        map.addNode(stairs);
        map.addNode(balcony);
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
        Place newPlace = null;
        // TO DO: decide what this returns for gameplay loopage
        // traverse the set of current room connections, pull out the 'name' attribute.
        // If 'name' is the same as the user-input String, pass the corresponding place
        // to replace the currentPlace attribute
        for (Place place : this.currentConnections) {
            String name = place.getName();
            if (name == placeName) {
                newPlace = place;
            }
        }
        if (newPlace == null) {
            System.out.println("There is not a traversable door between these places");
        } else {
            this.currentPlace = newPlace;
            this.findConnections();
        }
    }

    public String examine(String item) {
        String itemDesc = null;
        for (Item obj : this.inventory) {
            if (obj.shortDesc == item) {
                itemDesc = obj.shortDesc;
                break;
            }
        }
        if (itemDesc == null) {
            for (Item obj : this.currentPlace.inventory) {
                if (obj.shortDesc == item) {
                    itemDesc = obj.shortDesc;
                    break;
                }
            }
        }
        itemDesc = itemDesc == null ? "This item does not appear to be nearby. Make sure you typed its name correctly!"
                : itemDesc;
        return (itemDesc);
    }


    public void play() {
        Scanner sc = new Scanner(System.in);
        System.out.println("What would you like to do?");
        System.out.println(this.currentPlace.actionOptions);
        sc.close();
    }

    public void take(String item) {
        String whatHappens = null;
        if (whatHappens == null) {
            for (Item obj : this.inventory) {
                if (obj.shortDesc == item) {
                    System.out.println("The item " + obj.shortDesc
                            + " is already in your inventory! If you meant a different item, make sure you've typed its full name in correctly (i.e., \"red key\" instead of \"key\")");
                    break;
                }
            }
        }
        for (Item obj : this.currentPlace.inventory) {
            if (obj.shortDesc == item) {
                this.currentPlace.removeItem(obj);
                this.inventory.add(obj);
                break;
            }
        }
        whatHappens = whatHappens == null
                ? "This item does not appear to be nearby. Make sure you typed its name correctly!"
                : "You have taken " + item;
        System.out.println(whatHappens);
    }

    public void drop(String item) {
        String whatHappens = null;
        for (Item obj : this.inventory) {
            if (obj.shortDesc == item) {
                this.inventory.remove(obj);
                this.currentPlace.addItem(obj);
                System.out.println("You have dropped the " + obj.shortDesc);
                break;
            }
        }
        if (whatHappens == null) {
            System.out.println("There doesn't appear to be a " + item
                    + "in your inventory. If you think this is an error, make sure you type in the full name of an object!");
        }
    }

    public void executeAction(String action) {
        Pattern pattern = null;
        Matcher matcher = null;
        if (action.contains("move")) {
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

        } else if (action.contains("examine")) {
            pattern = Pattern.compile("(?<=examine )\\w+$");
            matcher = pattern.matcher(action);
            if (matcher.find()) {
                String item = matcher.group();
                System.out.println(this.examine(item));
            } else {
                System.out.println(
                        "I don't understand... Reprinting action options. Did you mean to say \'examine [item]\'?");
            }

        } else if (action.contains("take")) {
            pattern = Pattern.compile("(?<=take )\\w+$");
            matcher = pattern.matcher(action);
            if (matcher.find()) {
                String item = matcher.group();
                this.take(item);
            } else {
                System.out.println(
                        "I don't understand... Reprinting action options. Did you mean to say \'take [item]\'?");
            }

        } else if (action.contains("drop")) {
            pattern = Pattern.compile("(?<=drop )\\w+$");
            matcher = pattern.matcher(action);
            if (matcher.find()) {
                String item = matcher.group();
                this.drop(item);
            } else {
                System.out.println(
                        "I don't understand... Reprinting action options. Did you mean to say \'drop [item]\'?");
            }

        } else {
            System.out.println("I can't understand that action, please try again");
            // TO DO: when the loop is more figured out, get rid of this and put it in the play() loop. 
            System.out.println(this.currentPlace.actionOptions);
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        //Establish the premise with long ass print statement.
        System.out.println("\nIt's a sunny day and you are surrounded by beautiful grassland and forest to the North, West, and South. Ahead is a tower, similar to one most mages build, and beyond that is your destination: Wildeshore City. Due to excellent travel conditions, you're running two days early and should arrive by nightfall. \n\nAs you begin to pass by the mage's tower you hear a voice from above: \n\"What ho there, traveler! Do you think you could lend an old man a hand?\" \nLooking up, you see a weathered man with a long beard waving down at you from the tower's balcony. When he sees that he has your attention, he calls out again:\n \"I seem to be rather stuck up here! You see, I've locked myself at the top of my tower and dropped the key!\"\nCasting a look around, you indeed see a brass key in the grass next to the tower. \n\"If you could come up and unlock this door, I'd be forever grateful,\" the mage continues, \"I can even give you a reward if you'd like.\" \nA reward does sound nice... and you're running early. \n");
    }



}
