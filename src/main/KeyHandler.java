package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener { // KeyHandler đang triển khai giao diện KeyListener -  phương thức được sử dụng để lắng nghe các sự kiện bàn phím

	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	//DEBUG
	boolean checkDrawTime = false;
	   
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	@Override
	public void keyTyped(KeyEvent e) {		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode(); //Trả về một phím được nhấn
		
		// TILE STATE
		if(gp.gameState == gp.tileState) {
			
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if (gp.ui.commandNum < 0) {
		            gp.ui.commandNum = 2;
		        }
			}
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;	
				if (gp.ui.commandNum > 2) {
		            gp.ui.commandNum = 0;
		        }
			}
			if (code == KeyEvent.VK_ENTER) {
				if (gp.ui.commandNum == 0) {
					gp.gameState = gp.playState;
					gp.playMusic(0);
				}
				if (gp.ui.commandNum == 1) {
					//Sẽ cập nhật
				}
				if (gp.ui.commandNum == 2) {
					System.exit(0);
				}
				
			}
		}
		//PLAY STATE
		if(gp.gameState == gp.playState) {
			if(code == KeyEvent.VK_W) {
				upPressed = true;
				
			}
			if(code == KeyEvent.VK_S) {
				downPressed = true;
						
			}
			if(code == KeyEvent.VK_A) {
				leftPressed = true;
				
			}
			if(code == KeyEvent.VK_D) {
				rightPressed =  true;
				
			}
		}
			if(code == KeyEvent.VK_P) {  
				if(gp.gameState == gp.playState) { //nếu đang play thì press P dừng lại,
					gp.gameState = gp.pauseState;
				}
				else if (gp.gameState==gp.pauseState) { //đang pause thì press P để play
					gp.gameState = gp.playState;
					
				}
					}
			
			//DEBUG
			if(code == KeyEvent.VK_T) {
				if(checkDrawTime == false) {
					checkDrawTime = true;
				}
				else if (checkDrawTime == true) {
					checkDrawTime = false;
				}		
			}
		}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
			
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
					
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
			
		}
		if(code == KeyEvent.VK_D) {
			rightPressed =  false;
			
		}
		
	} 

}
