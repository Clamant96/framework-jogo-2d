package br.com.helpconnect.frameworks;

import java.awt.Graphics;
import java.util.LinkedList;

import br.com.helpconnect.game.Game;

public class GameController {
	
	public LinkedList<GameObject> obj = new LinkedList<>();
	
	private GameObject tempObj;
	
	public void update() {
		
		for(int i = 0; i < obj.size(); i++) {
			tempObj = obj.get(i);
			tempObj.tick(obj);
			
		}
		
	}
	
	public void draw(Graphics g) {
		
		for(int i = 0; i < obj.size(); i++) {
			tempObj = obj.get(i);
			tempObj.render(g);
			
		}
		
	}
	
	public void addObj(GameObject obj) {
		
		this.obj.add(obj);
		
		System.out.println("[DEBUG GameController] - Objeto criado com sucesso => "+ obj.getId());
		
	}
	
	public void removeObj(GameObject obj) {
		
		this.obj.remove(obj);
		
		System.out.println("[DEBUG GameController] - Objeto excluido com sucesso => "+ obj.getId());
		
	}

	public void criarMundo() {
		// MUNDO DE BLOCO 32 X 32
		
		for (int xx = 0; xx < Game.LARGURA * 2 + 32; xx+=32) {
			
			// RENDERIZA O TOP
			addObj(new Bloco(xx, 0, ObjectId.BLOCO));
			// RENDERIZA O CHAO
			addObj(new Bloco(xx, Game.ALTURA * 2 - 32, ObjectId.BLOCO));
			// RENDERIZA O PAREDE ESQUERDA
			addObj(new Bloco(0, xx + 32, ObjectId.BLOCO));
			// RENDERIZA O PAREDE DIREITA
			addObj(new Bloco(Game.ALTURA * 2 - 32, xx, ObjectId.BLOCO));
			
			// RENDERIZA PLATAFORMA / PLATAFORMA 1
			for (int i = 0; i < 10; i++) {
				addObj(new Bloco(160 + i * 32, 350, ObjectId.BLOCO));
				
				System.out.println("PLATAFORMA 1 | BLOCO: "+ i);
				
			}
			
			// RENDERIZA PLATAFORMA / PLATAFORMA 2
			for (int i = 0; i < 5; i++) {
				addObj(new Bloco(96 + i * 32, 190, ObjectId.BLOCO));
				
				System.out.println("PLATAFORMA 2 | BLOCO: "+ i);
				
			}
			
		}
		
	}
}
