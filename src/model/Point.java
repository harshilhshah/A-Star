package model;

public class Point {
	
	private short x;
	private short y;
	
	public Point(int x, int y){
		this.setX(x);
		this.setY(y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = (short)x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = (short)y;
	}

}
