import java.util.*;
import com.google.common.graph.*;

public class Game {
    public Player player;
    public Place currentPlace;
    public  MutableGraph<Place> map;
    public boolean stillPlaying;
    public ArrayList<Place> currentConnections;

    
    public MutableGraph<Place> ConstructMap() {
        MutableGraph<Place> map = GraphBuilder.undirected().build();
        Place outside = new Place("Outside", "Outside the tower.", "long description of the outside", "TBD");
        Place entryway = new Place("Entry Way", "A triangular room with three painted doors.", "On the wall to your left is a red door. It seems newly painted. On the middle of the right wall is a newly painted blue door. To the right of the blue door is a yellow door. Freshly painted seems to be the theme here.", "TBD");
        Place kitchen = new Place("Kitchen", "A triangular room with a table, cabinets, cauldron, and trap door.", "You are in a kitchen of sorts. Directly to your right is a table and two chairs. On the table are three bottles of liquid and some parchment. Across from you is a bubbling cauldron and on the left wall is a counter and several cabinets. To your immediate left is a trap door with a black lock. The only exit is the red door.", "TBD");
        Place basement = new Place("Basement", "short desc", "long desc", "TBD");
        Place study = new Place("Study", "short desc", "long desc", "TBD");
        Place bedroom = new Place("Bedroom", "short desc", "long desc", "TBD");
        Place bathroom = new Place("bathroom", "short desc", "long desc", "TBD");
        Place stairs = new Place("Staircase", "short desc", "long desc", "TBD");
        Place balcony = new Place("Balcony", "short desc", "long desc", "TBD");

        return(map);
    }


}
















    public void findConnections() {
        this.currentConnections = this.map.adjacentNodes(this.currentPlace).toArray();
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



