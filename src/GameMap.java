import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class GameMap {
	
	private int[][] mapArr;
	
	private Tile charPos;;
	
	private ArrayList<Enemy> enemies;
	
	private ArrayList<Integer> walkables = new ArrayList<Integer>(Arrays.asList(0,2));

	private int wallPercentage = 75;

	private int swampPercentage = 75;

	private int height = 64;

	private int width = 128;
	
	public GameMap(){
		mapArr = generateMap(width, height);
	}
	
	private int[][] generateMap(int width, int height) {
		
		int[][] arr;
		
		arr = new int[height][width];
		
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				int value = 0;
				// Border?
				if (y == 0 || y == height - 1 || x == 0 || x == width - 1){
					value = 1;
				} else {
					Random random = new Random();
					// Wall?
					if(random.nextInt(100) > wallPercentage){
						value = 1;
					// Swamp?
					} else if(random.nextInt(100) > swampPercentage){
						value = 2;
					}
				}
				arr[y][x] = value;
			}
		}
		
		// Add hero
		arr[height-2][width/2] = 0;
		charPos = new Tile(width/2, height-2);
		
		// Add enemies
		arr[1][1] = 0;
		arr[2][1] = 0;
		arr[2][2] = 0;
		arr[1][2] = 0;
		arr[1][width - 2] = 0;
		arr[1][width - 3] = 0;
		arr[2][width - 3] = 0;
		arr[2][width - 2] = 0;
		enemies = new ArrayList<Enemy>(Arrays.asList(
					new Enemy(1,1), 
					new Enemy(width -2,1)
				));
		
		return arr;
	}

	public void loadMapFromFile(String filename) throws IOException{
		File file = new File(filename);
		loadMapFromString(fileToString(file));
	}

	private void loadMapFromString(String str) {
		
		
	}

	public ArrayList<Tile> getAdjacentSquares(Tile tile) {
		
		ArrayList<Tile> adjacent = new ArrayList<Tile>();
		int x = tile.getX();
		int y = tile.getY();
		
		// Left?
		if (x > 0){
			Tile left = new Tile(x-1, y);
			if (isWalkable(left)){
				adjacent.add(left);
			}
		}
		
		// Right?
		if (x < mapArr[0].length - 1){
			Tile right = new Tile(x+1, y);
			if (isWalkable(right)){
				adjacent.add(right);
			}
		}
		
		// Up?
		if (y > 0){
			Tile up = new Tile(x, y-1);
			if (isWalkable(up)){
				adjacent.add(up);
			}
		}
		
		// Down?
		if (y < mapArr.length - 1){
			Tile down = new Tile(x, y+1);
			if (isWalkable(down)){
				adjacent.add(down);
			}
		}
		
		return adjacent;
	}

	public boolean isWalkable(Tile t) {
		int value = mapArr[t.getY()][t.getX()];
		if (walkables.contains(value)){
			return true;
		}
		return false;
	}
	
	private String fileToString(File file) throws IOException {
      int len;
      char[] chr = new char[4096];
      StringBuffer buffer = new StringBuffer();
      FileReader reader = new FileReader(file);
      try {
          while ((len = reader.read(chr)) > 0) {
              buffer.append(chr, 0, len);
          }
      } finally {
          reader.close();
      }
      return buffer.toString();
  }

	public Tile getCharPos() {
		return charPos;
	}

	public void setCharPos(Tile charPos) {
		this.charPos = charPos;
	}

	public int[][] getMapArr() {
		return mapArr;
	}

	public void setMapArr(int[][] mapArr) {
		this.mapArr = mapArr;
	}

	public ArrayList<Integer> getWalkables() {
		return walkables;
	}

	public void setWalkables(ArrayList<Integer> walkables) {
		this.walkables = walkables;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}

	public int getValue(Tile position) {
		
		return mapArr[position.getY()][position.getX()];
	}
}
