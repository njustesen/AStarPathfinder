
public class Tile {
	private int x;
	private int y;
	private int fScore;
	private int gScore;
	
	public Tile(int x, int y){
		this.x = x;
		this.y = y;
		this.gScore = 1000000;
		this.fScore = 1000000;
	}

	@Override 
	public boolean equals(Object o) {
		if (this == o) return true;
		
		if (o == null || getClass() != o.getClass()) return false;
		
		Tile test = (Tile) o;
		
		if (x == test.getX() && y == test.getY()) return true;
		
		return false;
	}
	
	@Override
	public int hashCode() {
		int hash = 1;
        hash = hash * 17 + x;
        hash = hash * 31 + y;
        return hash;
	}
	
	public int getFScore() {
		return fScore;
	}
	
	public void setFScore(int fScore) {
		this.fScore = fScore;
	}
	
	public int getGScore() {
		return gScore;
	}
	
	public void setGScore(int gScore) {
		this.gScore = gScore;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "X: " + x + ", Y: " + y;
	}

	public boolean isNextTo(Tile position) {
		if (position.x > x +1) return false;
		if (position.x < x -1) return false;
		if (position.y > y +1) return false;
		if (position.y < y -1) return false;
		return true;
	}
}
