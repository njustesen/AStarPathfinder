import java.util.ArrayList;
import java.util.Date;


public class Enemy{
	private Tile position;
	private int stuck;
	public static final int stuckTime = 20;

	public Enemy(Tile position) {
		this.position = position;
		this.stuck = 0;
	}

	public Enemy(int x, int y) {
		this.position = new Tile(x, y);
	}

	Tile getPosition() {
		return position;
	}

	public void setPosition(Tile position) {
		this.position = position;
	}
	
	public int getStuck() {
		return stuck;
	}

	public void setStuck(int stuck) {
		this.stuck = stuck;
	}

	public void act(GameMap map, Tile hero){
		
		ArrayList<Tile> path = new ArrayList<Tile>();
		
		if (stuck > 0){
			stuck--;
		} else {
		
			// Search for shortest route
			try {
				long timeA = new Date().getTime();
				path = Astar.getShortestPath(map, position, hero);
				long timeB = new Date().getTime();
				System.out.println("Search took: " + (timeB - timeA) + " ms.");
			} catch (PathNotFoundException e) {
				System.out.println("Path not found");
				//e.printStackTrace();
			}
			
			// Move one tile
			if (path.size() > 0 && path.get(0).isNextTo(position)){
				position = path.get(0);
				
				// Stuck?
				if(map.getValue(position) == 2){
					stuck = stuckTime ;
				}
			}
			
		}
	}
	
}
