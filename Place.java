import java.util.ArrayList;
import java.util.Hashtable;

public class Place {
    public ArrayList<Item> items; // an arraylist of interactable objects within a given place
    public String name;
    public String shortDesc;
    public String longDesc;
    public String actionOptions;


    public Place() {
        this.items = new ArrayList<Item>(); 
        this.name = "name";
        this.shortDesc = "shortDesc";
        this.longDesc = "longDesc";
        this.actionOptions = "actionOptions";
    }

    public Place(String name, String shortDesc, String longDesc, String actionOptions) {
        this.items = new ArrayList<Item>(); 
        this.name = name;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.actionOptions = actionOptions;
    }

    public String getName() {
        return(this.name);
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    // public void moveOptions() {
    //     //this will output a printed list of all the potential actions that a player can make within the room. any other actions will throw an error
    //     System.out.println("blank");
    //     // yellow, red, blue
    // }

}
