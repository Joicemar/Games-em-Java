package graficosComJava;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{
	
	private Thread thread;
	private boolean isRunning;
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private final int WIDTH = 160;
	private final int HEIGHT = 120;
	private final int SCALE = 4;
	
	private BufferedImage image;
	
	//@SuppressWarnings("unused")
	private SpriteSheet sheet;
	private BufferedImage[] player;
	private int frames = 0;
	private int maxFrames = 10;
	private int curAnimation = 0, maxAnimation = 3;
	
	public Game() {
		sheet = new SpriteSheet("/spritsheet.png");
		player = new BufferedImage[3];
		player[0] = sheet.getSprite(0, 0, 16, 15);
		player[1] = sheet.getSprite(16, 0, 16, 15);
		player[2] = sheet.getSprite(32, 0, 15, 15);

		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		initFrame();
		image = new BufferedImage(160,120,BufferedImage.TYPE_INT_RGB);
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
		Game game = new Game();
		game.start();
	}
	
	public void tick() {
		frames ++;
		if(frames >= maxFrames) {
			frames = 0;
			curAnimation++;
			if(curAnimation >= maxAnimation) {
				curAnimation = 0;
			}
		}
		
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
		
		//g.setFont(new Font("Arial", Font.BOLD,20));
		//g.setFont("Arial", Font.BOLD,20);
		//g.drawString("Olá", 22, 22);
		
		g.setColor(Color.white);
		
		/*renderizacao do jogo**/
		//fazendo um cast; estamos transformando o objeto g para o tipo Graphics2D, que é mais avancado que o Graphics
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0,0,180, 180));
		//g2.fillRect(0, 0, WIDTH, HEIGHT);
		//g2.rotate(Math.toRadians(45),90+8,90+8);  //Passamos o angulo 45(centro personagem) e rotacao 90 graus 2 pontos
		g2.drawImage(player[curAnimation], 90, 90, null);//passamos a imagem e a posição em pixel que ela será pintada
		
		//g.drawImage(player, 20, 20, null);
		/******/
		g.dispose(); //Esse metodo ajuda na performance, limpando arquivos já usados em segundo plano.
		g = bs.getDrawGraphics();
		//g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		bs.show();
		//g.fillRect(10, 10, 90, 60);
		//g.fillOval(52, 51, 90, 60);
		
		
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
