package visual;

import model.Point;
import model.Terrain;

public class SmallScaleGrid {
	
	private enum Action {
		UP, DOWN, LEFT, RIGHT;
	}


	public static void main(String[] args) {
		
		Box[][] b = new Box[3][3];
		int row = (int) Math.random() * 3;
		int col = (int) Math.random() * 3;	
		Point startingPoint = b[row][col].node.getPoint();
		Grid g = new Grid(b,startingPoint,null,null);
		
		initializeGridProbabilities(b);
		b[0][0].setTerrain(Terrain.UNBLOCKED_HIGHWAY_CELL);
		b[0][1].setTerrain(Terrain.UNBLOCKED_HIGHWAY_CELL);
		b[0][2].setTerrain(Terrain.PARTIALLY_BLOCKED_CELL);
		b[1][0].setTerrain(Terrain.UNBLOCKED_CELL);
		b[1][1].setTerrain(Terrain.UNBLOCKED_CELL);
		b[1][2].setTerrain(Terrain.UNBLOCKED_CELL);
		b[2][0].setTerrain(Terrain.UNBLOCKED_CELL);
		b[2][1].setTerrain(Terrain.BLOCKED_CELL);
		b[2][2].setTerrain(Terrain.UNBLOCKED_HIGHWAY_CELL);
		
		Action[] actions = {Action.RIGHT, Action.RIGHT, Action.DOWN, Action.DOWN};
		Terrain[] observations = {Terrain.UNBLOCKED_CELL, Terrain.UNBLOCKED_CELL, Terrain.UNBLOCKED_HIGHWAY_CELL, Terrain.UNBLOCKED_HIGHWAY_CELL};
		
		for(int step = 0; step < 4; step++){
			double observProb = 0.0;
			if(observations[step] == Terrain.UNBLOCKED_CELL){
				observProb = 0.9;
			}
			else if(observations[step] == Terrain.UNBLOCKED_HIGHWAY_CELL){
				observProb = 0.5;
			}
			else if(observations[step] == Terrain.PARTIALLY_BLOCKED_CELL){
				observProb = 0.5;
			}
			double nextX = observProb * (0.9 * b[row][col].node.getF_value() + 0.1 * b[row][col+1].node.getF_value());
			
		}
		
	}

	private static void initializeGridProbabilities(Box[][] b) {
		
		for(int i = 0; i < b.length; i++){
			for(int j = 0; j < b[i].length; j++){
				b[i][j].node.setF_value(1/9);
			}
		}
	}

}
