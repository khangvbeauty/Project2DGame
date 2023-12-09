package object;

import java.io.IOException;

import javax.imageio.ImageIO;

	public class OBJ_Boot extends SuperObject{
		public OBJ_Boot() {
			name = "Boots";
			try {
				image =ImageIO.read(getClass().getResourceAsStream("/Objects/santa boots.png"));
				
			}catch (IOException e) {
				e.printStackTrace();
			}
			solidArea.x = 5;
		}

	}

