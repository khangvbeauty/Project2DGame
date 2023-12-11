package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import tile.Tile;

public class Player extends Entity { //lớp kế thừa của lớp Entity
	
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle(); //tạo phần rắn va chạm
		solidArea.x = 8;
		solidArea.y  =16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21; //Vị trí bắt đầu
		speed = 4;
		direction = "down"; //hướng nhân vật mặc định
	}
	
	public void getPlayerImage() { // up ảnh nhân vật
		
		up1 = setup("boy_up_1");
		up2 = setup("boy_up_2");
		down1 = setup("boy_down_1");
		down2 = setup("boy_down_2");
		left1 = setup("boy_left_1");
		left2 = setup("boy_left_2");
		right1 = setup("boy_right_1");
		right2 = setup("boy_right_2");
	}
	
	public BufferedImage setup(String imageName) {
		
		UtilityTool uTool = new UtilityTool();
		
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public void update() {
		
		if (keyH.upPressed ==true || keyH.downPressed ==true ||
				keyH.leftPressed == true || keyH.rightPressed == true) {
			
			if(keyH.upPressed == true) {
				direction = "up";
			}
			else if(keyH.downPressed == true) {
				direction = "down";
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
			}
			else if(keyH.rightPressed == true) {
				direction = "right";
			}
			
			//Kiểm tra va chạm
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			//Kiểm tra va chạm đối tượng (Object)
			int objIndex = gp.cChecker.checkObject(this,true);
			pickUpObject(objIndex);
			
			// Nếu không chạm, player di chuyển
			if(collisionOn == false) {
				
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed; 
					break;
				}
			}
			
			spriteCounter++; // gọi 60fps/s tăng 1 đơn vị
			if (spriteCounter > 12) { //hình ảnh thay đổi khi đạt 12 khung hình
				if(spriteNum == 1) {
					spriteNum =2;
				}
				else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
		
	}
	public void pickUpObject(int i) {

		    if (i >= 0 && i < gp.obj.length) {
		        String objectName = gp.obj[i].name;
		        
		        switch(objectName) {
		        case "Key":
		        	gp.playSE(1);
		        	hasKey++;
		        	gp.obj[i] = null;
				gp.ui.showMessage("You got a key!");
				break;
		        case "Door":
		        	if(hasKey > 0) {
		        		gp.playSE(3);
		        		gp.obj[i] = null;
		        		hasKey--;
		        		gp.ui.showMessage("You opened the door!");
		        	}
		        	else {
		        		gp.ui.showMessage("You need a key!");
		        	}
		        	System.out.println("Key: "+hasKey);
		        	break;   
		        case "Boots":
		        	gp.playSE(2);
		        	speed += 2;
		        	gp.obj[i]=null;
	        		gp.ui.showMessage("Speed up!");
		        	break;
		        case "Chest":
		        	gp.ui.gameFinished = true;
		        	gp.stopMusic();
		        	gp.playSE(4);
		        	break;
		        	
		        }
		    }
	}
	public void draw(Graphics2D g2) {
		
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);//Phương thức vẽ 1 HCN
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;			
			}
			if (spriteNum == 2) {
				image = left2;			
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, null); //Vẽ ảnh lên screen
	}
}
