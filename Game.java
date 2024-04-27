import java.util.*;
import com.google.common.graph.*;

public class Game {
    protected ArrayList<Item> inventory;
    public  MutableGraph<Place> map;
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
        Place entryway = new Place("Entry Way", "A triangular room with three painted doors.", "On the wall to your left is a red door. It seems newly painted. On the middle of the right wall is a newly painted blue door. To the right of the blue door is a yellow door. Freshly painted seems to be the theme here.", "TBD");
        Place kitchen = new Place("Kitchen", "A triangular room with a table, cabinets, cauldron, and trap door.", "You are in a kitchen of sorts. Directly to your right is a table and two chairs. On the table are three bottles of liquid and some parchment. Across from you is a bubbling cauldron and on the left wall is a counter and several cabinets. To your immediate left is a trap door with a black lock. The only exit is the red door.", "TBD");
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
        return(map);
    }

    public void findConnections() {
        this.currentConnections = new ArrayList<Place>(this.map.adjacentNodes(this.currentPlace));
    }
    /** A helpful function while coding to easily see which rooms are currently "connected" to the currentPlace */
    public void printConnections() {
        System.out.println("You are currently in " + this.currentPlace.getName() + "\n\nThe following places are connected to this location:");
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
        // traverse the set of current room connections, pull out the 'name' attribute. If 'name' is the same as the user-input String, pass the corresponding place to replace the currentPlace attribute 
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

    public void play() {
        
    }
    public static void main(String[] args) {
        Game game = new Game();
        game.printConnections();
        //Establish the premise with long ass print statement.
        System.out.println("\nIt's a sunny day and you are surrounded by beautiful grassland and forest to the North, West, and South. Ahead is a tower, similar to one most mages build, and beyond that is your destination: Wildeshore City. Due to excellent travel conditions, you're running two days early and should arrive by nightfall. \n\nAs you begin to pass by the mage's tower you hear a voice from above: \n\"What ho there, traveler! Do you think you could lend an old man a hand?\" \nLooking up, you see a weathered man with a long beard waving down at you from the tower's balcony. When he sees that he has your attention, he calls out again:\n \"I seem to be rather stuck up here! You see, I've locked myself at the top of my tower and dropped the key!\"\nCasting a look around, you indeed see a brass key in the grass next to the tower. \n\"If you could come up and unlock this door, I'd be forever grateful,\" the mage continues, \"I can even give you a reward if you'd like.\" \nA reward does sound nice... and you're running early. \n");

    }



}


