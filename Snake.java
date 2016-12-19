import java.awt.Point;
import java.util.ArrayList;

public class Snake {
	
	public ArrayList<Point> segments = new ArrayList<Point>();
	public Point snakeHead;	
	static private int length;
	
	public Snake()
	{	
		//initializes the head of the snake 
		snakeHead = new Point(50, 50);
		length = 1;
		//adds the head to the array that keeps the segments of the snake
		segments.add(snakeHead);
	}
	public void DrawSnake(Point s)
	{
		//adds a point aka segment of the snake to the array
		addSegment(s);
		//repaints the canvas with the new segments
		Run.drawCanvas.repaint();
	}
	//adds point to array
	private void addSegment(Point s)
	{
		segments.add(s);
	}
	public void GrowTail()
	{
		length += 20;
	}
	public int GetTailLength()
	{
		return length;
	}
	

	
	}
