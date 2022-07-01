package br.com.helpconnect.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import br.com.helpconnect.entities.Player;
import br.com.helpconnect.frameworks.Cobaia;
import br.com.helpconnect.frameworks.GameController;
import br.com.helpconnect.frameworks.ObjectId;
import br.com.helpconnect.graficos.CarregarImagem;
import br.com.helpconnect.graficos.FolhaSprites;
import br.com.helpconnect.input.Teclado;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	public static JFrame jFrame;
	public static final int LARGURA = 320;
	public static final int ALTURA = 320;
	public static final int ESCALA = 2;
	
	private Thread thread;
	private boolean isRunning = true;
	private int frames = 0;
	
	private final BufferedImage backgroundImage;
	
	private Image img;
	private FolhaSprites spriteSheet; // PASSO 01
	
	private GameController gc;

	public Game() {
		this.setPreferredSize(new Dimension(LARGURA * ESCALA, ALTURA * ESCALA)); // DEFINE O TAMANHO DA JANELA
		
		initFrame(); // CHAMA O METODO DE CONFIGURACAO DA JANELA
		
		backgroundImage = new BufferedImage(LARGURA, ALTURA, BufferedImage.TYPE_INT_RGB);
		
		startGame();
		
	}

	private void startGame() {
		img = new CarregarImagem().pegarImagem("/coordenadas.png");
		spriteSheet = new FolhaSprites("/spritesheet-01.png"); // PASSO 02
		
		gc = new GameController();
		
		addKeyListener(new Teclado(gc));
		
		// OBJETOS
		gc.criarMundo();
		
		gc.addObj(new Cobaia(220, 250, ObjectId.COBAIA));
		gc.addObj(new Player(120, 450, ObjectId.PLAYER, gc));
		
	}

	private void initFrame() {
		jFrame = new JFrame("HELP CONNECT GAME 2D"); // INICIALIZA O GAME E DA UM NOME A JANELA
		jFrame.add(this); // ADICIONA A SI MESMO
		jFrame.setResizable(false); // NAO DEIXA REDIMENSIONAR A TELA
		jFrame.pack(); // AJUSTA TUDO NO CANVAS APLICANDO
		jFrame.setLocationRelativeTo(null); // ABRE A JANELA NO MEIO DA TELA
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // AO FECHAR A JANELA FINALIZA TODO O APP
		jFrame.setVisible(true); // DEIXA A JANELA VISIVEL
		
	}

	@Override
	public void run() {
		requestFocus(); // ALERTA O WINDOWS
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0; // FPS
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		
		double timer = System.currentTimeMillis();
		
		// LOOP GAME
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 100) {
				//System.out.println("FPS: "+ frames);
				jFrame.setTitle("HELP CONNECT GAME 2D - rodando a FPS: "+ frames);
				frames = 0;
				timer += 1000;
				
			}
			
		}
		
		stop();
		
	}
	
	private void stop() {
		isRunning = false;
		
		try {
			thread.join();
			
		} catch (InterruptedException erro) {
			
		}
		
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
		
	}

	private void tick() {
		gc.update();
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			
			return;
		}
		
		Graphics g = backgroundImage.getGraphics();
		// DEFINE AS CORES DE BACKGROUND
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, LARGURA, ALTURA);
		
		g = bs.getDrawGraphics();
		
		g.drawImage(backgroundImage, 0, 0, LARGURA * ESCALA, ALTURA * ESCALA, null);
		
		// RENDERIZA UM TEXTO
		// g.setColor(new Color(255, 0, 0)); // COR
		// g.setFont(new Font("arial", Font.BOLD, 30)); // FONT, PESO E TAMANHO
		// g.drawString("Renderizando texto", 50, 50); // TEXTO E POSICAO
		
		Graphics2D g2d = (Graphics2D) g;
		// g2d.drawImage(img, 5, 5, 625, 625, null);
		// g2d.drawImage(spriteSheet.pegarSprite(0, 0, 16, 16), 180, 50, 128, 128, null); // PASSO 03
		
		gc.draw(g2d);
		
		// FINAL DOS OBJs A SEREM DESENHADOS
		bs.show(); // MOSTRA TUDO O QUE FOI DESENHADO
		g.dispose();
		
	}

}
