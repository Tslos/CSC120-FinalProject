# CSC120-FinalProject

## Deliverables:
 - Your final codebase
 - Your revised annotated architecture diagram
 - Design justification (including a brief discussion of at least one alternative you considered)

    We ended up with three separate classes(Game, Place, and Item) for our game. In the beginning, we actually had an additional Player class but we decided to integrate it into the Game class because Player was doing the same things the Game needed to do so it made more sense to make one larger Game class then have two slightly smaller classes that did mostly the same thing. The Game class essentially keeps track of the user’s progress and place in the game. It instantiates the entire map of the game, holds all the methods containing the player’s actions, and has a play() method that holds the do…while loop and prints the exposition and ending to the game. The main method also sits in the Game class - for simplicity in the main method, the Game.play() method was constructed so that the only code necessary in the main method was the construction of a Game object, and a call to the Game.play() method. 

 - A map of your game's layout (if applicable)
 - `cheatsheet.md`
 - Completed `rubric.md`
  
## Additional Reflection Questions
 - What was your **overall approach** to tackling this project?

    We both knew that this sort of project would take a lot of time so from the first day we set up a weekly flexible meeting time so we would always have at least one chance a week to talk in person outside of class. We spent most of the first few classes just talking about what we hoped to include and what we thought wouldn’t work. This thoughtful approach, even though it slowed down the actual coding aspect of the project, helped us keep a clear idea of the project in mind. We made sure each of us was having a good time by assigning tasks that were enjoyable as well as challenging. For example, Reed did a lot of the storyboarding and creating descriptions for items and rooms as well as coming up with the puzzles the user would interact with along the way, while Tillie spent more time integrating and adapting those ideas into the actual code.

 - What **new thing(s)** did you learn / figure out in completing this project?
    The biggest “new thing” to learn was definitely Guava maps. There were a lot of different classes to comb through before we could settle on the final one. We even had to change the class last minute from MutableGraph<Place> to ImmutableGraph<Place> after making a secondary design decision about how the take() method would work (previously thought it would add a connection between nodes if a room had been unlocked, scrapped that idea for the current procedure). Although it didn’t end up being used in the final iteration, we also learned a bit about the Pattern() and Matcher() classes, because there was a moment when we wanted to parse through the user-input action to find the secondary action specifier (i.e. ‘red key’ in ‘take red key’), but realized halfway through implementation that it was much simpler to just test for equality using String.contains(). 
    
 - Is there anything that you wish you had **implemented differently**?
    
    It would have been nice to impose a size limit on the user’s inventory, so that if they took a lot of extraneous stuff, they would be forced to drop some items. Perhaps we could have even imposed a limit that forced the player to think about which keys they no longer needed, adding another layer of thinking ahead.
    
 - If you had **unlimited time**, what additional features would you implement?

    If we had more time, we would have developed a relationship between the items in a room and the description that gets printed out when the user looks around. As is, taking an item does not change the description at all, but it would be more realistic for the Item() class to have some sort of attribute that gets added to the longDesc attribute of a Place() when it is added to the Place’s inventory. 
    
 - What was the most helpful **piece of feedback** you received while working on your project? Who gave it to you?

    One piece of feedback we heard a few times (but primarily from Ian Eskesen) during workshop 3 was to make it clearer what interaction options were available to the user. This led us to capitalize every interactable item so that it was more clear to the user what was meant to be examined, picked up, etc. We were slightly concerned that it would be too much “hand holding” but reasoned that meta-knowledge infiltrates even the most open-world games, so it’s probably fine for this small-scale project. 
    
 - If you could go back in time and give your past self some **advice** about this project, what hints would you give?

 We would tell our past selves that they have to consider making a smaller game than what they originally thought of. Our original game concept was about double the size with a whole mirror realm-rescue situation. While it was a great idea, we definitely didn’t have the time to achieve it so it's very good that we ended up cutting it down. Having it be smaller from the start probably would have lessened the collective stress though.
    
 - _If you worked with a team: please comment on how your **team dynamics** influenced your experience working on this project.
    
We had very good communication! Frequent discord messages, meetings, etc. Specifically the frequent meetings were very helpful in keeping each other up to date. Each team member played to their strengths in terms of planning and execution of the game idea. 

Thank you to Reed for the amazing and wonderful idea of not doing a two-layered tower game when we realized how little time we had left…… the puzzle that ended up being executed was much more manageable and fun to play in one sitting!! 

Shout out to Tillie for slaying so much of the code. I would not have been able to complete even a fraction of what they did in the given time by myself. 

Also apologies to Jordan for the many many pull requests…
