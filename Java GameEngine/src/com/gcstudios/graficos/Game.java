package com.gcstudios.graficos;

import java.util.ArrayList;

import com.gcstudios.entities.Player;;

public class Game implements Runnable{
	
	private boolean isRunning;
	private Thread thread;
	
	private ArrayList<Entidade> entidades = new ArrayList<>();
	private Entidade e;
	
	public Game() {
		entidades.add(new Entidade());
		entidades.add(new Entidade());
		entidades.add(new Entidade());
		entidades.add(new Entidade());
		entidades.add(new Entidade());
		for(int i = 0; i < entidades.size(); i++) {
			e = entidades.get(0);
		}
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
		
	}

	public synchronized void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	public void tick() {
		//atualizar o jogo
		//System.out.println("tick");
	}
	public void render() {
		//renderizar o jogo
		//System.out.println("renderizar");
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isRunning) {
			tick();
			render();
			/*
			try {
				thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	}

}
