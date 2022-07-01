package br.com.helpconnect.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import br.com.helpconnect.frameworks.GameController;
import br.com.helpconnect.frameworks.GameObject;
import br.com.helpconnect.frameworks.ObjectId;

public class Teclado extends KeyAdapter {
	
	GameController gc;
	
	public Teclado(GameController gc) {
		this.gc = gc;
		
	}
	
	public void keyPressed(KeyEvent e) {
		int tecla = e.getKeyCode();
		
		for (int i = 0; i < gc.obj.size(); i++) {
			GameObject objTemp = gc.obj.get(i);
			
			if(objTemp.getId() == ObjectId.PLAYER) {
				if(tecla == KeyEvent.VK_D) {
					objTemp.setSpdX(5);
				}
				if(tecla == KeyEvent.VK_A) {
					objTemp.setSpdX(-5);
				}
				if(tecla == KeyEvent.VK_W) {
					objTemp.setSpdY(-5);
				}
				if(tecla == KeyEvent.VK_S) {
					objTemp.setSpdY(5);
				}	
			}
			
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		int tecla = e.getKeyCode();
		
		for (int i = 0; i < gc.obj.size(); i++) {
			GameObject objTemp = gc.obj.get(i);
			
			if(objTemp.getId() == ObjectId.PLAYER) {
				if(tecla == KeyEvent.VK_D) {
					objTemp.setSpdX(0);
				}
				if(tecla == KeyEvent.VK_A) {
					objTemp.setSpdX(0);
				}
				if(tecla == KeyEvent.VK_W) {
					objTemp.setSpdY(0);
				}
				if(tecla == KeyEvent.VK_S) {
					objTemp.setSpdY(0);
				}	
			}

		}
		
	}
	
}
