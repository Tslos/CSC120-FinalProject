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
<<<<<<< Updated upstream

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
=======
>>>>>>> Stashed changes
}
