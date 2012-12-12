import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeSet;

public class Astar {
	public static ArrayList<Tile> getShortestPath(GameMap map, Tile start, Tile goal) throws PathNotFoundException {
		
        HashTreeSet openSet = new HashTreeSet();
        
        HashSet<Tile> closedSet = new HashSet<Tile>();
        Map<Tile,Tile> cameFrom = new HashMap<Tile, Tile>();

        start.setGScore(0);
        start.setFScore(getHeuristic(start, goal));
        openSet.add(start);
        
        while (openSet.size()!= 0){
        
            Tile current = openSet.pollFirst();
            
            if (current.equals(goal)){
            	//System.out.println(openSet.size());
            	return createPath(current, start, cameFrom);
            }
            
        	closedSet.add(current);
            
        	for (Tile neighbor : map.getAdjacentSquares(current)){
            	
            	if (!closedSet.contains(neighbor)){	// Check for values
            		int tentGScore = current.getGScore() + cost(map.getValue(neighbor));
            		
            		if (!openSet.contains(neighbor) || neighbor.getGScore() <= tentGScore) {	// Check for values
            			neighbor.setGScore(tentGScore);
                		neighbor.setFScore(tentGScore + getHeuristic(neighbor, goal));
            			cameFrom.put(neighbor, current);
                        
                        if (!openSet.contains(neighbor)){
                        	openSet.add(neighbor);
                        }
                        
                    }
            		
                }
            	
            }
        }
        throw new PathNotFoundException();
    }


	private static int cost(int i) {
		if (i == 2){
			return Enemy.stuckTime;
		}
		return 1;
	}


	private static ArrayList<Tile> createPath(Tile goal, Tile start, Map<Tile, Tile> cameFrom) {
        
		// Collect path
		ArrayList<Tile> path = new ArrayList<Tile>();
        Tile from = goal;
        
        while (!(from == start)){
            path.add(from);
            from = cameFrom.get(from);
        }
        
        // Reverse list
        Collections.reverse(path);
        
        return path;
    }

    private static int getHeuristic(Tile start, Tile goal) {
        int width = Math.abs(start.getX() - goal.getX());
        int height = Math.abs(start.getY() - goal.getY());
        return width + height; // Manhattan distance
        //return (int)(Math.sqrt(width*width + height*height))*10;	// Diagonal distance
    }
}
