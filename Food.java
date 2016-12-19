import java.awt.Point;
import java.util.Random;


public class Food {
	
	public static Random foodX;
	public static Random foodY;
	public static Point foodPos;
	
	//initializes foodx and foody to random position in game window
	public Food()
	{	
		foodX = new Random();
		foodY = new Random();
	}
	//creates a new point which will be called in controller and placed in game window
	public static void DrawFood()
	{
		foodPos = new Point(foodX.nextInt(785) + 1,foodY.nextInt(675)+ 1);
	}


}
