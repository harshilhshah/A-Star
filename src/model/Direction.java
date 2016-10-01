package model;

public enum Direction {

	UNDECIDED("-1"), UP("0"), DOWN("1"), LEFT("2"),RIGHT("3");
	
	String choice;
	Direction(String c){
		choice = c;
	}
	
	public String toString(){
		return choice;
	}
}
