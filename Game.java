
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
        Place outside = Place("Outside", "Outside the tower.", "long description of the outside TBD");
        Place



        return(map);
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



