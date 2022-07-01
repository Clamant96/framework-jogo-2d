package br.com.helpconnect.frameworks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Cobaia extends GameObject {

	public Cobaia(float x, float y, ObjectId id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	public void tick(LinkedList<GameObject> obj) {
		x+=spdX;
		y+=spdY;
		
	}

	public void render(Graphics g) {
		g.setColor(Color.blue);
		// g.fillOval((int) x, (int) y, 64, 64); // CIRCULO CHEIO
		// g.drawOval((int) x, (int) y, 128, 128); // CIRCULO VAZIO
		// g.drawRect((int) x, (int) y, 128, 128); // QUADRADO VARIO
		g.fillRect((int) x, (int) y, 64, 64); // QUADRADO CHEIO
		
	}
	
	public Rectangle getBounds() {
		
		return new Rectangle((int) x, (int) y, 64, 64);
	}

}
