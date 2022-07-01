package br.com.helpconnect.graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FolhaSprites {
	
	private BufferedImage img;
	
	public FolhaSprites(String caminho) {
		try {
			img = ImageIO.read(getClass().getResource(caminho));
			System.out.println("[DEBUG Sprite]: sprite carregada com sucesso!");
			
		} catch (IOException | IllegalArgumentException erro) {
			System.err.println("[DEBUG Sprite]: arquivo não localizado. \n");
			erro.printStackTrace();
			System.exit(1);
		}

	}
	
	public BufferedImage pegarSprite(int x, int y, int w, int h) {
		
		return img.getSubimage(x, y, w, h);
	}
	
}
