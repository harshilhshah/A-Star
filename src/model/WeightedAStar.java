package model;

import visual.Box;

public class WeightedAStar extends AStar {
	
	public WeightedAStar(Box[][] g, double weight, HeuristicType type){
		this.grid = g;
		if(g == null || g.length == 0) throw new NullPointerException();
		this.weight = weight;
		this.rows = (short) g.length;
		this.cols = (short) g[0].length;
		this.hType = type;
	}

}
