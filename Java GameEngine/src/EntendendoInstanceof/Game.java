package EntendendoInstanceof;

public class Game {
	private Player player;
	private Inimigo inimigo;

	public Game() {
		player = new Player();
		if(player == null) {
			//System.out.println(player);
			System.out.println("O player não foi inicializado");
		}
		
		if(player instanceof Player) {
			System.out.println("O player foi inifializado com sucesso.");
		}
		
		inimigo = new Inimigo();
	}
	
	public Player getPlayer() { return player;	}
	public Inimigo getInimigo() { return inimigo;	}
	
	public static void main(String[] args) {
		Game game = new Game();
		Player player = new Player();
		
	}
	
}
