import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel {
	private int width, height;
	private int x, y;
	private int ax, ay;
	private int au, av;
	private ImageIcon image;
	private ImageIcon rock;
	private ImageIcon background;
	private final int SHIFT = 1;
	private Timer timer;
	private int xv, yv;

	public GamePanel() {
		width = 600;
		height = 400;

		x = 100;
		y = 100;
		ax = 200;
		ay = 200;
		au = 3;
		av = 2;
		xv = 0;
		yv = 0;

		image = new ImageIcon("ship.gif");
		background = new ImageIcon("space.jpg");
		rock = new ImageIcon("rock.gif");

		MotionListener listener = new MotionListener();
		addKeyListener(listener);
		timer = new Timer(20, new TimerListener());

		setFocusable(true);
		setPreferredSize(new Dimension(width, height));
		timer.start();
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background.getImage(), 0, 0, width, height, null);
		rock.paintIcon(this, g, ax, ay);
		rock.paintIcon(this, g, ax - width, ay);
		rock.paintIcon(this, g, ax, ay - height);
		rock.paintIcon(this, g, ax - width, ay - height);
		image.paintIcon(this, g, x, y);
		image.paintIcon(this, g, x - width, y);
		image.paintIcon(this, g, x, y - height);
		image.paintIcon(this, g, x - width, y - height);
		rock.paintIcon(this, g, ax, ay);
	}

	private class MotionListener implements KeyListener {
		public void keyPressed(KeyEvent event) {
			switch (event.getKeyCode()) {
			case KeyEvent.VK_UP:
				yv -= SHIFT;
				break;
			case KeyEvent.VK_DOWN:
				yv += SHIFT;
				break;
			case KeyEvent.VK_RIGHT:
				xv += SHIFT;
				break;
			case KeyEvent.VK_LEFT:
				xv -= SHIFT;
				break;
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
			case KeyEvent.VK_SPACE:
				x = (int) (width * Math.random());
				y = (int) (height * Math.random());
				break;
			}
			if (x < 0)
				x += width; // wraps ship around screen
			if (x > width)
				x -= width;
			if (y < 0)
				y += height;
			if (y > height)
				y -= height;
		}

		public void keyReleased(KeyEvent event) {
		}

		public void keyTyped(KeyEvent event) {
		}
	}

	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			width = getWidth();
			height = getHeight();
			ax += au; // asteroid horizontal mover
			ay += av; // asteroid vertical mover
			x += xv;
			y += yv;
			if (ax < 0)
				ax += width; // wraps astroid around screen
			if (ax > width)
				ax -= width;
			if (ay < 0)
				ay += height;
			if (ay > height)
				ay -= height;

			repaint();
		}
	}
}