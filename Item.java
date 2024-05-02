/**
 * The item class creates items that have names, descriptions, and (if they can only be used a set number of times)
 * sets their limited use and how many times they can be used. 
 * @author tillie
 */
public class Item {
    String name;
    String shortDesc;
    boolean limitedUse;
    int numUses;


    public Item(String name, String shortDesc, boolean limitedUse, int numUses) {
        this.name = name;
        this.shortDesc = shortDesc;
        this.limitedUse = limitedUse;
        this.numUses = numUses;
    }
    public Item(String desc) {
        this.name = desc;
        this.shortDesc = desc;
        this.limitedUse = false;
        this.numUses = 1;
    }

    /**
     * a method that allows the item to be used. 
     * @return a String stating that the player has used the item. 
     */
    protected String use() {
        String result = "You have used this " + this.shortDesc;
        if (this.limitedUse) {
            this.numUses = this.numUses - 1;
        } 
        if (this.numUses == 0) {
            result = this.shortDesc + "has been used up!";
        }
        return(result);
    }

    
}
