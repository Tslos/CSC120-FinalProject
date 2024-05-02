import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.common.graph.*;

/**
 * The game class controlls the game, keeps track of the player, and keeps track of the map. 
 * @author tillie and reed
 */
public class Game {
    protected ArrayList<Item> inventory;
    public MutableGraph<Place> map;
    public Place currentPlace;
    public ArrayList<Place> currentConnections;
    public boolean stillPlaying;
    public boolean gameComplete;

    /**
     * the game constructor. sets up the user's inventory, sets up and places the user on the map. 
     */
    public Game() {
        this.inventory = new ArrayList<Item>();
        Place outside = new Place("OUTSIDE", "Outside the tower.", "You are outside the mage\'s tower. Grassy fields surround you. The mage is standing up at the top of the tower, pointing you towards where he dropped his BRASS KEY. The ENTRYWAY seems to be unlocked", "none");
        outside.addItem(new Item("BRASS KEY", "A gleaming BRASS KEY", false, 0));
        this.currentPlace = outside;
        this.map = this.ConstructMap(outside);
        this.findConnections();
        this.stillPlaying = true;
        this.gameComplete = false;
    }

    /**
     * Creates the map (a mutable graph) for the game. It creates all the places the player can move to and populates that place with an array list of items that live in it. The places and items get string descriptions.
     * @param startPlace 
     * @return
     */
    public MutableGraph<Place> ConstructMap(Place startPlace) {
        MutableGraph<Place> map = GraphBuilder.undirected().build();
        Place entryway = new Place("ENTRYWAY", "A triangular room with three painted doors.",
                "On the wall to your left is a RED DOOR. It seems newly painted. On the middle of the right wall is a newly painted BLUE DOOR. To the right of the BLUE DOOR is a YELLOW DOOR, also newly painted. A RED KEY hangs from a string in front of you.",
                "none");
        entryway.addItem(new Item("RED KEY",
                "A painted RED KEY, with a string through its handle so that it can be hung from a keyhook", false, 0));
        Place kitchen = new Place("KITCHEN", "A triangular room with a TABLE, CABINETS, CAULDRON, and TRAP DOOR.",
                "You are in a KITCHEN of sorts. Directly to your right is a TABLE and two CHAIRS. On the TABLE are three bottles of POTION, one PINK, one CYAN, one PURPLE, and some PARCHMENT. Across from you is a bubbling CAULDRON and on the left wall is a counter and several CABINETS. To your immediate left is a trap door to the BASEMENT with a black lock. The only exit is the RED DOOR.",
                "RED");
        kitchen.addItem(new Item("CABINET", "A wooden CABINET with some plates inside. Atop the plates is a BLACK KEY.", false, 0));
        kitchen.addItem(new Item("TABLE", "A nondescript wooden TABLE covered in small cuts. Someone wasn't using a cutting board...", false, 0));
        kitchen.addItem(new Item("CHAIRS", "A wicker chair. You notice one of the NAILs in the back is coming out.", false, 0));
        kitchen.addItem(new Item("NAIL", "A rusty NAIL", false, 0));
        kitchen.addItem(new Item("CAULDRON", "A CAULDRON bubbling with green liquid.", false, 0));
        kitchen.addItem(new Item("PINK POTION", "A mysterious potion colored pink. You do not know what it does.", false, 0));
        kitchen.addItem(new Item("CYAN POTION", "A mysterious potion colored cyan. You do not know what it does.", false, 0));
        kitchen.addItem(new Item("PURPLE POTION", "A mysterious potion colored purple. You do not know what it does.", false, 0));
        kitchen.addItem(new Item("PARCHMENT", "On this parchment are three diagrams:\nGreen circle + pink circle  = green circle\nGreen circle  + cyan circle  = green circle \nGreen circle  + purple circle  = an empty circle", false, 0));
        kitchen.addItem(new Item("BLACK KEY", "A painted BLACK KEY", false, 0));
        kitchen.addItem(new Item("BLUE KEY", "A painted BLUE KEY", false, 0));

        Place basement = new Place("BASEMENT", "A dank BASEMENT full of storage.", "The basement is one large square room with lots of nooks and crannies. There are piles of boxes and old furniture stacked haphazardly around you. This does not seem like a safe place for young nieces to hang about. There is a PAPER NOTE hanging from a string right in front of your face. ", "BLACK");
        basement.addItem(new Item("PAPER NOTE", "A PAPER NOTE that reads \'Darling Niece, this room is dark and musty. If you stay down here you will catch a cold, nothing useful to you is down here. p.s. pay no attention to the giant bees\'", false, 0));
        
        Place study = new Place("STUDY", "A cozy STUDY room.", "A square area RUG covers most of the floor and a large BOOKSHELF with some brown books and some orange books that takes up the whole outside wall. There is a big CHAIR in the corner across from the BOOKSHELF and a door that's been painted ORANGE across from you.", "BLUE");
        study.addItem(new Item("CHAIR", "An overstuffed CHAIR in the corner of the STUDY", false, 0));
        study.addItem(new Item("BOOKSHELF", "A BOOKSHELF with some orange books, and some brown. You notice that the orange books form a rectuangular outline on the shelf.", false, 0));
        study.addItem(new Item("RUG", "A square RUG on the floor of the STUDY. There is a small lump underneath... moving the rug reveals an ORANGE KEY", false, 0));
        study.addItem(new Item("ORANGE KEY", "A painted ORANGE KEY", false, 0));

        Place bedroom = new Place("BEDROOM", "A BEDROOM", "A BEDROOM with a BED, which has a PILLOW sitting on top. There is also a door painted WHITE across from you. On the wall is a PIECE OF PAPER.", "ORANGE");
        bedroom.addItem(new Item("PIECE OF PAPER", "A paper note that reads: \'I'm soft but I'm not a kitten\nI'm rectangular but I am not a loaf of bread\nI have a case but I'm not a detective\nI sometimes have feathers but I'm not a bird\nI'm found beneath a head but I'm not a scarf\nWhat am I?", false, 0));
        bedroom.addItem(new Item("PILLOW", "A feather pillow. Moving it reveals a WHITE KEY underneath", false, 0));
        bedroom.addItem(new Item("WHITE KEY", "A painted WHITE KEY", false, 0));
        bedroom.addItem(new Item("BED", "A BED with a PILLOW. There is nothing remarkable about it.", false, 0));

        Place bathroom = new Place("BATHROOM", "A small BATHROOM", " A BATHROOM. There's a TOILET on the far wall. Closer to the door is a SINK with a TRASH BIN underneath and a MIRROR above.", "WHITE");
        bathroom.addItem(new Item("TOILET", "This wizard should probably clean his TOILET more frequently...", false, 0));
        bathroom.addItem(new Item("SINK", "A nondescript SINK.", false, 0));
        bathroom.addItem(new Item("TRASH BIN", "A small TRASH BIN underneath the bathroom SINK. Moving a piece of tissue reveals a YELLOW KEY!", false, 0));
        bathroom.addItem(new Item("MIRROR", "This MIRROR is rather dirty... When you breath on it to try and wipe some grime off, an arrow design is exposed. The arrow points downward...", false, 0));
        bathroom.addItem(new Item("YELLOW KEY", "A painted YELLOW", false, 0));

        Place stairs = new Place("STAIRCASE", "A tall winding STAIRCASE.", "A tall winding spiral staircase. At the top of the stairs is a BRASS DOOR.", "YELLOW");

        Place balcony = new Place("BALCONY", "A balcony with a scenic view, and a sleepy old man", "A balcony with a scenic view, and a sleepy old man", "BRASS");
        map.putEdge(startPlace, entryway);
        map.putEdge(entryway, kitchen);
        map.putEdge(kitchen, basement);
        map.putEdge(entryway, study);
        map.putEdge(study, bedroom);
        map.putEdge(bedroom, bathroom);
        map.putEdge(entryway, stairs);
        map.putEdge(stairs, balcony);
        return (map);
    }

