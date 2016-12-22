import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Spaceship gamer");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.add(new GamePanel());
	    frame.pack();
	    frame.setVisible(true);
	}

}
