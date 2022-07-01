package br.com.helpconnect.frameworks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Bloco extends GameObject {

	public Bloco(float x, float y, ObjectId id) {
		super(x, y, id);
		
	}

	public void tick(LinkedList<GameObject> obj) {
		
	}

	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect((int) x, (int) y, 32, 32);
		
		// DIVISAO DOS BLOCOS
		g.setColor(Color.BLACK);
		g.drawRect((int) x, (int) y, 32, 32);
		
	}

	public Rectangle getBounds() {
		
		return new Rectangle((int) x, (int) y, 32, 32);
	}

}
