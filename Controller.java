import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.Timer;

public class Controller implements ActionListener, KeyListener{
	public static Snake snake;
	public static Food food;
	public Timer timer;
	//constants for movement controls
	public static final int DOWN = 0, UP = 1, RIGHT = 2, LEFT = 3;
	public static int direction, score, time = 0;
	public int step;
	public static String newHighScore;
	public static boolean gameOver;
	public Scanner in;
	public FileWriter out;
	public static boolean printScore;
	public static int temp;
	public static String result;
	public static File directory;
	String highScoreFile;
	
	
	public Controller()
	{
		snake = new Snake();
		//initializes timer which will trigger the ActionListener to keep the snake moving
		timer = new Timer(7, this);
		//sets initial state of the game
		setState();
		//adds KeyListener to JFrame Window in Run.java
		Run.window.addKeyListener(this);
	
		timer.start();
		
		//hopefully allows high Score File to be accessible regardless of relative file path
		directory = new File("").getAbsoluteFile();
		highScoreFile = directory + "/src/highscore.txt";
		
		
	}
	public void setState()
	{
		//snake starts at top of window and goes down
		direction = DOWN;
		score = 0;
		gameOver = false;
		//increment the snake moves with every timer pulse
		step = 1;
		//clears contents of ArrayList containing snake segments
		snake.segments.clear();
		printScore = false;
		//initializes food
		food = new Food();
		Food.DrawFood();
		
	}
	// checks to see that snake has not run into itself
	public boolean EatsItself(int x, int y)
	{
		//loops over each point to make sure they are not equal
		for(Point seg: snake.segments)
		{
			if(seg.equals(new Point(x, y)))
				return gameOver = true;
				
		}
		return gameOver = false;
	}
	//checks highscore.txt to see if the high score has been exceeded
	public void SetHighScore()
	{
		temp = -1;
		try{
			in = new Scanner(new File(highScoreFile));
			if(in.hasNextInt())
				temp = in.nextInt();
			
			if(temp < score)
			{
				newHighScore = String.valueOf(score);
				out = new FileWriter(new File(highScoreFile));
				out.write(newHighScore);
				//signals that a new high score has been set
				printScore = true;
				
				out.flush();
				out.close();
			}
			else
				result = String.valueOf(temp);
			
			in.close();
		}
		//Display error message if File can't be open
		catch(FileNotFoundException e)
		{
			
			result = "High Score File could not be read\nCheck File Path on line 46 of Controller.";
		}
		catch(IOException e)
		{
			result = "High Score File could not be read";
		}
			
	}
	//controls movement of snake
	@Override
	public void actionPerformed(ActionEvent e) {
		//draws the snake 
		snake.DrawSnake(snake.snakeHead);
		if(gameOver)
		{
			SetHighScore();
			timer.stop();
		}
		switch(direction)
		{
			case DOWN:
					//if snake doesn't hit border and it's not eating itself, snake continues down
					if(snake.snakeHead.y < 670 && !EatsItself(snake.snakeHead.x, snake.snakeHead.y + step))
						snake.snakeHead = new Point(snake.snakeHead.x, snake.snakeHead.y + step);
					else
						gameOver = true;
					break;
			case RIGHT:
					//if snake doesn't hit right border and not eating itself continue right
					if(snake.snakeHead.x < 800 && !EatsItself(snake.snakeHead.x + step, snake.snakeHead.y))
						snake.snakeHead = new Point(snake.snakeHead.x + step, snake.snakeHead.y);
					else
						gameOver = true;
					break;
			case LEFT:
					//if snake doesn't hit left border and not eating itself continue left
					if(snake.snakeHead.x > 5 && !EatsItself(snake.snakeHead.x - step, snake.snakeHead.y))
						snake.snakeHead = new Point(snake.snakeHead.x - step, snake.snakeHead.y);
					else
						gameOver = true;
					break;
			case UP:
					//if snake not hitting top border and not eating itself, continue up
					if(snake.snakeHead.y > 5 && !EatsItself(snake.snakeHead.x, snake.snakeHead.y - step))
						snake.snakeHead = new Point(snake.snakeHead.x, snake.snakeHead.y - step);
					else
						gameOver = true;
					break;	
		}
		
		//ensures the Canvas draws the snake appropriately.
		if(snake.GetTailLength() < snake.segments.size())
			snake.segments.remove(0);
		//if the snakehead comes within range of food, it is "eaten" and length is increased to increase size
		//of snake as well as score
		//use distance because hitting the exact point was too hard
		if(snake.snakeHead.distance(Food.foodPos) < 6)
		{
			score++;
			snake.GrowTail();
			Food.DrawFood();
			//increases snake speed every 20 points
			if(score %  20 == 0)
				step++;
		}
	}
	//Handles key input and sets direction of snake
	@Override
	public void keyPressed(KeyEvent e) {
		
		int input = e.getKeyCode();

		if (input == KeyEvent.VK_LEFT && direction != RIGHT)
			direction = LEFT;
		
		if (input == KeyEvent.VK_RIGHT && direction != LEFT)
			direction = RIGHT;
	
		if (input == KeyEvent.VK_UP && direction != DOWN)
			direction = UP;
		
		if (input == KeyEvent.VK_DOWN && direction != UP)
			direction = DOWN;
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
