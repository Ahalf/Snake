import javax.swing.JFrame;

class Run 
{
	public static JFrame window;
	public static Canvas drawCanvas;
	static Controller execute;
	
	//initializes game window and Controller class where game logic occurs
	public static void main(String[] args)
	{
		
		window = new JFrame("Snake");
		window.setVisible(true);
		window.setResizable(false);
		window.setSize(800, 700);
		drawCanvas = new Canvas();
		window.add(drawCanvas);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		execute = new Controller();
		
		

		
	}
	
}

