package model;

import visual.Box;

public class UniformCostSearch extends AStar{

	public UniformCostSearch(Box[][] g){
		super(g);
		this.weight = 0;
		this.hType = HeuristicType.NONE;
	}


}
