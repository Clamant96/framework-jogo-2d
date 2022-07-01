package br.com.helpconnect.frameworks;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {
	
	protected ObjectId id; // ID DO OBJ
	
	protected float x; // POSICAO X NA TELA
	protected float y; // POSICAO Y NA TELA
	
	protected float spdX; // VELOCIDADE X 
	protected float spdY; // VELOCIDADE Y
	
	public GameObject(float x, float y, ObjectId id) {
		this.x = x;
		this.y = y;
		this.id = id;
		
	}
	
	public abstract void tick(LinkedList<GameObject> obj);
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();

	public ObjectId getId() {
		return id;
	}

	/*public void setId(ObjectId id) {
		this.id = id;
	}*/

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getSpdX() {
		return spdX;
	}

	public void setSpdX(float spdX) {
		this.spdX = spdX;
	}

	public float getSpdY() {
		return spdY;
	}

	public void setSpdY(float spdY) {
		this.spdY = spdY;
	}
	
}