    /**
     * a method that doesn't return anything that finds all the places that are connected to the player's 
     * current place on the map. This was just to check to make sure the map is working. 
     */
    private void findConnections() {
        this.currentConnections = new ArrayList<Place>(this.map.adjacentNodes(this.currentPlace));
    }

    /**
     * A helpful function while coding to easily see which rooms are currently
     * "connected" to the currentPlace
     */
    public void printConnections() {
        System.out.println("You are currently in " + this.currentPlace.getName()
                + "\n\nThe following places are connected to this location:");
        for (Place room : this.currentConnections) {
            System.out.println(room.getName());
        }
    }


    /**
     * a method that allows the player to move between connected places on the map. 
     * @param placeName a string contianing the place the player wants to move to.
     * 
     */
    public void move(String placeName) {
        // System.out.println("You entered this place to go to: " + placeName);
        Place newPlace = null;
        // TO DO: decide what this returns for gameplay loopage
        // For each room next to the current one: is it the place the user wants to go?
        for (Place place : this.currentConnections) {
            String name = place.getName();
            String keyColor = place.getKeyColor();
            if (placeName.contains(name) || placeName.contains(keyColor)) {
                // loop through each inventory item
                for (Item item : this.inventory) {
                    // is it the key that we need? if yes, overwrite newPlace, because we can go
                    // there!
                    if (item.name.contains(keyColor)) {
                        newPlace = place;
                    }
                }
                // For the entryway, no key is needed, so this extra step is necessary because
                // the inventory at the moment is 0
                if (place.needsKey == "none") {
                    newPlace = place;
                }
            }
           
        }
        // After all the looping, check if newPlace was overwritten (we had a key and
        // were next to a door)
        if (newPlace != null) {
            // If we are good to go, change current places and rewrite current connections
            this.currentPlace = newPlace;
            System.out.println("You have moved to " + newPlace.name);
            this.findConnections();
        }
        else{
            System.out.println("I'm sorry, you either can't go there yet or misspelled a word. Try again! (If you don't know what to do, try typing \"Help\".) ");
        }
        if (this.currentPlace.name.contains("BALCONY")) {
            this.gameComplete = true;
        }
        
    }

