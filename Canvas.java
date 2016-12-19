import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Canvas extends JPanel {
	Random r;
	Random gC;
	Random b;
	//initializes colors for GUI
	public static Color green = new Color(0, 153, 0);
	public static Color garnet = new Color(134,38,51);
	public static Color gold = new Color(238,212,132);
	//will display High Score
	public static String highscore;
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		//sets background to green
		g.setColor(green);
		g.fillRect(0, 0, 800, 700);
		
		//sets the food to gold
		g.setColor(gold);
		g.fillRect(Food.foodPos.x, Food.foodPos.y, 10, 10);
		//paints snake based on number of points in segment array
		for(Point seg: Controller.snake.segments)
		{
			g.setColor(garnet);
			g.fillRect(seg.x, seg.y, 10, 10);
		}
		
		String scoreBoard = new String("Score: " + Controller.score);
		g.setColor(Color.WHITE);
		//prints score at top of screen
		g.drawString(scoreBoard, (int) (getWidth() / 2 - scoreBoard.length() * 2.5), 10);
		
		String gameOver = new String("Game Over!\nScore " + Controller.score);
		if(Controller.printScore)
			highscore = new String("\nYou set a new high score!\nHighScore is: " + Controller.newHighScore);
		else
			highscore = new String("\nHighScore is: " + Controller.result);
		
		if(Controller.gameOver)
		{
			g.drawString(highscore, (int) (getWidth() / 2 - highscore.length() * 2.5), getHeight() / 2);
			g.drawString(gameOver, (int) (getWidth() / 2 - highscore.length() * 2.5), (getHeight() / 2)-15);
			
		}
		
	}
	
}
