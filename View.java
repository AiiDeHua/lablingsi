import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * View: Contains everything about graphics and images Know size of world, which
 * images to load etc
 *
 * has methods to provide boundaries use proper images for direction load images
 * for all direction (an image should only be loaded once!!! why?)
 **/
public class View extends JPanel {

	int xloc, yloc;
	final int frameCount = 10;
	int picNum = 0;
	static JFrame frame;
	BufferedImage[] pics;

	final static int frameWidth = 500;
	final static int frameHeight = 300;
	final static int imgWidth = 165;
	final static int imgHeight = 165;

	HashMap<Direction, BufferedImage[]> bufferedImages;
	Direction currentDirection;

	public void paint(Graphics g) {
		picNum = (picNum + 1) % frameCount;
		g.drawImage(bufferedImages.get(currentDirection)[picNum], xloc, yloc, Color.gray, this);
	}

	public int getWidth() {
		return frameWidth;
	}

	public int getHeight() {
		return frameHeight;
	}

	public int getImageWidth() {
		return imgWidth;
	}

	public int getImageHeight() {
		return imgHeight;
	}

	public void update(int x, int y, Direction dir) {
		xloc = x;
		yloc = y;
		currentDirection = dir;
		frame.repaint();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public View() {
		bufferedImages = new HashMap<Direction, BufferedImage[]>();

		for (Direction dir : Direction.values()) {
			BufferedImage img = createImage(dir);
			pics = new BufferedImage[10];

			for (int i = 0; i < frameCount; i++)
				pics[i] = img.getSubimage(imgWidth * i, 0, imgWidth, imgHeight);

			bufferedImages.put(dir, pics);
		}
	}

	private BufferedImage createImage(Direction dir) {
		BufferedImage bufferedImage;
		try {
			if (dir == Direction.NORTH) {
				bufferedImage = ImageIO.read(new File("images/orc/orc_forward_north.png"));
			}

			else if (dir == Direction.SOUTH) {

				bufferedImage = ImageIO.read(new File("images/orc/orc_forward_south.png"));
			}

			else if (dir == Direction.EAST) {
				bufferedImage = ImageIO.read(new File("images/orc/orc_forward_east.png"));
			}

			else if (dir == Direction.WEST) {
				bufferedImage = ImageIO.read(new File("images/orc/orc_forward_west.png"));
			}

			else if (dir == Direction.NORTHWEST) {
				bufferedImage = ImageIO.read(new File("images/orc/orc_forward_northwest.png"));
			}

			else if (dir == Direction.NORTHEAST) {
				bufferedImage = ImageIO.read(new File("images/orc/orc_forward_northeast.png"));
			}

			else if (dir == Direction.SOUTHWEST) {
				bufferedImage = ImageIO.read(new File("images/orc/orc_forward_southwest.png"));
			}

			// (heading == Heading.southeast)
			else {
				bufferedImage = ImageIO.read(new File("images/orc/orc_forward_southeast.png"));
			}

			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		frame = new JFrame();
		Controller cl = new Controller();
		frame.getContentPane().add(cl.getView());
		frame.setBackground(Color.gray);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frameWidth, frameHeight);
		frame.setVisible(true);
		cl.start();
	}
}