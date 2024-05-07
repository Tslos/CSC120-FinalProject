/**
 * The item class creates items that have names, descriptions, and (if they can only be used a set number of times)
 * sets their limited use and how many times they can be used. 
 * @author tillie
 */
public class Item {
    String name;
    String shortDesc;


    public Item(String name, String shortDesc) {
        this.name = name;
        this.shortDesc = shortDesc;
    }
    public Item(String desc) {
        this.name = desc;
        this.shortDesc = desc;
    }

    
}
