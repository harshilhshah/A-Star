package model;

import controller.Utility;
import visual.Grid;

public enum HeuristicType{
	NONE(-1), EUCLIDEAN(0), MANHATTAN(1), BORDERPOINT(2), CENTER(3), RANDOM(4);
	
	int i;
	
	HeuristicType(int type){
		i = type;
	}
	
	public double getDistance(Point p1, Point p2){
		switch(i){
			case 0:
				return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(),2));
			case 1:
				return (Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY())) * 1.0;
			case 2:
				Point gravitateTo = null;
				if(p2.getX() < Grid.cols/2 && p2.getY() < Grid.rows/2){ //top left
					gravitateTo = new Point(0,0);
				}else if(p2.getX() < Grid.cols/2 && p2.getY() > Grid.rows/2){ //bottom left
					gravitateTo = new Point(0,Grid.rows);
				}else if(p2.getX() > Grid.cols/2 && p2.getY() < Grid.rows/2){ //top right
					gravitateTo = new Point(Grid.cols,0);
				}else{ //bottom right
					gravitateTo = new Point(Grid.cols,Grid.rows);
				}
				return Math.sqrt(Math.pow(p1.getX() - gravitateTo.getX(), 2) + Math.pow(p1.getY() - gravitateTo.getY(),2));
			case 3:
				Point center = new Point(Grid.cols/2, Grid.rows/2);
				return Math.sqrt(Math.pow(p1.getX() - center.getX(), 2) + Math.pow(p1.getY() - center.getY(),2));
			case 4:
				Point random = Utility.generateRandomPoint();
				return (Math.abs(random.getX() - p2.getX()) + Math.abs(random.getY() - p2.getY())) * 1.0;
			case 5:
				Point bottomright = new Point(0, 200 ); //col,row
				double pointToBR = Math.sqrt(Math.pow(p1.getX() - bottomright.getX(), 2) + Math.pow(p1.getY() - bottomright.getY(),2));
				double BRtoGoal = Math.sqrt(Math.pow(bottomright.getX() - p2.getX(), 2) + Math.pow(bottomright.getY() - p2.getY(),2));
				return pointToBR;
			default:
				return 0.0;
		}
		
		
	}
	
}