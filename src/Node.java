
public class Node implements Comparable<Node>{

	Tile position;
	private int fScore;
	private int gScore;
	
	public Node (Tile position){
		this.position = position;
		this.fScore = 0;
		this.gScore = 0;
	}
	
	public Node (Tile position, int gScore, int fScore){
		this.position = position;
		this.gScore = gScore;
		this.fScore = fScore;
	}
	
	@Override
	public int compareTo(Node other) {
		return fScore - other.getFScore();
	}
	
	public boolean equals(Object o) {
		if (this == o) return true;
		
		if (o == null || getClass() != o.getClass()) return false;
		
		Node test = (Node) o;
		
		if (position != null ? !position.equals(test.getPosition()) : test.getPosition() != null) return false;
		
		return true;
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
	
	public Tile getPosition(){
		return this.position;
	}

	public void setPosition(Tile position) {
		this.position = position;
	}
}
