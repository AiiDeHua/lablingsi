/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/
public class Model{
	int frameWidth;
	int frameHeight;
	int imgWidth;
	int imgHeight;
	
	static int xloc = 0;
	static int yloc = 0;
	final int xIncr = 8;
	final int yIncr = 2;
	
	int isTravelingEast = -1;
	int isTravelingSouth = -1;
	
	Direction currentDirection = Direction.SOUTHEAST;
	
	Model(int fw,int fh,int w, int h){
		frameWidth = fw;
		frameHeight = fh;
		imgWidth = w;
		imgHeight = h;
	}
	
	void updateLocationAndDirection(){
		if (xloc < 0 || xloc >= frameWidth-imgWidth) {
			isTravelingEast *= -1;
		}

		if (yloc < 0 || yloc >= frameHeight-imgHeight) {
			isTravelingSouth *= -1;
		}

		if (isTravelingEast == 1 && isTravelingSouth == 1) {
			currentDirection = Direction.SOUTHEAST;
		}

		if (isTravelingEast == -1 && isTravelingSouth == 1) {
			currentDirection = Direction.SOUTHWEST;
		}

		if (isTravelingEast == 1 && isTravelingSouth == -1) {
			currentDirection = Direction.NORTHEAST;
		}

		if (isTravelingEast == -1 && isTravelingSouth == -1) {
			currentDirection = Direction.NORTHWEST;
		}
		xloc += (xIncr * isTravelingEast);
		yloc += (yIncr * isTravelingSouth);
	}
	public int getX(){
		return xloc;
	}
	public int getY(){
		return yloc;
	}
	public Direction getDirect(){
		return currentDirection;
	}
}