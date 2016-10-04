package model;

import java.util.ArrayList;

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
	
	public boolean existIn(ArrayList<Point> points){
		boolean result = true;
		for(int i = 0; i < points.size(); i++){
			if(this.equals(points.get(i)))
				return result;
		}
		return false;
	}
	
	public boolean equals(Point otherPoint){
		return otherPoint != null && x == otherPoint.getX() && y == otherPoint.getY();
	}
	
	public String toString(){
		return "(R"+y+",C"+x+")";
	}

}
