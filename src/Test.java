import java.util.Comparator;
import java.util.TreeSet;

import javax.swing.JFrame;


public class Test {
	
	public static void main(String[] args) {
        
		TreeSet<Tile> tileSet = new TreeSet<Tile>( new Comparator<Tile>() {

			@Override public int compare(Tile t1, Tile t2) {
				
				return t1.getFScore() - t2.getFScore();
				
			}
			
		});
		
		boolean t1 = new Tile(0,0).equals(new Tile(0,0));
		System.out.println(t1);
		
		boolean t2 = new Tile(0,0) == new Tile(0,0);
		System.out.println(t2);
		
		Tile a = new Tile(1,1);
		a.setFScore(9);
		if (!tileSet.contains(a)){
			tileSet.add(a);
			System.out.println("a: " + tileSet);
		}
		
		Tile b = new Tile(1,2);
		b.setFScore(9);
		if (!tileSet.contains(b)){
			tileSet.add(b);
			System.out.println("b: " + tileSet);
		}
		
		Tile c = new Tile(3,2);
		c.setFScore(2);
		if (!tileSet.contains(c)){
			tileSet.add(c);
			System.out.println("c: " + tileSet);
		}
		
		
		Tile d = new Tile(1,2);
		d.setFScore(10);
		if (!tileSet.contains(d)){
			tileSet.add(d);
			System.out.println("d: " + tileSet);
		}
		
	}
	
}
