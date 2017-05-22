import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class LifeGame extends Applet implements Runnable {
	public static final int CELL_SIZE = 15;
	public static final int INTERVAL = 150;
	
	Thread thread = null;
	Board board;
	
	public void init() {
		board = new Board(20, 20, 0.4);
		
		thread = new Thread(this);
		thread.start();
	}
	
	// restart
	public void start() {}
	public void update() {}
	
	public void paint (Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, board.getWidth()*CELL_SIZE, board.getHeight()*CELL_SIZE);
		int w = board.getWidth();
		int h = board.getHeight();
		g.setColor(Color.GREEN);
		for (int y = 0; y < h; y++)
			for (int x = 0; x < w; x++)
				if (board.isAlive(x, y))
					g.fillRect(x*CELL_SIZE, y*CELL_SIZE, CELL_SIZE, CELL_SIZE);
	}
	
	public void run() {
		while (true) {
			board.update();
			repaint();
			try {
				thread.sleep(INTERVAL);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
