import java.util.ArrayList;

import com.google.common.graph.*;

public class GamePlay {
    public ArrayList<Place> constructRooms() {
        Place entryway = new Place("Entry Way", "short description", "long descripton");
    }
    public static void main(String[] args) {
        //MutableValueGraph<Place, Integer> materialMap = ValueGraphBuilder.undirected().build();
        MutableGraph<Place> materialMap = GraphBuilder.undirected().build();
        
    }
}
