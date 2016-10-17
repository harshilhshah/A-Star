package model;

import visual.Box;

public class RegularAStar extends AStar {

	public RegularAStar(Box[][] g, HeuristicType hType){
		super(g);
		this.weight = 1;
		this.hType = hType;
	}

}
