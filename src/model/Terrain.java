package model;

public enum Terrain { 
	
	BLOCKED_CELL("0"), UNBLOCKED_CELL("1"), PARTIALLY_BLOCKED_CELL("2"),
	UNBLOCKED_HIGHWAY_CELL("a"), PARTIALL_BLOCKED_HIGHWAY_CELL("b");
	
	String state;
	Terrain(String s){
		state = s;
	}
	
	public String toString(){
		return state;
	}

};