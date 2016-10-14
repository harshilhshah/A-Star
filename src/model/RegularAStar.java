package model;

import visual.Box;

public class RegularAStar extends AStar {

	public RegularAStar(Box[][] g){
		this.grid = g;
		if(g == null || g.length == 0) throw new NullPointerException();
		this.weight = 1;
		this.rows = (short) g.length;
		this.cols = (short) g[0].length;
		this.hType = HeuristicType.EUCLIDEAN;
	}

}
