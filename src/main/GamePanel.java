package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable { //Lớp kế thừa JPanel, lớp con có nhiều chức năng được bổ sung

	// Cài đặt màn hình
	final int originalTileSize = 16; // tiêu chuẩn 16x16 nhân vật, NPCs, ô bản đồ
	final int scale = 3; //Tỉ lệ
	
	final int tileSize = originalTileSize * scale; // 48x48 thực tế
	final int maxScreenCol = 16; //16 cột
	final int maxScreenRow = 12; //12 hàng
	final int screenWidth = tileSize * maxScreenCol; // 768px
	final int screenHeight = tileSize * maxScreenRow; // 576px
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread; //Luồng lặp 1 quy trình
	
	//Tạo mặc định vị trí người chơi
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight)); 
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusCycleRoot(true); //Nhận đầu vào chính
	}
	
	public void startGameThread() { // Khởi tạo game thread
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() { // Tạo loop game
		while (gameThread != null) {
			// 1 UPDATE: cập nhật thông tin như là vị trí nhân vật
			update();
			// 2 DRAW: vẽ màn hình với các thông tin cập nhật
			repaint();
		}
		
	}
	public void update() {
		
		if(keyH.upPressed == true) {
			playerY -= playerSpeed;
		}
		else if(keyH.downPressed == true) {
			playerY += playerSpeed;
		}
		else if(keyH.leftPressed == true) {
			playerX -= playerSpeed;
		}
		else if(keyH.rightPressed == true) {
			playerX += playerSpeed;
		}
	}
	public void paintComponent(Graphics g) { // Phương thức tiêu chuẩn vẽ mọi thứ JPanel, Graphics có nhiều hàm vẽ
		
		super.paintComponent(g); // super là lớp cha của một lớp
		
		Graphics2D g2 = (Graphics2D)g; // Lớp kế thừa lớp Graphics
		
		g2.setColor(Color.white);
		
		g2.fillRect(playerX, playerY, tileSize, tileSize);//Phương thức vẽ 1 HCN
		
		g2.dispose(); //giải phóng bộ nhớ
	}
	
}
