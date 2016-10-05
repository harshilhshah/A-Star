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

	@Override
	public boolean equals(Object otherPoint){
		if (otherPoint != null && otherPoint instanceof Point)
        {
			return otherPoint != null && x == ((Point) otherPoint).getX() && y == ((Point)otherPoint).getY();
        }
		return false;
	}
	
	public String toString(){
		return "(R"+y+",C"+x+")";
	}

}
