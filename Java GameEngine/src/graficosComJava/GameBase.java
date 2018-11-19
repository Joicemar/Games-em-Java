package graficosComJava;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class GameBase extends Canvas implements Runnable{
	private Thread thread;
	private boolean isRunning;
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private final int WIDTH = 160;
	private final int HEIGHT = 120;
	private final int SCALE = 4;
	
	private BufferedImage image;
	
	public GameBase() {
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		initFrame();
		image = new BufferedImage(169,120,BufferedImage.TYPE_INT_RGB);
	}
	public void initFrame() {
		//Aqui chama a janela
		frame = new JFrame("Game #1");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);  //Aqui torna visível a tela
	}
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
		
		
	}
	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		GameBase game = new GameBase();
		game.start();
	}
	
	public void tick() {
		
	}
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.createGraphics();
		g.setColor(new Color(19,199,19));
		
		//Aqui definmos a posição x, y, e os tamanhos da tela
		g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		bs.show();
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		//Looping profissional usado até pelo minecraft
		while(isRunning) {
			//nanoTime é um verificador mais preciso
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: "+frames);
				frames = 0;
				timer+=1000;
			}
		if(isRunning == false) {
			stop();
		}
		}
		
	}
		
}
