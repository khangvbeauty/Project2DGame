package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable { //Lớp kế thừa JPanel, lớp con có nhiều chức năng được bổ sung

	// Cài đặt màn hình
	final int originalTileSize = 16; // tiêu chuẩn 16x16 nhân vật, NPCs, ô bản đồ
	final int scale = 3; //Tỉ lệ
	
	public final int tileSize = originalTileSize * scale; // 48x48 thực tế
	public final int maxScreenCol = 16; //16 cột
	public final int maxScreenRow = 12; //12 hàng
	public final int screenWidth = tileSize * maxScreenCol; // 768px
	public final int screenHeight = tileSize * maxScreenRow; // 576px
	
	//World settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxScreenCol;
	public final int worldHeight = tileSize * maxScreenRow;



	
	// FPS
	int FPS = 60;
	 
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread; //Luồng lặp 1 quy trình
	public AssetSetter aSetter = new AssetSetter(this);
	public CollisionChecker cChecker = new CollisionChecker(this);
	
	public Player player = new Player(this,keyH);
	public SuperObject obj[]= new SuperObject[10];
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight)); 
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true); //Nhận đầu vào chính
	}
	
	public void setupGame() {
		aSetter.setObject();
	}
	
	public void startGameThread() { // Khởi tạo game thread
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() { // Tạo loop game
		while (gameThread != null) {
			
			double drawInterval = 1000000000/FPS; //0.1666 giây
			double nextDrawTime = System.nanoTime() + drawInterval;
			
			// 1 UPDATE: cập nhật thông tin như là vị trí nhân vật
			update();
			// 2 DRAW: vẽ màn hình với các thông tin cập nhật
			repaint();
						
			try { //Phương thức sleep. Có cách khác Phương thức delta 
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if (remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public void update() {
		
		player.update();
		
	}
	public void paintComponent(Graphics g) { // Phương thức tiêu chuẩn vẽ mọi thứ JPanel, Graphics có nhiều hàm vẽ
		
		super.paintComponent(g); // super là lớp cha của một lớp
		
		Graphics2D g2 = (Graphics2D)g; // Lớp kế thừa lớp Graphics
		//TILE
		tileM.draw(g2);
		
		//OBJECT
		for(int i=0; i<obj.length;i++) {
			if (obj[i]!= null) {
				obj[i].draw(g2,  this);
			}
		}
		
		//PLAYER
		player.draw(g2);
		
		g2.dispose(); //giải phóng bộ nhớ
	}
	
}