    /**
     * a method that allows the player to learn more about an object. 
     * @param statement a string containing the name of the item the player wants to learn more about.
     */
    public void examine(String statement) {
        String itemDesc = null;
        for (Item obj : this.inventory) {
            if (statement.contains(obj.name)) {
                itemDesc = obj.shortDesc;
                break;
            }
        }
        if (itemDesc == null) {
            for (Item obj : this.currentPlace.inventory) {
                if (statement.contains(obj.name)) {
                    itemDesc = obj.shortDesc;
                    break;
                }
            }
        }
        itemDesc = itemDesc == null ? "This item does not appear to be nearby. Make sure you typed its name correctly!"
                : itemDesc;
        System.out.println(itemDesc);
    }


    /**
     * a method that doesn't return anything but does print out the players inventory. 
     */
    public void printInventory() {
        System.out.println("You have the following items in your inventory:");
        for (Item item : this.inventory) {
            System.out.println(item.shortDesc);
        }
    } 

    /**
     * a method that allows the player add an item to their inventory. This also removes the item from the room's inventory.
     * @param item a string containing the name of the object the player wants to add to their inventory. 
     */
    public void take(String item) {
        boolean itemFound = false;
        for (Item obj : this.inventory) {
            if (item.contains(obj.name)) {
                System.out.println("The item " + obj.name
                        + " is already in your inventory! If you meant a different item, make sure you've typed its full name in correctly (i.e., \"RED KEY\" instead of \"KEY\")");
                itemFound = true;
                break;
            }
        }
        if (itemFound == false) {
            for (Item obj : this.currentPlace.inventory) {
                if (item.contains(obj.name)) {
                    this.currentPlace.removeItem(obj);
                    this.inventory.add(obj);
                    System.out.println("You have taken " + obj.name);
                    itemFound = true;
                    break;
                }
            }
        }
        if (!itemFound) {
            System.out.println(
                    "This item does not appear to be nearby... make sure you typed it in correctly! (Hint: Type \'take [item]\')");
        }
    }

    /**
     * a method that allows the player to remove an item from their inventory and adds that item to the inventory of the room they are currently in.
     * @param item a string containing the name of the item
     */
    public void drop(String item) {
        boolean itemFound = false;
        for (Item obj : this.inventory) {
            if (item.contains(obj.name)) {
                this.inventory.remove(obj);
                this.currentPlace.addItem(obj);
                System.out.println("You have dropped the " + obj.name);
                itemFound = true;
                break;
            }
        }
        if (!itemFound) {
            System.out.println("There doesn't appear to be a " + item
                    + " in your inventory. If you think this is an error, make sure you type in the full name of an object!");
        }
    }

    /**
     * a method allowing the user to pour a potion into the cauldron, revealing the blue key. 
     * @param action a string containing the color of the potion they are using. 
     */
    public void pourPotion(String action) {
        if (action.contains("PINK")) {
            System.out.println("You pour the PINK POTION into the CAULDRON. A puff of smoke goes up, but once it clears, nothing has changed. Perhaps you should look around for clues?");
        } else if (action.contains("CYAN")) {
            System.out.println("You pour the CYAN POTION into the CAULDRON. A puff of smoke goes up, but once it clears, nothing has changed. Perhaps you should look around for clues?");
        }else if (action.contains("PURPLE")) {
            System.out.println("You pour the PURPLE POTION into the CAULDRON. A puff of smoke goes up, and once it clears, you see the cauldron is now empty of liquid. A BLUE KEY lies in the bottom of it.");
        }
    }

