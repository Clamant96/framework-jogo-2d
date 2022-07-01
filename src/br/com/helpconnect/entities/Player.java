package br.com.helpconnect.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import br.com.helpconnect.frameworks.GameController;
import br.com.helpconnect.frameworks.GameObject;
import br.com.helpconnect.frameworks.ObjectId;

public class Player extends GameObject {
	
	// TAMANHO DO PLAYER
	private int width = 64;
	private int height = 128;
	
	// CAIXAS DE COLISAO
	private int colW = width;
	private int colH = height;
	
	private GameController gc;

	public Player(float x, float y, ObjectId id, GameController gc) {
		super(x, y, id);
		this.gc = gc;
		
	}

	public void tick(LinkedList<GameObject> obj) {
		x+=spdX;
		y+=spdY;
		
		verificarColisao(obj);
		
	}

	private void verificarColisao(LinkedList<GameObject> obj) {
		
		for (int i = 0; i < gc.obj.size(); i++) {
			
			GameObject tempObj = gc.obj.get(i);
			
			if(tempObj.getId() == ObjectId.BLOCO) {
				
				// TOPO
				if(getBounds().intersects(tempObj.getBounds())) {
					System.out.println("COLISAO TOPO!");
					
					y = tempObj.getY() + 32; // COLISAO COM BLOCO
				
				// BAIXO
				} else if(getBoundsBaixo().intersects(tempObj.getBounds())) {
					System.out.println("COLISAO BAIXO!");
					
					y = tempObj.getY() - height; // COLISAO COM BLOCO
				
				// DIREITA
				} else if(getBoundsDir().intersects(tempObj.getBounds())) {
					System.out.println("COLISAO DIREITA!");
					
					x = tempObj.getX() - width; // COLISAO COM BLOCO
					
				// ESQUERDA
				} else if(getBoundsEsq().intersects(tempObj.getBounds())) {
					System.out.println("COLISAO ESQUERDA!");
					
					x = tempObj.getX() + 32; // COLISAO COM BLOCO
					
				}
			
			// COLISAO COBAIA
			}else if (tempObj.getId() == ObjectId.COBAIA) {
				
				if(getBounds().intersects(tempObj.getBounds())) {
					System.out.println("COLISAO COM A COBAIA!");
					
					gc.obj.get(i).setSpdY(-20);
					
				} else if(getBoundsBaixo().intersects(tempObj.getBounds())) {
					System.out.println("COLISAO COM A COBAIA!");
					
					gc.obj.get(i).setSpdY(+20);
					
				}else if(getBoundsDir().intersects(tempObj.getBounds())) {
					System.out.println("COLISAO COM A COBAIA!");
					
					gc.obj.get(i).setSpdX(+20);
					
				}else if(getBoundsEsq().intersects(tempObj.getBounds())) {
					System.out.println("COLISAO COM A COBAIA!");
					
					gc.obj.get(i).setSpdX(-20);
					
				}
				
			}
			
		}
		
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		// g.fillOval((int) x, (int) y, 64, 64); // CIRCULO CHEIO
		// g.drawOval((int) x, (int) y, 128, 128); // CIRCULO VAZIO
		// g.drawRect((int) x, (int) y, 128, 128); // QUADRADO VARIO
		g.fillRect((int) x, (int) y, width, height); // QUADRADO CHEIO
		
		Graphics2D g2d = (Graphics2D) g;
		
		// RENDERIZACAO AS CAIXAS DE COLISAO
		// TOPO
		g.setColor(Color.RED);
		g2d.draw(getBounds());
		
		// BAIXO
		g.setColor(Color.GREEN);
		g2d.draw(getBoundsBaixo());
		
		// DIREITA
		g.setColor(Color.MAGENTA);
		g2d.draw(getBoundsDir());
		
		// ESQUERDA
		g.setColor(Color.CYAN);
		g2d.draw(getBoundsEsq());
		
		// DEBUG x, y
		g.setFont(new Font("arial", Font.BOLD, 16));
		g.setColor(new Color(255, 50, 20));
		g.drawString("x, y: "+ x +" / "+ y +" "+ this.id, (int) x, (int) y);
		
	}

	public Rectangle getBounds() {
		
		return new Rectangle((int) x + colW / 2 - (colW / 2) / 2, (int) y, colW / 2, colH / 2 - 1);
	}
	
	public Rectangle getBoundsBaixo() {
		
		return new Rectangle((int) x + colW / 2 - (colW / 2) / 2, (int) y + colH / 2, colW / 2, colH / 2 - 1);
	}
	
	public Rectangle getBoundsDir() {
	
		return new Rectangle((int) x + colW - 6, (int) y + 5, 5, colH - 10);
	}
	
	public Rectangle getBoundsEsq() {
		
		return new Rectangle((int) x, (int) y + 5, 5, colH - 10);
	}
}
