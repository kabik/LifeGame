
public class Board {
	private int width, height;
	private double denth;
	private boolean cells[][];
	private int neighborhood[][];

	public Board(int width, int height, double denth) {
		this.width = width;
		this.height = height;
		this.denth = denth;
		cells = new boolean[width][height];
		neighborhood = new int[width][height];
		reset();
	}

	public void reset() {
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++) {
				cells[x][y] = (Math.random() < getDenth());
			}
	}

	public void update() {
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				neighborhood[x][y] = 0;
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				for (int h = -1; h <= 1; h++) 
					for (int w = -1; w <= 1; w++)
						if (inside(x+w, y+h) && cells[x+w][y+h] && (w != 0 || h != 0))
							neighborhood[x][y]++;
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				cells[x][y] = canBeAliveNext(x, y);		
	}

	public void show() {
		System.out.println("---");
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				if (isAlive(x, y)) {
					System.out.print(1);
				} else {
					System.out.print(0);
				}
				System.out.print(' ');
			}
			System.out.println();
		}
		System.out.println("---");
	}

	public boolean inside(int x, int y) {
		return x >= 0 && x < getWidth() && y >= 0 && y < getHeight();
	}
	public int getWidth() { return this.width; }
	public int getHeight() { return this.height; }
	public double getDenth() { return this.denth; }
	public boolean isAlive(int x, int y) { return this.cells[x][y]; }
	public boolean canBeAliveNext(int x, int y) {
		return neighborhood[x][y] == 3 || (cells[x][y] && neighborhood[x][y] == 2);
	}
}
