package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import object.OBJ_Heart;
import object.OBJ_Key;
import object.SuperObject;

public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font maruMonica, purisaB;
	BufferedImage keyImage;
	BufferedImage heart_full, heart_half, heart_blank;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public int commandNum = 0;
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");

	public UI(GamePanel gp) {
		this.gp = gp;

		try {
			InputStream is = getClass().getResourceAsStream("/font/MP16REG.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
			purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		OBJ_Key key = new OBJ_Key(gp);
		keyImage = key.image;
		
		// CREATE HUD OBJECT
		SuperObject heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
		
		
	}

	public void showMessage(String text) {

		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D g2) {

		//PRESS P TO PAUSE
		this.g2 = g2;

		g2.setFont(maruMonica);
		g2.setColor(Color.white);
		
		//TITLE STATE
		if (gp.gameState == gp.tileState) {
			drawTitleScreen();
		}
		//PLAY STATE
		if (gp.gameState == gp.playState) {
			drawPlayerLife();
			drawPauseScreen();
		}
		// PAUSE STATE
		if (gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();

		}
		
		//FOUND TREASURE
		if (gameFinished == true) {

			g2.setFont(maruMonica);
			g2.setColor(Color.white);

			String text;
			int textLength;
			int x;
			int y;

			text = "You found the treasure!";
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth / 2 - textLength / 2;
			y = gp.screenHeight / 2 - (gp.tileSize * 3);
			g2.setColor(Color.black);
			for (int i = -2; i <= 2; i++) { //Tạo viền
			    for (int j = -2; j <= 2; j++) {
			        g2.drawString(text, x+i, y+j);
			    }
			}
			g2.setColor(Color.white);
			g2.drawString(text, x, y);

			text = "Your Time is : " + dFormat.format(playTime) + "!";
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth / 2 - textLength / 2;
			y = gp.screenHeight / 2 + (gp.tileSize * 4);
			g2.setColor(Color.black);
			for (int i = -2; i <= 2; i++) { //Tạo viền
			    for (int j = -2; j <= 2; j++) {
			        g2.drawString(text, x+i, y+j);
			    }
			}
			g2.setColor(Color.white);
			g2.drawString(text, x, y);

			g2.setFont(maruMonica);
			g2.setColor(Color.black);
			text = "Congratulations!";
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,70F));
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth / 2 - textLength / 2;
			y = gp.screenHeight / 2 + (gp.tileSize * 2);
			for (int i = -2; i <= 2; i++) { //Tạo viền
			    for (int j = -2; j <= 2; j++) {
			        g2.drawString(text, x+i, y+j);
			    }
			}
			g2.setColor(Color.yellow);
			g2.drawString(text, x, y);

			gp.gameThread = null;

		} else {
			if (gp.gameState == gp.playState) {
				g2.setFont(maruMonica);
				g2.setColor(Color.black);
				g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
				//Tạo viền
				for (int i = -2; i <= 2; i++) {
				    for (int j = -2; j <= 2; j++) {
				        g2.drawString("x " + gp.player.hasKey, 74+i, 130+j);
				    }
				}
				g2.setColor(Color.white);
				g2.drawString("x " + gp.player.hasKey, 74, 130);
				g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize * 2, gp.tileSize, gp.tileSize, null);

				// TIME
				playTime += (double) 1 / 60;

				// MESSAGE
				if (messageOn == true) {
			        g2.setFont(g2.getFont().deriveFont(30F));

			        // Vẽ viền
			        g2.setColor(Color.black);
			        for (int i = -2; i <= 2; i++) {
			            for (int j = -2; j <= 2; j++) {
			                if (i != 0 || j != 0) { // Đảm bảo không vẽ viền trên vị trí của văn bản chính
			                    g2.drawString(message, (gp.tileSize / 2)+i, (gp.tileSize*5)+j);
			                }
			            }
			        }

			        // Vẽ văn bản
			        g2.setColor(Color.white);
			        g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

			        messageCounter++;

			        if (messageCounter > 120) { // FPS 120 (2 giây)
			            messageCounter = 0;
			            messageOn = false;
			        }
			    }
			}
			
		}

	}
	public void drawPlayerLife() {
		
		gp.player.life = 1;
		
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		
		//DRAW MAX LIFE
		while(i < gp.player.maxLife/2) {
			g2.drawImage(heart_blank,x,y,null);
			i++;
			x += gp.tileSize;
		}
		
		// RESET
		x = gp.tileSize/2;
		y = gp.tileSize/2;
		i = 0;
		
		//DRAW CURRENT LIFE
		while(i < gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if (i < gp.player.life) {
				g2.drawImage(heart_full,x,y,null);
			}
			i++;
			x += gp.tileSize;
		}
		
	}

	public void drawTitleScreen() {
		
			g2.setColor(new Color(0,0,0));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			//TITLE NAME 
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
			String text = "Hunt For Treasure";
			int x = getXforCenteredText(text);
			int y = gp.tileSize*3;
			
			//SHADOW
			g2.setColor(Color.gray);
			g2.drawString(text, x+5, y+5);
			
			//MAIN COLOR
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			// LOGO
			x = gp.screenWidth/2 - (gp.tileSize*2)/2;
			y += gp.tileSize*2;
			g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
			
			//MENU
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
			
			text = "NEW GAME";
			x = getXforCenteredText(text);
			y += gp.tileSize*3.5;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x - gp.tileSize, y);
			}
			
			text = "LOAD GAME";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x - gp.tileSize, y);
			}
			
			text = "QUIT";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x - gp.tileSize, y);
			}
	}
	public void drawPauseScreen() {

		if (gp.gameState == gp.pauseState) {
	        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
	        String text = "PAUSED";
	        int x = getXforCenteredText(text);
	        int y = gp.screenHeight / 2;
	        g2.setColor(Color.black);
	        for (int i = -2; i <= 2; i++) { //Tạo viền
	            for (int j = -2; j <= 2; j++) {
	                g2.drawString(text, x+i, y+j);
	            }
	        }
	        g2.setColor(Color.white);
	        g2.drawString(text, x, y);

	        g2.drawString(text, x, y);
	    }

	}

	public int getXforCenteredText(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth / 2 - length / 2;
		return x;
	}
}
