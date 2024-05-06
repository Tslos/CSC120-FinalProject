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
