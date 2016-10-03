package visual;

import java.awt.Rectangle;

import model.Terrain;

public class Box extends Rectangle{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Terrain terrain = Terrain.UNBLOCKED_CELL;
	private int highway_index = 0;
	
	int heuristicCost = 0; //Heuristic cost
    int finalCost = 0; //G+H
    int i, j;
    Box parent;

	public Box(int c, int r, int cellWidth, int cellHeight){
		super(c * cellWidth,r * cellHeight, cellWidth, cellHeight);
		i = c;
		j = r;
	}
	
	@Override
	public String toString(){
		return (getTerrain() == Terrain.PARTIALLY_BLOCKED_HIGHWAY_CELL || getTerrain() == Terrain.UNBLOCKED_HIGHWAY_CELL) ? getTerrain().toString() + highway_index : getTerrain().toString();
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}
	
	protected Box getParent() {
		return parent;
	}

	protected void setParent(Box parent) {
		this.parent = parent;
	}

	
	public int getHighway_index() {
		return highway_index;
	}
	
	public void setHighway_index(int highway_index) {
		this.highway_index = highway_index;
	}
}