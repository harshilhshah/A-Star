package model;

import visual.Box;

public class WeightedAStar extends AStar {
	
	public WeightedAStar(Box[][] g, double weight, HeuristicType type){
		super(g);
		this.weight = weight;
		this.hType = type;
	}

}
