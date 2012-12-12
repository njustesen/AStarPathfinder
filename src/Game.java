import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Game extends JPanel {
	
	public static int FPS = 12;
	
	private GameMap gameMap;
	private Hero hero;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private int tileSize = 5;
	
	public Game(){
		gameMap = new GameMap();
		setPreferredSize(new Dimension(gameMap.getMapArr()[0].length * tileSize, gameMap.getMapArr().length * tileSize));
    	setBackground(Color.black);
	}

	public static void main(String[] args) {
		
		Game game = new Game();
		
		// Create the window
        JFrame f = new JFrame ("A*");
        // Sets the behavior for when the window is closed
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        f.getContentPane().add(game);
        // arrange the components inside the window
        f.pack();
        //By default, the window is not visible. Make it visible.
        f.setVisible(true);
        
        game.run();

	}

	private void run() {
		long lastUpdateTime = new Date().getTime();
		while(true){
			long delta = new Date().getTime() - lastUpdateTime;
			if (delta > 1000 / FPS){
				lastUpdateTime = new Date().getTime();
				update(delta);
				draw();
			} else {
				try {
					Thread.sleep(1000 / FPS - delta);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void update(long delta){
		// Move enemies
		for(int e = 0; e < gameMap.getEnemies().size(); e++){
	        gameMap.getEnemies().get(e).act(gameMap, gameMap.getCharPos()); 
        }
		
		// Move char
		
	}
	
	private void draw(){
		
		repaint();
		
		
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
        Graphics2D g2 = (Graphics2D)g;
        
        // Paint map
        for(int y = 0; y < gameMap.getMapArr().length; y++){
        	for(int x = 0; x < gameMap.getMapArr()[0].length; x++){
        		g2.setColor(getColorCode(gameMap.getMapArr()[y][x]));
                g2.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
        	}
        }
        
        // Paint char
        g2.setColor(Color.BLUE);
        g2.fillOval(gameMap.getCharPos().getX() * tileSize, 
        			gameMap.getCharPos().getY() * tileSize, 
        			tileSize, 
        			tileSize);
        
        // Paint enemies 
        for(int e = 0; e < gameMap.getEnemies().size(); e++){
	        g2.setColor(Color.RED);
	        g2.fillOval(gameMap.getEnemies().get(e).getPosition().getX() * tileSize, 
	        			gameMap.getEnemies().get(e).getPosition().getY() * tileSize, 
	        			tileSize, 
	        			tileSize);
        }
        
    }

	private Color getColorCode(int i) {
		
		switch(i){
		case 0 : return Color.BLACK;
		case 1 : return Color.GREEN;
		case 2 : return new Color(0,50,0);
		}
		
		return Color.PINK;
	}
	

}
