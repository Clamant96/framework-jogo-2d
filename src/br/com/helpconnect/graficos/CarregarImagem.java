package br.com.helpconnect.graficos;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CarregarImagem {
	
	private Image img;
	
	public Image pegarImagem(String caminho) {
		
		try {
			img = ImageIO.read(getClass().getResource(caminho));
			System.out.println("[DEBUG CarregarImagem]: imagem carregada com sucesso!");
			
			return img;
			
		} catch (IOException | IllegalArgumentException erro) {
			System.err.println("[DEBUG CarregarImagem]: arquivo não localizado. \n");
			erro.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
	
}
