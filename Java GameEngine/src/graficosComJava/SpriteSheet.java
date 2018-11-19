package graficosComJava;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	// Aqui transforma nosso arquivo PNG em uma BufferedImage
	public BufferedImage spritesheet;
	
	public SpriteSheet(String path) {
		// path é o nome do arquivo de imagem que o getresource ira pegar
		try {
			spritesheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//Criamos um metodo do tipo BufferedImage
	public BufferedImage getSprite(int x, int y, int whidth, int height) {
		return spritesheet.getSubimage(x, y, whidth, height);
	}
	

}
