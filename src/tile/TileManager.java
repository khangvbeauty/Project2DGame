package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gp;
	Tile[] tile;
	int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		tile = new Tile[10];
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		
		getTileImage();
		loadMap("/maps/map01.txt");
	}
	
	public void getTileImage() {
	
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tile/grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tile/wall.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tile/water.png"));
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tile/earth.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tile/tree.png"));
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tile/sand.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath) {
		
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
				
				String line = br.readLine(); // đọc 1 dòng, nhận chuỗi
				
				while (col < gp.maxScreenCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]); //Chuyển String sang Integer
					
					mapTileNum[col][row] = num;
					col++;
				}
				if (col == gp.maxScreenCol) {
					col = 0;
					row++;
					
				}
			}
			br.close();
			
		} catch(Exception e) {
			
		}
	}
	public void draw(Graphics2D g2) {
		
//		g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[2].image, 96, 0, gp.tileSize, gp.tileSize, null); demo vài ô
		 int col = 0;
		 int row = 0;
		 int x = 0;
		 int y = 0;
		 
		 while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
			 
			 int tileNum = mapTileNum[col][row];
			 
			 g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
			 col++;
			 x += gp.tileSize;
			 
			 if (col == gp.maxScreenCol) {
				 col = 0;
				 x= 0;
				 row++;
				 y += gp.tileSize;
				 
			 }
			 
		 }
	}
}
