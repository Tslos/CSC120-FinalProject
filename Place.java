import java.util.ArrayList;

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

    public String getName() {
        return(this.name);
    }

    public void addItem(Item item) {
        this.inventory.add(item);
    }

    public void removeItem(Item item) {
        this.inventory.remove(item);
    }


    // public void moveOptions() {
    //     //this will output a printed list of all the potential actions that a player can make within the room. any other actions will throw an error
    //     System.out.println("blank");
    //     // yellow, red, blue
    // }

}
