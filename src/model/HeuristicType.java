package model;

public enum HeuristicType{
	NONE(-1), EUCLIDEAN(0), MANHATTAN(1);
	
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
			default:
				return 0.0;
		}
		
		
	}
	
}