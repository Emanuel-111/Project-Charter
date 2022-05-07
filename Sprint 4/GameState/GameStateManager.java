package GameState;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameStateManager {

	private ArrayList<GameState> gameStates; // States are in arrays
	
	private int currentState; // A number that knows the current state
	
	// Gradually increase by 1 for each state
	public static final int MENUSTATE = 0;
	public static final int LEVEL0STATE = 1;
	public static final int LEVEL1STATE = 2;
	public static final int LEVEL2STATE = 3;
	public static final int LEVEL3STATE = 4;
	public static final int FIRSTSTORYBOOK = 5;
	public static final int SECONDSTORYBOOK = 6;
	public static final int THIRDSTORYBOOK = 7;
	public static final int LEVEL4STATE = 8;
	public static final int FINALCUTSCENE = 9;
	public static final int TUTORIALSTATE = 10;
	
	
	public GameStateManager()
	{
		// Each state in the game will be contained in an array
		gameStates = new ArrayList<GameState>();
		
		// The game will always start with the Menu State unless changed
		currentState = MENUSTATE;
		
		// For each state, you must follow the lines of code to add them
		gameStates.add(new MenuState(this));
		gameStates.add(new Level0State(this));
		gameStates.add(new Level1State(this));
		gameStates.add(new Level2State(this));
		gameStates.add(new Level3State(this));
		gameStates.add(new FirstStoryBook(this));
		gameStates.add(new SecondStoryBook(this));
		gameStates.add(new ThirdStoryBook(this));
		gameStates.add(new Level4State(this));
		gameStates.add(new FinalCutscene(this));
		gameStates.add(new TutorialState(this));
		
	}
	
	// Sets the state of the game
	public void setState(int state)
	{
		currentState = state;
		gameStates.get(currentState).init();
	}
	
	// Updates certain aspects in the state of the game
	public void update()
	{
		gameStates.get(currentState).update();
	}
	
	// Draws the current state
	public void draw(Graphics2D g)
	{
		gameStates.get(currentState).draw(g);
	}
	
	// Allows for key presses in the current state
	public void keyPressed(int k)
	{
		gameStates.get(currentState).keyPressed(k);
	}
	
	// Allows for key releases in the current state
	public void keyReleased(int k)
	{
		gameStates.get(currentState).keyReleased(k);
	}
	
	// When complete, head to MenuState
}
