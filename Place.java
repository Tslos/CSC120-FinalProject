import java.util.ArrayList;

public class Place {
    public ArrayList<Item> inventory; // an arraylist of interactable objects within a given place
    public String name;
    public String shortDesc;
    public String longDesc;
    public String needsKey;
    public boolean explored;


    public Place() {
        this.inventory = new ArrayList<Item>(); 
        this.name = "name";
        this.shortDesc = "shortDesc";
        this.longDesc = "longDesc";
        this.needsKey = "none";
        this.explored = false;
    }


    public Place(String name, String shortDesc, String longDesc, String needsKey) {
        this.inventory = new ArrayList<Item>(); 
        this.name = name;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.needsKey = needsKey;
        this.explored = false;
    }

    public Place(String name, String shortDesc, String longDesc, String needsKey, boolean explored) {
        this.inventory = new ArrayList<Item>(); 
        this.name = name;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.needsKey = needsKey;
        this.explored = explored;
    }

    public String getName() {
        return(this.name);
    }
    
    public String getKeyColor() {
        return(this.needsKey);
    }

    public void addItem(Item item) {
        this.inventory.add(item);
    }

    public void removeItem(Item item) {
        this.inventory.remove(item);
    }


}