    /**
     * a method that is run on all scanned user input while the game is being played. It finds the key word for the
     * available actions (move, take, drop, etc) and sends that input to the appropriate method. 
     * @param action a string containing what the player wants to do
     */
    public void executeAction(String action) {
        if (action.contains("MOVE ")) {
            this.move(action);
        } else if (action.contains("LOOK AROUND")) {
            System.out.println(this.currentPlace.longDesc);

        } else if (action.contains("EXAMINE ")) {
            this.examine(action);
        } else if (action.contains("TAKE ")) {
            this.take(action);
        } else if (action.contains("DROP ")) {
            this.drop(action);
        } else if (action.contains("POTION")) {
            this.pourPotion(action);
        } else if (action.contains("EXIT")) {
            this.stillPlaying = false;
        } else if (action.contains("HELP")) {
            this.help();
        } else{
            System.out.println("I'm sorry, I do not understand what you wrote. If you don't know what to do, try typing \"Help\".");
        }
    }

    /**
     * a method that prints out the actions that the player can do as well as how the game expects the input to be
     * formateed. 
     */
    public void help(){
        System.out.println("************** HELP MENU ************** \nYou can call this menu up at any time by typing the command ‘help’ into the console. \nThe action formula this game allows are:\n" + //
                        "move to [place]\n" + //
                        "move to [color] [door]\n" + //
                        "look around\n" + //
                        "examine [item]\n" + //
                        "take [item]\n" + //
                        "drop [item]\n" + //
                        "pour [color] potion\n" + //
                        "exit\n" + //
                        "help\n");
    }

    /**
     * a method that sets up the game (imports the scanner, prints exposition and helpful action reccomendations) 
     * and sets up the do...while loop that will keep the game running. 
     * When the do...while loop ends, it will print out the end of game code, and let the player accept or decline
     * the mage's reward offer. 
     * It also closes the scanner.
     */
    public void play() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\n************** WELCOME TO THE MAGE'S TOWER **************\n");
        this.help();
        System.out.println("It\'s a sunny day and you are surrounded by beautiful grassland. Ahead is a tower, similar to one most mages build, and beyond that is your destination: Wildeshore City. Due to excellent travel conditions, you\'re running two days early and should arrive by nightfall. \n" + //
                        "As you begin to pass by the mage\'s tower you hear a voice from above: \n\"What ho there, traveler! Do you think you could lend an old man a hand?\" \n" + //
                        "Looking up, you see a weathered man with a long beard waving down at you from the tower\'s balcony. When he sees that he has your attention, he calls out again:\n" + //
                        "\"I seem to be rather stuck up here! You see, I was making a puzzle for my neice but I\'ve locked myself at the top of my tower and dropped the key!\"\n" + //
                        "Casting a look around, you indeed see a BRASS KEY in the grass next to the tower. \n" + //
                        "\"If you could come up and unlock this door, I\'d be forever grateful,\" the mage continues, \"I can even give you a reward if you\'d like.\" \n" + //
                        "A reward does sound nice… and you\'re running early.\n" + //
                        "");
        
        System.out.println("What would you like to do? (hint: try looking around for a moment!)");
        do {
            executeAction(sc.nextLine().toUpperCase());
        } while (this.stillPlaying && !this.gameComplete);
        if (this.gameComplete) {
            System.out.println("You open the final door, revealing a covered balcony. On the stone floor is an intricate painting of the sun. While most of the building is made of pale yellow stone, the domed ceiling above you is a beautiful robin\'s egg blue decorated with gold accents. In front of you is the old man, who has obviously just woken up at the sound of your entrance. He seems very happy to see you.");
            System.out.println("\"Well done traveler!\" he says, \"I knew you would crack those puzzles eventually. Now let\'s see, about that reward…\" he pauses for several seconds before seeming to get an idea; \"How about I\'ll make another puzzle for you for when you travel back this way hm?\"");
            String acceptPuzzle = "";
            do {
                System.out.println("Do you accept his offer? (yes/no)");
                acceptPuzzle = sc.nextLine().toUpperCase();
            } while (!(acceptPuzzle.contains("YES") || acceptPuzzle.contains("NO")));
            if (acceptPuzzle.contains("YES")) {
                System.out.println("\"Excellent!\" The old man says, rubbing his hands together gleefully. \"I'll try to make the next one even harder, suitable for an adventurer so talented as yourself. Until then...\"");
            } else {
                System.out.println("\"I understand,\" he says, \"surely an adventurer so talented as yourself must be very busy. I thank you sincerely for helping me out of my predicament, and wish you a merry journey forward!\"");
            }
            System.out.println("He spins on the spot, and disappears in a puff of smoke. You make your way to Wildeshore City with only one remaining question: if he could teleport, why didn't he just get down that way?\"");
        }
        sc.close();
    }
    

    /**
     * the main method that creates the game and runs the play() method on the new game. 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }

}

