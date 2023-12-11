package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

	public class OBJ_Boot extends SuperObject{
		
		GamePanel gp;
		
		public OBJ_Boot(GamePanel gp) {
			
			this.gp = gp;
			
			name = "Boots";
			try {
				image =ImageIO.read(getClass().getResourceAsStream("/Objects/santa boots.png"));
				uTool.scaleImage(image, gp.tileSize, gp.tileSize);

			}catch (IOException e) {
				e.printStackTrace();
			}
			solidArea.x = 5;
		}

	}

